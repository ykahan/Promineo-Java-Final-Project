package customers;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerUtilImplTest {

    private List<Customer> customers;
    private CustomerUtilImpl util;

    @org.junit.Test
    public void testCalculateBillTotal() {
        populateCustomers();
        assertEquals(28.04, util.calculateBillTotal(customers.get(0).getStatement().get(0)), 0.0);
    }

    @org.junit.Test
    public void testCalculateCustomerTotal() {
        populateCustomers();
        assertEquals(576.04, util.calculateCustomerTotal(customers.get(1)), 0.0);
    }

    @org.junit.Test
    public void testCalculatePastDue() {
        populateCustomers();
        assertEquals(28.04, util.calculatePastDue(customers.get(1)), 0.0);
    }

    @org.junit.Test
    public void testCalculateAmountDueIn30Days() {
        populateCustomers();
        assertEquals(152.66, util.calculateAmountDueIn30Days(customers.get(1)), 0.0);
    }

    @org.junit.Test
    public void testCalculateAmountPaid() {
        populateCustomers();
        assertEquals(423.38, util.calculateAmountPaid(customers.get(1)), 0.0);
    }

    @org.junit.Test
    public void testCalculateCustomerWithMostOwed() {
        populateCustomers();
        assertEquals(customers.get(1), util.calculateCustomerWithMostOwed(customers));
    }

    @org.junit.Test
    public void testCalculateTotalPaidByState() {
        populateCustomers();
        assertEquals(697.38, util.calculateTotalPaidByState(customers, "AZ"), 0.0);
    }

    @org.junit.Test
    public void testCalculateHighestPayingCustomer() {
        populateCustomers();
        assertEquals(customers.get(1), util.calculateHighestPayingCustomer(customers));
    }

    @org.junit.Test
    public void testCalculateStateWithGreatestReceivables() {
        populateCustomers();
        assertEquals("AZ", util.calculateStateWithGreatestReceivables(customers));
    }

    @Test
    public void testMarkBillAsPaid() {
        populateCustomers();
        assertEquals(true, util.markBillAsPaid(customers, "c002", "1001").isPaid());
    }

    public void populateCustomers() {
		util = new CustomerUtilImpl();

        Address address1 = new Address();
        address1.setState("AZ");
        Address address2 = new Address();
        address2.setState("NY");

        LineItem li1 = new LineItem();
        li1.setPrice(24.76);
        LineItem li2 = new LineItem();
        li2.setPrice(10.15);
        LineItem li3 = new LineItem();
        li3.setPrice(92.17);
        LineItem li4 = new LineItem();
        li4.setPrice(17.89);
        LineItem li5 = new LineItem();
        li5.setPrice(32.45);

        Date today = new Date();
        LocalDateTime ldtToday = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        Date pastDue = Date.from(ldtToday.minusDays(5).atZone(ZoneId.systemDefault()).toInstant());
        Date dueIn20Days = Date.from(ldtToday.plusDays(20).atZone(ZoneId.systemDefault()).toInstant());

        Bill pastDueBill = new Bill();
        pastDueBill.setId("1001");
        pastDueBill.setLineItems(Arrays.asList(li2, li4));
        pastDueBill.setDueDate(pastDue);

        Bill notDueBill = new Bill();
        notDueBill.setId("1002");
        notDueBill.setLineItems(Arrays.asList(li3, li5));
        notDueBill.setDueDate(dueIn20Days);

        Bill paidBill = new Bill();
        paidBill.setId("1003");
        paidBill.setLineItems(Arrays.asList(li3, li5));
        paidBill.setDueDate(dueIn20Days);
        paidBill.setPaid(true);
        paidBill.setDatePaid(today);

        Bill paidBill2 = new Bill();
        paidBill2.setId("1004");
        paidBill2.setLineItems(Arrays.asList(li3, li5, li1));
        paidBill2.setDueDate(dueIn20Days);
        paidBill2.setPaid(true);
        paidBill2.setDatePaid(today);

        Bill paidBill3 = new Bill();
        paidBill3.setId("1004");
        paidBill3.setLineItems(Arrays.asList(li3, li5, li1));
        paidBill3.setDueDate(dueIn20Days);
        paidBill3.setPaid(true);
        paidBill3.setDatePaid(today);

        Customer c1 = new Customer();
        c1.setAddress(address1);
        c1.setId("c001");
        c1.setStatement(Arrays.asList(pastDueBill, paidBill, paidBill2));

        Customer c2 = new Customer();
        c2.setAddress(address1);
        c2.setId("c002");
        c2.setStatement(Arrays.asList(pastDueBill, paidBill, paidBill2, notDueBill, paidBill3));

        Customer c3 = new Customer();
        c3.setAddress(address2);
        c3.setId("c003");
        c3.setStatement(Arrays.asList(pastDueBill, paidBill));

        customers = Arrays.asList(c1, c2, c3);
    }
}