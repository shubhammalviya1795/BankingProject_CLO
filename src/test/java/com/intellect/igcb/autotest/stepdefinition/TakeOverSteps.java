package com.intellect.igcb.autotest.stepdefinition;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.intellect.igcb.autotest.pageobject.ApplicantDetailsPageObject;
import com.intellect.igcb.autotest.pageobject.AuthenticationPageObject;
import com.intellect.igcb.autotest.pageobject.LoanDetailsPageObject;
import com.intellect.igcb.autotest.pageobject.OfferDisplayPageObject;
import com.intellect.igcb.autotest.pageobject.RegistrationWithPANPageObject;
import com.intellect.igcb.autotest.pageobject.TakeOverPageObject;
import com.intellect.igcb.autotest.pageobject.URCRegistartionPageObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TakeOverSteps {

	ApplicantDetailsPageObject applicantDetailsPageObject;
	URCRegistartionPageObject urcregistartionPageObject;
	OfferDisplayPageObject offerDisplayPageObject;
	RegistrationWithPANPageObject registerPanPageObject;
	LoanDetailsPageObject loanDetailsPageObject;
	AuthenticationPageObject authenticationPageObject;
	AuthenticationPageObject authPageObject;
	TakeOverPageObject takeOverPageObject;

	// @takover1
	// Scenario: Display Existing Loan on Take Over Screen

	@Given("customer is on Take Over details screen")
	public void customer_is_on_take_over_details_screen() {

		authenticationPageObject.open();

		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", "7876543236");
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");

		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");

		/*
		 * loanDetailsPageObject.waitExplicit(1);
		 * 
		 * loanDetailsPageObject.clickOn("LoanDetails.loanDetailsNext");
		 * 
		 * applicantDetailsPageObject.waitExplicit(1);
		 * 
		 * applicantDetailsPageObject.clickOn("EntityScreen.entityDetailsNext");
		 * 
		 * applicantDetailsPageObject.waitExplicit(1);
		 * 
		 * applicantDetailsPageObject.bringInView("ApplicantDetails.nextBtn");
		 * 
		 * applicantDetailsPageObject.clickOn("ApplicantDetails.nextBtn");
		 */
		takeOverPageObject.isDisplayed("TakeOver.takeOverTab");

		takeOverPageObject.clickOn("TakeOver.takeOverTab");

	}

	@When("customer click on Take Over Tab")
	public void customer_click_on_take_over_tab() {

		takeOverPageObject.clickOn("TakeOver.takeOverTab");
	}

	@Then("system should displays all existing loan product of customer on Take Over screen")
	public void system_should_displays_all_existing_loan_product_of_customer_on_take_over_screen() {

		takeOverPageObject.isDisplayed("TakeOver.takeOverProduct");

	}

	// @takover2
	// Scenario: Select Product on Take Over Screen

	@Given("System display all existing product of customer")
	public void system_display_all_existing_product_of_customer() {

		takeOverPageObject.isDisplayed("TakeOver.takeOverProduct");
	}

	@When("Customer selects the product")
	public void customer_selects_the_product() {

		takeOverPageObject.clickOn("TakeOver.productEditBtn");
	}

	@Then("Customer should redirect to product details screen")
	public void customer_should_redirect_to_product_details_screen() {

		takeOverPageObject.isDisplayed("TakeOver.takeOverProductScreen");

	}

	// @takover3
	// Scenario: Customer select term loan product on Take Over screen

	@When("Customer select term loan product")
	public void customer_select_term_loan_product() {
		takeOverPageObject.clickOn("TakeOver.TermLoan");
	}

	@Then("system should display term loan product details screen")
	public void system_should_display_term_loan_product_details_screen() {

		takeOverPageObject.isDisplayed("TakeOver.takeOverProductScreen");

	}

	// @takover4
	// Scenario Outline: Invalid Loan Account Number

	@Given("Customer select Bank tab")
	public void customer_select_bank_tab() {

		takeOverPageObject.isDisplayed("TakeOver.loanAccountNumber");
	}

	@When("he inputs the invalid {string}")
	public void he_inputs_the_invalid(String aLoanAccountNumber) {
		takeOverPageObject.isDisplayed("TakeOver.loanAccountNumber");
		takeOverPageObject.waitExplicit(1);
		takeOverPageObject.enterInputWithValue("TakeOver.loanAccountNumber", aLoanAccountNumber);
	}

	@Then("system should display with follwing {string} message for bank tab details screen")
	public void system_should_display_with_follwing_message_for_bank_tab_details_screen(String aErrorMessage) {

		takeOverPageObject.isDisplayed("TakeOver.loanAccountNumber");
		String tmpActualError = takeOverPageObject.getTextContents("TakeOver.errorLabel");
		Assert.assertEquals("Error messages are invalid", aErrorMessage, tmpActualError); // error msg not displaying on
																							// UI
	}

	// @takover5
	// Scenario: Term loan bank tab fields

	@Then("system should display with following fields for bank tab")
	public void system_should_display_with_following_fields_for_bank_tab(List<String> dataTable) {

		takeOverPageObject.isDisplayed("TakeOver.ifscCode");
		takeOverPageObject.isDisplayed("TakeOver.loanAccountNumber");
		takeOverPageObject.isDisplayed("TakeOver.sanctionLetter");
		takeOverPageObject.isDisplayed("TakeOver.useExistingSanctionLetterCheckbox"); // check box not present on UI
		takeOverPageObject.isDisplayed("TakeOver.uploadButton");

	}

	// @takover6
	// Scenario: Serching functionality of IFSC Number

	@When("customer click on search icon on IFSC field")
	public void customer_click_on_search_icon_on_ifsc_field() {

		takeOverPageObject.clickOn("TakeOver.ifscField");
	}

	@Then("system should display with following fields for find IFSC Code details screen")
	public void system_should_display_with_following_fields_for_find_ifsc_code_details_screen(List<String> dataTable) {

		takeOverPageObject.waitExplicit(1);
		takeOverPageObject.clickOn("TakeOver.changeBranch/IFSCCode");
		takeOverPageObject.isDisplayed("TakeOver.bankNameLabel");
		takeOverPageObject.isDisplayed("TakeOver.branchName/CityLabel");
		takeOverPageObject.isDisplayed("TakeOver.findButton");

	}

	// @takover7
	// Scenario: Customer is able to add IFSC Code field details

	@When("Customer is add all the IFSC Code field details")
	public void customer_is_add_all_the_ifsc_code_field_details(Map<String, String> userDetails) {

		takeOverPageObject.clickOn("TakeOver.ifscField");
		// takeOverPageObject.clickOn("TakeOver.changeBranch/IFSCCode");
		takeOverPageObject.clickOn("Takeover.bankNameSelect");

		// span[contains(text(),'ICICI BANK LIMITED')]
		WebElement selectedOtion1 = takeOverPageObject.getDriver()
				.findElement(By.xpath("//span[contains(text(),'ICICI BANK LIMITED')]"));
		// takeOverPageObject.bringInView("selectedOtion1");
		takeOverPageObject.waitExplicit(1);
		takeOverPageObject.clickOn(selectedOtion1);

		String city = userDetails.get("Branch Name/City");
		takeOverPageObject.enterInputWithValue("TakeOver.enterBranchName/City", city);
		takeOverPageObject.clickOn("TakeOver.findButton");

		takeOverPageObject.bringInView("TakeOver.selectBtn");
		takeOverPageObject.clickOn("TakeOver.selectBtn");
	}

	@Then("System should show all the bank details on IFSC code details Screen")
	public void system_should_show_all_the_bank_details_on_ifsc_code_details_screen() {
		takeOverPageObject.isDisplayed("TakeOver.selectedBankDetails");
	}

	// @takover8
	// Scenario: Customer is able to add Bank tab field details

	@When("cutomer is add Loan Account Number")
	public void cutomer_is_add_loan_account_number(Map<String, String> userDetails) {

		String loanAcc = userDetails.get("Loan Account Number");
		takeOverPageObject.enterInputWithValue("TakeOver.loanAccountNumber", loanAcc);

	}

	@When("customer upload senction letter document")
	public void customer_upload_senction_letter_document() {
		takeOverPageObject.clickOn("TakeOver.sanctionLetter");
	}

	@When("customer click on update button for bank tab details")
	public void customer_click_on_update_button_for_bank_tab_details() {

		takeOverPageObject.clickOn("TakeOver.updateBtn");

	}

	@Then("Term loan Updated message should be display") // update message is not displaying
	public void term_loan_updated_message_should_be_display(String docString) {

		takeOverPageObject.isDisplayed("TakeOver.updateMessage");
	}

	// @takover9
	// Scenario: Change Bank details

	@When("customer change Branch\\/IFSC Code details")
	public void customer_change_branch_ifsc_code_details(Map<String, String> userDetails) {

		takeOverPageObject.clickOn("TakeOver.ifscField");
		takeOverPageObject.clickOn("Takeover.bankNameSelect");
		// span[contains(text(),'ICICI BANK LIMITED')]
		WebElement selectedOtion1 = takeOverPageObject.getDriver()
				.findElement(By.xpath("//span[contains(text(),'ICICI BANK LIMITED')]"));
		takeOverPageObject.waitExplicit(1);
		takeOverPageObject.clickOn(selectedOtion1);

		String city = userDetails.get("Branch Name/City");
		takeOverPageObject.enterInputWithValue("TakeOver.enterBranchName/City", city);
		takeOverPageObject.clickOn("TakeOver.findButton");

		takeOverPageObject.bringInView("TakeOver.selectBtn");
		takeOverPageObject.clickOn("TakeOver.selectBtn");

	}

	@Then("Changed Bank details should be display on IFSC bank tab screen")
	public void changed_bank_details_should_be_display_on_ifsc_bank_tab_screen() {

		takeOverPageObject.isDisplayed("TakeOver.changedBankDetails");

	}

	// @takover10
	// Scenario Outline: Sanction letter Valid format // it's not taking xpath
	// upload

	@When("customer Upload sanction letter")
	public void customer_upload_sanction_letter() {

		takeOverPageObject.waitExplicit(2);

		WebElement upload_file = takeOverPageObject.getDriver().findElement(By.xpath("//*[@id=\"file\"]"));

		upload_file.sendKeys("C:\\Users\\DELL\\Downloads/image.jpeg");

	}

	@Then("system should validate the sanction as per below rules")
	public void system_should_validate_the_sanction_as_per_below_rules() {

		String text = takeOverPageObject.getTextContents("TakeOver.uploadedFileName");
		System.out.println(text);

		Optional<String> fileExt = Optional.ofNullable(text).filter(f -> f.contains("."))
				.map(f -> f.substring(text.lastIndexOf(".") + 1));
//		String ext = null;
//		if(null != text) {
//			if(text.contains(".")) {
//				ext = text.substring(text.lastIndexOf(".") + 1);
//			}
//		}

		if (fileExt.isPresent()) {
			String ext = fileExt.get();
			if ("pdf".equalsIgnoreCase(ext)) {
				System.out.println("PDF");
			} else if ("jpeg".equalsIgnoreCase(ext)) {
				System.out.println("jpeg");
			} else if ("png".equalsIgnoreCase(ext)) {
				System.out.println("png");
			}
		}

		
		
		  String size = takeOverPageObject.getTextValue("TakeOver.uploadedFileSize");
		  System.out.println(size);
		  
		  //if (fileExt.isPresent()) { String ext = fileExt.get();
		 
		  
		 
	}

	// @takover11
	// Scenario Outline: Sanction letter Invalid format

	// @takover12
	// Scenario: Sanction letter option is not mandatory

	@When("customer click on update button without uploading sanction letter")
	public void customer_click_on_update_button_without_uploading_sanction_letter() {
		takeOverPageObject.bringInView("TakeOver.updateBtn");
		takeOverPageObject.clickOn("TakeOver.updateBtn");
	}

	@Then("custmer should be able to update all details because sanction letter upload is optional parameter")
	public void custmer_should_be_able_to_update_all_details_because_sanction_letter_upload_is_optional_parameter() {

		takeOverPageObject.isDisplayed("TakeOver.updateAllDetails");
	}

	// @takover13
	// Scenario: Verify customer is able to view Delete option for Sanction Letter

	@Then("customer should be able to view Delete icon on sanction letter")
	public void customer_should_be_able_to_view_delete_icon_on_sanction_letter() {
		takeOverPageObject.isDisplayed("TakeOver.deleteIcon");
	}

	// @takover14
	// Scenario: Verify customer is able to delete the Sanction Letter

	@When("click on delete option")
	public void click_on_delete_option() {
		takeOverPageObject.clickOn("TakeOver.deleteIcon");
	}

	@Then("sanction letter should be delete")
	public void sanction_letter_should_be_delete() {
		takeOverPageObject.isDisplayed("TakeOver.uploadSactionLetter");
	}

	// @takover15
	// Scenario: User Select NBFC option

	@Given("customer select NBFC tab")
	public void customer_select_nbfc_tab() {

		takeOverPageObject.clickOn("TakeOver.nbfcTab");

	}

	@Then("System should display NBFC fields screen")
	public void system_should_display_nbfc_fields_screen(List<String> dataTable) {
		takeOverPageObject.isDisplayed("TakeOver.loanAccountNumberLabel");
		takeOverPageObject.isDisplayed("TakeOver.nbfcNameLabel");
		takeOverPageObject.isDisplayed("TakeOver.cityLabel");
		takeOverPageObject.isDisplayed("TakeOver.uploadSactionLetter");
		takeOverPageObject.isDisplayed("TakeOver.updateBtn");
	}

	// @takover16 // update message should be display on UI
	// Scenario: customer is able to Update NBFC tab field details

	@When("cutomer is add all the following details")
	public void cutomer_is_add_all_the_following_details(Map<String, String> userDetails) {

		takeOverPageObject.waitExplicit(1);

		String loanAcc = userDetails.get("Loan Account Number");
		takeOverPageObject.enterInputWithValue("TakeOver.loanAccountNumber", loanAcc);

		String nbfcName = userDetails.get("NBFC Name");
		takeOverPageObject.enterInputWithValue("TakeOver.nbfcName", nbfcName);

		String city = userDetails.get("City");
		takeOverPageObject.enterInputWithValue("TakeOver.city", city);

		takeOverPageObject.clickOn("TakeOver.updateBtn");

	}

	// @takover17
	// Scenario Outline: User enter Invalid Loan Account Number in NBFC tab

	@Then("system should display with follwing {string} message for NBFC tab details screen")
	public void system_should_display_with_follwing_message_for_nbfc_tab_details_screen(String errorMessage) {
		String actualMessage = takeOverPageObject.getTextContents("TakeOver.errorMessage");
		Assert.assertEquals("ErrorMessage are not same", errorMessage, actualMessage);
	}

	// @takover18
	// Scenario: Invalid bank name under NBFC tab

	@When("he inputs the invalid NBFC Name")
	public void he_inputs_the_invalid_nbfc_name(Map<String, String> userDetails) {

		String nbfcName = userDetails.get("NBFC Name");
		takeOverPageObject.enterInputWithValue("TakeOver.nbfcName", nbfcName);

	}

	@Then("system should display with follwing {string} message for NBFC Name as below")
	public void system_should_display_with_follwing_message_for_nbfc_name_as_below(String errorMessage) {
		String actualMessage = takeOverPageObject.getTextContents("TakeOver.errorMessage");
		Assert.assertEquals("ErrorMessage are not same", errorMessage, actualMessage);
	}

	// @takover19
	// Scenario: Invalid city name under NBFC tab

	@When("he inputs the invalid City Name")
	public void he_inputs_the_invalid_city_name(Map<String, String> userDetails) {

		String city = userDetails.get("CityName");
		takeOverPageObject.enterInputWithValue("TakeOver.city", city);

	}

	@Then("system should display with follwing {string} message for City Name as below")
	public void system_should_display_with_follwing_message_for_city_name_as_below(String errorMessage) {
		String actualMessage = takeOverPageObject.getTextContents("TakeOver.errorMessage");
		Assert.assertEquals("ErrorMessage are not same", errorMessage, actualMessage);
	}

	// @takover20
	// Scenario: To check product details are editable

	@When("customer click on pencil icon of any product")
	public void customer_click_on_pencil_icon_of_any_product() {

		takeOverPageObject.clickOn("TakeOver.PencilIcon");

	}

	@Then("system should allow customer to edit the product details")
	public void system_should_allow_customer_to_edit_the_product_details() {

		takeOverPageObject.isDisplayed("TakeOver.EditProduct");
	}

	// @takover21
	// Scenario: Customer closed product on product details screen

	@Given("customer select prdouct")
	public void customer_select_prdouct() {

		takeOverPageObject.clickOn("TakeOver.Product");
	}

	@When("customer click on closed icon")
	public void customer_click_on_closed_icon() {

		takeOverPageObject.clickOn("TakeOver.ClosedIcon");

	}

	@Then("system should redirect to Take Over product details screen")
	public void system_should_redirect_to_take_over_product_details_screen() {

		takeOverPageObject.isDisplayed("TakeOver.takeOverDetails");

	}

	// @takover22
	// Scenario: Customer should display all the product details for the loans he
	// want to transfer

	@Given("Customer is on Takeover Details Screen")
	public void customer_is_on_takeover_details_screen() {

		takeOverPageObject.isDisplayed("TakeOver.takeOverDetails");

	}

	@Given("system display all the products")
	public void system_display_all_the_products() {

		takeOverPageObject.isDisplayed("TakeOver.takeOverDetails");

	}

	@When("Customer click on Next button on take over screen")
	public void customer_click_on_next_button_on_take_over_screen() {

		takeOverPageObject.clickOn("TakeOver.NextBtn");
	}

	@Then("all Takeover product details should be come up followed by collateral details") // Need to discuss
	public void all_takeover_product_details_should_be_come_up_followed_by_collateral_details() {

	}

}
