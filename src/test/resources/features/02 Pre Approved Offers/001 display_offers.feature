#Author: anand.sawant@intellectdesign.com
@DisplayOffer
Feature: Display Pre approved offers
  Show the Single or Multiple pre approved offers for existing Bank Customer
	
	
  Scenario: Display Pre Approved Offers
    Given Existing Bank Customer with following details is on PAN regsitration page
      | Name         | Virat Associates |
      | Mobile       |       9876543236 |
      | PAN          | PBNHP6783P       |
      | Constitution | PROPRIETORSHIP   |
      | Pincode      |           110026 |
    And his PAN associated with single bank account
    And He is entitled for an pre approved offer
    When He Proceed with PAN validation
    Then He should see pre approved offer details
    And Option to proceed with pre approved Offer
    And Option to select for Higher Loan

  
  Scenario: Display Pre Approved Offers for Customer with Multiple Bank Acount
    Given Existing Bank Customer with following details is on PAN regsitration page
      | Name         | Virat and Sons |
      | Mobile       |     9876543228 |
      | PAN          | HBNHD6783H     |
      | Constitution | PROPRIETORSHIP |
      | Pincode      |         110026 |
    And his PAN associated with multiple bank account
    And He is entitled for an pre approved offer
    When He Proceed with PAN validation
    Then Multiple Accounts should displayed as list
    And User should have option only to select one account
    And display the offer page on selection of the account.

  
  Scenario: Verify Existing Bank Customer Account
    Given Existing Bank Customer with following details is on PAN regsitration page
      | Name         | ABC traders    |
      | Mobile       |     9876543233 |
      | PAN          | MBNHM6783M     |
      | Constitution | PROPRIETORSHIP |
      | Pincode      |         110026 |
    And his PAN is not associated with any bank account
    But His mobile number number is asscoiated to one or more account
    When He Proceed with PAN validation
    Then Customer Verification Page is displayed
    And He is provided an option to verify with
      | Aadhaar     |
      | Customer ID |

  @TestOffer
  Scenario Outline: Sucessful Verification of Existing Bank Customer Account with CIF
    Given Existing Bank Customer with following details is on PAN regsitration page
      | Name         | <name>         |
      | Mobile       | <mobile>       |
      | PAN          | <pan>          |
      | Constitution | PROPRIETORSHIP |
      | Pincode      |         110026 |
    And his PAN is not associated with any bank account
    But His mobile number number is asscoiated to one or more account
    And He Proceed with PAN validation
    And Customer Verification Page is displayed
    When He enters '<Verification Paramater>' with '<Verification Parameter Value>'
    Then He should be navigated to Approved Offers Page

    Examples: 
      | name           | mobile     | pan        | Verification Paramater | Verification Parameter Value |
      | ABC traders    | 9876543233 | MBNHM6783M | Customer ID            |                        10466 |
      | Virat Services | 9876543222 | GBNAG6783G | Aadhaar                |                   5456987455 |

  
  Scenario Outline: UnSucessful Verification of Existing Bank Customer Account with CIF
    Given Existing Bank Customer with following details is on PAN regsitration page
      | Name         | <name>         |
      | Mobile       | <mobile>       |
      | PAN          | <pan>          |
      | Constitution | PROPRIETORSHIP |
      | Pincode      |         110026 |
    And his PAN is not associated with any bank account
    But His mobile number number is asscoiated to one or more account
    And He Proceed with PAN validation
    And Customer Verification Page is displayed
    When He enters '<Verification Paramater>' with '<Verification Parameter Value>'
    Then Follwing '<Error>' Message should be displayed

    Examples: 
      | name           | mobile     | pan        | Verification Paramater | Verification Parameter Value | Error                                          |
      | ABC traders    | 9876543233 | MBNHM6783M | Customer ID            |                        12345 | Invalid Customer ID. 2 more attempts remaining |
      | Virat Services | 9876543222 | GBNAG6783G | Aadhaar                |                 123456780987 | Invalid Aadhaar. 2 more attempts remaining     |

  
  Scenario: Exhuasting Maximum attempt of Verification
    Given Existing Bank Customer with following details is on PAN regsitration page
      | Name         | ABC traders    |
      | Mobile       |     9876543233 |
      | PAN          | MBNHM6783M     |
      | Constitution | PROPRIETORSHIP |
      | Pincode      |         110026 |
    And his PAN is not associated with any bank account
    But His mobile number number is asscoiated to one or more account
    And He Proceed with PAN validation
    And Customer Verification Page is displayed
    When he incorrectly enters the CIF number three times
    Then the user is prompted with following error meesage
      """
      Max number of attempts exceeded
      """
    And Option to Proceed as non existing Bank customer is provided
    #And On accepting option the Customer is navigated to Proposal Initiation Page
    ##QUERY: Please provide the mesage contents
    #And On Rejecting the Option Thank you message is dispalyed
