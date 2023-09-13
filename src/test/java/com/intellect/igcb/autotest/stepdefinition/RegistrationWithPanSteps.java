package com.intellect.igcb.autotest.stepdefinition;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.intellect.igcb.autotest.pageobject.AuthenticationPageObject;
import com.intellect.igcb.autotest.pageobject.RegistrationWithPANPageObject;
import com.intellect.igcb.autotest.utility.DBAccess;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class RegistrationWithPanSteps {

	AuthenticationPageObject authenticationPageObject;
	RegistrationWithPANPageObject registerPanPageObject;

	@Given("Customer navigates to portal from any Commercial Segment")
	public void customer_navigates_to_portal_from_any_commercial_segment() {
		authenticationPageObject.open();
	}

	@Given("The Customer with following details is on PAN registration page")
	public void the_customer_with_following_details_is_on_pan_registration_page(Map<String, String> userDetails) {
		String tmpMobileNumber = userDetails.get("Mobile");
		DBAccess.deleteUserRegistrationByMobile(tmpMobileNumber);
		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput",tmpMobileNumber);
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");		
		for(int i=0;i<6;i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp"+i, String.valueOf(i+1));			
		}		
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");
		
	}

	@When("he enters his valid PAN number as {string}")
	public void he_enters_his_valid_pan_number_as(String aPanNumber) {
		registerPanPageObject.isDisplayed("PanRegistration.BusinessPanInput");
		registerPanPageObject.enterInputWithValue("PanRegistration.BusinessPanInput", aPanNumber);
	}

	@Then("He should be displayed following")
	public void he_should_be_displayed_following(Map<String, String> userDetails) {
		registerPanPageObject.isDisplayed("PanRegistration.NameLbl");
		System.out.println("Name Displayed" + registerPanPageObject.getTextContents("PanRegistration.NameLbl"));
		System.out.println("Constitution Selected" + registerPanPageObject.getTextContents("PanRegistration.Constitution"));
	}

	@Then("provison to change Constitution with following values")
	public void provison_to_change_constitution_with_following_values(io.cucumber.datatable.DataTable dataTable) {
		System.out.println(" INFO : The Customerprovison to change Constitution with following values");
		registerPanPageObject.clickOn("PanRegistration.Constitution");		
		WebElement selectedOtion = registerPanPageObject.getDriver().findElement(By.xpath("//*/li/span[text()='HUF']"));		
		registerPanPageObject.clickOn(selectedOtion);		
	}

	@Then("provison to input Pincode")
	public void provison_to_input_pincode() {
		registerPanPageObject.enterInputWithValue("PanRegistration.Pincode", "400071");
		
	}

	@Then("provison to input Employee Code")
	public void provison_to_input_employee_code() {
		registerPanPageObject.enterInputWithValue("PanRegistration.PromoCode", "PROMO12345");
	}
	
	//-----------------------------------------------------
	
	@Given("The New to Bank Customer with following details is on PAN registration page")
	public void the_new_to_bank_customer_with_following_details_is_on_pan_registration_page(Map<String, String> userDetails) {
		
		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput",userDetails.get("Mobile"));
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");		
		for(int i=0;i<6;i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp"+i, String.valueOf(i+1));			
		}		
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");
		
		authenticationPageObject.waitExplicit(2);
		registerPanPageObject.isDisplayed("PanRegistration.BusinessPanInput");
		registerPanPageObject.enterInputWithValue("PanRegistration.BusinessPanInput", userDetails.get("PAN"));
		
		
	}
	@Given("He additionally enters follwing details")
	public void he_additionally_enters_follwing_details(Map<String, String> userDetails) {
		
		registerPanPageObject.enterInputWithValue("PanRegistration.Pincode", userDetails.get("Pincode"));
		registerPanPageObject.clickOn("PanRegistration.Constitution");		
		WebElement selectedOtion = registerPanPageObject.getDriver().findElement(By.xpath("//*/li/span[text()='"+userDetails.get("Constitution")+"']"));		
		registerPanPageObject.clickOn(selectedOtion);
	    
	}
	@Given("does not enter any Employee Code")
	public void does_not_enter_any_employee_code() {
	    System.out.println("INFO");
	    registerPanPageObject.waitExplicit(2);
	}
	@Given("He Proceed further")
	public void he_proceed_further() {
	   
	   registerPanPageObject.clickOn("PanRegistration.Next");
	}
	@Then("He should be sucessfully registered with the Portal")
	public void he_should_be_sucessfully_registered_with_the_portal() {
		//TODO : check in Database
		registerPanPageObject.waitExplicit(2);
	}
	@Then("He should see the Proposal Initiation Page")
	public void he_should_see_the_proposal_initiation_page() {
		//TODO : Pending for Sprint 2
		
	}
	//-------------------------------------------
	
	@Then("He should be displayed with follwing {string}")
	public void he_should_be_displayed_with_follwing(String aError) {
		
		String tmpActualError = registerPanPageObject.getTextContents("PanRegistration.ErrorLbl");
		Assert.assertEquals("Error messages are in correct", aError, tmpActualError);
	    
	}
	
	//-------------------------------------------
	@When("He accidentally enters an incorrect  PAN number as {string}")
	public void he_accidentally_enters_an_incorrect_pan_number_as(String aPan) {
		registerPanPageObject.isDisplayed("PanRegistration.BusinessPanInput");
		registerPanPageObject.enterInputWithValue("PanRegistration.BusinessPanInput", aPan);
	}
	@When("He is displayed  details of other user")
	public void he_is_displayed_details_of_other_user(Map<String, String> userDetails) {
		registerPanPageObject.isDisplayed("PanRegistration.NameLbl");		
		Assert.assertEquals("Names are not matching", userDetails.get("Name"), registerPanPageObject.getTextContents("PanRegistration.NameLbl"));
	}
	@When("He should see an option to change his PAN number")
	public void he_should_see_an_option_to_change_his_pan_number() {
		registerPanPageObject.isDisplayed("PanRegistration.NotyouLink");
		registerPanPageObject.clickOn("PanRegistration.NotyouLink");
	}
	@Then("redirect to PAN Entry page")
	public void redirect_to_pan_entry_page() {
		registerPanPageObject.isDisplayed("PanRegistration.Header");
	}
	
	//---------------------------------------------
	@When("NSDL system is not working")
	public void nsdl_system_is_not_working() {
	   System.out.println("INFO");
	}
	@Then("System should not fetch addtional user details")
	public void system_should_not_fetch_addtional_user_details() {
		System.out.println("INFO");
		//boolean fetchAddtionalDetails = registerPanPageObject.isDisplayed("PanRegistration.NameLbl");
		//Assert.assertFalse("PAN Name should not be displayed", fetchAddtionalDetails);
	}
	
	//-----------------------------------------------
	
	@Given("The Customer with following details has validated his name with PAN number")
	public void the_customer_with_following_details_has_validated_his_name_with_pan_number(Map<String, String> userDetails) {
		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput",userDetails.get("Mobile"));
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");		
		for(int i=0;i<6;i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp"+i, String.valueOf(i+1));			
		}		
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");
		
		authenticationPageObject.waitExplicit(2);
		registerPanPageObject.isDisplayed("PanRegistration.BusinessPanInput");
		registerPanPageObject.enterInputWithValue("PanRegistration.BusinessPanInput", userDetails.get("PAN"));
		
//		authenticationPageObject.waitExplicit(2);		
//		registerPanPageObject.clickOn("PanRegistration.Constitution");		
//		WebElement selectedOtion = registerPanPageObject.getDriver().findElement(By.xpath("//*/li/span[text()='"+userDetails.get("Constitution")+"']"));		
//		registerPanPageObject.clickOn(selectedOtion);
		
		
	}
	@When("he inputs invalid pin code as {string}")
	public void he_inputs_invalid_pin_code_as(String aPincode) {
		registerPanPageObject.enterInputWithValue("PanRegistration.Pincode", aPincode);
		registerPanPageObject.clickOnUsingJS("PanRegistration.Next");
		authenticationPageObject.waitExplicit(2);
	}
	@Then("Follwing error message should be displyed {string}")
	public void follwing_error_message_should_be_displyed(String aErrorMessage) {
		registerPanPageObject.isDisplayed("PanRegistration.PincodeErrLabel");
		String tmpActualError = registerPanPageObject.getTextContents("PanRegistration.PincodeErrLabel");
		Assert.assertEquals("Error messages are incorrect", aErrorMessage, tmpActualError);
		authenticationPageObject.waitExplicit(2);
	}
	
	@Then("pincode will accept value as {string}")
	public void pin_code_will_accept_values_as(String aExpected) {
		
		String tmpPincodeOp = registerPanPageObject.getTextValue("PanRegistration.Pincode");
		Assert.assertEquals("Value does not match", aExpected, tmpPincodeOp);
		
	}
	
	@Then("pincode will accept value as  {string}")
	public void pincode_will_accept_value_as(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("Next Button Enabled is {string}")
	public void next_button_is_disabled(String aBtnState) {
	    Assert.assertEquals("Next Button is not in proper state", 
	    		Boolean.valueOf(aBtnState).booleanValue(),registerPanPageObject.isEnabled("PanRegistration.Next"));
	}
	
	//----------------------------------------------
	@When("he provided pincode as {string}")
	public void he_provided_pincode_as(String aPincode) {
		registerPanPageObject.enterInputWithValue("PanRegistration.Pincode", aPincode);
		registerPanPageObject.clickOnUsingJS("PanRegistration.Next");
		authenticationPageObject.waitExplicit(2);
	}
	@When("there is no branch assiciated with this pincode")
	public void there_is_no_branch_assiciated_with_this_pincode() {
	    System.out.println("INFO Step");
	}
	@Then("System should allow to proceed this user with further registration")
	public void system_should_allow_to_proceed_this_user_with_further_registration() {
		String tmpActualError = registerPanPageObject.getTextContents("PanRegistration.PincodeErrLabel");
		Assert.assertEquals("Error messages are incorrect", "Invalid Pincode", tmpActualError);
	}
}
