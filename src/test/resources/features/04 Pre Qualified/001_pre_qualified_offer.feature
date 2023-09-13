Feature: Pre Qualified

  Scenario: Customer Accept Pre Qualified Offer
    Given Customer is on Pre Qualified Offer Page
    When Customer accept Pre Qualified Offer
    Then the loan offer details as per the offer pre-populated
    And System redirected to URC Number Page

  Scenario: Customer Decline Pre Qualified Offer
    Given Customer is on Pre Qualified Offer
    When Customer Decline  Pre Qualified Offer
    Then System redirected to URC Number Page

  Scenario: Pre Qualified for Multiple offer
    Given Customer is on Pre Qualified Offer Page
    When Customer has Pre Qualified offer
    And Multiple offer is displayed on Screen
    Then Customer should have option only to select one Offer
    And other offers should be disable

  Scenario: Accept the offer
    Given Customer is on URC Number Page
    When He accept Pre Qualified offer
    And enter URC Number
    And URC Number get Validate
    Then System redirected to Loan Requirement
    And the Loan requirement screen should be pre-filled

  Scenario: Decline the Offer
    Given Customer is on URC Number Page
    When He decline Pre Qualified offer
    And He enter URC Number
    Then System redirected to Loan Requirement
    And Customer enter Loan details manually

  Scenario: Update URC Number
    Given The Customer is on URC Number Page
    When He enter Invalid URC Number
    Then System Show error 'XX'(Message yet to confirm by business)

  Scenario Outline: Verify URC Number with Incorect Number
    Given The Customer is on URC Number Page
    When He enter incorrect URN Number '<URC>'
    Then System should prompt to '<error scenario>'

    Examples: 
      | URC Number  | error scenario                  |
      |   000000000 | Should not be zero              |
      |   153787863 | Should not be number            |
      | ajdnajksj   | Should not be alphabets         |
      | ##$%@$      | Should not be Special Charcters |
      | UFVN 133245 | Should not be White Space       |

  Scenario: Verify URC Number with Correct Number
    Given The Customer is on URC Number
    When He enter correct URN Number '<UDYAM-UP-00-0123456>'
    Then System fetch the detail through URC and  PAN
    And should displayed Customer requirement as

  Scenario: Verify URC Number with Correct Number
    Given The Customer is on URC Number
    When He enter correct URN Number '<UDYAM-UP-00-0123456>'
    Then System fetch the detail through URC and  PAN
    And should displayed Customer requirement as following
      | Loan Required For     |
      | Customer Profile      |
      | Facility              |
      | Product               |
      | Other Question        |
      | Loan Amount           |
      | Transfer of loan{Y/N} |

  Scenario: Select mailing address
       Given: The Customer is on Customer Information Page

    When He select address from multiple sources like CIF / URC / GST
    Then Customer should have option to select one of the mailing address to be considered for loan application

  Scenario: Select Product
    Given The Customer is on Customer Requirement Page
    When He select Product from multiple Product
    Then Customer should have option to select one of the Product at a time

  Scenario: Pincode validation-(Generic)
    Given The Customer is on Customer Information Page
    When Customer enter invalid pincode
    Then System not allowed to initiate
    And error message to be prompted as'Invalid Pincode'

  Scenario: Services of Master PQ
    Given The Customer is on Customer Information Page
    When System trigger the services of Master
    Then System  master of PQ the required services to be triggered

  Scenario: Customer allow manual input
    Given The Customer is on Customer Information Page
    When System not fetch the Customer Information
    Then System should allow to enter addition details of borrower / co borrower / guarantor to customer

  Scenario: CRM Lead updated at various stages-(Generic)
    Given The Customer is on different stages
    When CRM Lead updated at various stages
    Then it should be updated at each stage
