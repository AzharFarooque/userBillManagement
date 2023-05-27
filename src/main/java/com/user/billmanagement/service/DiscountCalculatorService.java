package com.user.billmanagement.service;

import com.user.billmanagement.model.User;

public interface DiscountCalculatorService {

    public double calculateNetPayableAmount(User user);
}
