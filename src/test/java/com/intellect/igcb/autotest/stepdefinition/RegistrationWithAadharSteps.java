package com.intellect.igcb.autotest.stepdefinition;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import com.intellect.igcb.autotest.pageobject.AuthenticationPageObjectWithQueryParam;
import com.intellect.igcb.autotest.utility.DBAccess;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationWithAadharSteps {
	
	AuthenticationPageObjectWithQueryParam authPageObjectWithParam;
	
	@Given("The Customer with following details want to apply for loan")
	public void the_customer_with_following_details_want_to_apply_for_loan(Map<String, String> aUserDetails) {
		authPageObjectWithParam.open(new String[] { aUserDetails.get("Channel"), aUserDetails.get("BusineSegment") });		
		DBAccess.deleteUserRegistrationByMobile(aUserDetails.get("Mobile"));		
		authPageObjectWithParam.waitExplicit(1);
		authPageObjectWithParam.enterInputWithValue("AutheticationPage.mobileNumberInput", aUserDetails.get("Mobile"));
		authPageObjectWithParam.clickOn("AutheticationPage.getOTPButton");
		
	}
	@When("he sucessfully validates with mobile OTP")
	public void he_sucessfully_validates_with_mobile_otp() {
		authPageObjectWithParam.waitExplicit(1);
		for (int i = 0; i < 6; i++) {
			authPageObjectWithParam.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authPageObjectWithParam.clickOn("EnterOTPPage.VerifyOTPBtn");
	}
	@Then("Registration Page is displayed with provision to enter either of following details")
	public void registration_page_is_displayed_with_provision_to_enter_either_of_following_details(List<String> aFields) {		
		authPageObjectWithParam.isDisplayed("AadharRegistration.PanOption");
		authPageObjectWithParam.isDisplayed("AadharRegistration.AadharOption");
		Assert.assertTrue("Aadhar selection does not exists", aFields.contains(authPageObjectWithParam.getTextContents("AadharRegistration.AadharLabel")));
		Assert.assertTrue("Pan selection does not exists", aFields.contains(authPageObjectWithParam.getTextContents("AadharRegistration.PANLabel")));
	}
	
	@Then("Option to Enter Aadhar Field")
	public void option_to_enter_aadhar_field() {
		authPageObjectWithParam.isDisplayed("AadharRegistration.InputField");
	}

}
