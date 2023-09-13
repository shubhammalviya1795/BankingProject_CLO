Feature: Collaterals

  @collateral_1
  Scenario: Customer select Collaterals details
    Given Customer is on Collaterals Tab screen
    When Customer click on collaterals tab
    Then system should navigates to Collateral details screen with following option
      | +Add collaterals worth $2cr                                |
      | +Add Government Backed Scheme                              |
      | Avail this loan under a govt backed schemes with help icon |
      | Benefit 1 of choosing govt scheme                          |
      | Benefit 2 Lorem ipsum                                      |
      | Back Button                                                |
      | Next_Button                                                |

  @collateral_2
  Scenario: Select scheme
    Given Customer is on Collaterals Tab screen
    When Customer select +Add Government Backed Scheme option
    Then He should see the Select Scheme page with following Scheme descripiton
      | Scheme 1 |
      | Scheme 2 |
      | Scheme 3 |
    And customer should be able to select any one scheme from the list

  @collateral_3
  Scenario: customer select Add Government Backed Scheme
    Given Customer is on Collaterals Tab screen
    When Customer select +Add Government Backed Scheme option
    Then system should display multiple number of scheme in the list.

  @collateral_4
  Scenario: customer select any scheme from Government Backed Scheme
    Given Customer is on Collaterals Tab screen
    And Customer select +Add Government Backed Scheme option
    When select any scheme by customer
    Then selected scheme should display on Collaterals Tab screen
    And delete icon also visible on selected scheme

  @collateral_5
  Scenario: Delete Government Backed Scheme
    Given Customer is on Collaterals Tab screen
    And selected scheme is display on Collaterals Tab screen
    When customer click on delete icon on selected scheme
    Then system should display following message
      """
      scheme deleted sucessfully
      """

  @collateral_6
  Scenario: Select Add collaterals worth
    Given Customer is on Collaterals Tab screen
    When Customer select +Add collateral worth $2cr option
    Then system should redirect to add collateral Details screen

  @collateral_7
  Scenario: Verify various type of collaterals
    Given Customer is on Collaterals Tab screen
    And Customer click on Add collateral worth $2cr option
    When Customer click on collateral type
    Then System should dispaly collateral fields such as < Collateral Type >
      | Property          |
      | Vehicle/Equipment |
      | Liquid Securities |
      | Stock/Book Dates  |
      | Plant & Machinery |

  @collateral_8
  Scenario: Verify Subtypes feilds as per the collaterals types
    Given Customer is on Collaterals Tab screen
    And Customer click on Add collateral worth $2cr option
    And Customer select collaterals type as Property
    When Customer click on Sub Types of collaterals
    Then System should show subtypes of collaterals as per the Master

  @collateral_9
  Scenario: Customer select collateral Type as Property
    Given Customer is on Collaterals Tab screen
    And Customer select +Add collateral worth $2cr option
    When Customer select Type as Property
      | Type     | Property             |
      | Sub-type | Residential Property |
    Then Following Details should be displayed
      | Add Address                             |
      | Approx market value                     |
      | Usage                                   |
      | Leased/Rented                           |
      | Owned                                   |
      | Add Owner                               |
      | This collateral is an Existing takeover |

  @collateral_10
  Scenario: Customer add all collateral Type of Property details
    Given Customer is on Collaterals Tab screen
    And Customer select +Add collateral worth $2cr option
    When customer enter all property details
      | Type                                             | Property                            |
      | Sub-type                                         | Residential Property                |
      | Address                                          | office 32, Tower D, fortis hospital |
      | Pincode                                          |                              420022 |
      | Approx market value                              |                         1,00,00,000 |
      | Usage                                            | Owned                               |
      | Add Owner_Name                                   | Raj Kumar                           |
      | This collateral is an Existing takeover_checkbox | checked                             |
      | Bank Name                                        | SBI                                 |
    And Click on ADD
    Then customer should see the confirmation message for Property details
      """
      Property details added Successfully
      """

  @collateral_11
  Scenario: +Add Address on Collateral details screen
    Given Customer is on Collaterals Tab screen
    And Customer select +Add collateral worth $2cr option
    And customer select Property and sub property type details
      | Type     | Property             |
      | Sub-type | Residential Property |
    When click on +Add Address by Customer
    Then System should redirect to Property Address Screen
    And +Add new option should visible
    And Close icon should visible
    And Selected Address should visible in green tick
    And Customer should be able to select Address from Existing list

  @collateral_12
  Scenario: Change address on property address screen
    Given Customer is on Collaterals Tab screen
    And Customer select +Add collateral worth $2cr option
    And customer select Property and sub property type details
      | Type     | Property             |
      | Sub-type | Residential Property |
    When Customer Update Address on property address screen
      | Address | office 64, Tower A, MY hospital |
      | Pincode |                          461001 |
    And click on Update button on address screen
    Then System should redirect to Property Collateral Detail Screen

  @collateral_13
  Scenario: +Add Owner on Collateral details screen
    Given Customer is on Collaterals Tab screen
    And Customer select +Add collateral worth $2cr option
    And customer select Property and sub property type details
      | Type     | Property             |
      | Sub-type | Residential Property |
    When click on +Add Owner by Customer
    And Customer should be able to select Owner from Existing list
    Then Selected owner should visible in green tick
    And +Add new option should visible
    And Close icon should visible

  @collateral_14
  Scenario: Change Owner Name
    Given Customer is on Collaterals Tab screen
    And Customer select +Add collateral worth $2cr option
    And customer select Property and sub property type details
      | Type     | Property             |
      | Sub-type | Residential Property |
    When Customer Change new Owner Details
    And Enter new Owner Name
      | Add Owner_Name | Raj Kumar sahu |
    Then System should redirect to Property Collateral Detail Screen

  @collateral_15
  Scenario: Collateral Type as vehicle Equipment
    Given Customer is on Collaterals Tab screen
    And Customer select +Add collateral worth $2cr option
    When Customer select collateral Type as Vehicle Equipment
      | Type     | Vehicle Equipment |
      | Sub-type | Used              |
    Then Following fields should be displayed for vehicle Equipment
      | Manufacturer         |
      | Make                 |
      | Model                |
      | Asset Value          |
      | Margin(Down payment) |
      | Discount             |
      | Add button           |
    And Close button should visible

  @collateral_16
  Scenario: Customer add vehicle Equipment details as collateral Type
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When customer enter all vehicle Equipment details
      | Sub-type             | Used       |
      | Manufacturer         | John cerry |
      | Make                 | Tractor    |
      | Model                | XWS901     |
      | Asset Value          |    1500000 |
      | Margin(Down payment) |     500000 |
      | Discount             |     200000 |
    And Click on ADD
    Then customer should see the confirmation message for vehicle equipment
      """
      Vehicle Equipment details added Successfully
      """

  @collateral_17
  Scenario: Collateral Type as Liquid securities
    Given Customer is on Collaterals Tab screen
    And Customer select +Add collateral worth $2cr option
    When Customer select collateral Type as Liquid securities
      | Type     | Liquid securities |
      | Sub-type | Corporate Bonds   |
    Then Following fields should be displayed for Liquid securities and Corporate Bonds
      | Field Name  |
      | Fund Type   |
      | Share Forms |
      | Value       |
      | Add button  |
      | +Add owner  |
    And Close button should visible

  @collateral_18
  Scenario: Customer add Liquid securities details as collateral Type
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When customer enter all Liquid securities details
      | Sub-type    | Corporate Bonds |          |
      | Field Name  | xyz             |          |
      | Fund Type   | Debt            | Equity   |
      | Share Forms | Demat           | Physical |
      | Value       |          500000 |          |
    And Click on ADD
    Then customer should see the confirmation message for Liquid securities and corporate bonds
      """
      Liquid securities details added Successfully
      """

  @collateral_19
  Scenario: Collateral Type as Liquid securities
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When Customer select collateral Type as Liquid securities and Shares
      | Type     | Liquid securities |
      | Sub-type | Shares            |
    Then Following fields should be displayed for Liquid securities and Shares
      | Field Name          |
      | Fund Type           |
      | Share Forms         |
      | Value               |
      | Add Security Holder |
    And Close button should visible

  @collateral_20
  Scenario: Collateral Type as Liquid securities
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    And Customer select collateral Type as Liquid securities
      | Type     | Liquid securities |    |
      | Sub-type | Shares            | MF |
    When Customer click on +Add Security Holder option
    Then System should allowed to add the Security Holder

  @collateral_21
  Scenario: Select the collateral type as ‘Liquid securities’ & Sub-Type as ‘FD’
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When Customer select collateral Type as following details
      | Type     | Liquid securities |
      | Sub-type | FD                |
    Then System should disply below option to customer
      | Existing FD   |
      | Authorise New |
      | Add button    |

  @collateral_22
  Scenario: Collateral sub-type is other than FD                // Manual
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When Collateral sub-type is other than FD
    Then FD APIs should not be triggered

  @collateral_23
  Scenario: Existing FD
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    And Customer select collateral Type as following details
      | Type     | Liquid securities |
      | Sub-type | FD                |
    When Customer is having existing FD
    Then System should display all the list of existing FD
    And customer sholud allow to select FD details

  @collateral_24
  Scenario: Existing FD details
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    And Customer select collateral Type as following details
      | Type     | Liquid securities |
      | Sub-type | FD                |
    When Customer select Authorise New FD option
    Then System should disply below FD details to customer
      | FD A/C number |
      | Tenure        |
      | FD Amount     |
      | Maturity      |

  @collateral_25
  Scenario: Authorise new FD
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    And Customer select collateral Type as following details
      | Type     | Liquid securities |
      | Sub-type | FD                |
    When Customer is not having Existing FD
    Then System should allow customer to open new FD

  @collateral_26
  Scenario: Existing Bank account details
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    And Customer select collateral Type as following details
      | Type     | Liquid securities |
      | Sub-type | FD                |
    When Customer select Authorise New FD option
    Then System should display below Account details to customer
      | A/C Number      |
      | Values          |
      | Current Balance |
      | ADD             |

  @collateral_27
  Scenario: Select FD amount in lacs
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    And Customer select collateral Type as following details
      | Type     | Liquid securities |
      | Sub-type | FD                |
    When Customer select Authorise New FD
    Then customer should be able to Enter amount in lacs while Opening the FD

  @collateral_28
  Scenario: Display multiple current accounts
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    And Customer select collateral Type as following details
      | Type     | Liquid securities |
      | Sub-type | FD                |
    When Customer select Authorise New FD
    Then customer should displays the multiple current accounts if customer having multiple

  @collateral_29
  Scenario: Account balance is greater
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    And Customer select collateral Type as following details
      | Type     | Liquid securities |
      | Sub-type | FD                |
    When customer account balance is less
    And customer enters greater value
    Then System should not allow user to greater value
    And should display below message as
      """
      Your account balance is less
      """

  @collateral_30
  Scenario: Exceeds account limit
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    And Customer select collateral Type as following details
      | Type     | Liquid securities |
      | Sub-type | FD                |
    When customer exceeds account limit while opening new FD
    Then System should disply below error message as
      """
      Account limit is exceeds
      """

  @collateral_31
  Scenario: collateral type as ‘Liquid securities’ & Sub-Type as ‘Post Office’
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When Customer select collateral Type as following details for Liquid securities and Post Office
      | Type     | Liquid securities |
      | Sub-type | Post Office       |
    Then System should display all the following values as per the master
      | Field Name           |       |          |
      | Fund Type            | NSC   | KVP      |
      | Share Forms          | Demat | Physical |
      | Value                |       |          |
      | Add button           |       |          |
      | +Add Security Holder |       |          |

  @collateral_32
  Scenario: collateral type as ‘Liquid securities’ & Sub-Type as ‘Insurance’
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When Customer select collateral Type as following details for Liquid securities and Insurance
      | Type     | Liquid securities |
      | Sub-type | Insurance         |
    Then system should show all the below values as per the master
      | Field Name           |       |          |
      | Fund Type            | Pvt   | LIC      |
      | Share Forms          | Demat | Physical |
      | Value                |       |          |
      | +Add Security Holder |       |          |
      | Upload button        |       |          |
      | Add button           |       |          |

  @collateral_33
  Scenario: Collateral type as ‘Stock/Booked dates’
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When Customer select collateral Type as following details for Stock type
      | Type       | Stock/Booked dates |
      | Stock type | Stock              |
    Then system should display all the below values as per the master for type as Stock
      | Stock type    |
      | Aging on date |
      | Value         |
      | ADD Address   |
      | Upload button |
      | Add button    |

  @collateral_34
  Scenario: Stock Document Upload when collateral type as ‘Stock/Booked dates’
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    And Collateral type as ‘Stock/Booked dates’
    When Customer upload the Stock Document
    Then Uploaded documents should be only jpeg,png,pdf format
    And Uploaded document file size should be upto 1MB

  @collateral_35
  Scenario: Collateral type as ‘Plant & Machinery’
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When Customer select collateral Type as following details for Plant & Machinery
      | Type | Plant & Machinery |
    Then system should display all the following fields as per the master for type as Plant & Machinery
      | Set-up Year              |
      | Address with edit option |
      | Value                    |
      | Depreciation             |
      | Add button               |

  @collateral_36
  Scenario: Collateral type as ‘Plant & Machinery’
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    And Customer select collateral Type as following details for Plant & Machinery
      | Type | Plant & Machinery |
    When Customer click on edit Address option
    Then System should allowed customer to edit Address

  @collateral_37
  Scenario: Collateral Screen details after adding all collateral
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When Customer successfully add the all collateral details
    Then All added collateral or security list should be displayed on the collateral Screen
    And Total Collateral Worth amount should be displayed
    And All added collateral or security list should have Edit option

  @collateral_38
  Scenario: Product selected is unsecured &  loan amount applied is below 10 lacs
    Given Customer is on Product Details Screen
    When Product selected is unsecured
    And Loan amount applied is below 10 lacs
    Then Collateral capture screen should be skipped

  # Api scenario
  Scenario: LS collateral Dedupe call and return property details
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When Customer collateral type is Property
    Then LS collateral Dedupe should be call
    And return property details

  # Api scenario
  Scenario: LS collateral Dedupe returns multiple records
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When LS collateral Dedupe returns multiple records
    Then Journey goes to RM assistance

  # Api scenario
  Scenario: In case of a match-Property already exists
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When Match is found
    Then System should show the message that this property already exists
    And a request to contact the RM

  # Api scenario
  Scenario: In case of a no match-Property not exists already
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When Match is not found
    Then System should allow customer to proceed

  # Api scenario
  Scenario: CERSAI API will be triggered
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When Collateral type is Property
    Then CERSAI API should be triggered
    And System should verify the added collateral has also been mortgaged/hypothecated/lien marked to any other lender

  # Api scenario
  Scenario: Customer collateral type is combination with  FD and Other
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When Collateral type is combination with FD and Other
    Then Only FD APIs  should be triggered

  # Api scenario
  Scenario: Customer collateral type is combination with  FD and Other
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When Collateral type is combination with Property and Other
    Then Only Property APIs  should be triggered

  # Api scenario
  Scenario: Customer able to add more than one collateral type
    Given Customer is on Collaterals Tab Screen
    And Customer select +Add collateral worth $2cr option
    When Customer click on Add button
    Then Customer should able to add more than one collateral type
