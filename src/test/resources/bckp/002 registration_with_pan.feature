#Author: aiswarya.nimbalkar@qualitykoisk.com
Feature: Resigtration with PAN Number
  As a prospective SME Loan Customer
  I would like to register with PAN Number
  So that i can proceed with the next steps loan application

  Background: Customer navigates from Commercial Segment
    Given Customer navigates to portal from any Commercial Segment

  Scenario: Verify valid PAN number
    Given The Customer with following details is on PAN registration page
      | Name   | Mr Ramachandra Kumaraswamy |
      | Mobile |                 9928365278 |
    When he enters his valid PAN number as 'AGTHN7650K'
    Then He should be displayed following
      | Name         | Mr Ramachandra Kumaraswamy |
      | Constitution | Propertiership             |
    And provison to change Constitution with following values
      | Constitution   |
      | Proprietorship |
      | Individual     |
      | Partnership    |
    And provison to input Pincode
    And provison to input Employee Code

  Scenario: Register with valid PAN
    Given The New to Bank Customer with following details is on PAN registration page
      | Name   | Mr Ramachandra Kumaraswamy |
      | Mobile |                 9928365278 |
      | PAN    | AGTH87650                  |
    And He additionally enters follwing details
      | Constitution | Propertiership |
      | Pincode      |         400999 |
    But does not enter any Employee Code
    And He Proceed further
    Then He should be sucessfully registered with the Portal
    And He should see the Proposal Initiation Page

  Scenario Outline: Invalid PAN number
    Given The Customer with following details is on PAN registration page
      | Name   | Mr Ramachandra Kumaraswamy |
      | Mobile |                 9928365278 |
    When He inputs the invalid <pan> details
    Then He should be displayed with follwing <error>

    Examples: 
      | pan           | error              |
      | 1234567891123 | Invalid PAN Number |
      |         12345 | Invalid PAN Number |
      | $%@           | Invalid PAN Number |
      | ABCDE 1234 K  | Invalid PAN Number |
      | ABCDEFGHIG    | Invalid PAN Number |
      |    1234567891 | Invalid PAN Number |

  Scenario: Verification with Incorrect PAN number
    Given The Customer with following details is on PAN registration page
      | Name   | Mr Ramachandra Kumaraswamy |
      | Mobile |                 9928365278 |
    When He accidentally enters an incorrect  PAN number as 'AGTH87651'
    And He is displayed  details of other user
      | Name | Mr John Doe |
    And He should see an option to change his PAN number
    Then redirect to PAN Entry page
  

  @Manual
  Scenario: PAN validation NSDL system is not working
    Given The Customer with following details has validated his mobile number
      | Mobile | 9928365278 |
    And He enters the PAN number
      | PAN | AGTH87650 |
    When NSDL system is not working
    Then System should not fetch addtional user details
    ##QUERY: Please provide the mesage contents
    And System show error message as 'Please Try after some time'

  Scenario Outline: PAN registration with invalid pincode
    Given The Customer with following details has validated his name with PAN number
      | Name         | Mr Ramachandra Kumaraswamy |
      | Mobile       |                 9928365278 |
      | PAN          | AGTH87650                  |
      | Constitution | Propertiership             |
    When he inputs invalid pin code as <pin_code>
    Then Follwing error message should be displyed <error>

    Examples: 
      | pin_code | error                      |
      |          | Please provide the Pincode |
      | anbd0009 | Invalid Pincode            |
      |  1234567 | Invalid Pincode            |
      |    12345 | Invalid Pincode            |
      | #$%@     | Invalid Pincode            |
      | abcd     | Invalid Pincode            |
      | 123 456  | Invalid Pincode            |

  Scenario: Pincode with non existing bank branch
    Given The Customer with following details has validated his name with PAN number
      | Name         | Mr Ramachandra Kumaraswamy |
      | Mobile       |                 9928365278 |
      | PAN          | AGTH87650                  |
      | Constitution | Propertiership             |
    When he provided pincode as 400071
    But there is no branch assiciated with this pincode
    Then System should allow to proceed this user with further registration

  #QUERY: Need the specification for Emplyoee code field
  Scenario Outline: PAN regsitration with Employee Code
    Given The Customer with following details has validated his name with PAN number
      | Name         | Mr Ramachandra Kumaraswamy |
      | Mobile       |                 9928365278 |
      | PAN          | AGTH87650                  |
      | Constitution | Propertiership             |
      | Pincode      |                     400071 |
    And He enters Invalid Employee Code as <employee code>
    Then following <error> should be displayed

    Examples: 
      | employee code | error        |
      | ABCD          | Invalid Code |
