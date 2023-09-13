@ApplicantFlow
Feature: Applicant Flow Details
  Add the Applicant and Co applicants to Proposal
	
	
  Scenario: Display Entity Details
    Given Customer with following details is on Loan Application Details
      | Name        | Mr. Virat K and Sons |
      | Mobile      |           9876543228 |
      | PAN         | HBNHD6783H           |
      | URC         | UDYAM-KL-12-0003434  |
      | Sector      | Manufacturing        |
      | Facility    | Working Capital      |
      | ProductType | Bank Guarantee       |
    When He Moves Next
    Then Entity Details page is displayed with following details
      | Business_Name         | Virat and Sons             |
      | Email                 | marketingemail@gmail.com   |
      | Industry              | ABRASIVES AND GRINDING     |
      | Sub_Industry          | 01101 - GROWING OF CEREALS |
      | Date_Of_Incorporation | 08-01-2000                 |
    And Option to add communication address
	
	
  Scenario: Select Communication Address for Entity
    Given Customer with following details is on Entity Details Page
      | Name   | Mr. Virat K and Sons |
      | Mobile |           9876543228 |
      | PAN    | HBNHD6783H           |
      | URC    | UDYAM-UP-0123456     |
    When he selects add communication address
    Then follwing address details are displayed
      | Beside Reliance Smart - 751022 |
    And User selects address option 2
    And He is navigated to Entity Details Page
    And the selected address is displayed
    And Option to Select Address Type is provided
    And Option to Change the Address is provided

	
  Scenario: Add new Communication Address for Entity
    Given Customer with following details is on Address Selection Page for Entity
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    When he clicks on Add New option
    Then he can add address with following details
      | Address_Line_1 | Offce 32, Tower D |
      | Pin_Code       |            110026 |
    And clicks on Add
    And He is navigated to Entity Details Page
    And the new added address is displayed
    And Option to Select Address Type is provided
    And Option to Change the Address is provided
    And Option to Edit the address is provided

	
  Scenario: Update the new Communication Address for Entity
    Given Customer with following details is on Entity Details Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    And have added communication address
    When he clicks on Edit communication address
    Then he can Edit address with following details
      | Address_Line_1 | Offce 32, Tower E |
      | Pin_Code       |            110026 |
    And clicks on Update
    And He is navigated to Entity Details Page
    And The new Updated address is displayed

	
  Scenario: Delete the new Communication Address for Entity
    Given Customer with following details is on Entity Details Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    And have added communication address
    When he clicks on Edit communication address
    Then he can view Delete address Icon
    And clicks on Delete icon button
    And He is navigated to add new communication address page
    And option to Add new communication address

 
  Scenario: Applicant Details for Partnership
    Given Customer with following details is on Entity Details Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    And have added communication address
    When He clicks on Next
    Then He should be navigated to Applicant Details page with following partner details
      | ANKIT SHARMAN JAIN    |
      | SAMBIT HARI SHARMA    |
      | OMKAR JITENDRA VIMANI |
    And has option to Edit the partner
    And option to click on Next

  @Manual
  Scenario: Applicant Details for Company
    Given Customer with following details is on Entity Details Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    And and have added communication address
    When He clicks on Next
    Then He should be navigated to Applicant Details page with following Director details
      | Entity Name | PAN         | Date of Birth | Address   |
      | Director1   | AUUPS77474M | 23-06-78      | Address 1 |
      | Director2   | AUUPS77474M | 23-06-78      | Address 2 |
    And has option to Edit the Director
    And option to click on Next

	
  Scenario: Add co-applicant as Indiviual
    Given Customer with following details is on Applicant Details Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    When he clicks on Add Co-applicant option
    Then he can add Co-applicant with following details
      | PAN           | ANDPS3572T       |
      | First Name    | Ashutosh         |
      | Middle Name   | T                |
      | Last Name     | Mishra           |
      | Date of Birth | 23-06-1999       |
      | Address       | Offce 32,Tower D |
      | Pin_Code      |           110026 |
      | Customer_ID   |       8376726236 |
    And clicks on Add button for add Co-applicant
    And He is navigated to Applicant Details Page
    And Option to Edit the Co-applicant Details is displayed

	
  Scenario: Add co-applicant as Company
    Given Customer with following details is on Applicant Details Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    When he clicks on Add Co-applicant option
    And Enter Pan details
      | PAN | HBNHD6783H |
    Then System should fetch following Co-applicant details
      | Business_Name         | Virat and Sons      |      
      | Date_Of_Incorporation | 26-06-2009          |
      | Address               | Offce 32,Tower D    |
      | Pin_Code              |              110026 |
    And clicks on Add
    And He is navigated to Applicant Details Page
    And Option to Edit the Co-applicant Details is displayed
	
	@Manual
	@Manual:Failed
  Scenario: Update co-applicatnt
    Given Customer with following details is on Applicant Details Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    When he clicks on Edit Co-applicant option
    Then he can Edit Co-applicant with following details
      | PAN           | ANDPS3572T       |
      | First Name    | Ashutosh         |
      | Middle Name   | T                |
      | Last Name     | Mishra           |
      | Date of Birth | 23-06-1999       |
      | Address       | Offce 62,Tower A |
      | Pin_Code      |           110042 |
      | Customer_ID   |            12345 |
    And clicks on Update co-applicant
    And He is navigated to Applicant Details Page
    And the new updated Co-applicant details is displayed
    And Option to Select Add Co-applicant is provided
    And Option to Edit the Co-applicant is provided

  @Manual
  Scenario: Update the Director Address Details
    Given Customer with following details is on Applicant Details Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    When he clicks on Edit Partner option
    Then he can Edit Director with following details
      | Entity Name | PAN         | Date of Birth | Address   |
      | Director1   | AUUPS77474M | 23-06-78      | Address 1 |
      | Director2   | AUUPS77474M | 23-06-78      | Address 2 |
    And clicks on update
    And He is navigated to Applicant Details Page
    And Option to Edit the Director Details is displayed

  @Manual
  Scenario: Validate Entity Details
    Given Customer with following details is on Loan Application Details
      | Name        | Virat and Sons   |
      | Mobile      |       9876543228 |
      | PAN         | HBNHD6783H       |
      | URC         | UDYAM-UP-0123456 |
      | Sector      | Wholesale Trader |
      | Facility    | Working Capital  |
      | ProductType | Cash Credit      |
    When He Moves Next
    Then Entity Details page is displayed with following details
      | Business_Name         | Virat and Sons      |
      | Email                 | viratnsons@bbci.com |
      | Industry              | Loremipsum          |
      | Sub_Industry          | SubLoremipsum       |
      | Date_Of_Incorporation | 26-06-2009          |
    And Option to add communication address
    And user click on ADD button without entering address and Pin code details
      | Address  |  |
      | Pin_Code |  |
    And System should display Error message “Field Should not be blank”.
