package com.intellect.igcb.autotest.stepdefinition;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.intellect.igcb.autotest.pageobject.AuthenticationPageObject;
import com.intellect.igcb.autotest.pageobject.OfferDisplayPageObject;
import com.intellect.igcb.autotest.pageobject.RegistrationWithPANPageObject;
import com.intellect.igcb.autotest.utility.DBAccess;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DisplayOfferSteps {
	
	AuthenticationPageObject authenticationPageObject;
	RegistrationWithPANPageObject registerPanPageObject;
	OfferDisplayPageObject offerDisplayPageObject;
	
	
	
	@Given("Existing Bank Customer with following details is on PAN regsitration page")
	public void existing_bank_customer_with_following_details_is_on_pan_regsitration_page(Map<String, String> userDetails) {
		
		//clean up the data
		DBAccess.deleteUserRegistrationByMobile(userDetails.get("Mobile"));
		
		authenticationPageObject.open();
		authenticationPageObject.waitExplicit(2);
		
		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput",userDetails.get("Mobile"));
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");		
		for(int i=0;i<6;i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp"+i, String.valueOf(i+1));			
		}		
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");
		
		authenticationPageObject.waitExplicit(2);
		registerPanPageObject.isDisplayed("PanRegistration.BusinessPanInput");
		registerPanPageObject.enterInputWithValue("PanRegistration.BusinessPanInput", userDetails.get("PAN"));
		
		//sikp constitution as its already selected
		//authenticationPageObject.waitExplicit(5);		
		//registerPanPageObject.clickOn("PanRegistration.Constitution");		
		//WebElement selectedOtion = registerPanPageObject.getDriver().findElement(By.xpath("//*/li/span[text()='"+userDetails.get("Constitution")+"']"));		
		//registerPanPageObject.clickOn(selectedOtion);
		
		authenticationPageObject.waitExplicit(2);
		registerPanPageObject.enterInputWithValue("PanRegistration.Pincode", userDetails.get("Pincode"));	
		
	}
	
	@Given("his PAN associated with single bank account")
	public void his_pan_associated_with_single_bank_account() {
	    System.out.println("INFO::");
	}
	@Given("He is entitled for an pre approved offer")
	public void he_is_entitled_for_an_pre_approved_offer() {
		 System.out.println("INFO::");
	}
	@When("He Proceed with PAN validation")
	public void he_proceed_with_pan_validation() {
		authenticationPageObject.waitExplicit(2);
		authenticationPageObject.waitExplicit(2);
		registerPanPageObject.isDisplayed("PanRegistration.Next");
		registerPanPageObject.clickOn("PanRegistration.Next");	
	}
	@Then("He should see pre approved offer details")
	public void he_should_see_pre_approved_offer_details() {
		authenticationPageObject.waitExplicit(2);
		offerDisplayPageObject.isDisplayed("PreApprovedOffer.CongratsText");
		offerDisplayPageObject.isDisplayed("PreApprovedOffer.OfferTitle");
	}
	@Then("Option to proceed with pre approved Offer")
	public void option_to_proceed_with_pre_approved_offer() {
		authenticationPageObject.waitExplicit(2);
		offerDisplayPageObject.isDisplayed("PreApprovedOffer.AcceptAndContinueBtn");
	}
	@Then("Option to select for Higher Loan")
	public void option_to_select_for_higher_loan() {
		authenticationPageObject.waitExplicit(2);
		offerDisplayPageObject.isDisplayed("PreApprovedOffer.HigherLoanLink");
	}
	
	//-----------------------------------
	
	@Given("his PAN associated with multiple bank account")
	public void his_pan_associated_with_multiple_bank_account() {
		authenticationPageObject.waitExplicit(2);
	}
	@Then("Multiple Accounts should displayed as list")
	public void multiple_accounts_should_displayed_as_list() {
		offerDisplayPageObject.isDisplayed("AccountSelection.Header");
		offerDisplayPageObject.isDisplayed("AccountSelection.FirstAccount");
		offerDisplayPageObject.isDisplayed("AccountSelection.SecondAccount");
		Assert.assertEquals("First Account is incorrectly listed", "Virat and Sons", authenticationPageObject.getTextContents("AccountSelection.FirstAccountName"));
		Assert.assertEquals("Second Account is incorrectly listed", "Virat Enterprises", authenticationPageObject.getTextContents("AccountSelection.SecondAccountName"));
	}
	@Then("User should have option only to select one account")
	public void user_should_have_option_only_to_select_one_account() {
		offerDisplayPageObject.clickOn("AccountSelection.SecondAccount");
		offerDisplayPageObject.clickOn("AccountSelection.FirstAccount");
		authenticationPageObject.waitExplicit(2);
	}
	
	@Then("display the offer page on selection of the account.")
	public void display_the_offer_page_on_selection_of_the_account() {
		offerDisplayPageObject.clickOn("AccountSelection.NextBtn");
		authenticationPageObject.waitExplicit(2);
		offerDisplayPageObject.isDisplayed("PreApprovedOffer.CongratsText");
		//offerDisplayPageObject.isDisplayed("PreApprovedOffer.OfferTitle");
	}
	//-----------------------------------------------------
	
	@Given("his PAN is not associated with any bank account")
	public void his_pan_is_not_associated_with_any_bank_account() {
	   System.out.println("INFO");
	}
	@Given("His mobile number number is asscoiated to one or more account")
	public void his_mobile_number_number_is_asscoiated_to_one_or_more_account() {
		System.out.println("INFO");
	}
	@Then("Customer Verification Page is displayed")
	public void customer_verification_page_is_displayed() {
		offerDisplayPageObject.isDisplayed("CustomerVerification.Header");
		Assert.assertEquals("Header doesn not match", "How would you like to verify yourself?", offerDisplayPageObject.getTextContents("CustomerVerification.Header"));		
	}
	
	@Then("He is provided an option to verify with")
	public void he_is_provided_an_option_to_verify_with(List<String> aOptions) {
	    String aadharLabel = offerDisplayPageObject.getTextContents("CustomerVerification.AadharLbl");
	    String cifLabel = offerDisplayPageObject.getTextContents("CustomerVerification.CIFLbl");
	    Assert.assertTrue("Aadhar is not present", aOptions.contains(aadharLabel));
	    Assert.assertTrue("Customer Id is not present", aOptions.contains(cifLabel));
	}
	
	
	//----------------------------------------------------
	@When("He enters {string} with {string}")
	public void he_enters_with(String aVerificationParameter, String aParamaterValue) {
	    if(aVerificationParameter.equals("Customer ID")) {
	    	offerDisplayPageObject.isDisplayed("CustomerVerification.InputLabel");
	    	Assert.assertEquals("Label is incorect", "Customer ID", offerDisplayPageObject.getTextContents("CustomerVerification.InputLabel"));
	    	offerDisplayPageObject.enterInputWithValue("CustomerVerification.VerificationInput", aParamaterValue);
	    	offerDisplayPageObject.clickOn("CustomerVerification.Next");
	    }
	    if(aVerificationParameter.equals("Aadhaar")) {
	    	offerDisplayPageObject.clickOn("CustomerVerification.AadharSelectBox");
	    	offerDisplayPageObject.isDisplayed("CustomerVerification.InputLabel");
	    	Assert.assertEquals("Label is incorect", "Aadhaar", offerDisplayPageObject.getTextContents("CustomerVerification.InputLabel"));
	    	offerDisplayPageObject.enterInputWithValue("CustomerVerification.VerificationInput", aParamaterValue);
	    	offerDisplayPageObject.clickOn("CustomerVerification.Next");
	    }
	}
	@Then("He should be navigated to Approved Offers Page")
	public void he_should_be_navigated_to_approved_offers_page() {
		offerDisplayPageObject.isDisplayed("PreApprovedOffer.CongratsText");
	}
	
	//------------------------
	@Then("Follwing {string} Message should be displayed")
	public void follwing_message_should_be_displayed(String aErrorMessage) {		
		Assert.assertEquals("Incorrect Message Displayed", aErrorMessage, offerDisplayPageObject.getTextContents("CustomerVerification.ErrorMessage"));
	}
	
	//------------------------------
	
	@When("he incorrectly enters the CIF number three times")
	public void he_incorrectly_enters_the_cif_number_times() {
	    for(int i=0;i<3;i++) {
	    	authenticationPageObject.waitExplicit(2);
	    	offerDisplayPageObject.enterInputWithValue("CustomerVerification.VerificationInput", String.valueOf((i+1)*10000));	    	
	    }
	}
	@Then("the user is prompted with following error meesage")
	public void the_user_is_prompted_with_following_error_meesage(String docString) {
		Assert.assertEquals("Message doesnt match", docString, offerDisplayPageObject.getTextContents("MaxAttemptPage.Header"));
	}
	@Then("Option to Proceed as non existing Bank customer is provided")
	public void option_to_proceed_as_non_existing_bank_customer_is_provided() {
		Assert.assertEquals("Message doesnt match", "Do you want to continue as a non-HDFC bank customer?", offerDisplayPageObject.getTextContents("MaxAttemptPage.ContinueMsg"));
		offerDisplayPageObject.isDisplayed("MaxAttemptPage.Yes");
		offerDisplayPageObject.isDisplayed("MaxAttemptPage.No");
		
	}

}
