@LoanDetails
Feature: Loan Details

  Scenario: Loan Details Page
    Given NTB Customer with following details wish to apply loan
      | Name   | Virat Enterprises   |
      | Mobile |          9876543226 |
      | PAN    | DBNEG6783D          |
      | URC    | UDYAM-GJ-12-0005678 |
    When He sucessfully validates his URC number
    Then he should see the Loan details page with follwing elements
      | Loan_Details_Title |
      | Sector_Label       |
      | Sector_Dropdown    |
      | Facility_Label     |
      | Facility_Dropdown  |
      | Add_Product_Button |
      | Next_Button        |

	
  Scenario: Add a New Product
    Given NTB Customer with following details wish to apply loan
      | Name   | Virat Enterprises   |
      | Mobile |          9876543226 |
      | PAN    | DBNEG6783D          |
      | URC    | UDYAM-GJ-12-0005678 |
    And he is on Loan Details Page
    When he click on Add Product
    Then he should see the Product details page with follwing elements
      | Product_Details_Title           |
      | Type_Label                      |
      | Type_Dropdown                   |
      | Purpose_Label                   |
      | Purpose_Textfield               |
      | Loan_Amount_Label               |
      | Loan_Amount_TextField           |
      | Existing_Loan_Transfer_CheckBox |
      | Add_Button                      |

  Scenario: Add a Product Details
    Given NTB Customer with following details wish to apply loan
      | Name   | Virat Enterprises   |
      | Mobile |          9876543226 |
      | PAN    | DBNEG6783D          |
      | URC    | UDYAM-GJ-12-0005678 |
    And He is on Product details Page
      | Sector_Dropdown   | Manufacturing   |
      | Facility_Dropdown | Working Capital |
    When he Enter all Product details
      | Type_Dropdown                   | Cash Credit      |
      | Purpose_Textfield               | Loan for Service |
      | Loan_Amount_TextField           |           300000 |
      | Existing_Loan_Transfer_CheckBox | checked          |
    And click on Add button
    Then he should see the confirmation message "Product added Successfully"
    And he should see the all product details on Loan details Page
      | ProductList_Amount | ₹3,00,000 |
      | ProductList_Name   | CC       |
	
	
  Scenario: Update existing product
    Given NTB Customer has already added one product with following details
      | Name               | Virat Enterprises   |
      | Mobile             |          9876543226 |
      | PAN                | DBNEG6783D          |
      | URC                | UDYAM-GJ-12-0005678 |
      | ProductList_Amount | ₹3,00,000            |
      | ProductList_Name   | CC                  |
    When He click edit icon on existing product details
    Then He should be able to edit following details
      | Type_Dropdown         | Bank Guarantee         |
      | Purpose_Textfield     | Loan for Manufacturing |
      | Loan_Amount_TextField |                 500000 |
    And click on Update button
    And He should see the updated product details on Loan details Page
      | ProductList_Amount | ₹5,00,000 |
      | ProductList_Name   | BG       |

  Scenario: Delete existing product
    Given NTB Customer has already added one product with following details
      | Name               | Virat Enterprises   |
      | Mobile             |          9876543226 |
      | PAN                | DBNEG6783D          |
      | URC                | UDYAM-GJ-12-0005678 |
      | ProductList_Amount | ₹3,00,000            |
      | ProductList_Name   | CC                  |
    When He click edit icon on existing product details
    When he click Delete icon on existing product details
    Then he should not be able to view Deleted Product on Loan details page

  
  Scenario: Add additional product to existing loan details
    Given NTB Customer has already added one product with following details
      | Name               | Virat Enterprises   |
      | Mobile             |          9876543226 |
      | PAN                | DBNEG6783D          |
      | URC                | UDYAM-GJ-12-0005678 |
      | ProductList_Amount | ₹3,00,000            |
      | ProductList_Name   | CC                  |
    When he click on Add Product
    And he Enter all Product details
      | Product_Details_Title           | Value          |
      | Type_Dropdown                   | Bank Guarantee |
      | Purpose_Textfield               | Loan for Agri  |
      | Loan_Amount_TextField           |         500000 |
      | Existing_Loan_Transfer_CheckBox | checked        |
    And click on Add button
    Then he should see the confirmation message "Product added Successfully"
    And Total Loan amount is '₹8,00,000'
