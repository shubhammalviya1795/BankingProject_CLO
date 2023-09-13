package com.intellect.igcb.autotest.stepdefinition;

import java.util.List;
import java.util.Map;

import com.intellect.igcb.autotest.pageobject.ApplicantDetailsPageObject;
import com.intellect.igcb.autotest.pageobject.AuthenticationPageObject;
import com.intellect.igcb.autotest.pageobject.DocumentAcceptancePageObject;
import com.intellect.igcb.autotest.pageobject.OfferDisplayPageObject;
import com.intellect.igcb.autotest.pageobject.RegistrationWithPANPageObject;
import com.intellect.igcb.autotest.pageobject.URCRegistartionPageObject;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DocumentAcceptanceSteps {

	ApplicantDetailsPageObject applicantDetailsPageObject;
	URCRegistartionPageObject urcregistartionPageObject;
	OfferDisplayPageObject offerDisplayPageObject;
	RegistrationWithPANPageObject registerPanPageObject;
	DocumentAcceptancePageObject documentAcceptancePageObject;
	AuthenticationPageObject authenticationPageObject;

	// Scenario:1 Document Acceptance Page
	@Given("Customer with following details wish to apply for loan")
	public void customer_with_following_details_wish_to_apply_for_loan(Map<String, String> userDetails) {

		authenticationPageObject.open();

		String tmpMobileNumber = userDetails.get("Mobile");
		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", tmpMobileNumber);
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");

		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");

	}

	@Given("he has selected pre approved offer {string}")
	public void he_has_selected_pre_approved_offer(String string) {

		offerDisplayPageObject.isDisplayed("PreApprovedOffer.AcceptAndContinueBtn");
		offerDisplayPageObject.clickOn("PreApprovedOffer.AcceptAndContinueBtn");

	}

	@When("He sucessfully validates the URC number")
	public void he_sucessfully_validates_the_urc_number() {

		urcregistartionPageObject.enterInputWithValue("URC.udyamNumber", "UDYAM-MH-12-0005680");
		urcregistartionPageObject.clickOn("URC.udyamSubmit");
		
	}

	@Then("he should be navigated to Documnet Acceptance stage")
	public void he_should_be_navigated_to_documnet_acceptance_stage() {

		documentAcceptancePageObject.isDisplayed("DocumentAcceptance.docAcceptenceTitle");
		//documentAcceptancePageObject.clickOn("DocumentAcceptance.unRead");

		//documentAcceptancePageObject.clickOn("DocumentAcceptance.accept&ProceedBtn");

	}

	@Then("should see following document listing")
	public void should_see_following_document_listing(List<String> dataTable) {

		documentAcceptancePageObject.isDisplayed("DocumentAcceptance.sanctionLetter");
		
		documentAcceptancePageObject.isDisplayed("DocumentAcceptance.customerAgreement");

	}

	@Then("a indicator of unread against each document")
	public void a_indicator_of_unread_against_each_document() {

		 documentAcceptancePageObject.isDisplayed("DocumentAcceptance.unRead");

	}

	// Scenario:2 Read and Accept the Documents
	@Given("Customer with following details is on Document Accetance Page")
	public void customer_with_following_details_is_on_document_accetance_page(
			io.cucumber.datatable.DataTable dataTable) {
	}

	@When("He clicks on a unread document name {string}")
	public void he_clicks_on_a_unread_document_name(String string) {
	}

	@Then("He should navigated to the contents of the document")
	public void he_should_navigated_to_the_contents_of_the_document() {
	}

	@Then("option to Accept and Continue")
	public void option_to_accept_and_continue() {
	}

	// Scenario:3 Esign options for properietor
	@Given("Customer with follwing details have read and accepted the document")
	public void customer_with_follwing_details_have_read_and_accepted_the_document(
			io.cucumber.datatable.DataTable dataTable) {
	}

	@Given("he has a contitution of {string}")
	public void he_has_a_contitution_of(String string) {
	}

	@Then("she should see the following esign option")
	public void she_should_see_the_following_esign_option(io.cucumber.datatable.DataTable dataTable) {
	}

	// Scenario:4 Esign options for properietor
	@Given("The Customer is on Saction Letter")
	public void the_customer_is_on_saction_letter() {
	}

	@Given("He close the Saction Letter screen")
	public void he_close_the_saction_letter_screen() {
	}

	@Then("System will not allow for NetBanking authentication")
	public void system_will_not_allow_for_net_banking_authentication() {
	}

	// Scenario:5 Customer open sanction letter but not Accept
	@Given("The Customer is on Customer Agreement")
	public void the_customer_is_on_customer_agreement() {

	}

	// Scenario:6 Customer open Customer Agreement but not Accept
	@Given("The Customer is on Document Acceptance Page")
	public void the_customer_is_on_document_acceptance_page() {

	}

	@When("He select Sanction Letter & Customer Agreement")
	public void he_select_sanction_letter_customer_agreement() {

	}

	@Then("System Should allow to download Sanction Letter & Customer Agreement")
	public void system_should_allow_to_download_sanction_letter_customer_agreement() {

	}

	// Scenario:6 Customer download Sanction Letter & Customer Agreement
	@Given("The Customer is on Document Acceptance")
	public void the_customer_is_on_document_acceptance() {

	}

	@When("Customer select Sanction Letter & Customer Agreement")
	public void customer_select_sanction_letter_customer_agreement() {

	}

	@Then("eSign authentication is only option for Partnership & solo Prop")
	public void e_sign_authentication_is_only_option_for_partnership_solo_prop() {
	}

}
