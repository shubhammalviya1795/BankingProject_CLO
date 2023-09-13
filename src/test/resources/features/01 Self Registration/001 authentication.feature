#Author: aiswarya.nimbalkar@qualitykoisk.com, anand.sawant@intellectdesign.com
@Authentication
Feature: Authentication
  As a prospective SME Loan Customer
  I would like to login with valid mobile number
  So that i can get access to SME Portal for my loan application
  
	
  Scenario: Portal Landing Page
    Given The Customer want to apply for a loan
    When He access the Landing Page of Portal
    Then He should be able to view HDFC Advertisemnt Banner
    And Able to view detailed advertisement on clicking 'read more' option
    And Input for Mobile Number prefixed with '+91'
    And Option for 'Get OTP'
    And Message indicating
      """
      By entering your mobile number, you agree to the T & C and privacy policy..
      """

  Scenario: More Advertisement Details
    Given Customer is on Portal Landing Page
    When he clicks on 'read more' link on adverstisement banner
    Then he should see new page with more details of advertisement

  Scenario Outline: Record Source Channel and Segment
    Given Prospective Customer navigates to Portal using follwing '<channel>' and '<segment>'
    When He sucessfully validates using '<mobile>' number and OTP
    Then System should record the '<channel>' and '<segment>' againts the '<mobile>' to be used in BRE in further processing of application

    Examples: 
      | mobile     | channel | segment |
      | 9988786780 | BANK    | BBG     |
      | 9898675645 | BANK    | AGRI    |

  Scenario: Default Channel and Segment
    When Prospective Customer navigates to Portal directly (without any redirection)
    Then Default Channel will be set to 'BANK'
    And Default Segment will be set to 'BBG'

  Scenario Outline: Invalid Mobile Number
    Given Customer is on Portal Landing Page
    When He provides a Invalid '<mobile>' Number
    Then Customer should be provided with follwing error warning '<error>'

    Examples: 
      | mobile      | error                    |
      |       01234 | Please enter valid data. |
      | 01123112345 | Please enter valid data. |
      | abcde12345  | Please enter valid data. |
      |             | Value cannot be empty    |

  Scenario: Display Terms & Condition and Privacy Policy
    Given Customer is on Portal Landing Page
    And He has seen the message for T & C and Privacy Policy
    When He provides a valid Mobile Number
    And Tries to Get OTP
    Then First Terms and Conditions will be displayed in details
    And followed by Privacy Policy in details on subsequent page.

  Scenario: Accept T&C and Privacy Policy
    Given Customer has provided valid mobile number
    And has viewed and accepted T&C
    And has viewed and accepted Privacy Policy
    Then OTP will be triggered
    And Message will be displayed indicating last 4 digits of mobile entered
    And OTP Prompt with 6 digits will be displayed
    And Option to Resend OTP will be displayed in non editable mode
    And Decremental Time counter for Resend OTP from 30 seconds will be displayed

  Scenario: Re-enter Mobile Number
    Given the Customer has entered a incorrect mobile number
    And He is on OTP prompt
    Then He should be provided with option to change his number
    And He should be redirected back to Mobile Entry Landing Page

  Scenario: Login with valid OTP
    Given The Customer has received the SMS with OTP
    And He is on OTP Prompt Page
    When He enters the correct OTP
    Then He should be redirected to Input PAN number page

  @Manual
  Scenario: Resend OTP
    Given The customer is on OTP prompt
    When Customer doesnt receives any OTP message due to network issues
    Then after 30 seconds Resend OTP option should be enabled
    And He can click on Resend OTP
    And New OTP should be triggered on same mobile number

  @Manual
  Scenario: Invalid OTP Entry
    Given The Customer is on the OTP Screen
    When He enters a wrong OTP for 3 times
    #Q: upto what time limit the user should be bloacked start the validation again
    Then Customer will be shown 'Thank You please try later'

  @Manual
  Scenario: Auto fill of OTP from SMS on Mobile Device
    Given Customer login using valid mobile number
    And He is on OTP Entry Screen
    When He clicks on from the message with OTP Number
    Then OTP should be auto fill with the prompted OTP message

  @Manual
  Scenario: OTP Timeout
    Given User is on OTP Screen
    #Please specify the validity of the OTP and application to wait
    And he doesnt enter the OTP untill XX seconds
    Then OTP validation should fail

  @Manual
  Scenario: OTP once expired
    Given Customer has provided mobile number
    And generated the OTP
    But He doesnt go further with OTP validation
    And After some time he again enters the mobile number
    When He enter the previous session OTP
    Then System should not allow user to proceed further

  @Manual
  Scenario Outline: Resumption with already registered customer
    Given Customer has already registered in CLO with following details
      | Name   | Mr Ramachandra Kumaraswamy |
      | Mobile |                 9928365278 |
      | PAN    | AGTH87650                  |
    And He has dropped out from <dropped page>
    When He tries to login again with same mobile number
    Then He should be redirected to <resuming page>

    Examples: 
      | dropped page          | resuming page         |
      | Offer Detail          | Offer Detail          |
      | Customer Verification | Customer Verification |
      | Proposal Initiation   | Proposal Initiation   |
