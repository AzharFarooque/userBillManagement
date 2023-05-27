package com.user.billmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.billmanagement.model.User;
import com.user.billmanagement.service.DiscountCalculatorService;

@RestController
public class DiscountCalculatorController {
    
    @Autowired
    private DiscountCalculatorService discountService;
    
    @PostMapping("/calculate-net-payable-amount")
    public ResponseEntity<String> calculateNetPayableAmount(@RequestBody User user) {
        
        double netPayableAmount = discountService.calculateNetPayableAmount(user);
        
        return ResponseEntity.ok("Total payable amount is "+netPayableAmount);
    }
}
