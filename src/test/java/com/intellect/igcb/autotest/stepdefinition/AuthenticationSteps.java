package com.intellect.igcb.autotest.stepdefinition;

import static org.junit.Assert.fail;

import org.junit.Assert;

import com.intellect.igcb.autotest.model.UserRegistration;
import com.intellect.igcb.autotest.pageobject.AuthenticationPageObject;
import com.intellect.igcb.autotest.pageobject.AuthenticationPageObjectWithQueryParam;
import com.intellect.igcb.autotest.utility.DBAccess;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;

public class AuthenticationSteps {

	AuthenticationPageObject authPageObject;
	AuthenticationPageObjectWithQueryParam authPageObjectWithParam;

	@Given("The Customer want to apply for a loan")
	public void the_customer_want_to_apply_for_a_loan() {
		//informational text
	}

	@When("He access the Landing Page of Portal")
	public void he_access_the_landing_page_of_portal() {
		authPageObject.open();
	}

	@Then("He should be able to view HDFC Advertisemnt Banner")
	public void he_should_be_able_to_view_hdfc_advertisemnt_banner() {
		if (!authPageObject.isDisplayed("AutheticationPage.advertismentBanner")) {
			fail("Adevrtisemnt Banner is not displayed");
		}
	}

	@Then("Able to view detailed advertisement on clicking {string} option")
	public void able_to_view_detailed_advertisement_on_clicking_option(String aLinkText) {
		if (!authPageObject.isDisplayed("AutheticationPage.readMoreLink")) {
			fail("Readmore link not displayed");
		}
		Assert.assertEquals("Link text is not equal", aLinkText, authPageObject.getTextContents("AutheticationPage.readMoreLink"));
		
	}

	@Then("Input for Mobile Number prefixed with {string}")
	public void input_for_mobile_number_prefixed_with(String aMobileText) {
		if (!authPageObject.isDisplayed("AutheticationPage.mobileNumberPrefix")) {
			fail("prefix not found");
		}
		Assert.assertEquals("Mobile text is not equal", aMobileText, authPageObject.getTextContents("AutheticationPage.mobileNumberPrefix"));

	}

	@Then("Option for {string}")
	public void option_for(String aBtnText) {
		if (!authPageObject.isDisplayed("AutheticationPage.getOTPButton")) {
			fail("Get OTP Option not found");
		}
		Assert.assertEquals("OTP Btn text is not equal", aBtnText, authPageObject.getTextContents("AutheticationPage.getOTPButton"));
	}

	@Then("Message indicating")
	public void message_indicating(String docString) {
		if (!authPageObject.isDisplayed("AutheticationPage.TCAndPPMsg")) {
			fail("Message indicating not found");
		}
		String tmpText = authPageObject.getTextContents("AutheticationPage.TCAndPPMsg");		
		Assert.assertEquals("text is not equal", docString, tmpText.trim());
	}

	@Given("Customer is on Portal Landing Page")
	public void customer_is_on_portal_landing_page() {
		authPageObject.open();
		
	}

	@When("he clicks on {string} link on adverstisement banner")
	public void he_clicks_on_link_on_adverstisement_banner(String string) {
		authPageObject.clickOn("AutheticationPage.readMoreLink");
	}

	@Then("he should see new page with more details of advertisement")
	public void he_should_see_new_page_with_more_details_of_advertisement() {
		//throw new PendingException("More Advertisement Details are yet to be provided");
		//TODO --> Will be captured when more details are provided
	}

	@When("He provides a Invalid {string} Number")
	public void he_provides_a_invalid_number(String aMobile) {
		authPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", aMobile);
		authPageObject.clickOn("AutheticationPage.getOTPButton");
	}

	@Then("Customer should be provided with follwing error warning {string}")
	public void customer_should_be_provided_with_follwing_error_warning(String aExpectedError) {
		String actualErrorMessage = authPageObject.getTextContents("AutheticationPage.errorMessage");
		Assert.assertEquals("Error Messages are not equal", aExpectedError, actualErrorMessage);
		

	}

	@Given("He has seen the message for T & C and Privacy Policy")
	public void he_has_seen_the_message_for_t_c_and_privacy_policy() {
		if (!authPageObject.isDisplayed("AutheticationPage.TCAndPPMsg")) {
			fail("Message For TC and PrivacyPolicy not found");
		}
	}

