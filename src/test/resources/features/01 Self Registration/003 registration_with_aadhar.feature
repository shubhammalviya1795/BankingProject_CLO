#Author: anand.sawant@intellectdesign.com

@RegistrationWithAadhar
Feature: Registration with Aadhar Number
  As a prospective SME Loan Customer from Individual Segment
  I would like to register with Aadhar Number
  So that i can proceed with the next steps loan application

  
  Scenario: Registration for Indivdual Segment Customer
    Given The Customer with following details want to apply for loan
      | Name          | Virat Services |
      | Mobile        |     9876543222 |
      | Channel       | BANK           |
      | BusineSegment | AGRI           |
    When he sucessfully validates with mobile OTP
    Then Registration Page is displayed with provision to enter either of following details
      | PAN     |
      | Aadhaar |
    And Option to Enter Aadhar Field

  @Manual
  Scenario: Registration without Required Information
    Given The Customer with following details want to apply for loan
      | Name   | Mr Ramachandra Kumaraswamy |
      | Mobile |                 9928365278 |
    When he try to proceed without entering any required details
    Then follwing error is displayed 'Please enter either PAN or Aadhar number'

  @Manual
  Scenario: Proceed with Aadhar Number
    Given The Customer with following details want to apply for loan
      | Name   | Mr Ramachandra Kumaraswamy |
      | Mobile |                 9928365278 |
    And He is on Registration Page
    When He enters valid Aadhar number '1234 0989 1223'
    Then System should register the Aahar reference number with Aadhar Vault Service
    And He enters follwing information
      | Pin code      |
      | Employee Code |

  @Manual
  Scenario Outline: Invalid Aadhar Number
    Given The Customer with following details want to apply for loan
      | Name   | Mr Ramachandra Kumaraswamy |
      | Mobile |                 9928365278 |
    And He is on Registration Page
    When He enters InValid <Aadhar> number
    Then He should get follwing <Error>

    Examples: 
      | Aadhar         | Error                 |
      | ABCD WERT QWER | Invalid Aadhar Number |
      |           1234 | Invalid Aadhar Number |

  @Manual
  Scenario: Sucessful Aadhar Registration
    Given The Customer with following details want to apply for loan
      | Name   | Mr Ramachandra Kumaraswamy |
      | Mobile |                 9928365278 |
    And he is on Aadhar Registration Page with follwing information
      | Aadhar  | 1121 1121 1143 |
      | Pincode |         400999 |
    When he Submits this information
    Then Customer is registered with the Portal
    And Proposal Initoation Page is diaplyed

  @Manual
  Scenario: Register with valid Individual PAN
    Given The New to Bank Customer with following details is on Registration page
      | Name   | Mr Ramachandra Kumaraswamy |
      | Mobile |                 9928365278 |
      | PAN    | AGTH87650                  |
    And He additionally enters follwing details
      | Constitution | Individual |
      | Pincode      |     400999 |
    And He Proceed further
    Then He should be sucessfully registered with the Portal
    And He should see the Proposal Initiation Page
