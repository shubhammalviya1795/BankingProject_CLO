#Author: anand.sawant@intellectdesign.com
Feature: Display Pre approved offers
  Show the Single or Multiple pre approved offers for existing Bank Customer

  Scenario: Display Pre Approved Offers
    Given Existing Bank Customer with following details is on PAN regsitration page
      | Name    | Mr Ramachandra Kumaraswamy |
      | Mobile  |                 9928365278 |
      | PAN     | AGTH87650                  |
      | Pincode |                     400999 |
    And his PAN associated with single bank account
    And He is entitled for an pre approved offer
    When He Proceed with PAN validation
    Then He should see pre approved offer details
    And Option to proceed with pre approved Offer
    And Option to select for Higher Loan

  Scenario: Display Pre Approved Offers for Customer with Multiple Bank Acount
    Given Existing Bank Customer with follwing details is on PAN regsitration page
      | Name    | Mr Ramachandra Kumaraswamy |
      | Mobile  |                 9928365278 |
      | PAN     | AGTH87650                  |
      | Pincode |                     400999 |
    And his PAN associated with multiple bank account
    And He is entitled for an pre approved offer
    When He Proceed with PAN validation
    Then Multiple Accounts should displayed as list
    And User should have option only to select one account
    And display the offer page on selection of the account.

  Scenario: Verify Existing Bank Customer Account
    Given Existing Bank Customer with follwing details is on PAN regsitration page
      | Name    | Mr Ramachandra Kumaraswamy |
      | Mobile  |                 9928365278 |
      | PAN     | AGTH87650                  |
      | Pincode |                     400999 |
    And his PAN is not associated with any bank account
    But His mobile number number is asscoiated to one or more account
    When He Proceed with PAN validation
    Then Customer Verification Page is displayed
    And He is provided an option to verify with
      | Aadhar Number |
      | CIF Number    |
      | Card Number   |

  Scenario Outline: Sucessful Verification of Existing Bank Customer Account with CIF
    Given Existing Bank Customer with follwing details is on Customer Verification Page
      | Name                     | Mr Ramachandra Kumaraswamy     |
      | Mobile                   |                     9928365278 |
      | PAN                      | AGTH87650                      |
      | Pincode                  |                         400999 |
      | <Verification Paramater> | <Verification Parameter Value> |
    And his PAN is not associated with any bank account
    But His mobile number number is asscoiated to one or more account
    When He enters <Verification Paramater> with <Verification Parameter Value>
    Then He should be navigated to Approved Offers Page

    Examples: 
      | Verification Paramater | Verification Parameter Value |
      | CIF                    |                     12345678 |
      | Aadhar                 |                 123456780987 |
      | Card Number            |                 112143112165 |

  Scenario Outline: UnSucessful Verification of Existing Bank Customer Account with CIF
    Given Existing Bank Customer with follwing details is on Customer Verification Page
      | Name                     | Mr Ramachandra Kumaraswamy     |
      | Mobile                   |                     9928365278 |
      | PAN                      | AGTH87650                      |
      | Pincode                  |                         400999 |
      | <Verification Paramater> | <Verification Parameter Value> |
    And his PAN is not associated with any bank account
    But His mobile number number is asscoiated to one or more account
    When He enters <Verification Paramater> with Incorrect Values
    Then Follwing <Error> Message should be displayed

    Examples: 
      | Verification Paramater | Verification Parameter Value | Error          |
      | CIF                    |                     12345678 | Invalid CIF    |
      | Aadhar                 |                 123456780987 | Invalid Aadhar |
      | Card Number            |                 112143112165 | Invalid Card   |

  Scenario Outline: Exhuasting Maximum attempt of Verification
    Given Existing Bank Customer with follwing details is on Customer Verification Page
      | Name    | Mr Ramachandra Kumaraswamy |
      | Mobile  |                 9928365278 |
      | PAN     | AGTH87650                  |
      | Pincode |                     400999 |
    And he incorrectly enters the CIF number 3 times
    Then the user is prompted with following error meesage
      """
      Max number of attempts exceded
      """
    And Option to Proceed as non existing Bank customer is provided
    And On accepting option the Customer is navigated to Proposal Initiation Page
    ##QUERY: Please provide the mesage contents
    And On Rejecting the Option Thank you message is dispalyed