	@When("He provides a valid Mobile Number")
	public void he_provides_a_valid_mobile_number() {
		// TODO: Verify if this step is required
	}

	@When("Tries to Get OTP")
	public void tries_to_get_otp() {
		// TODO: Verify if this step is required
	}

	@Then("First Terms and Conditions will be displayed in details")
	public void first_terms_and_conditions_will_be_displayed_in_details() {
		if (!authPageObject.isDisplayed("AutheticationPage.termCondLink")) {
			fail("Terms and Condition Link not found");
		}
		if (!authPageObject.isDisplayed("AutheticationPage.privacyPolicyLink")) {
			fail("Privacy Policy Link not found");
		}
		authPageObject.clickOn("AutheticationPage.termCondLink");
		authPageObject.isDisplayed("AutheticationPage.TermsAndcondition");
		String tmpText = authPageObject.getTextContents("AutheticationPage.TermsAndcondition");
		Assert.assertEquals("T&C is not displayed", "Terms & Condition", tmpText);

	}

	@Then("followed by Privacy Policy in details on subsequent page.")
	public void followed_by_privacy_policy_in_details_on_subsequent_page() {
		
		//authPageObject.clickOn("OkcookieBtn");
		//authPageObject.waitExplicit(2);	
		
		authPageObject.clickOn("AutheticationPage.NextButton");
		String tmpText = authPageObject.getTextContents("AutheticationPage.TermsAndcondition");
		Assert.assertEquals("Privacy Policy not displayed", "Privacy Policy", tmpText);
	}

	// -----------------------------------
	@Given("Customer has provided valid mobile number")
	public void customer_has_provided_valid_mobile_number() {
		authPageObject.open();
		authPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", "9869786756");

	}

	@Given("has viewed and accepted T&C")
	public void has_viewed_and_accepted_t_c() {		
		//authPageObject.clickOn("OkcookieBtn");
		authPageObject.waitExplicit(2);				
		authPageObject.clickOn("AutheticationPage.termCondLink");

	}

	@Given("has viewed and accepted Privacy Policy")
	public void has_viewed_and_accepted_privacy_policy() {
		authPageObject.clickOn("AutheticationPage.NextButton");
		authPageObject.clickOn("AutheticationPage.NextButton");
	}

	@Then("OTP will be triggered")
	public void otp_will_be_triggered() {
		//TODO: Check if this step is required
		authPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", "9869786756");
		authPageObject.clickOn("AutheticationPage.getOTPButton");
	}

	@Then("Message will be displayed indicating last {int} digits of mobile entered")
	public void message_will_be_displayed_indicating_last_digits_of_mobile_entered(Integer int1) {
		String tmpSentToNumber = authPageObject.getTextContents("EnterOTPPage.SentToNumber");
		Assert.assertEquals("Mobile Number Message Sent is Incorrecr", "XXXXXX6756", tmpSentToNumber);
	}

	@Then("OTP Prompt with {int} digits will be displayed")
	public void otp_prompt_with_digits_will_be_displayed(Integer int1) {

		for (int i = 0; i < int1.intValue(); i++) {
			if (!authPageObject.isDisplayed("EnterOTPPage.otp" + i)) {
				fail("OTP Input not available");
			}
		}
	}

	@Then("Option to Resend OTP will be displayed in non editable mode")
	public void option_to_resend_otp_will_be_displayed_in_non_editable_mode() {
		if (!authPageObject.isDisplayed("EnterOTPPage.ResendOTPMsg")) {
			fail("OTP Input not available");
		}
	}

	@Then("Decremental Time counter for Resend OTP from {int} seconds will be displayed")
	public void decremental_time_counter_for_resend_otp_from_seconds_will_be_displayed(Integer int1) {
		String tmpTimerText = authPageObject.getTextContents("EnterOTPPage.ResendOTPMsg");
		Serenity.recordReportData().asEvidence().withTitle("Decremental Time Counter Text").andContents(tmpTimerText);
	}

	// -------------------------------------
	@Given("the Customer has entered a incorrect mobile number")
	public void the_customer_has_entered_a_incorrect_mobile_number() {
		authPageObject.open();
		authPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", "9869786756");
		authPageObject.clickOn("AutheticationPage.getOTPButton");
	}

	@Given("He is on OTP prompt")
	public void he_is_on_otp_prompt() {
		if (!authPageObject.isDisplayed("EnterOTPPage.EnterOTPLabel")) {
			fail("OTP_PromptPage not found");
		}
	}

