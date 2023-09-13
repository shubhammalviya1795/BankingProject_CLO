package com.intellect.igcb.autotest.stepdefinition;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.intellect.igcb.autotest.pageobject.ApplicantDetailsPageObject;
import com.intellect.igcb.autotest.pageobject.AuthenticationPageObject;
import com.intellect.igcb.autotest.pageobject.LoanDetailsPageObject;
import com.intellect.igcb.autotest.pageobject.OfferDisplayPageObject;
import com.intellect.igcb.autotest.pageobject.RegistrationWithPANPageObject;
import com.intellect.igcb.autotest.pageobject.URCRegistrationPageObject;
import com.intellect.igcb.autotest.utility.DBAccess;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ApplicantDetailsStep {

	ApplicantDetailsPageObject applicantDetailsPageObject;
	URCRegistrationPageObject urcregistartionPageObject;
	OfferDisplayPageObject offerDisplayPageObject;
	RegistrationWithPANPageObject registerPanPageObject;
	LoanDetailsPageObject loanDetailsPageObject;
	AuthenticationPageObject authenticationPageObject;
	AuthenticationPageObject authPageObject;

	// Scenario 1 Display Entity Details
	@Given("Customer with following details is on Loan Application Details")
	public void customer_with_following_details_is_on_loan_application_details(Map<String, String> userDetails) {
		String tmpMobileNumber = userDetails.get("Mobile");
		DBAccess.deleteUser(tmpMobileNumber);
		DBAccess.executeSqlFile("applicant_details_setup.sql");

		authPageObject.open();

		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", tmpMobileNumber);
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");

		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");

	}

	@When("He Moves Next")
	public void he_moves_next() {
		loanDetailsPageObject.clickOn("LoanDetails.Next_Button");
		loanDetailsPageObject.waitExplicit(3);
	}

	@Then("Entity Details page is displayed with following details")
	public void entity_details_page_is_displayed_with_following_details(Map<String, String> userDetails) {

		applicantDetailsPageObject.isDisplayed("EntityScreen.businessName");
		String actualData = applicantDetailsPageObject.getTextValue("EntityScreen.businessName");
		Assert.assertEquals("Business_Name is not matched", userDetails.get("Business_Name"), actualData);

		applicantDetailsPageObject.isDisplayed("EntityScreen.email");
		actualData = applicantDetailsPageObject.getTextValue("EntityScreen.email");
		Assert.assertEquals("Email is not matched", userDetails.get("Email"), actualData);

		applicantDetailsPageObject.isDisplayed("EntityScreen.industry");
		actualData = applicantDetailsPageObject.getTextContents("EntityScreen.industry");
		Assert.assertEquals("Industry is not matched", userDetails.get("Industry"), actualData);

		applicantDetailsPageObject.isDisplayed("EntityScreen.subIndustry");
		actualData = applicantDetailsPageObject.getTextContents("EntityScreen.subIndustry");
		Assert.assertEquals("Sub_Industry is not matched", userDetails.get("Sub_Industry"), actualData);

		applicantDetailsPageObject.isDisplayed("EntityScreen.date_Of_Incorporation");
		actualData = applicantDetailsPageObject.getTextValue("EntityScreen.date_Of_Incorporation");
		Assert.assertEquals("Date_Of_Incorporation is same", userDetails.get("Date_Of_Incorporation"), actualData);

	}

	@Then("Option to add communication address")
	public void option_to_add_communication_address() {

		applicantDetailsPageObject.isDisplayed("EntityScreen.entityDetailsAddCommunicationAddress");

	}

	// Scenario 2 Select Communication Address for Entity
	@Given("Customer with following details is on Entity Details Page")
	public void customer_with_following_details_is_on_entity_details_page(Map<String, String> userDetails) {
		String tmpMobileNumber = userDetails.get("Mobile");

		DBAccess.deleteUser(tmpMobileNumber);
		DBAccess.executeSqlFile("applicant_details_setup.sql");
		authPageObject.open();
		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", tmpMobileNumber);
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");

		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");
		// TODO: below line should be removed. added as resume is not working for
		// Proposal
		loanDetailsPageObject.clickOn("LoanDetails.Next_Button");
	}

	@When("he selects add communication address")
	public void he_selects_add_communication_address() {
		applicantDetailsPageObject.clickOn("EntityScreen.entityDetailsAddCommunicationAddress");

	}

	@Then("follwing address details are displayed")
	public void follwing_address_details_are_displayed(List<String> aAddresses) {

		applicantDetailsPageObject.isDisplayed("EntityScreen.addressFetchCommunicationAddress");
		Assert.assertEquals("Addresses are not equals", aAddresses.get(0), applicantDetailsPageObject.getTextContents("EntityScreen.addressFetchCommunicationAddress"));

	}

	@Then("User selects address option {int}")
	public void user_selects_address_option(Integer int1) {
		applicantDetailsPageObject.clickOn("EntityScreen.addressFetchCommunicationAddress");

	}

	@Then("He is navigated to Entity Details Page")
	public void he_is_navigated_to_entity_details_page() {

		applicantDetailsPageObject.isDisplayed("EntityScreen.entityDetailsTitle");

	}

	@Then("the selected address is displayed")
	public void the_selected_address_is_displayed() {

		applicantDetailsPageObject.isDisplayed("EntityScreen.selectedAddress");

	}

	@Then("Option to Select Address Type is provided")
	public void option_to_select_address_type_is_provided() {

		applicantDetailsPageObject.isDisplayed("EntityScreen.addressType");
	}

	@Then("Option to Change the Address is provided")
	public void option_to_change_the_address_is_provided() {

		applicantDetailsPageObject.isDisplayed("EntityScreen.entityDetailsChange");

	}

	// Scenario 3 Add new Communication Address for Entity
	@Given("Customer with following details is on Address Selection Page for Entity")
	public void customer_with_following_details_is_on_address_selection_page_for_entity(
			Map<String, String> userDetails) {
		String tmpMobileNumber = userDetails.get("Mobile");

		DBAccess.deleteUser(tmpMobileNumber);
		DBAccess.executeSqlFile("applicant_details_setup.sql");
		authPageObject.open();
		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", tmpMobileNumber);
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");

		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");
		// TODO: below line should be removed. added as resume is not working for
		// Proposal
		loanDetailsPageObject.clickOn("LoanDetails.Next_Button");

	}

	@When("he clicks on Add New option")
	public void he_clicks_on_add_new_option() {

		applicantDetailsPageObject.clickOn("EntityScreen.entityDetailsAddCommunicationAddress");
		applicantDetailsPageObject.clickOn("EntityScreen.entityDetailsAddNew");
	}

	@Then("he can add address with following details")
	public void he_can_add_address_with_following_details(Map<String, String> userDetails) {

		String tmpAddress = userDetails.get("Address_Line_1");
		applicantDetailsPageObject.enterInputWithValue("EntityScreen.addressLine1", tmpAddress);
		String tmpPincode = userDetails.get("Pin_Code");
		applicantDetailsPageObject.enterInputWithValue("EntityScreen.pincode", tmpPincode);

	}

	@Then("clicks on Add")
	public void clicks_on_add() {
		applicantDetailsPageObject.clickOn("EntityScreen.addressDetailsAdd");

	}

	@Then("the new added address is displayed")
	public void the_new_added_address_is_displayed() {
		applicantDetailsPageObject.isDisplayed("EntityScreen.addedCommunicationAddress");

	}

	@Then("Option to Edit the address is provided")
	public void option_to_edit_the_address_is_provided() {
		applicantDetailsPageObject.isDisplayed("EntityScreen.addedCommunicationAddressEditIcon");

	}

	// Scenario 4 Update the new Communication Address for Entity
	@Given("have added communication address")
	public void have_added_communication_address() {

		applicantDetailsPageObject.clickOn("EntityScreen.entityDetailsAddCommunicationAddress");
		applicantDetailsPageObject.clickOn("EntityScreen.entityDetailsAddNew");
		applicantDetailsPageObject.enterInputWithValue("EntityScreen.addressLine1", "Offce 32, Tower D");
		applicantDetailsPageObject.enterInputWithValue("EntityScreen.pincode", "110026");
		applicantDetailsPageObject.clickOn("EntityScreen.addressDetailsAdd");

	}

	@When("he clicks on Edit communication address")
	public void he_clicks_on_edit_communication_address() {

		applicantDetailsPageObject.clickOn("EntityScreen.addedCommunicationAddressEditIcon");
	}

	@Then("he can Edit address with following details")
	public void he_can_edit_address_with_following_details(Map<String, String> userDetails) {

		String tmpAddress = userDetails.get("Address_Line_1");
		applicantDetailsPageObject.enterInputWithValue("EntityScreen.addressLine1", tmpAddress);
		String tmpPincode = userDetails.get("Pin_Code");
		applicantDetailsPageObject.enterInputWithValue("EntityScreen.pincode", tmpPincode);

	}

	@Then("clicks on Update")
	public void clicks_on_update() {

		applicantDetailsPageObject.clickOn("EntityScreen.addressDetailsUpdate");
	}

	@Then("The new Updated address is displayed")
	public void the_new_updated_address_is_displayed() {

		applicantDetailsPageObject.isDisplayed("EntityScreen.updatedCommunicationAddress");
	}

	// Scenario 5 Delete the new Communication Address for Entity
	@Then("he can view Delete address Icon")
	public void he_can_view_delete_address_icon() {

		applicantDetailsPageObject.isDisplayed("EntityScreen.addressDetailsDelete");
	}

	@Then("clicks on Delete icon button")
	public void clicks_on_delete_icon_button() {
		applicantDetailsPageObject.clickOn("EntityScreen.addressDetailsDelete");

	}

	@Then("He is navigated to add new communication address page")
	public void he_is_navigated_to_add_new_communication_address_page() {
		applicantDetailsPageObject.isDisplayed("EntityScreen.entityDetailsAddCommunicationAddress");
	}

	@Then("option to Add new communication address")
	public void option_to_add_new_communication_address() {

		applicantDetailsPageObject.isDisplayed("EntityScreen.entityDetailsAddCommunicationAddress");

	}

	// Scenario 6 Applicant Details for Partnership
	@When("He clicks on Next")
	public void he_clicks_on_next() {
		try {
			authPageObject.clickOn("OkcookieBtn");
		}catch(Exception e) {
			System.out.println("Accept Cookie  - Button not found !");
		}
		authPageObject.waitExplicit(2);
		applicantDetailsPageObject.bringInView("EntityScreen.entityDetailsNext");
		applicantDetailsPageObject.clickOn("EntityScreen.entityDetailsNext");
	}

	@Then("He should be navigated to Applicant Details page with following partner details")
	public void he_should_be_navigated_to_applicant_details_page_with_following_partner_details(
			List<String> partnerNames) {	
		
		for(int i=2;i <4;i++) {
			String tmpLocator = "/html/body/app-root/app-applicant-details/section/div/div[2]/app-applicant-type/div[1]/div[" + i +"]/div/div/div/div[2]/div[1]/h3[2]";
			WebElement id = applicantDetailsPageObject.getDriver().findElement(By.xpath(tmpLocator));	
			String tmpPartnerName = id.getText();
			Assert.assertTrue("Partner Name " + tmpPartnerName + " Not found", partnerNames.contains(tmpPartnerName.trim()));
		}
		
	}

	@Then("has option to Edit the partner")
	public void has_option_to_edit_the_partner() {

		applicantDetailsPageObject.isDisplayed("Applicant.Partner.edit");

	}

	@Then("option to click on Next")
	public void option_to_click_on_next() {
		
		applicantDetailsPageObject.bringInView("Applicant.Partner.next");
		applicantDetailsPageObject.isDisplayed("Applicant.Partner.next");
		//applicantDetailsPageObject.clickOn("Applicant.Partner.next");
	}

	// Scenario 7 Applicant Details for Company
	@Given("and have added communication address")
	public void and_have_added_communication_address() {

		applicantDetailsPageObject.isDisplayed("Entity Screen.addedCommunicationAddress");

	}

	@Then("He should be navigated to Applicant Details page with following Director details")
	public void he_should_be_navigated_to_applicant_details_page_with_following_director_details(
			Map<String, String> userDetails) {

		String actualData = applicantDetailsPageObject.getTextContents("Applicant.Director.entityName");
		Assert.assertEquals("Entity Name is match", actualData, userDetails.get("Entity Name"));
		applicantDetailsPageObject.isDisplayed("Applicant.Director.entityName");

		actualData = applicantDetailsPageObject.getTextContents("Applicant.Director.aPAN");
		Assert.assertEquals("Entity Name is match", actualData, userDetails.get("PAN"));
		applicantDetailsPageObject.isDisplayed("Applicant..Director.aPAN");

		actualData = applicantDetailsPageObject.getTextContents("Applicant.Director.aDOB");
		Assert.assertEquals("Entity Name is match", actualData, userDetails.get("Date of Birth"));
		applicantDetailsPageObject.isDisplayed("Applicant.Director.aDOB");

		actualData = applicantDetailsPageObject.getTextContents("Applicant.Director.aAddress");
		Assert.assertEquals("Entity Name is match", actualData, userDetails.get("Address"));
		applicantDetailsPageObject.isDisplayed("Applicant.Director.aAddress");

	}

	@Then("has option to Edit the Director")
	public void has_option_to_edit_the_director() {

		applicantDetailsPageObject.isDisplayed("Applicant.Director.editIcon");

	}

	// Scenario 8 Add co-applicant as Indiviual
	@Given("Customer with following details is on Applicant Details Page")
	public void customer_with_following_details_is_on_applicant_details_page(Map<String, String> userDetails) {
		
		String tmpMobileNumber = userDetails.get("Mobile");

		DBAccess.deleteUser(tmpMobileNumber);
		DBAccess.executeSqlFile("applicant_details_setup.sql");
		authPageObject.open();

		
		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", tmpMobileNumber);
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");

		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");
		
		try {
			authPageObject.clickOn("OkcookieBtn");
		}catch(Exception e) {
			System.out.println("Accept Cookie  - Button not found !");
		}
		authPageObject.waitExplicit(2);
		
		// TODO: below line should be removed. added as resume is not working for
		// Proposal
		loanDetailsPageObject.clickOn("LoanDetails.Next_Button");
		


	}

	@When("he clicks on Add Co-applicant option")
	public void he_clicks_on_add_co_applicant_option() {
		
		applicantDetailsPageObject.bringInView("EntityScreen.entityDetailsNext");
		applicantDetailsPageObject.clickOn("EntityScreen.entityDetailsNext");
		applicantDetailsPageObject.bringInView("Applicant.addCo-applicant");
		applicantDetailsPageObject.clickOn("Applicant.addCo-applicant");
	}

	@Then("he can add Co-applicant with following details")
	public void he_can_add_co_applicant_with_following_details(Map<String, String> userDetails) {

		String tmpPAN = userDetails.get("PAN");
		applicantDetailsPageObject.enterInputWithValue("Applicant.Co-applicant.pan", tmpPAN);

		applicantDetailsPageObject.waitExplicit(1);

		String tmpFirstname = applicantDetailsPageObject.getTextValue("Applicant.Co-applicant.firstName");
		Assert.assertEquals("First_Name is match", tmpFirstname, userDetails.get("First Name"));

		String tmpMiddleName = applicantDetailsPageObject.getTextValue("Applicant.Co-applicant.middleName");
		Assert.assertEquals("Middle_NAme is match", tmpMiddleName, userDetails.get("Middle Name"));

		String tmpLastName = applicantDetailsPageObject.getTextValue("Applicant.Co-applicant.lastName");
		Assert.assertEquals("Middle_NAme is match", tmpLastName, userDetails.get("Last Name"));

		//String tmpDOB = userDetails.get("Date of Birth");
		//applicantDetailsPageObject.enterInputWithValue("Applicant.Co-applicant.dob", tmpDOB);

		String tmpAddress = userDetails.get("Address");
		applicantDetailsPageObject.enterInputWithValue("Applicant.Co-applicant.address", tmpAddress);

		String tmpPincode = userDetails.get("Pin_Code");
		applicantDetailsPageObject.enterInputWithValue("Applicant.Co-applicant.pincode", tmpPincode);

		String tmpCustomerId = userDetails.get("Customer_ID");
		applicantDetailsPageObject.enterInputWithValue("Applicant.Co-applicant.customerId", tmpCustomerId);

		applicantDetailsPageObject.waitExplicit(1);

	}

	@Then("clicks on Add button for add Co-applicant")
	public void clicks_on_add_button_for_add_co_applicant() {

		applicantDetailsPageObject.clickOn("Applicant.Co-applicant.addbutton");
	}

	@Then("He is navigated to Applicant Details Page")
	public void he_is_navigated_to_applicant_details_page() {

		applicantDetailsPageObject.isDisplayed("Applicant.applicantDetailsTitle");
	}

	@Then("Option to Edit the Co-applicant Details is displayed")
	public void option_to_edit_the_co_applicant_details_is_displayed() {

		applicantDetailsPageObject.waitExplicit(2);
		applicantDetailsPageObject.isDisplayed("Applicant.Co-applicant.editIcon");

	}

	// Scenario 9 Add co-applicant as Company
	@Given("Enter Pan details")
	public void enter_pan_details(Map<String, String> aData) {

		applicantDetailsPageObject.enterInputWithValue("Applicant.Co-applicant.companyPan", aData.get("PAN"));
	}

	@Then("System should fetch following Co-applicant details")
	public void system_should_fetch_following_co_applicant_details(Map<String, String> userDetails) {

		String actualData = applicantDetailsPageObject.getTextContents("Applicant.Co-applicant.businessName");
		Assert.assertEquals("Business_Name is match", actualData, userDetails.get("Business_Name"));
		applicantDetailsPageObject.isDisplayed("Applicant.Co-applicant.businessName"); // This Id are not there



		actualData = applicantDetailsPageObject.getTextContents("Applicant.Co-applicant.Date_Of_Incorporation");
		Assert.assertEquals("Date_Of_Incorporation is match", actualData, userDetails.get("Date_Of_Incorporation"));
		applicantDetailsPageObject.isDisplayed("Applicant.Co-applicant.Date_Of_Incorporation");

		actualData = applicantDetailsPageObject.getTextContents("Applicant.Co-applicant.address");
		Assert.assertEquals("Address is match", actualData, userDetails.get("Address"));
		applicantDetailsPageObject.isDisplayed("Applicant.Co-applicant.address");

		actualData = applicantDetailsPageObject.getTextContents("Applicant.Co-applicant.pin_Code");
		Assert.assertEquals("pin_Code is match", actualData, userDetails.get("pin_Code"));
		applicantDetailsPageObject.isDisplayed("Applicant.Co-applicant.pin_Code");
	}

	// Scenario 10 Update co-applicatnt
	@When("he clicks on Edit Co-applicant option")
	public void he_clicks_on_edit_co_applicant_option() {

		applicantDetailsPageObject.clickOn("EntityScreen.entityDetailsNext");
		applicantDetailsPageObject.clickOn("Applicant.Co-applicant.editIcon");

	}

	@Then("he can Edit Co-applicant with following details")
	public void he_can_edit_co_applicant_with_following_details(Map<String, String> userDetails) {

		String tmpPAN = userDetails.get("PAN");
		applicantDetailsPageObject.enterInputWithValue("Applicant.Co-applicant.pan", tmpPAN);

		applicantDetailsPageObject.waitExplicit(1);

		String tmpFirstname = applicantDetailsPageObject.getTextValue("Applicant.Co-applicant.firstName");
		Assert.assertEquals("First_Name is match", tmpFirstname, userDetails.get("First Name"));

		String tmpMiddleName = applicantDetailsPageObject.getTextValue("Applicant.Co-applicant.middleName");
		Assert.assertEquals("Middle_NAme is match", tmpMiddleName, userDetails.get("Middle Name"));

		String tmpLastName = applicantDetailsPageObject.getTextValue("Applicant.Co-applicant.lastName");
		Assert.assertEquals("Middle_NAme is match", tmpLastName, userDetails.get("Last Name"));

		/*
		 * String tmpDOB = userDetails.get("Date of Birth");
		 * applicantDetailsPageObject.enterInputWithValue("Applicant.Co-applicant.dob",
		 * tmpDOB );
		 */
		String tmpAddress = userDetails.get("Address");
		applicantDetailsPageObject.enterInputWithValue("Applicant.Co-applicant.address", tmpAddress);

		String tmpPincode = userDetails.get("Pin_Code");
		applicantDetailsPageObject.enterInputWithValue("Applicant.Co-applicant.pincode", tmpPincode);

		String tmpCustomerId = userDetails.get("Customer_ID");
		applicantDetailsPageObject.enterInputWithValue("Applicant.Co-applicant.customerId", tmpCustomerId);
	}

	@Then("clicks on Update co-applicant")
	public void clicks_on_update_co_applicant() {
		applicantDetailsPageObject.waitExplicit(1);
		applicantDetailsPageObject.clickOn("Applicant.Co-applicant.update");
	}

	@Then("the new updated Co-applicant details is displayed")
	public void the_new_updated_co_applicant_details_is_displayed() {

		applicantDetailsPageObject.isDisplayed("Applicant.Co-applicant.updatedDetails");
	}

	@Then("Option to Select Add Co-applicant is provided")
	public void option_to_select_add_co_applicant_is_provided() {

		applicantDetailsPageObject.isDisplayed("Applicant.addCo-applicant");
	}

	@Then("Option to Edit the Co-applicant is provided")
	public void option_to_edit_the_co_applicant_is_provided() {

		applicantDetailsPageObject.isDisplayed("Applicant.Co-applicant.editIcon");

	}

	// Scenario 11 Update the Director Address Details
	@Then("he can Edit Director with following details")
	public void he_can_edit_director_with_following_details(Map<String, String> userDetails) {

		String actualData = applicantDetailsPageObject.getTextContents("Applicant.Director.entityName");
		Assert.assertEquals("Entity Name is match", actualData, userDetails.get("Entity Name"));
		applicantDetailsPageObject.isDisplayed("Applicant.Director.entityName"); // This is ID'S not there

		actualData = applicantDetailsPageObject.getTextContents("Applicant.Director.aPAN");
		Assert.assertEquals("PAN is match", actualData, userDetails.get("PAN"));
		applicantDetailsPageObject.isDisplayed("Applicant.Director.aPAN");

		actualData = applicantDetailsPageObject.getTextContents("Applicant.Director.aDOB");
		Assert.assertEquals("Date of Birth is match", actualData, userDetails.get("Date of Birth"));
		applicantDetailsPageObject.isDisplayed("Applicant.Director.aDOB");

		actualData = applicantDetailsPageObject.getTextContents("Applicant.Director.aAddress");
		Assert.assertEquals("Address", actualData, userDetails.get("Address"));
		applicantDetailsPageObject.isDisplayed("Applicant.Director.aAddress");

	}

	@Then("Option to Edit the Director Details is displayed")
	public void option_to_edit_the_director_details_is_displayed() {
		applicantDetailsPageObject.isDisplayed("Applicant.Director.edit");

	}

	@When("he clicks on Edit Partner option")
	public void he_clicks_on_edit_partner_option() {

		applicantDetailsPageObject.clickOn("Applicant.Partner.edit");

	}

	// Scenario 12 Validate Entity Details
	@Then("System should display Error message field Should not be blank")
	public void system_should_display_error_message_field_should_not_be_blank(Map<String, String> userDetails) {

		String tmpAddress = userDetails.get("Address");
		String tmpPincode = userDetails.get("Pin_Code");
		loanDetailsPageObject.enterInputWithValue("EntityScreen.addressLine1", tmpAddress);
		loanDetailsPageObject.enterInputWithValue("EntityScreen.pincode", tmpPincode);
		loanDetailsPageObject.isDisplayed("EntityScreen.errorMessage");

	}

}
