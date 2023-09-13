package com.intellect.igcb.autotest.stepdefinition;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.intellect.igcb.autotest.pageobject.AuthenticationPageObject;
import com.intellect.igcb.autotest.pageobject.OfferDisplayPageObject;
import com.intellect.igcb.autotest.pageobject.RegistrationWithPANPageObject;
import com.intellect.igcb.autotest.pageobject.URCRegistartionPageObject;
import com.intellect.igcb.autotest.utility.DBAccess;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class URCRegistartionSteps {

	AuthenticationPageObject authenticationPageObject;
	URCRegistartionPageObject urcregistartionPageObject;
	OfferDisplayPageObject offerDisplayPageObject;
	RegistrationWithPANPageObject registerPanPageObject;

	// Scenario 1 URC Input Page for Pre Approved Offer
	@Given("Customer with following details is on Pre Approved Offer Page")
	public void customer_with_following_details_is_on_pre_approved_offer_page(Map<String, String> userDetails) {
		String tmpMobileNumber = userDetails.get("Mobile");
		DBAccess.deleteUser(tmpMobileNumber);
		DBAccess.executeSqlFile("offer_details_setup.sql");
		

		authenticationPageObject.open();
		authenticationPageObject.waitExplicit(2);

		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", tmpMobileNumber);
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");
		authenticationPageObject.waitExplicit(2);
		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");

	}

	@When("He Accepts the Offer")
	public void he_accepts_the_offer() {

		offerDisplayPageObject.clickOn("PreApprovedOffer.AcceptAndContinueBtn");
	}

	@Then("{string} page is displayed with following details")
	public void page_is_displayed_with_following_details(String string, List<String> dataTable) {
		urcregistartionPageObject.isDisplayed("URC.Title");
		urcregistartionPageObject.isDisplayed("URC.NumberLabel");
		urcregistartionPageObject.isDisplayed("URC.udyamNumber");
		urcregistartionPageObject.isDisplayed("URC.udyamSubmit");
		urcregistartionPageObject.isDisplayed("URC.donthaveurcLink");
		urcregistartionPageObject.isDisplayed("URC.udyamCreate");

	}

	// Scenario 2 URC Input Page for Higer Loan Request
	@When("He clicks on the Higher Loan Link")
	public void he_clicks_on_the_higher_loan_link() {
		offerDisplayPageObject.clickOn("PreApprovedOffer.HigherLoanLink");

	}

	// Scenario 3 URC Input Page for NTB Customer
	@Given("NTB Customer with following details has validated his PAN")
	public void ntb_customer_with_following_details_has_validated_his_pan(Map<String, String> userDetails) {
		DBAccess.deleteUser("9876543226");
		authenticationPageObject.open();
		authenticationPageObject.waitExplicit(1);
		String tmpMobileNumber = userDetails.get("Mobile");
		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", tmpMobileNumber);
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");

		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");

		authenticationPageObject.waitExplicit(1);
		registerPanPageObject.isDisplayed("PanRegistration.BusinessPanInput");
		registerPanPageObject.enterInputWithValue("PanRegistration.BusinessPanInput", userDetails.get("PAN"));
		authenticationPageObject.waitExplicit(1);
		registerPanPageObject.enterInputWithValue("PanRegistration.Pincode", userDetails.get("Pincode"));
		registerPanPageObject.clickOn("PanRegistration.Next");
		authenticationPageObject.waitExplicit(1);

	}

	// Scenario 4 URC Number with Incorect Value

	@Given("Customer with following details is on URC Page")
	public void customer_with_following_details_is_on_urc_page(Map<String, String> userDetails) {
		String tmpMobileNumber = userDetails.get("Mobile");
		DBAccess.deleteUser(tmpMobileNumber);
		DBAccess.executeSqlFile("urc_stage_setup.sql");

		authenticationPageObject.open();
		authenticationPageObject.waitExplicit(2);

		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", tmpMobileNumber);
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");
		authenticationPageObject.waitExplicit(2);
		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");
		urcregistartionPageObject.waitExplicit(2);
		urcregistartionPageObject.isDisplayed("URC.udyamNumber");

	}

	@Given("The Customer is on URC Number Page")
	public void the_customer_is_on_urc_number_page() {

		urcregistartionPageObject.isDisplayed("URC.udyamNumber");
	}

	@When("He enter incorrect URC Number {string}")
	public void he_enter_incorrect_urc_number(String aURC) {
		urcregistartionPageObject.enterInputWithValue("URC.udyamNumber", aURC);
		urcregistartionPageObject.clickOn("URC.udyamSubmit");
	}

	@Then("System should prompt to {string}")
	public void system_should_prompt_to(String errorMessage) {

		String actualMessage = urcregistartionPageObject.getTextContents("URC.errorMessage");
		Assert.assertEquals("ErrorMessage are not same", errorMessage, actualMessage);
	}

	// Scenario 5 URC number not registered as micro entity for ETB customer
	@Given("Customer with following details is on URC Input Page")
	public void customer_with_following_details_is_on_urc_input_page(Map<String, String> userDetails) {
		String tmpMobileNumber = userDetails.get("Mobile");
		DBAccess.deleteUser(tmpMobileNumber);
		DBAccess.executeSqlFile("urc_stage_setup.sql");

		authenticationPageObject.open();

		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", tmpMobileNumber);
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");

		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");
		urcregistartionPageObject.waitExplicit(2);
		urcregistartionPageObject.clickOn("PreApprovedOffer.AcceptAndContinueBtn");

		String tmpURCNumber = userDetails.get("URC");

		urcregistartionPageObject.enterInputWithValue("URC.udyamNumber", tmpURCNumber);

	}

	@When("He submits the URC number")
	public void he_submits_the_urc_number() {
		urcregistartionPageObject.clickOn("URC.udyamSubmit");
	}

	@When("his URC is registered with Medium Enterprises")
	public void his_urc_is_registered_with_medium_enterprises() {
		//INFO
		System.out.println("URC is not registered with Medium Enterprises");
		urcregistartionPageObject.waitExplicit(2);

	}

	@Then("He should see the following message")
	public void he_should_see_the_following_message(String docString) {
		
		String tmpUdyamMsg = urcregistartionPageObject.getTextContents("URC.udyamMessage");

		Assert.assertEquals("Expected Msg are not equal", docString, tmpUdyamMsg);
	}

	// Scenario 6 URC Number Name mismatch
	@Given("Customer with following details is on URC Input Page for ETB customer")
	public void customer_with_following_details_is_on_urc_input_page_for_etb_customer(Map<String, String> userDetails) {
		String tmpMobileNumber = userDetails.get("Mobile");
		DBAccess.deleteUser(tmpMobileNumber);
		DBAccess.executeSqlFile("offer_details_setup.sql");
		
		
		authenticationPageObject.open();

		
		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", tmpMobileNumber);
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");

		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");

		urcregistartionPageObject.clickOn("PreApprovedOffer.AcceptAndContinueBtn");

		String tmpURCNumber = userDetails.get("URC");

		urcregistartionPageObject.enterInputWithValue("URC.udyamNumber", tmpURCNumber);
	}

	@When("his name match is only {int}%")
	public void his_name_match_is_only(Integer int1) {

		System.out.println("name miss match is less than 80% found");
	}

	@Then("System should display the following message")
	public void system_should_display_the_following_message(String docString) {
		
		String tmpMsg = urcregistartionPageObject.getTextContents("URC.seemsLikeNameMismatch");
		Assert.assertEquals("Msg are not equal", docString, tmpMsg);
		
	}

	// Scenario 7 URC number not registered as micro entity for NTB Customer
	@Given("NTB Customer with following details is on URC Input Page")
	public void ntb_customer_with_following_details_is_on_urc_input_page(Map<String, String> userDetails) {
		String tmpMobileNumber = userDetails.get("Mobile");
		DBAccess.deleteUser(tmpMobileNumber);
		DBAccess.executeSqlFile("urc_stage_setup.sql");
		
		authenticationPageObject.open();

		
		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", tmpMobileNumber);
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");

		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");

		urcregistartionPageObject.clickOn("PreApprovedOffer.AcceptAndContinueBtn");

		String tmpURCNumber = userDetails.get("URC");

		urcregistartionPageObject.enterInputWithValue("URC.udyamNumber", tmpURCNumber);

	}

	@Then("He should be navigated to Loan Details Page")
	public void he_should_be_navigated_to_loan_details_page() {

		urcregistartionPageObject.isDisplayed("LoanDetails.Loan_Details_Title");

	}

	// Scenario 8 URC Number Name mismatch for NTB Customer
	// duplicate steps Scenario 7 and 8

	// Scenario 9 Skip URC Registration
	@When("He clicks on Skip the URC Link")
	public void he_clicks_on_skip_the_urc_link() {

		urcregistartionPageObject.clickOn("URCRegistration.SkipThisStep");

	}

	// Scenario 10 Skip URC by continuing without Offer
	@Given("Customer URC is not registered as Micro enterpirse")
	public void customer_urc_is_not_registered_as_micro_enterpirse() {

		if (!urcregistartionPageObject.isDisplayed("URC.udyamMessage")) {
			fail("micro entrity Message not found");
		}

	}

	@Given("He has pre approved offer")
	public void he_has_pre_approved_offer() {

		offerDisplayPageObject.isDisplayed("PreApprovedOffer.OfferTitle");

	}

	@Given("See the Error message {string}")
	public void see_the_error_message(String aURCNumber) {

		if (!urcregistartionPageObject.isDisplayed("URC.notRegisteredTitle")) {
			fail("Error Message not found");
		}

	}

	@When("He Clicks on Continue without Offer")
	public void he_clicks_on_continue_without_offer() {

		urcregistartionPageObject.clickOn("URC.udyamContinueWithoutOffer");

	}

	// Scenario 11 Maximum attempt for Inavlid URC entry
	@When("he enter the Invalid URC number for {int} times")
	public void he_enter_the_invalid_urc_number_for_times(Integer int1) {
		
		for(int i=0;i<3;i++) {
			urcregistartionPageObject.enterInputWithValue("URC.udyamNumber", "INVAL-ID-2828282882");
			urcregistartionPageObject.clickOn("URC.udyamSubmit");
			urcregistartionPageObject.waitExplicit(5);
		}

	}

	@Then("He should be displayed follwing error message")
	public void he_should_be_displayed_follwing_error_message(String docString) {
		String msg = urcregistartionPageObject.getTextContents("URC.MaxAttemptMsg");
		Assert.assertEquals("Msg not same", docString, msg);

	}

	// Scenario 12 and 13 step is duplicate that's by not visible

	// Scenario 14 Display multiple pre approved offer

	@Given("The Customer is on Offer Page")
	public void the_customer_is_on_offer_page() {
		authenticationPageObject.open();

		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", "9876543228");
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");

		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");

		urcregistartionPageObject.clickOn("PreApprovedOffer.AcceptAndContinueBtn");

	}

	@When("He click on Accept & Continue button")
	public void he_click_on_accept_continue_button() {

		urcregistartionPageObject.clickOn("PreApprovedOffer.AcceptAndContinueBtn");

	}

	@Then("other offers become disabled")
	public void other_offers_become_disabled() {
		System.out.println("other offers become disabled");

	}

	// Scenario 15 Customer Name Not Match & he click on Change
	@Given("The Customer is on Name mismatch page")
	public void the_customer_is_on_name_mismatch_page() {

		authenticationPageObject.open();

		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", "9876543228");
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");

		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");

		urcregistartionPageObject.clickOn("PreApprovedOffer.AcceptAndContinueBtn");

		urcregistartionPageObject.enterInputWithValue("URC.udyamNumber", "UDYAM-UP-0123456");

		urcregistartionPageObject.clickOn("URC.udyamSubmit");

	}

	@When("He click on Change")
	public void he_click_on_change() {

		urcregistartionPageObject.clickOn("URC.udyamChange");

	}

	@Then("System should redirected to URC Number Page")
	public void system_should_redirected_to_urc_number_page() {

		urcregistartionPageObject.isDisplayed("URC.udyamNumber");

	}

	// Scenario: 16 Customer don't have URC Number // add
	@Given("The Customer is on URC Number")
	public void the_customer_is_on_urc_number() {

		authenticationPageObject.open();

		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", "9876543228");
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");

		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");

		urcregistartionPageObject.clickOn("PreApprovedOffer.AcceptAndContinueBtn");

	}

	@When("Customer click on dont have URC Video option")
	public void customer_click_on_dont_have_urc_video_option() {

		urcregistartionPageObject.clickOn("URC.udyamGuide");

	}

	@Then("System should redirected video of how to register for URC")
	public void system_should_redirected_video_of_how_to_register_for_urc() {

		System.out.println("System should redirected video of how to register for URC");
	}

	// Scenario 17 Skip URC Number
	@When("He Enter URC Number")
	public void he_enter_urc_number() {

		urcregistartionPageObject.enterInputWithValue("URC.udyamNumber", "UDYAM-UP-0123456");

		urcregistartionPageObject.clickOn("URC.udyamSubmit");

	}

	@When("Name match from URC and from PAN is less than {int}%") // Need to discuss with Sir
	public void name_match_from_urc_and_from_pan_is_less_than(Integer int1) {

	}

	@When("Customer doesn't Skip URC") // Need to discuss with Sir
	public void customer_doesn_t_skip_urc() {

	}

	@Then("System redicted to URC Acceptance Page")
	public void system_redicted_to_urc_acceptance_page() {

		urcregistartionPageObject.isDisplayed("URC.udyamNumber");

	}

	// Manual Check
	// Scenario 18 Trigger API after completing Customer Information
	@Given("Customer is on Customer Information Page")
	public void customer_is_on_customer_information_page() {

	}

	@When("Customer completed all filled data")
	public void customer_completed_all_filled_data() {

	}

	@Then("System trigger all the following API as")
	public void system_trigger_all_the_following_api_as(io.cucumber.datatable.DataTable dataTable) {

	}

}
