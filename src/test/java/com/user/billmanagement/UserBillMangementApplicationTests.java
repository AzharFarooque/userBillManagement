package com.user.billmanagement;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.user.billmanagement.model.Bill;
import com.user.billmanagement.model.User;
import com.user.billmanagement.serviceImpl.DiscountCalculatorServiceImpl;

@SpringBootTest
class UserBillMangementApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
    public void testCalculateNetPayableAmountEmployee() {
        Bill bill = new Bill();
        bill.setAmount(995);
        bill.setItem("food");
        User user = new User ();
        user.setMembershipDuration(1);
        user.setUserType("employee");
        user.setBill(bill);

        DiscountCalculatorServiceImpl calculator = new DiscountCalculatorServiceImpl();
        double netPayableAmount = calculator.calculateNetPayableAmount(user);

        // 30% discount for employees, $45 discount for every $100, total discount: $298.5 + $45
        assertEquals(651.5, netPayableAmount, 0.3);
    }
	
	@Test
    public void testCalculateNetPayableAmountAffiliate() {
        Bill bill = new Bill();
        bill.setAmount(1000);
        bill.setItem("food");

        User user = new User ();
        user.setMembershipDuration(1);
        user.setUserType("affiliate");
        user.setBill(bill);

        DiscountCalculatorServiceImpl calculator = new DiscountCalculatorServiceImpl();
        double netPayableAmount = calculator.calculateNetPayableAmount(user);

        assertEquals(850, netPayableAmount, 0.1);
    }
	
	@Test
    public void testCalculateNetPayableAmountCustomer() {
        Bill bill = new Bill();
        bill.setAmount(1000);
        bill.setItem("food");

        User user = new User ();
        user.setMembershipDuration(2);
        user.setUserType("customer");
        user.setBill(bill);

        DiscountCalculatorServiceImpl calculator = new DiscountCalculatorServiceImpl();
        double netPayableAmount = calculator.calculateNetPayableAmount(user);

        assertEquals(900, netPayableAmount, 0.05);
    }
	
	@Test
    public void testCalculateNetPayableAmountWithGrocery() {
        Bill bill = new Bill();
        bill.setAmount(1000);
        bill.setItem("groceries");

        User user = new User ();
        user.setMembershipDuration(2);
        user.setUserType("customer");
        user.setBill(bill);

        DiscountCalculatorServiceImpl calculator = new DiscountCalculatorServiceImpl();
        double netPayableAmount = calculator.calculateNetPayableAmount(user);

        assertEquals(1000, netPayableAmount, 0.0);
    }
	
	@Test
    public void testCalculateNetPayableAmountCustomerWithLessThan2Year() {
        Bill bill = new Bill();
        bill.setAmount(1000);
        bill.setItem("food");

        User user = new User ();
        user.setMembershipDuration(1);
        user.setUserType("customer");
        user.setBill(bill);

        DiscountCalculatorServiceImpl calculator = new DiscountCalculatorServiceImpl();
        double netPayableAmount = calculator.calculateNetPayableAmount(user);

        assertEquals(950, netPayableAmount , 0.0);
    }
	
	@Test
    public void testCalculateNetPayableAmountEmployeeWhenAmountLessthan100() {
        Bill bill = new Bill();
        bill.setAmount(99);
        bill.setItem("food");

        User user = new User ();
        user.setMembershipDuration(1);
        user.setUserType("employee");
        user.setBill(bill);

        DiscountCalculatorServiceImpl calculator = new DiscountCalculatorServiceImpl();
        double netPayableAmount = calculator.calculateNetPayableAmount(user);

        assertEquals(69.3, netPayableAmount , 0.3);
    }

}
