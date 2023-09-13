package com.intellect.igcb.autotest.stepdefinition;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.intellect.igcb.autotest.pageobject.ApplicantDetailsPageObject;
import com.intellect.igcb.autotest.pageobject.AuthenticationPageObject;
import com.intellect.igcb.autotest.pageobject.CollateralsPageObject;
import com.intellect.igcb.autotest.pageobject.LoanDetailsPageObject;
import com.intellect.igcb.autotest.pageobject.OfferDisplayPageObject;
import com.intellect.igcb.autotest.pageobject.RegistrationWithPANPageObject;
import com.intellect.igcb.autotest.pageobject.TakeOverPageObject;
import com.intellect.igcb.autotest.pageobject.URCRegistartionPageObject;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CollateralsSteps {

	ApplicantDetailsPageObject applicantDetailsPageObject;
	URCRegistartionPageObject urcregistartionPageObject;
	OfferDisplayPageObject offerDisplayPageObject;
	RegistrationWithPANPageObject registerPanPageObject;
	LoanDetailsPageObject loanDetailsPageObject;
	AuthenticationPageObject authenticationPageObject;
	AuthenticationPageObject authPageObject;
	TakeOverPageObject takeOverPageObject;
	CollateralsPageObject collateralsPageObject;

	// @collateral_1//Scenario: Customer select Collateral details
	@Given("Customer is on Collaterals Tab screen")
	public void customer_is_on_collaterals_tab_screen() {

		authenticationPageObject.open();

		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", "7876543236");
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");

		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");

		collateralsPageObject.waitExplicit(1);
		collateralsPageObject.isDisplayed("Collaterals.collateralsTab");

		collateralsPageObject.waitExplicit(1);

		collateralsPageObject.clickOn("Collaterals.collateralsTab");

	}

	@When("Customer click on collaterals tab")
	public void customer_click_on_collaterals_tab() {

		collateralsPageObject.clickOn("Collaterals.collateralsTab");

	}

	@Then("system should navigates to Collateral details screen with following option")
	public void system_should_navigates_to_collateral_details_screen_with_following_option(List<String> dataTable) {

		collateralsPageObject.isDisplayed("Collaterals.addCollateralsWorth2cr");
		collateralsPageObject.isDisplayed("Collaterals.addGovermentBackedScheme");
		collateralsPageObject.isDisplayed("Collaterals.availLoanGovtBackedScheme");
		collateralsPageObject.isDisplayed("Collaterals.benefit_1GovtScheme");
		collateralsPageObject.isDisplayed("Collaterals.Benefit_2LoremIpsum");
		collateralsPageObject.isDisplayed("Collaterals.backBtn");
		collateralsPageObject.isDisplayed("Collaterals.nextBtn");

	}

	// @collateral_2 //Scenario: Select scheme
	@When("Customer select +Add Government Backed Scheme option")
	public void customer_select_add_government_backed_scheme_option() {

		// collateralsPageObject.clickOn("Collaterals.collateralsTab");

		// collateralsPageObject.waitExplicit(1);

		collateralsPageObject.clickOn("Collaterals.addGovermentBackedScheme");

	}

	@Then("He should see the Select Scheme page with follwing Scheme descripiton")
	public void he_should_see_the_select_scheme_page_with_follwing_scheme_descripiton(List<String> dataTable) {

		collateralsPageObject.isDisplayed("Collaterals.bankGuaranteeScheme_1");
		collateralsPageObject.isDisplayed("Collaterals.bankGuaranteeScheme_2");
		collateralsPageObject.isDisplayed("Collaterals.bankGuaranteeScheme_3");

	}

	@Then("customer should be able to select any one scheme from the list")
	public void customer_should_be_able_to_select_any_one_scheme_from_the_list() {

		collateralsPageObject.clickOn("Collaterals.bankGuaranteeScheme_1");

	}

	// @collateral_3 // Scenario: customer select Add Government Backed Scheme

	@Then("system should display multiple number of scheme in the list.")
	public void system_should_display_multiple_number_of_scheme_in_the_list() {

		collateralsPageObject.isDisplayed("Collaterals.multipleNumberOfScheme");

	}

	// @collateral_4 // Scenario: customer select any scheme from Government Backed
	// Scheme
	@When("select any scheme by customer")
	public void select_any_scheme_by_customer() {

		collateralsPageObject.clickOn("Collaterals.bankGuaranteeScheme_3");

	}

	@Then("selected scheme should display on Collaterals Tab screen")
	public void selected_scheme_should_display_on_collaterals_tab_screen() {

		collateralsPageObject.isDisplayed("Collaterals.collateralsTab");

	}

	@Then("delete icon also visible on selected scheme")
	public void delete_icon_also_visible_on_selected_scheme() {

		collateralsPageObject.isDisplayed("Collaterals.adressDelete");

	}

	// @collateral_5

	@Given("selected scheme is display on Collaterals Tab screen")
	public void selected_scheme_is_display_on_collaterals_tab_screen() {

		collateralsPageObject.isDisplayed("Collaterals.bankGuaranteeScheme_3");

	}

	@When("customer click on delete icon on selected scheme")
	public void customer_click_on_delete_icon_on_selected_scheme() {

		collateralsPageObject.clickOn("Collaterals.adressDelete");

	}

	@Then("system should display following message") // Message is not coming
	public void system_should_display_following_message(String docString) {

		collateralsPageObject.isDisplayed("");
		System.out.println("Error Msg Displayed" + collateralsPageObject.getTextContents(""));

	}

	// @collateral_6
	@When("Customer select +Add collateral worth $2cr option")
	public void customer_select_add_collateral_worth_$2cr_option() {

		// collateralsPageObject.clickOn("Collaterals.collateralsTab");

		// collateralsPageObject.waitExplicit(1);

		collateralsPageObject.clickOn("Collaterals.addCollateralsWorth2cr");

	}

	@Then("system should redirect to add collateral Details screen")
	public void system_should_redirect_to_add_collateral_details_screen() {

		collateralsPageObject.isDisplayed("Collaterals.addCollateralsWorth2cr");

	}

	// @collateral_7
	@Given("Customer click on Add collateral worth $2cr option")
	public void customer_click_on_add_collateral_worth_$2cr_option() {

		collateralsPageObject.clickOn("Collaterals.addCollateralsWorth2cr");

	}

	@When("Customer click on collateral type")
	public void customer_click_on_collateral_type() {

		collateralsPageObject.clickOn("Collaterals.type");

	}

	@Then("System should dispaly collateral fields such as < Collateral Type >")
	public void system_should_dispaly_collateral_fields_such_as_collateral_type(List<String> dataTable) {

		collateralsPageObject.isDisplayed("Collaterals.propertyType");
		collateralsPageObject.isDisplayed("Collaterals.vehicleType");
		collateralsPageObject.isDisplayed("Collaterals.liquidType");
		collateralsPageObject.isDisplayed("Collaterals.stockType");
		collateralsPageObject.isDisplayed("Collaterals.plantType");

	}

	// @collateral_8
	@Given("Customer select collaterals type as Property")
	public void customer_select_collaterals_type_as_property() {

		collateralsPageObject.clickOn("Collaterals.propertyType");

	}

	@When("Customer click on Sub Types of collaterals")
	public void customer_click_on_sub_types_of_collaterals() {

		collateralsPageObject.clickOn("Collaterals.propertySubType");

	}

	@Then("System should show subtypes of collaterals as per the Master")
	public void system_should_show_subtypes_of_collaterals_as_per_the_master() {

		collateralsPageObject.isDisplayed("Collaterals.propertySubType");

	}

	// @collateral_9

	@When("Customer select Type as Property")
	public void customer_select_type_as_property(Map<String, String> userDetails) {

		collateralsPageObject.clickOn("Collaterals.propertyType");

		WebElement selectedOtion = collateralsPageObject.getDriver()
				.findElement(By.xpath("//span[contains(text(),'PROPERTY')]"));
		collateralsPageObject.clickOn(selectedOtion);

		collateralsPageObject.clickOn("Collaterals.propertySubType");
		collateralsPageObject.waitExplicit(1);
		WebElement selectedOtion1 = collateralsPageObject.getDriver()
				.findElement(By.xpath("//span[contains(text(),'Residential Property')]"));
		collateralsPageObject.clickOn(selectedOtion1);

	}

	@Then("Following Details should be displayed")
	public void following_details_should_be_displayed(List<String> dataTable) {

		collateralsPageObject.isDisplayed("Collaterals.addAddress");
		collateralsPageObject.isDisplayed("Collaterals.approxMarketValue");
		collateralsPageObject.isDisplayed("Collaterals.usage");
		collateralsPageObject.isDisplayed("Collaterals.addOwner");
		collateralsPageObject.isDisplayed("Collaterals.property_Existing_Takeover");
	}

	// @collateral_10
	@When("customer enter all property details")
	public void customer_enter_all_property_details(Map<String, String> userDetails) {

		collateralsPageObject.clickOn("Collaterals.propertyType");
		WebElement type = collateralsPageObject.getDriver()
				.findElement(By.xpath("//span[contains(text(),'PROPERTY')]"));
		collateralsPageObject.clickOn(type);

		collateralsPageObject.clickOn("Collaterals.propertySubType");
		collateralsPageObject.waitExplicit(1);
		WebElement subtype = collateralsPageObject.getDriver()
				.findElement(By.xpath("//span[contains(text(),'Residential Property')]"));
		collateralsPageObject.clickOn(subtype);

		collateralsPageObject.clickOn("Collaterals.addAddress");
		collateralsPageObject.clickOn("Collaterals.addNew");

		String addressline1 = userDetails.get("Address");
		collateralsPageObject.enterInputWithValue("Collaterals.addingNewAddress", addressline1);

		String pincode = userDetails.get("Pincode");
		collateralsPageObject.enterInputWithValue("Collaterals.adressPinCode", pincode);
		collateralsPageObject.clickOn("Collaterals.addAddressBtn");

		String approxValue = userDetails.get("Approx market value");
		collateralsPageObject.waitExplicit(1);
		collateralsPageObject.enterInputWithValue("Collaterals.approxMarketValue", approxValue);

		WebElement usageOwned = collateralsPageObject.getDriver().findElement(By.xpath(
				"//body/app-root[1]/app-upload-tabs[1]/section[1]/div[1]/div[2]/div[2]/app-collaterals[1]/div[2]/div[1]/app-collateral-details[1]/section[1]/div[1]/div[2]/div[1]/form[1]/div[3]/app-type-property[1]/div[1]/div[1]/div[2]/div[3]/mat-radio-group[1]/mat-radio-button[1]/label[1]/div[2]"));
		collateralsPageObject.clickOn(usageOwned);

		collateralsPageObject.clickOn("Collaterals.addOwner");
		collateralsPageObject.clickOn("Collaterals.addNewOwner");
		String addOwnerName = userDetails.get("Add Owner_Name");
		collateralsPageObject.enterInputWithValue("Collaterals.ownerName", addOwnerName);
		collateralsPageObject.clickOn("Collaterals.ownerAddBtn");
		collateralsPageObject.waitExplicit(1);
		collateralsPageObject.clickOn("Collaterals.addedOwner");
		collateralsPageObject.clickOn("Collaterals.exitIcon");
		collateralsPageObject.bringInView("Collaterals.checkboxExistingTakeover");
		collateralsPageObject.clickOn("Collaterals.checkboxExistingTakeover");
		collateralsPageObject.bringInView("Collaterals.bankName");
		collateralsPageObject.clickOn("Collaterals.existingBankName");
	}

	@When("Click on ADD")
	public void click_on_add() {
		collateralsPageObject.bringInView("Collaterals.propertyAddBtn");   
		collateralsPageObject.clickOn("Collaterals.propertyAddBtn");

	}

	@Then("customer should see the confirmation message for Property details")
	public void customer_should_see_the_confirmation_message_for_property_details(String docString) {

		collateralsPageObject.isDisplayed("Collaterals.confimationMsg");

	}

	// @collateral_11
	@Given("customer select Property and sub property type details")
	public void customer_select_property_and_sub_property_type_details(Map<String, String> userDetails) {

		collateralsPageObject.clickOn("Collaterals.propertyType");
		WebElement type = collateralsPageObject.getDriver()
				.findElement(By.xpath("//span[contains(text(),'PROPERTY')]"));
		collateralsPageObject.clickOn(type);

		collateralsPageObject.clickOn("Collaterals.propertySubType");
		collateralsPageObject.waitExplicit(1);
		WebElement subtype = collateralsPageObject.getDriver()
				.findElement(By.xpath("//span[contains(text(),'Residential Property')]"));
		collateralsPageObject.clickOn(subtype);

	}

	@When("click on +Add Address by Customer")
	public void click_on_add_address_by_customer() {

		collateralsPageObject.clickOn("Collaterals.addAddress");

	}

	@Then("System should redirect to Property Address Screen")
	public void system_should_redirect_to_property_address_screen() {

		collateralsPageObject.isDisplayed("Collaterals.propertyAddressScreen");

	}

	@Then("+Add new option should visible")
	public void add_new_option_should_visible() {

		collateralsPageObject.isDisplayed("Collaterals.addNew");

	}

	@Then("Close icon should visible")
	public void close_icon_should_visible() {

		collateralsPageObject.isDisplayed("Collaterals.closeIcon");

	}

	@Then("Selected Address should visible in green tick")
	public void selected_address_should_visible_in_green_tick() {

		collateralsPageObject.isDisplayed("Collaterals.greenTick");

	}

	@Then("Customer should be able to select Address from Existing list")
	public void customer_should_be_able_to_select_address_from_existing_list() {

		collateralsPageObject.clickOn("Collaterals.ExistingAddress");

	}

	// @collateral_12

	@When("Customer Update Address on property address screen")
	public void customer_update_address_on_property_address_screen(Map<String, String> userDetails) {

		collateralsPageObject.clickOn("Collaterals.addAddress");
		collateralsPageObject.clickOn("Collaterals.addNew");

		String addressline1 = userDetails.get("Address");
		collateralsPageObject.enterInputWithValue("Collaterals.addingNewAddress", addressline1);

		String pincode = userDetails.get("Pincode");
		collateralsPageObject.enterInputWithValue("Collaterals.adressPinCode", pincode);
		collateralsPageObject.clickOn("Collaterals.addAddressBtn");
		// a[contains(text(),'Change')]
		collateralsPageObject.clickOn("Collaterals.editIconBtn");

		String changeAdd = userDetails.get("Address");
		collateralsPageObject.enterInputWithValue("Collaterals.addingNewAddress", changeAdd);

		String chgPincode = userDetails.get("Pincode");
		collateralsPageObject.enterInputWithValue("Collaterals.adressPinCode", chgPincode);
	}

	@When("click on Update button on address screen")
	public void click_on_update_button_on_address_screen() {
		// button[@id='addressDetailsUpdate']
		collateralsPageObject.clickOn("Collaterals.addressUpdateBtn");
	}

	@Then("System should redirect to Property Collateral Detail Screen")
	public void system_should_redirect_to_property_collateral_detail_screen() {

		collateralsPageObject.isDisplayed("Collaterals.collateralProperty");

	}

	// @collateral_13

	@When("click on +Add Owner by Customer")
	public void click_on_add_owner_by_customer() {

		collateralsPageObject.clickOn("Collaterals.addOwner");

	}

	@When("Customer should be able to select Owner from Existing list")
	public void customer_should_be_able_to_select_owner_from_existing_list() {

		collateralsPageObject.clickOn("Collaterals.existingOwner");

	}

	@Then("Selected owner should visible in green tick")
	public void selected_owner_should_visible_in_green_tick() {

		collateralsPageObject.isDisplayed("");

	}

	// @collateral_14

	@When("Customer Change new Owner Details")
	public void customer_change_new_owner_details() {
		collateralsPageObject.clickOn("Collaterals.addOwner");
		collateralsPageObject.clickOn("Collaterals.addNewOwner");
		collateralsPageObject.enterInputWithValue("Collaterals.ownerName", "Rajesh");
		collateralsPageObject.clickOn("Collaterals.ownerAddBtn");
		collateralsPageObject.waitExplicit(1);
		collateralsPageObject.clickOn("Collaterals.addedOwner");
		collateralsPageObject.clickOn("Collaterals.exitIcon");
		collateralsPageObject.waitExplicit(1);
		//collateralsPageObject.bringInView("Collaterals.ownersChange");
		collateralsPageObject.clickOn("Collaterals.ownersChange");
	}

	@When("Enter new Owner Name")
	public void enter_new_owner_name(Map<String, String> userDetails) {
		collateralsPageObject.clickOn("Collaterals.addNewOwner");
		String addOwnerName = userDetails.get("Add Owner_Name");
		collateralsPageObject.enterInputWithValue("Collaterals.ownerName", addOwnerName);
		collateralsPageObject.clickOn("Collaterals.ownerAddBtn");
		collateralsPageObject.waitExplicit(1);
		collateralsPageObject.clickOn("Collaterals.updatedAddOwner");
		collateralsPageObject.clickOn("Collaterals.exitIcon");
	
	}
	
	

	// @collateral_15

	@When("Customer select collateral Type as Vehicle Equipment")
	public void customer_select_collateral_type_as_vehicle_equipment(Map<String, String> userDetails) {

		collateralsPageObject.isDisplayed("Collaterals.vehicleType");
		System.out.println("Collaterals Vehicle Type Displayed"
				+ collateralsPageObject.getTextContents("Collaterals.vehicleType"));
		System.out.println("Collaterals Vehicle SubType Displayed"
				+ collateralsPageObject.getTextContents("Collaterals.vehicleSubType"));

	}

	@Then("Following fields should be displayed for vehicle Equipment")
	public void following_fields_should_be_displayed_for_vehicle_equipment(List<String> dataTable) {

		collateralsPageObject.isDisplayed("Collaterals.manufacturer");
		collateralsPageObject.isDisplayed("Collaterals.make");
		collateralsPageObject.isDisplayed("Collaterals.model");
		collateralsPageObject.isDisplayed("Collaterals.assetValue");
		collateralsPageObject.isDisplayed("Collaterals.marginDownPayment");
		collateralsPageObject.isDisplayed("Collaterals.discount");
		collateralsPageObject.isDisplayed("Collaterals.vehicleAddBtn");

	}

	@Then("Close button should visible")
	public void close_button_should_visible() {

		collateralsPageObject.isDisplayed("");

	}

	// @collateral_16

	@When("customer enter all vehicle Equipment details")
	public void customer_enter_all_vehicle_equipment_details(Map<String, String> userDetails) {

		collateralsPageObject.isDisplayed("Collaterals.propertySubType");
		System.out.println("Collaterals PropertySubType Displayed"
				+ collateralsPageObject.getTextContents("Collaterals.propertySubType"));
		System.out.println("Collaterals manufacturer Displayed"
				+ collateralsPageObject.getTextContents("Collaterals.manufacturer"));
		System.out.println("Collaterals make Displayed" + collateralsPageObject.getTextContents("Collaterals.make"));
		System.out.println("Collaterals model Displayed" + collateralsPageObject.getTextContents("Collaterals.model"));
		System.out.println(
				"Collaterals assetValue Displayed" + collateralsPageObject.getTextContents("Collaterals.assetValue"));
		System.out.println("Collaterals marginDownPayment Displayed"
				+ collateralsPageObject.getTextContents("Collaterals.marginDownPayment"));
		System.out.println(
				"Collaterals discount Displayed" + collateralsPageObject.getTextContents("Collaterals.discount"));

	}

	@Then("customer should see the confirmation message for vehicle equipment")
	public void customer_should_see_the_confirmation_message_for_vehicle_equipment(String docString) {

		collateralsPageObject.isDisplayed("");
		System.out.println("Error Msg Displayed" + collateralsPageObject.getTextContents(""));

	}

	// @collateral_17
	
	@When("Customer select collateral Type as Liquid securities")
	public void customer_select_collateral_type_as_liquid_securities(Map<String, String> userDetails) {

		
		collateralsPageObject.clickOn("Collaterals.liquidType");

		WebElement type = collateralsPageObject.getDriver()
				.findElement(By.xpath("//span[contains(text(),'Liquid Securities')]"));
		collateralsPageObject.clickOn(type);

		collateralsPageObject.clickOn("Collaterals.liquidSubTypeShares");
		collateralsPageObject.waitExplicit(1);
		WebElement subtype = collateralsPageObject.getDriver().findElement(By.xpath("//span[contains(text(),'Corporate Bonds')]"));
		collateralsPageObject.clickOn(subtype);


	}

	@Then("Following fields should be displayed for Liquid securities and Corporate Bonds")
	public void following_fields_should_be_displayed_for_liquid_securities_and_corporate_bonds(List<String> dataTable) {

		collateralsPageObject.isDisplayed("Collaterals.fieldName");
		collateralsPageObject.isDisplayed("Collaterals.fundTypeDebt");
		collateralsPageObject.isDisplayed("Collaterals.shareFormsDemat");
		collateralsPageObject.isDisplayed("Collaterals.value");
		collateralsPageObject.isDisplayed("Collaterals.liquidAddBtn");
		collateralsPageObject.isDisplayed("Collaterals.addOwner");

	}



	// @collateral_18
	@When("customer enter all Liquid securities details")
	public void customer_enter_all_liquid_securities_details(Map<String, String> userDetails) {

		collateralsPageObject.isDisplayed("Collaterals.propertySubType");
		System.out.println("Collaterals PropertySubType Displayed"
				+ collateralsPageObject.getTextContents("Collaterals.propertySubType"));
		System.out.println(
				"Collaterals FieldName Displayed" + collateralsPageObject.getTextContents("Collaterals.fieldName"));
		System.out.println("Collaterals FundTypeDebt Displayed"
				+ collateralsPageObject.getTextContents("Collaterals.fundTypeDebt"));
		System.out.println("Collaterals ShareFormsDemat Displayed"
				+ collateralsPageObject.getTextContents("Collaterals.shareFormsDemat"));
		System.out.println("Collaterals Value Displayed" + collateralsPageObject.getTextContents("Collaterals.value"));

	}

	@Then("customer should see the confirmation message for Liquid securities and corporate bonds")
	public void customer_should_see_the_confirmation_message_for_liquid_securities_and_corporate_bonds(
			String docString) {

		collateralsPageObject.isDisplayed("");
		System.out.println("Error Msg Displayed" + collateralsPageObject.getTextContents(""));

	}

	// @collateral_19
	@When("Customer select collateral Type as Liquid securities and Shares")
	public void customer_select_collateral_type_as_liquid_securities_and_shares(Map<String, String> userDetails) {

		collateralsPageObject.isDisplayed("Collaterals.liquidType");
		System.out.println(
				"Collaterals liquidType Displayed" + collateralsPageObject.getTextContents("Collaterals.liquidType"));
		System.out.println("Collaterals liquidSubType Displayed"
				+ collateralsPageObject.getTextContents("Collaterals.liquidSubTypeShares"));

	}

	@Then("Following fields should be displayed for Liquid securities and Shares")
	public void following_fields_should_be_displayed_for_liquid_securities_and_shares(List<String> dataTable) {

		collateralsPageObject.isDisplayed("Collaterals.fieldName");
		collateralsPageObject.isDisplayed("Collaterals.fundTypeDebt");
		collateralsPageObject.isDisplayed("Collaterals.shareFormsDemat");
		collateralsPageObject.isDisplayed("Collaterals.value");
		collateralsPageObject.isDisplayed("Collaterals.addSecurityHolder");

	}

	// @collateral_20

	@When("Customer click on +Add Security Holder option")
	public void customer_click_on_add_security_holder_option() {

		collateralsPageObject.clickOn("Collaterals.addSecurityHolder");

	}

	@Then("System should allowed to add the Security Holder")
	public void system_should_allowed_to_add_the_security_holder() {

		collateralsPageObject.isDisplayed("Collaterals.addSecurityHolder");

	}

	// @collateral_21
	@When("Customer select collateral Type as following details")
	public void customer_select_collateral_type_as_following_details(Map<String, String> userDetails) {

		collateralsPageObject.isDisplayed("Collaterals.propertyType");
		System.out.println("Collaterals propertyType Displayed"
				+ collateralsPageObject.getTextContents("Collaterals.propertyType"));
		System.out.println("Collaterals PropertySubType Displayed"
				+ collateralsPageObject.getTextContents("Collaterals.propertySubType"));

	}

	@Then("System should disply below option to customer")
	public void system_should_disply_below_option_to_customer(List<String> dataTable) {

		collateralsPageObject.isDisplayed("");
		collateralsPageObject.isDisplayed("");
		collateralsPageObject.isDisplayed("");

	}

	// @collateral_22

	@When("Collateral sub-type is other than FD")
	public void collateral_sub_type_is_other_than_fd() {

		collateralsPageObject.isDisplayed("");

	}

	@Then("FD APIs should not be triggered")
	public void fd_ap_is_should_not_be_triggered() {

		collateralsPageObject.isDisplayed("");

	}

	// @collateral_23

	@When("Customer is having existing FD")
	public void customer_is_having_existing_fd() {

		collateralsPageObject.isDisplayed("");

	}

	@Then("System should display all the list of existing FD")
	public void system_should_display_all_the_list_of_existing_fd() {

		collateralsPageObject.isDisplayed("");

	}

	@Then("customer sholud allow to select FD details")
	public void customer_sholud_allow_to_select_fd_details() {

		collateralsPageObject.clickOn("");

	}

	// @collateral_24
	@When("Customer select Authorise New FD option")
	public void customer_select_authorise_new_fd_option() {

		collateralsPageObject.clickOn("");

	}

	@Then("System should disply below FD details to customer")
	public void system_should_disply_below_fd_details_to_customer(Map<String, String> userDetails) {

		collateralsPageObject.isDisplayed("");
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));

	}

	// @collateral_25

	@When("Customer is not having Existing FD")
	public void customer_is_not_having_existing_fd() {

		collateralsPageObject.isDisplayed("");

	}

	@Then("System should allow customer to open new FD")
	public void system_should_allow_customer_to_open_new_fd() {

		collateralsPageObject.isDisplayed("");

		collateralsPageObject.clickOn("");

	}

	// @collateral_26
	@Then("System should display below Account details to customer")
	public void system_should_display_below_account_details_to_customer(List<String> dataTable) {

		collateralsPageObject.isDisplayed("");
		collateralsPageObject.isDisplayed("");
		collateralsPageObject.isDisplayed("");
		collateralsPageObject.isDisplayed("");
		collateralsPageObject.isDisplayed("");
		collateralsPageObject.isDisplayed("");

	}

	// @collateral_27

	@When("Customer select Authorise New FD")
	public void customer_select_authorise_new_fd() {

		collateralsPageObject.clickOn("");

	}

	@Then("customer should be able to Enter amount in lacs while Opening the FD")
	public void customer_should_be_able_to_enter_amount_in_lacs_while_opening_the_fd() {

		collateralsPageObject.enterInputWithValue("", "");
	}

	// @collateral_28

	@Then("customer should displays the multiple current accounts if customer having multiple")
	public void customer_should_displays_the_multiple_current_accounts_if_customer_having_multiple() {

		collateralsPageObject.isDisplayed("");

	}

	// @collateral_29

	@When("customer account balance is less")
	public void customer_account_balance_is_less() {

		collateralsPageObject.isDisplayed("");

	}

	@When("customer enters greater value")
	public void customer_enters_greater_value() {

		collateralsPageObject.enterInputWithValue("", "");

	}

	@Then("System should not allow user to greater value")
	public void system_should_not_allow_user_to_greater_value() {

		collateralsPageObject.isDisplayed("");

	}

	@Then("should display below message as")
	public void should_display_below_message_as(String docString) {

		collateralsPageObject.isDisplayed("");

	}
	// @collateral_30

	@When("customer exceeds account limit while opening new FD")
	public void customer_exceeds_account_limit_while_opening_new_fd() {

		collateralsPageObject.isDisplayed("");

	}

	@Then("System should disply below error message as")
	public void system_should_disply_below_error_message_as(String docString) {

		collateralsPageObject.isDisplayed("");

	}

	// @collateral_31

	@When("Customer select collateral Type as following details for Liquid securities and Post Office")
	public void customer_select_collateral_type_as_following_details_for_liquid_securities_and_post_office(
			Map<String, String> userDetails) {

		collateralsPageObject.isDisplayed("");
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));

	}

	@Then("System should display all the following values as per the master")
	public void system_should_display_all_the_following_values_as_per_the_master(Map<String, String> userDetails) {

		collateralsPageObject.isDisplayed("");
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));

	}

	// @collateral_32
	@When("Customer select collateral Type as following details for Liquid securities and Insurance")
	public void customer_select_collateral_type_as_following_details_for_liquid_securities_and_insurance(
			Map<String, String> userDetails) {

		collateralsPageObject.isDisplayed("");
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));

	}

	@Then("system should show all the below values as per the master")
	public void system_should_show_all_the_below_values_as_per_the_master(Map<String, String> userDetails) {

		collateralsPageObject.isDisplayed("");
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));

	}

	// @collateral_33
	@When("Customer select collateral Type as following details for Stock type")
	public void customer_select_collateral_type_as_following_details_for_stock_type(Map<String, String> userDetails) {

		collateralsPageObject.isDisplayed("");
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));

	}

	@Then("system should display all the below values as per the master for type as Stock")
	public void system_should_display_all_the_below_values_as_per_the_master_for_type_as_stock(List<String> dataTable) {

		collateralsPageObject.isDisplayed("");
		collateralsPageObject.isDisplayed("");
		collateralsPageObject.isDisplayed("");
		collateralsPageObject.isDisplayed("");
		collateralsPageObject.isDisplayed("");
		collateralsPageObject.isDisplayed("");

	}

	// @collateral_34
	@Given("Collateral type as ‘Stock/Booked dates’")
	public void collateral_type_as_stock_booked_dates() {

		collateralsPageObject.isDisplayed("");

	}

	@When("Customer upload the Stock Document")
	public void customer_upload_the_stock_document() {

		collateralsPageObject.isDisplayed("");
		collateralsPageObject.clickOn("");

	}

	@Then("Uploaded documents should be only jpeg,png,pdf format")
	public void uploaded_documents_should_be_only_jpeg_png_pdf_format() {

		collateralsPageObject.isDisplayed("");

	}

	@Then("Uploaded document file size should be upto 1MB")
	public void uploaded_document_file_size_should_be_upto_1mb() {

		collateralsPageObject.isDisplayed("");

	}

	// @collateral_35

	@When("Customer select collateral Type as following details for Plant & Machinery")
	public void customer_select_collateral_type_as_following_details_for_plant_machinery(
			Map<String, String> userDetails) {

		collateralsPageObject.isDisplayed("");
		System.out.println("" + collateralsPageObject.getTextContents(""));

	}

	@Then("system should display all the following fields as per the master for type as Plant & Machinery")
	public void system_should_display_all_the_following_fields_as_per_the_master_for_type_as_plant_machinery(
			Map<String, String> userDetails) {

		collateralsPageObject.isDisplayed("");
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));
		System.out.println("" + collateralsPageObject.getTextContents(""));

	}

	// @collateral_36

	@When("Customer click on edit Address option")
	public void customer_click_on_edit_address_option() {

		collateralsPageObject.clickOn("");

	}

	@Then("System should allowed customer to edit Address")
	public void system_should_allowed_customer_to_edit_address() {

		collateralsPageObject.isDisplayed("");
		collateralsPageObject.clickOn("");

	}

	// @collateral_37

	@When("Customer successfully add the all collateral details")
	public void customer_successfully_add_the_all_collateral_details() {

		collateralsPageObject.clickOn("");

	}

	@Then("All added collateral or security list should be displayed on the collateral Screen")
	public void all_added_collateral_or_security_list_should_be_displayed_on_the_collateral_screen() {

		collateralsPageObject.isDisplayed("");

	}

	@Then("Total Collateral Worth amount should be displayed")
	public void total_collateral_worth_amount_should_be_displayed() {

		collateralsPageObject.isDisplayed("");

	}

	@Then("All added collateral or security list should have Edit option")
	public void all_added_collateral_or_security_list_should_have_edit_option() {

		collateralsPageObject.clickOn("");

	}

	// @collateral_38
	@Given("Customer is on Product Details Screen")
	public void customer_is_on_product_details_screen() {

		collateralsPageObject.isDisplayed("");

	}

	@When("Product selected is unsecured")
	public void product_selected_is_unsecured() {

		collateralsPageObject.isDisplayed("");
		collateralsPageObject.clickOn("");

	}

	@When("Loan amount applied is below {int} lacs")
	public void loan_amount_applied_is_below_lacs(Integer int1) {

		collateralsPageObject.isDisplayed("");

	}

	@Then("Collateral capture screen should be skipped")
	public void collateral_capture_screen_should_be_skipped() {

		collateralsPageObject.isDisplayed("");

	}

}
