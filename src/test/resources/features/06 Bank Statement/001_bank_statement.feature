Feature: Credit Verification

  Scenario: Select E-Verify
    Given Customer is on Credit Verification page
    When Customer click on E-Verify button
    Then Customer should redirect to Accept Terms and Conditon page
