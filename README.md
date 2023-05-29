
# ----------------------------------------------------------------------------
# Service is implement to calculate the discount on the basis of the different scenerios
#There are 2 moddel class User and Bil
#user is carrying the information of user and bill carrying the information of the bill details and item 
#server can be run on port 8088
#url for the calculation call "host:8088/calculate-net-payable-amount" , POST method
#Request Payload 
{
    "userType": "customer",
    "membershipDuration": 2,
    "bill": {
        "amount": 990,
        "item": "non-groceries"
    }
}
#Response ;- Total payable amount is 895.5
------------------------------------------------------

#Build command : mvn clean install
#Run command : mvn spring-boot:run
#Sonar : sonar:sonar
#Eclipse Run Alt + Shift + X,T or right click on testClass go to Run as and select JUnit Test Cases
#Eclipse code coverage Alt + Shift + E,T or right click on testClass go to Coverage as and select JUnit Test Cases
