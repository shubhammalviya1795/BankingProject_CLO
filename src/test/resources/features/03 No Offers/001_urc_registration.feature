@URCRegistration
Feature: URC Registration

  Scenario: URC Input Page for Pre Approved Offer
    Given Customer with following details is on Pre Approved Offer Page
      | Name   | Virat and Sons |
      | Mobile |     9876543228 |
      | PAN    | HBNHD6783H     |
    When He Accepts the Offer
    Then 'URCRegistraton' page is displayed with following details
      | URC_Title          |
      | URC_Number_Label   |
      | URC_Number_Input   |
      | Submit_Button      |
      | Dont_Have_URC_Link |
      | URC_Site_Link      |

  Scenario: URC Input Page for Higher Loan Request
    Given Customer with following details is on Pre Approved Offer Page
      | Name   | Virat and Sons |
      | Mobile |     9876543228 |
      | PAN    | HBNHD6783H     |
    When He clicks on the Higher Loan Link
    Then 'URCRegistraton' page is displayed with following details
      | URC_Title          |
      | URC_Number_Label   |
      | URC_Number_Input   |
      | Submit_Button      |
      | Dont_Have_URC_Link |
      | URC_Site_Link      |

  Scenario: URC Input Page for NTB Customer
    Given NTB Customer with following details has validated his PAN
      | Name    | Virat Enterprises |
      | Mobile  |        9876543226 |
      | PAN     | DBNEG6783D        |
      | Pincode |            110026 |
    Then 'URCRegistraton' page is displayed with following details
      | URC_Title           |
      | URC_Number_Label    |
      | URC_Number_Input    |
      | Submit_Button       |
      | Skip_This_Step_Link |
      | Dont_Have_URC_Link  |
      | URC_Site_Link       |

  Scenario Outline: URC Number with Incorect Value
    Given NTB Customer with following details has validated his PAN
      | Name    | Virat Enterprises |
      | Mobile  |        9876543226 |
      | PAN     | DBNEG6783D        |
      | Pincode |            110026 |
    When He enter incorrect URC Number '<URC>'
    Then System should prompt to '<error scenario>'

    Examples: 
      | URC         | error scenario                     |
      |   000000000 | Minimum 19 characters are required |
      |             | Value cannot be empty              |
      |   153787863 | Minimum 19 characters are required |
      | ajdnajksj   | Minimum 19 characters are required |
      | ##$%@$      | Minimum 19 characters are required |
      | UFVN 133245 | Minimum 19 characters are required |

  Scenario: URC number not registered as micro entity for ETB customer
    Given Customer with following details is on URC Input Page
      | Name   | Virat Enterprises   |
      | Mobile |          9876543222 |
      | PAN    | GBNAG6783G          |
      | URC    | UDYAM-BR-12-0005894 |
    When He submits the URC number
    And his URC is registered with Medium Enterprises
    Then He should see the following message
      """
      is not registered as micro entity hence you cannot avail this pre-approved offer
      """

  Scenario: URC Number Name mismatch
    Given Customer with following details is on URC Input Page for ETB customer
      | Name   | Virat and Sons      |
      | Mobile |          9876543228 |
      | PAN    | HBNHD6783H          |
      | URC    | UDYAM-MP-12-0005656 |
    When He submits the URC number
    And his name match is only 50%
    Then System should display the following message
      """
      Seems like there is a name mismatch!
      """

  ##TODO: Check with Asim
  @Manual
  Scenario: URC number not registered as micro entity for NTB Customer
    Given NTB Customer with following details is on URC Input Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543222 |
      | PAN    | GBNAG6783G       |
      | URC    | UDYAM-UP-0123456 |
    And his URC is registered with Medium Enterprises
    When He submits the URC number
    Then He should be navigated to Loan Details Page

  ##TODO: Check with Asim
  @Manual
  Scenario: URC Number Name mismatch for NTB Customer
    Given NTB Customer with following details is on URC Input Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543222 |
      | PAN    | GBNAG6783G       |
      | URC    | UDYAM-UP-0123456 |
    And his name match is only 50%
    When He submits the URC number
    Then He should be navigated to Loan Details Page

  Scenario: Skip URC Registration
    Given NTB Customer with following details is on URC Input Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543222 |
      | PAN    | GBNAG6783G       |
      | URC    | UDYAM-UP-0123456 |
    When He clicks on Skip the URC Link
    Then He should be navigated to Loan Details Page

  @Manual
  Scenario: Skip URC by continuing without Offer
    Given Customer URC is not registered as Micro enterpirse
    And He has pre approved offer
    And He submits the URC number
    And See the Error message 'URC Number not registered'
    When He Clicks on Continue without Offer
    Then He should be navigated to Loan Details Page

 
  Scenario: Maximum attempt for Inavlid URC entry
    Given Customer with following details is on URC Input Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543222 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    When he enter the Invalid URC number for 3 times
    Then He should be displayed follwing error message
      """
      Max number of attempts exceeded
      """

  @Manual
  Scenario: URC Number sucessful validation for pre approved offer
    Given Customer with following details is on URC Input Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    And He has selected the pre approved offer
    When He submits the URC number
    Then He should be navigated to Loan Details Page

  @Manual
  Scenario: URC Number sucessful validation for NTB customer
    Given NTB Customer with following details is on URC Input Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    When He submits the URC number
    Then He should be navigated to Loan Details Page

  @Manual
  Scenario: Display multiple pre approved offer
    Given The Customer is on Offer Page
    When He click on Accept & Continue button
    Then other offers become disabled

  @Manual
  Scenario: Customer Name Not Match & he click on Change
    Given The Customer is on Name mismatch page
    When He click on Change
    Then System should redirected to URC Number Page

  @Manual
  Scenario: Customer don't have URC Number
    Given The Customer is on URC Number
    When Customer click on dont have URC Video option
    Then System should redirected video of how to register for URC

  @Manual
  Scenario: Skip URC Number
    Given The Customer is on URC Number
    When He Enter URC Number
    And Name match from URC and from PAN is less than 80%
    And Customer doesn't Skip URC
    Then System redicted to URC Acceptance Page

  @Manual
  Scenario: Trigger API after completing Customer Information
    Given Customer is on Customer Information Page
    When Customer completed all filled data
    Then System trigger all the following API as
      | Posidex                  |
      | Hunter                   |
      | multi-bureau(consumer)   |
      | multi-bureau(commercial) |
