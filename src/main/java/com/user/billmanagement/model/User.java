package com.user.billmanagement.model;

public class User {
    
    private Integer membershipDuration;
    
    private String userType; 
    private Bill bill;

    public Bill getBill() {
        return bill;
    }
    public void setBill(Bill bill) {
        this.bill = bill;
    }
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public Integer getMembershipDuration() {
        return membershipDuration;
    }
    public void setMembershipDuration(Integer membershipDuration) {
        this.membershipDuration = membershipDuration;
    }
   
}
