@tag
Feature: Loan Details

  Scenario: Loan Details Page
    Given NTB Customer with following details wish to apply loan
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    When He sucessfully validates his URC number
    Then he should see the Loan details page with follwing elements
      | Loan_Details_Title    |
      | Sector_Label          |
      | Sector_Dropdown       |
      | Facility_Label        |
      | Facility_Dropdown     |
      | Add_Production_Button |
      | Next_Button           |

  Scenario: Add a New Product
    Given NTB Customer with following details wish to apply loan
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    And he is on Loan Details Page
    When he click on Add Product
    Then he should see the Product details page with follwing elements
      | Product_Details_Title           |
      | Type_Label                      |
      | Type_Dropdown                   |
      | Purpose_Label                   |
      | Purpose_textfield               |
      | Loan_Amount_Label               |
      | Loan_Amount_TextField           |
      | Existing_Loan_Transfer_CheckBox |
      | Add_Button                      |

  Scenario: Add a Product Details
    Given NTB Customer with following details is on Product details Page
    When he Enter all Product details
      | Product_Details_Title           | Value            |
      | Type_Dropdown                   | Cash Credit      |
      | Purpose_textfield               | Loan for Service |
      | Loan_Amount_TextField           |           500000 |
      | Existing_Loan_Transfer_CheckBox | Checked          |
    And click on Add button
    Then he should see the confirmation message "Product added Successfully"
    And he should see the all product details on Loan details Page

  Scenario: Update existing product
    Given NTB Customer with following details is on Loan details Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    And he should see the existing product details on Loan details Page
    When he click edit icon on existing product details
    And he should be able to edit following details
      | Product_Details_Title           | Value            |
      | Type_Dropdown                   | Cash Credit      |
      | Purpose_textfield               | Loan for Service |
      | Loan_Amount_TextField           |           500000 |
      | Existing_Loan_Transfer_CheckBox | Checked          |
    And click on Update button
    Then he should be able to view updated Product details on Loan details page

  Scenario: Delete existing product
    Given NTB Customer with following details is on Loan details Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    And he should see the existing product details on Loan details Page
    When he click Delete icon on existing product details
    Then he should not be able to view Deleted Existing Product on Loan details page

  Scenario: Add additional product to existing loan details
    Given NTB Customer with following details is on Loan details Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    And he should see the existing product details on Loan details Page
    When he click on Add Product button
    And he Enter all Product details
      | Product_Details_Title           | Value         |
      | Type_Dropdown                   | Loan Credit   |
      | Purpose_textfield               | Loan for Agri |
      | Loan_Amount_TextField           |        500000 |
      | Existing_Loan_Transfer_CheckBox | Checked       |
    And click on Add button
    Then he should see the confirmation message "Product added Successfully"
    And he should see the all addetional product details on Loan details Page
