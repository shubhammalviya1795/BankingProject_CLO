#Author: aiswarya.nimbalkar@qualitykoisk.com
@RegsitrationWithPAN
Feature: Resigtration with PAN Number
  As a prospective SME Loan Customer
  I would like to register with PAN Number
  So that i can proceed with the next steps loan application

  Background: Customer navigates from Commercial Segment
    Given Customer navigates to portal from any Commercial Segment

  Scenario: Verify valid PAN number
    Given The Customer with following details is on PAN registration page
      | Name   | Virat Enterprises |
      | Mobile |        9876543226 |
    When he enters his valid PAN number as 'DBNEG6783D'
    Then He should be displayed following
      | Name         | Virat Enterprises |
      | Constitution | INDIVIDUAL        |
    And provison to change Constitution with following values
      | Constitution   |
      | Proprietorship |
      | Individual     |
      | Partnership    |
    And provison to input Pincode
    And provison to input Employee Code

  Scenario Outline: Invalid PAN number
    Given The Customer with following details is on PAN registration page
      | Name   | Mr. Viratfive K Kohlifive |
      | Mobile |                9876543226 |
    When he enters his valid PAN number as '<pan>'
    Then He should be displayed with follwing '<error>'

    Examples: 
      | pan           | error                              |
      | 1234567891123 | Please enter valid data.           |
      |         12345 | Minimum 10 characters are required |
      | $%@           | Minimum 10 characters are required |
      | ABCDE 1234 K  | Please enter valid data.           |
      | ABCDEFGHIG    | Please enter valid data.           |
      |    1234567891 | Please enter valid data.           |

  Scenario: Verification with Incorrect PAN number
    Given The Customer with following details is on PAN registration page
      | Name   | Mr. ABC D Enterprises |
      | Mobile |            9928365278 |
    When He accidentally enters an incorrect  PAN number as 'CKNDG6783C'
    And He is displayed  details of other user
      | Name | Mr. ABC D Enterprises |
    And He should see an option to change his PAN number
    Then redirect to PAN Entry page

	@Manual
	@Manual:Passed
  Scenario: PAN validation NSDL system is not working
    Given The Customer with following details is on PAN registration page
      | Name   | Virat Enterprises |
      | Mobile |        9876543226 |
    And he enters his valid PAN number as 'ABCDE1234J'
    When NSDL system is not working
    Then System should not fetch addtional user details
    ##QUERY: Please provide the mesage contents
    And He should be displayed with follwing 'Please Try after some time'

  Scenario Outline: PAN registration with invalid numeric pincode
    Given The Customer with following details has validated his name with PAN number
      | Name         | Virat Enterprises |
      | Mobile       |        9876543226 |
      | PAN          | DBNEG6783D        |
      | Constitution | PROPRIETORSHIP    |
    When he inputs invalid pin code as '<pin_code>'
    Then Follwing error message should be displyed '<error>'

    Examples: 
      | pin_code | error                      |
      |          | Please provide the Pincode |
      |     0009 | Minimum 6 Digits Required  |
      |    12345 | Minimum 6 Digits Required  |

  
  Scenario Outline: PAN registration with invalid characters pincode
    Given The Customer with following details has validated his name with PAN number
      | Name         | Virat Enterprises |
      | Mobile       |        9876543226 |
      | PAN          | DBNEG6783D        |
      | Constitution | PROPRIETORSHIP    |
    When he inputs invalid pin code as '<pin_code_input>'
    Then pincode will accept value as '<pin_code_output>'
    And Next Button Enabled is '<btn_state>'

    Examples: 
      | pin_code_input | pin_code_output | btn_state |
      |        1234567 |          123456 | true      |
      | #$%@           |                 | false     |
      | abcd           |                 | false     |
      | 123 456        |          123456 | true      |

  Scenario: Pincode with non existing bank branch
    Given The Customer with following details has validated his name with PAN number
      | Name         | Mr. Viratfive K Kohlifive |
      | Mobile       |                9928365278 |
      | PAN          | DBNEG6783D                |
      | Constitution | PROPRIETORSHIP            |
    When he provided pincode as '123456'
    But there is no branch assiciated with this pincode
    Then System should allow to proceed this user with further registration

  Scenario: Register with valid PAN
    Given The New to Bank Customer with following details is on PAN registration page
      | Name   | Mr. Viratfive K Kohlifive |
      | Mobile |                9876543226 |
      | PAN    | DBNEG6783D                |
    And He additionally enters follwing details
      | Constitution | PROPRIETORSHIP |
      | Pincode      |         110026 |
    But does not enter any Employee Code
    And He Proceed further
    Then He should be sucessfully registered with the Portal
    And He should see the Proposal Initiation Page

  #QUERY: Need the specification for Emplyoee code field
  @Manual:Passed
  Scenario Outline: PAN regsitration with Employee Code
    Given The Customer with following details has validated his name with PAN number
      | Name         | Mr Ramachandra Kumaraswamy |
      | Mobile       |                 9928365278 |
      | PAN          | AGTH87650                  |
      | Constitution | PROPRIETORSHIP             |
      | Pincode      |                     400071 |
    And He enters Invalid Employee Code as <employee code>
    Then following <error> should be displayed

    Examples: 
      | employee code | error        |
      | ABCD          | Invalid Code |
