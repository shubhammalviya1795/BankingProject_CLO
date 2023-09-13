#Author: anand.sawant@intellectdesign.com
@LeadCapture
Feature: Lead Capture
  To capture the leads of customer in VRM/CRM System using their mobile number and PAN

  @Manual
  Scenario: Create Lead Dropout from OTP entry
    Given Customer with valid mobile number '9920289288' has login into the Portal
    And He is on OTP Prompt page
    When He closes his browser
    Then Unverified Lead should be captured in the System.

  @Manual
  Scenario: Create Lead on Invalid OTP entry
    Given Customer with valid mobile number '9920289288' has login into the Portal
    And He enters wrong OTP for 3 times
    Then Unverified Lead should be captured in the System.

  @Manual
  Scenario: Create Lead on Timeout OTP entry
    Given Customer with valid mobile number '9920289288' has login into the Portal
    When He doesn not enters the OTP for long time
    Then VRM Unverified Lead should be created in system

  @Manual
  Scenario: Create Lead on  dropout after mobile validation
    Given Customer with valid mobile number '9920289288' has login into the Portal
    And He enters valid OTP
    Then VRM Verified Lead should be created in system

  @Manual
  @Manual:Passed
  Scenario: Create CRM Lead
    Given Customer is an existing LaserSoft customer
    And has already applied for Loan in LaserSoft
    And RM named 'Mr Satish  Pradhan' is associated to this customer
    When He autheticates on Portal using following details
      | Name         | Mr Ramachandra Kumaraswamy |
      | Mobile       |                 9928365278 |
      | PAN          | AGTH87650                  |
      | Constitution | Propertiership             |
    Then He should disaplyed a messaging saying he is already a customer
    And CRM lead is created in the system
    And SMS and Email Should be sent Relationship Manager 'Mr Satish  Pradhan'
  
  @Manual
  @Manual:Passed
  Scenario: Parellel Accounts in Potal and Lasersoft
    Given Customer has already registed in Portal
    And has also registered himself in Lasersoft
    When He try to resume his loan application from Portal
    Then He should be allowed to proceed in Portal
