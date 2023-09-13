Feature: TakeOver

  @takover1
  Scenario: Display Existing Loan on Take Over Screen
    Given customer is on Take Over details screen
    When customer click on Take Over Tab
    Then system should displays all existing loan product of customer on Take Over screen

  @takover2
  Scenario: Select Product on Take Over Screen
    Given customer is on Take Over details screen
    And System display all existing product of customer
    When Customer selects the product
    Then Customer should redirect to product details screen

  @takover3
  Scenario: Customer select term loan product on Take Over screen
    Given customer is on Take Over details screen
    When Customer select term loan product
    Then system should display term loan product details screen

  @takover4
  Scenario Outline: Invalid Loan Account Number
    Given customer is on Take Over details screen
    And Customer select term loan product
    And system should display term loan product details screen
    And Customer select Bank tab
    When he inputs the invalid '<LoanAccountNumber>'
    Then system should display with follwing '<error>' message for bank tab details screen

    Examples: 
      | LoanAccountNumber | error                       |
      | 1234@7891S23      | Invalid Loan Account Number |
      |             12345 | Invalid Loan Account Number |
      | $%@               | Invalid Loan Account Number |
      | ABCDE 1234 K      | Invalid Loan Account Number |
      | ABCDEFGHIG        | Invalid Loan Account Number |
      |        1234567891 | Invalid Loan Account Number |

  @takover5
  Scenario: Term loan bank tab fields
    Given customer is on Take Over details screen
    And Customer select term loan product
    And system should display term loan product details screen
    When Customer select Bank tab
    Then system should display with following fields for bank tab
      | IFSC Code                             |
      | Loan Account Number                   |
      | Sanction Letter (optional)            |
      | Use existing sanction letter checkbox |
      | Upload button                         |

  @takover6
  Scenario: Serching functionality of IFSC Number
    Given customer is on Take Over details screen
    And Customer select term loan product
    And system should display term loan product details screen
    And Customer select Bank tab
    When customer click on search icon on IFSC field
    Then system should display with following fields for find IFSC Code details screen
      | Bank Name        |
      | Branch Name/City |
      | Find button      |

  
  @takover7
  Scenario: Customer is able to add IFSC Code field details
    Given customer is on Take Over details screen
    And Customer select term loan product
    And Customer select Bank tab
    When Customer is add all the IFSC Code field details
      | Bank Name        | ICICI BANK LIMITED |
      | Branch Name/City | Mumbai             |
    Then System should show all the bank details on IFSC code details Screen 
   

  @takover8
  Scenario: Customer is able to update Bank tab field details
    Given customer is on Take Over details screen
    And Customer select term loan product
    And Customer select Bank tab
    And Customer is add all the IFSC Code field details
      | Bank Name        | ICICI BANK LIMITED |
      | Branch Name/City | Mumbai             |
    When cutomer is add Loan Account Number
      | Loan Account Number | 20283813527 |
    And customer upload senction letter document
    And customer click on update button for bank tab details
    Then Term loan Updated message should be display
      """
      Term loan details updated successfully.
      """

  @takover9
  Scenario: Change Bank details
    Given customer is on Take Over details screen
    And Customer select term loan product
    And Customer select Bank tab
    When customer change Branch/IFSC Code details
      | Bank Name        | ICICI BANK LIMITED |
      | Branch Name/City | Mumbai             |
    Then Changed Bank details should be display on IFSC bank tab screen

 
  @takover10
  Scenario Outline: Saction letter Valid format
    Given customer is on Take Over details screen
    And Customer select term loan product
    And Customer select Bank tab
    When customer Upload sanction letter
    Then system should validate the sanction as per below rules

    Examples: 
      | File size should be maximum up to 1 MB |
      | File format should be pdf, jpeg, png   |

  @takover11
  Scenario Outline: Saction letter Invalid format
    Given customer is on Take Over details screen
    And Customer select term loan product
    And Customer select Bank tab
    When customer Upload sanction letter
    Then system should validate the sanction as per below rules

    Examples: 
      | If File size is greater than 1 MB then system should show error message |
      | If File format is excel,txt,xls then system should show error message   |

  @takover12
  Scenario: Saction letter option is not mandatory
    Given customer is on Take Over details screen
    And Customer select term loan product
    And Customer select Bank tab
    And Customer is add all the IFSC Code field details
      | Bank Name        | ICICI Bank |
      | Branch Name/City | Mumbai     |
    When cutomer is add Loan Account Number
      | Loan Account Number | 20283813527 |
    And customer click on update button without uploading sanction letter
    Then custmer should be able to update all details because sanction letter upload is optional parameter

  @takover13
  Scenario: Verify customer is able to view Delete option for Sanction Letter
    Given customer is on Take Over details screen
    And Customer select term loan product
    And Customer select Bank tab
    When customer Upload sanction letter
    Then customer should be able to view Delete icon on sanction letter

  @takover14
  Scenario: Verify customer is able to delete the Sanction Letter
    Given customer is on Take Over details screen
    And Customer select term loan product
    And Customer select Bank tab
    And customer Upload sanction letter
    When click on delete option
    Then sanction letter should be delete

  @takover15
  Scenario: User Select NBFC option
    Given customer is on Take Over details screen
    And Customer select term loan product
    When customer select NBFC tab
    Then System should display NBFC fields screen
      | Loan Account Number                   |
      | NBFC Name                             |
      | City                                  |
      | Sanction Letter (optional)            |
      | Use existing sanction letter checkbox |
      | Upload button                         |

  @takover16
  Scenario: customer is able to Update NBFC tab field details
    Given customer is on Take Over details screen
    And Customer select term loan product
    And customer select NBFC tab
    When cutomer is add all the following details
      | Loan Account Number                   | 20283813527 |
      | NBFC Name                             | SBI Bank    |
      | City                                  | Mumbai      |
      | Sanction Letter (optional)            |             |
      | Use existing sanction letter checkbox |             |
      | Upload button                         |             |
    Then Term loan Updated message should be display
      """
       Term loan details updated successfully.
      """

  @takover17
  Scenario Outline: User enter Invalid Loan Account Number in NBFC tab
    Given customer is on Take Over details screen
    And Customer select term loan product
    And customer select NBFC tab
    When he inputs the invalid '<LoanAccountNumber>'
    Then system should display with follwing '<error>' message for NBFC tab details screen

    Examples: 
      | LoanAccountNumber | error                       |
      | 1234@7891S23      | Invalid Loan Account Number |
      |             12345 | Invalid Loan Account Number |
      | $%@               | Invalid Loan Account Number |
      | ABCDE 1234 K      | Invalid Loan Account Number |
      | ABCDEFGHIG        | Invalid Loan Account Number |
      |        1234567891 | Invalid Loan Account Number |

  @takover18
  Scenario Outline: Invalid bank name under NBFC tab
    Given customer is on Take Over details screen
    And Customer select term loan product
    And customer select NBFC tab
    When he inputs the invalid '<NbfcName>'
    Then system should display with follwing '<error>' message for NBFC Name as below

    Examples: 
      | NbfcName               | error             |
      |              123456764 | Invalid NBFC Name |
      | @#$#!%%                | Invalid NBFC Name |
      | SD%&*()@!#ghh1156      | Invalid NBFC Name |
      | gujrat abcd 12345 bank | Invalid NBFC Name |

  @takover19
  Scenario Outline: Invalid city name under NBFC tab
    Given customer is on Take Over details screen
    And Customer select term loan product
    And customer select NBFC tab
    When he inputs the invalid '<CityName>'
    Then system should display with follwing '<error>' message for City Name as below

    Examples: 
      | CityName          | error             |
      |         123456764 | Invalid City Name |
      | @#$#!^*#          | Invalid City Name |
      | SD%&*()@!#ghh1156 | Invalid City Name |
      | SD%Mumbai         | Invalid City Name |

  @takover20
  Scenario: To check product details are editable
    Given customer is on Take Over details screen
    When customer click on pencil icon of any product
    Then system should allow customer to edit the product details

  @takover21
  Scenario: Customer closed product on product details screen
    Given customer is on Take Over details screen
    And customer select any prdouct
    When customer click on closed icon
    Then system should redirect to Take Over product details screen

  @takover22
  Scenario: Customer should display all the product details for the loans he want to transfer
    Given Customer is on Takeover Details Screen
    And system display all the products
    When Customer click on Next button on take over screen
    Then all Takeover product details should be come up followed by collateral details
