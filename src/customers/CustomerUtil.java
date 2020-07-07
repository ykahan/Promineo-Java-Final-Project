package customers;

import java.util.List;

public interface CustomerUtil {
    public double calculateBillTotal(Bill bill);
    public double calculateCustomerTotal(Customer customer);
    public double calculatePastDue(Customer customer);
    public double calculateAmountDueIn30Days(Customer customer);
    public double calculateAmountPaid(Customer customer);
    public Customer calculateCustomerWithMostOwed(List<Customer> customers);
    public Customer calculateHighestPayingCustomer(List<Customer> customers);
    public double calculateTotalPaidByState(List<Customer> customers, String state);
    public String calculateStateWithGreatestReceivables(List<Customer> customers);
    public Bill markBillAsPaid(List<Customer> customers, String customerID, String billID);
}
