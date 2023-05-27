package com.user.billmanagement.serviceImpl;


import org.springframework.stereotype.Service;

import com.user.billmanagement.model.User;
import com.user.billmanagement.service.DiscountCalculatorService;


@Service

public class DiscountCalculatorServiceImpl implements  DiscountCalculatorService {
    private static final double EMPLOYEE_DISCOUNT_PERCENTAGE = 0.3;
    private static final double AFFILIATE_DISCOUNT_PERCENTAGE = 0.1;
    private static final double CUSTOMER_DISCOUNT_PERCENTAGE = 0.05;
    private static final double DISCOUNT_PER_HUNDRED = 5.0;
    private static final double GROCERY_DISCOUNT_PERCENTAGE = 0.0;

    public double calculateNetPayableAmount(User user) {
        
        
        double percentageBasedDiscount;
        double perHundereDiscount;
        double totalDiscount;
        
        /* totalAMount in request */
        double netPayableAmount = user.getBill().getAmount();

        if (user.getBill() != null) {
            /* no discount for groceries */
            if(user.getBill().getItem().equalsIgnoreCase("groceries") ) {
                netPayableAmount -= netPayableAmount * GROCERY_DISCOUNT_PERCENTAGE;

            }
            else {
                /* calculating employee payable amount after discount   */
                if (user.getUserType().equalsIgnoreCase("employee")) {
                    
                    percentageBasedDiscount = netPayableAmount * EMPLOYEE_DISCOUNT_PERCENTAGE;
                    perHundereDiscount = amountBasedDiscount(user.getBill().getAmount());
                    totalDiscount = percentageBasedDiscount + perHundereDiscount;
                    netPayableAmount -= totalDiscount;

                }
                /* calculating affiliate payable amount after discount   */
                else if (user.getUserType().equalsIgnoreCase("affiliate")) {
                    percentageBasedDiscount = netPayableAmount * AFFILIATE_DISCOUNT_PERCENTAGE;
                    perHundereDiscount = amountBasedDiscount(user.getBill().getAmount());
                    totalDiscount = percentageBasedDiscount + perHundereDiscount;
                    netPayableAmount -= totalDiscount;

                } 
                /* calculating  customer payable amount who have membership more than 2 years     */

                else if (user.getUserType().equalsIgnoreCase("customer") && user.getMembershipDuration() >= 2) {
                    percentageBasedDiscount = netPayableAmount * CUSTOMER_DISCOUNT_PERCENTAGE;
                    perHundereDiscount = amountBasedDiscount(user.getBill().getAmount());
                    totalDiscount = percentageBasedDiscount + perHundereDiscount;
                    netPayableAmount -= totalDiscount;
                }
                /* calculating  customer payable amount who have membership more less than 2 year     */

                else if (user.getUserType().equalsIgnoreCase("customer") && user.getMembershipDuration() < 2)
                {
                    perHundereDiscount = amountBasedDiscount(user.getBill().getAmount());
                    netPayableAmount -= perHundereDiscount;

                }

            }
            

        }
        
        return netPayableAmount;
    }

    /* per hundred discount calculation*/
    private double amountBasedDiscount(double amount) {
        if (amount > 100) {
            double discountPerHundredAmount = amount / 100;
            double discountAmount = Math.floor(discountPerHundredAmount);
            

            return (int) discountAmount * DISCOUNT_PER_HUNDRED;
        } else {
            return 0.0;
        }
    }
}
