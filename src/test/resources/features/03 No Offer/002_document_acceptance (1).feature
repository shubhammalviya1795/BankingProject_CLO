@DocummentAcceptance
Feature: Post Sanction Document Acceptance

  Scenario: Document Acceptance Page
    Given Customer with following details wish to apply for loan
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    And he has selected pre approved offer 'Overdraft'
    When He sucessfully validates the URC number
    Then he should be navigated to Documnet Acceptance stage
    And should see following document listing
      | Sanction Letter    |
      | Customer Agreement |
    And a indicator of unread against each document

  Scenario Outline: Read and Accept the Documents
    Given Customer with following details is on Document Accetance Page
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    When He clicks on a unread document name '<document>'
    Then He should navigated to the contents of the document
    And option to Accept and Continue

    Examples: 
      | document           |
      | Sanction Letter    |
      | Customer Agreement |

  Scenario: Esign options for properietor
    Given Customer with follwing details have read and accepted the document
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    And he has a contitution of 'PROPERIETOR'
    Then she should see the following esign option
      | Net Banking |
      | NESL        |

  Scenario: Esign options for non properietor
    Given Customer with follwing details have read and accepted the document
      | Name   | Virat and Sons   |
      | Mobile |       9876543228 |
      | PAN    | HBNHD6783H       |
      | URC    | UDYAM-UP-0123456 |
    And he has a contitution of 'PARTNER'
    Then she should see the following esign option
      | NESL |

  Scenario: Customer open sanction letter but not Accept
    Given The Customer is on Saction Letter
    And He close the Saction Letter screen
    Then System will not allow for NetBanking authentication

  Scenario: Customer open Customer Agreement but not Accept
    Given The Customer is on Customer Agreement
    And He close the Customer Agreement screen
    Then System will not allow for NetBanking authentication

  Scenario: Customer download Sanction Letter & Customer Agreement
    Given The Customer is on Document Acceptance Page
    When He select Sanction Letter & Customer Agreement
    Then System Should allow to download Sanction Letter & Customer Agreement

  Scenario: Document Acceptance by Partnership & Solo Prop
    Given The Customer is on Document Acceptance
    When Customer select Sanction Letter & Customer Agreement
    Then eSign authentication is only option for Partnership & solo Prop
