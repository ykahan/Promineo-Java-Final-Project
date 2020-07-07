package customers;

import java.util.*;

public class CustomerUtilImpl implements CustomerUtil{
    @Override
    public double calculateBillTotal(Bill bill) {
        double total = 0.0;
        List<LineItem> lineItems = bill.getLineItems();
        for(LineItem lineItem: lineItems){
            total += lineItem.getPrice();
        }
        return total;
//        return 28.04;
    }

    @Override
    public double calculateCustomerTotal(Customer customer) {
        double total = 0.0;
        List<Bill> bills = customer.getStatement();
        for(Bill bill: bills){
            total += calculateBillTotal(bill);
        }
        return total;
    }

    @Override
    public double calculatePastDue(Customer customer) {
        double totalPastDue = 0.0;
        List<Bill> bills = customer.getStatement();
        for(Bill bill: bills){
            if(bill.getDueDate().before(new Date())) {
                if(!bill.isPaid()){
                    totalPastDue += calculateBillTotal(bill);
                }
            }
        }
        return totalPastDue;
    }

    @Override
    public double calculateAmountDueIn30Days(Customer customer) {
        double totalDueWithin30Days = 0.0;
        List<Bill> bills = customer.getStatement();
        Date thirtyDaysInFuture = getOffsetDate(30);
        for(Bill bill: bills){
            if(!bill.isPaid()){
                if(bill.getDueDate().before(thirtyDaysInFuture)){
                    totalDueWithin30Days += calculateBillTotal(bill);
                }
            }
        }
        return totalDueWithin30Days;
    }

    private Date getOffsetDate(int offset){
        Date today = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        cal.add(Calendar.DATE, offset);
        return cal.getTime();
    }

    @Override
    public double calculateAmountPaid(Customer customer) {
        List<Bill> bills = customer.getStatement();
        double totalPaid = 0.0;
        for(Bill bill: bills){
            if(bill.isPaid()){
                totalPaid += calculateBillTotal(bill);
            }
        }
        return totalPaid;
    }

    @Override
    public Customer calculateCustomerWithMostOwed(List<Customer> customers) {
        Customer customerWithMostOwed = customers.get(0);
        double highestAmountOwed = 0.0;
        for(Customer customer: customers){
            double amountOwed = calculateCustomerTotal(customer) - calculateAmountPaid(customer);
            if(amountOwed > highestAmountOwed){
                highestAmountOwed = amountOwed;
                customerWithMostOwed = customer;
            }
        }
        return customerWithMostOwed;
    }

    @Override
    public Customer calculateHighestPayingCustomer(List<Customer> customers) {
        Customer customerThatPaidTheMost = customers.get(0);
        double highestAmountPaid = 0.0;
        for(Customer customer: customers){
            double amountPaid = calculateAmountPaid(customer);
            if(amountPaid > highestAmountPaid){
                highestAmountPaid = amountPaid;
                customerThatPaidTheMost = customer;
            }
        }
        return customerThatPaidTheMost;
    }

    @Override
    public double calculateTotalPaidByState(List<Customer> customers, String state) {
        double totalPaid = 0.0;
        for(Customer customer: customers){
            if(customer.getAddress().getState().contentEquals(state)) {
                totalPaid += calculateAmountPaid(customer);
            }
        }
        return totalPaid;
    }

    public double calculateTotalBillsByState(List<Customer> customers, String state){
        double totalBills = 0.0;
        for(Customer customer: customers){
            if(customer.getAddress().getState().contentEquals(state)){
                totalBills += calculateCustomerTotal(customer);
            }
        }
        return totalBills;
    }

    public double calculateTotalReceivableByState(List<Customer> customers, String state){
        return calculateTotalBillsByState(customers, state) - calculateTotalPaidByState(customers, state);
    }

    @Override
    public String calculateStateWithGreatestReceivables(List<Customer> customers) {
        Map<String, Double> statesAndReceivables = createStateAndReceivablesMap(customers);
        return stateWithHighestReceivables(statesAndReceivables);
    }

    private String stateWithHighestReceivables(Map<String, Double> statesAndReceivables) {
        double highestReceivables = 0.0;
        String stateWithHighestReceivables = "";
        for(String key: statesAndReceivables.keySet()){
            double receivables = statesAndReceivables.get(key);
            if(receivables > highestReceivables){
                highestReceivables = receivables;
                stateWithHighestReceivables = key;
            }
        }
        return stateWithHighestReceivables;
    }

    private Map createStateAndReceivablesMap(List<Customer> customers) {
        Map<String, Double> statesAndReceivables = new HashMap<>();
        Set<String> states;
        for(Customer customer: customers) {
            String state = customer.getAddress().getState();
            states = statesAndReceivables.keySet();
            if (!states.contains(state)) {
                double receivables = calculateTotalReceivableByState(customers, state);
                statesAndReceivables.put(state, receivables);
            }
        }
        return statesAndReceivables;
    }

    @Override
    public Bill markBillAsPaid(List<Customer> customers, String customerID, String billID) {
        for(Customer customer: customers){
            if(customer.getId().contentEquals(customerID)){
                for(Bill bill: customer.getStatement()){
                    if(bill.getId().contentEquals(billID)){
                        bill.setDatePaid(new Date());
                        bill.setPaid(true);
                        return bill;
                    }
                }
            }
        }
        return null;
    }
}