	@Then("He should be provided with option to change his number")
	public void he_should_be_provided_with_option_to_change_his_number() {
		if (!authPageObject.isDisplayed("EnterOTPPage.ChangeNumberLink")) {
			fail("EnterOTPPage.ChangeNumberLink not found");
		}
		authPageObject.clickOn("EnterOTPPage.ChangeNumberLink");
	}

	@Then("He should be redirected back to Mobile Entry Landing Page")
	public void he_should_be_redirected_back_to_mobile_entry_landing_page() {
		if (!authPageObject.isDisplayed("AutheticationPage.mobileNumberInput")) {
			fail("MobileEntry Page not found");
		}
	}
	// -----------------------------------------------------

	@Given("Prospective Customer navigates to Portal using follwing {string} and {string}")
	public void prospective_customer_navigates_to_portal_using_follwing(String aChannel, String aSegment) {
		System.out.println("Channel = " + aChannel);
		System.out.println("Segment = " + aSegment);
		authPageObjectWithParam.open(new String[] { aChannel, aSegment });
	}

	@When("He sucessfully validates using {string} number and OTP")
	public void he_sucessfully_validates_using_number_and_otp(String aMobile) {
		DBAccess.deleteUserRegistrationByMobile(aMobile);
		System.out.println("aMobile = " + aMobile);
		authPageObjectWithParam.waitExplicit(1);
		authPageObjectWithParam.enterInputWithValue("AutheticationPage.mobileNumberInput", aMobile);
		authPageObjectWithParam.clickOn("AutheticationPage.getOTPButton");
		authPageObjectWithParam.waitExplicit(5);
		for (int i = 0; i < 6; i++) {
			authPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");

	}

	@Then("System should record the {string} and {string} againts the {string} to be used in BRE in further processing of application")
	public void system_should_record_the_channel_and_segment_againts_the_mobile_number_to_be_used_in_bre_in_further_processing_of_application(
			String aChn, String seg, String mob) {
		UserRegistration user = DBAccess.findUserRegistrationByMobile(mob);
		Assert.assertNotNull("No users created", user);
		Assert.assertEquals("Channel saved is incorrect", aChn, user.getChannel());
		Assert.assertEquals("Business Segment saved is incorrect", seg, user.getBusinessSegment());
	}

	// --------------------------------------------------------
	@When("Prospective Customer navigates to Portal directly \\(without any redirection)")
	public void prospective_customer_navigates_to_portal_directly_without_any_redirection() {
		DBAccess.deleteUserRegistrationByMobile("7676898989");
		authPageObject.open();
		authPageObject.waitExplicit(1);
		authPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", "7676898989");
		authPageObject.clickOn("AutheticationPage.getOTPButton");
		authPageObject.waitExplicit(1);
		for (int i = 0; i < 6; i++) {
			authPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");
	}

	@Then("Default Channel will be set to {string}")
	public void default_channel_will_be_set_to(String aChn) {
		UserRegistration user = DBAccess.findUserRegistrationByMobile("7676898989");
		Assert.assertNotNull("No users created", user);
		Assert.assertEquals("Channel saved is incorrect", aChn, user.getChannel());

	}

	@Then("Default Segment will be set to {string}")
	public void default_segment_will_be_set_to(String seg) {
		UserRegistration user = DBAccess.findUserRegistrationByMobile("7676898989");
		Assert.assertNotNull("No users created", user);
		Assert.assertEquals("Business Segment saved is incorrect", seg, user.getBusinessSegment());
	}

	// ----------------------------------------------------------

	@Given("The Customer has received the SMS with OTP")
	public void the_customer_has_received_the_sms_with_otp() {
		System.out.println("INFO : The Customer has received the SMS with OTP");
		authPageObject.open();
		authPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", "9876543237");
		authPageObject.clickOn("AutheticationPage.getOTPButton");
	}

	@Given("He is on OTP Prompt Page")
	public void he_is_on_otp_prompt_page() {
		authPageObject.isDisplayed("EnterOTPPage.EnterOTPLabel");
	}

	@When("He enters the correct OTP")
	public void he_enters_the_correct_otp() {
		for (int i = 0; i < 6; i++) {
			authPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");
	}

	@Then("He should be redirected to Input PAN number page")
	public void he_should_be_redirected_to_input_pan_number_page() {
		authPageObject.isDisplayed("PanRegistration.Header");
	}

}
