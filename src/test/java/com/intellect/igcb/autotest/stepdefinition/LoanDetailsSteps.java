package com.intellect.igcb.autotest.stepdefinition;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.network.Network;

import com.intellect.igcb.autotest.pageobject.AuthenticationPageObject;
import com.intellect.igcb.autotest.pageobject.LoanDetailsPageObject;
import com.intellect.igcb.autotest.pageobject.URCRegistrationPageObject;
import com.intellect.igcb.autotest.utility.DBAccess;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;

public class LoanDetailsSteps {

	AuthenticationPageObject authenticationPageObject;
	URCRegistrationPageObject urcRegistrationPageObject;
	LoanDetailsPageObject loanDetailsPageObject;
	
	
	
	@Given("NTB Customer with following details wish to apply loan")
	public void ntb_customer_with_following_details_wish_to_apply_loan(Map<String, String> aUserDetails) {
		authenticationPageObject.open();
		String tmpMobileNumber = aUserDetails.get("Mobile");
		DBAccess.deleteUserRegistrationByMobile(tmpMobileNumber);
		DBAccess.deleteProposal("22012900000024");
		DBAccess.createNewNTBUserRegistration();
		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", tmpMobileNumber);
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");
		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");
	}

	@When("He sucessfully validates his URC number")
	public void he_sucessfully_validates_his_urc_number() {
		urcRegistrationPageObject.clickOn("URCRegistration.SkipThisStep");
	}

	@Then("he should see the Loan details page with follwing elements")
	public void he_should_see_the_loan_details_page_with_follwing_elements(List<String> aElements) {
		for (String element : aElements) {
			loanDetailsPageObject.isDisplayed("LoanDetails." + element);
		}
	}

	// -----------------------------------
	@Given("he is on Loan Details Page")
	public void he_is_on_loan_details_page() {
		urcRegistrationPageObject.clickOn("URCRegistration.SkipThisStep");
		loanDetailsPageObject.isDisplayed("LoanDetails.Loan_Details_Title");
	}

	@When("he click on Add Product")
	public void he_click_on_add_product() {
		loanDetailsPageObject.clickOn("LoanDetails.Add_Product_Button");
	}

	@Then("he should see the Product details page with follwing elements")
	public void he_should_see_the_product_details_page_with_follwing_elements(List<String> aElements) {
		for (String element : aElements) {
			loanDetailsPageObject.isDisplayed("LoanDetails." + element);
		}
	}

	// ----------------------------------------------
	@Given("He is on Product details Page")
	public void he_is_on_product_details_page(Map<String, String> aFormValues) {
		urcRegistrationPageObject.clickOn("URCRegistration.SkipThisStep");
		loanDetailsPageObject.isDisplayed("LoanDetails.Loan_Details_Title");		
		Set<String> elements = aFormValues.keySet();
		for (String element : elements) {
			loanDetailsPageObject.isDisplayed("LoanDetails." + element);
			if (element.contains("Dropdown")) {
				loanDetailsPageObject.selectValueFor("LoanDetails." + element, aFormValues.get(element));
			}
			if (element.contains("Textfield")) {
				loanDetailsPageObject.enterInputWithValue("LoanDetails." + element, aFormValues.get(element));
			}
			if (element.contains("CheckBox")) {
				if (aFormValues.get(element) != null && aFormValues.get(element).equals("checked")) {
					loanDetailsPageObject.clickOn("LoanDetails." + element);
				}
			}
		}
		loanDetailsPageObject.clickOn("LoanDetails.Add_Product_Button");
	}

	@When("he Enter all Product details")
	public void he_enter_all_product_details(Map<String, String> aFormValues) {
		Set<String> elements = aFormValues.keySet();
		for (String element : elements) {
			loanDetailsPageObject.isDisplayed("LoanDetails." + element);
			if (element.contains("Dropdown")) {
				loanDetailsPageObject.selectValueFor("LoanDetails." + element, aFormValues.get(element));
			}
			if (element.contains("Textfield") || element.contains("TextField")) {
				loanDetailsPageObject.enterInputWithValue("LoanDetails." + element, aFormValues.get(element));
			}
			if (element.contains("CheckBox")) {
				if (aFormValues.get(element) != null && aFormValues.get(element).equals("checked")) {
					loanDetailsPageObject.clickOn("LoanDetails." + element);
				}
			}
		}
	}

	@When("click on Add button")
	public void click_on_add_button() {
		loanDetailsPageObject.clickOn("LoanDetails.Add_Button");
	}

	@Then("he should see the confirmation message {string}")
	public void he_should_see_the_confirmation_message(String string) {
		// TODO
	}

	@Then("he should see the all product details on Loan details Page")
	public void he_should_see_the_all_product_details_on_loan_details_page(Map<String, String> aFormValues) {
		for (Map.Entry<String, String> formFiled : aFormValues.entrySet()) {
			System.out.println("key: " + formFiled.getKey() + " value: " + formFiled.getValue());
			if (loanDetailsPageObject.isDisplayed("LoanDetails." + formFiled.getKey())) {
				Assert.assertEquals("Values are not equal for " + formFiled.getKey(), formFiled.getValue(),
						loanDetailsPageObject.getTextContents("LoanDetails." + formFiled.getKey()));
			}
		}
	}
	// -----------------------

	@Given("NTB Customer has already added one product with following details")
	public void ntb_customer_has_already_added_one_product_with_following_details(Map<String, String> aFormValues) {
		//authenticationPageObject.captureRequestSelenium();
		captureRequestResponse();
		authenticationPageObject.open();
		String tmpMobileNumber = aFormValues.get("Mobile");
		DBAccess.deleteUserRegistrationByMobile(tmpMobileNumber);
		DBAccess.deleteProposal("22012900000024");
		DBAccess.createNewNTBUserRegistrationWithProposal();
		
		authenticationPageObject.enterInputWithValue("AutheticationPage.mobileNumberInput", tmpMobileNumber);
		authenticationPageObject.clickOn("AutheticationPage.getOTPButton");
		for (int i = 0; i < 6; i++) {
			authenticationPageObject.enterInputWithValue("EnterOTPPage.otp" + i, String.valueOf(i + 1));
		}
		authenticationPageObject.clickOn("EnterOTPPage.VerifyOTPBtn");
		if (loanDetailsPageObject.isDisplayed("LoanDetails.ProductList_Name")) {
			Assert.assertEquals("Values are not equal for ProductList_Name ", aFormValues.get("ProductList_Name"),
					loanDetailsPageObject.getTextContents("LoanDetails.ProductList_Name"));
		}
		if (loanDetailsPageObject.isDisplayed("LoanDetails.ProductList_Amount")) {
			Assert.assertEquals("Values are not equal for ProductList_Amount ", aFormValues.get("ProductList_Amount"),
					loanDetailsPageObject.getTextContents("LoanDetails.ProductList_Amount"));
		}
		//loanDetailsPageObject.selectValueFor("LoanDetails.Sector_Dropdown", "Manufacturing");
		//loanDetailsPageObject.selectValueFor("LoanDetails.Facility_Dropdown", "Working Capital");
	}
	
	public void captureRequestResponse() {       
        DevTools devTool = loanDetailsPageObject.getDevTools();
        devTool.createSession();
        devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
       
        devTool.addListener(Network.requestWillBeSent(), requestSent -> {
        	  System.out.println("------------------------request------------------------------");
        	  System.out.println("Request Id => " + requestSent.getRequestId());
              System.out.println("Request URL => " + requestSent.getRequest().getUrl());
              System.out.println("Request Method => " + requestSent.getRequest().getMethod());
              //System.out.println("Request Headers => " + requestSent.getRequest().getHeaders().toString());
              if(requestSent.getRequest().getPostData() != null) {
            	  System.out.println("Request Post data => " + requestSent.getRequest().getPostData().toString());            	  
              }             
              System.out.println("------------------------------------------------------");
        });    
        devTool.addListener(Network.responseReceived(), responseReceieved -> {
        	System.out.println("------------------------response------------------------------");
            System.out.println("Response Url => " + responseReceieved.getResponse().getUrl());
            System.out.println("Reuest Id => " + responseReceieved.getRequestId());
            System.out.println("Response Status => " + responseReceieved.getResponse().getStatus());
            //System.out.println("Response Headers => " + responseReceieved.getResponse().getHeaders().toString());
            System.out.println("Response MIME Type => " + responseReceieved.getResponse().getMimeType().toString());  
            String tmpMime = responseReceieved.getResponse().getMimeType();
            if("application/json".equals(tmpMime) && responseReceieved.getResponse().getStatus() >= 400) {
            	String  responseBody = devTool.send(Network.getResponseBody(responseReceieved.getRequestId())).getBody();
            	System.out.println("Response Content =>" + responseBody);   
            	Serenity.recordReportData().asEvidence().withTitle(responseReceieved.getResponse().getUrl()).andContents(responseBody);
            }           
            
            System.out.println("------------------------------------------------------");

      });

  }

	@When("He click edit icon on existing product details")
	public void he_click_edit_icon_on_existing_product_details() {
		loanDetailsPageObject.clickOn("LoanDetails.ProductList_Edit_Button");
		loanDetailsPageObject.waitExplicit(2);
	}

	@Then("He should be able to edit following details")
	public void he_should_be_able_to_edit_following_details(Map<String, String> aFormValues) {
		Set<String> elements = aFormValues.keySet();
		for (String element : elements) {
			loanDetailsPageObject.isDisplayed("LoanDetails." + element);
			if (element.contains("Dropdown")) {
				loanDetailsPageObject.selectValueFor("LoanDetails." + element, aFormValues.get(element));
			}
			if (element.contains("Textfield") || element.contains("TextField")) {
				loanDetailsPageObject.enterInputWithValue("LoanDetails." + element, aFormValues.get(element));
			}
			if (element.contains("CheckBox")) {
				if (aFormValues.get(element) != null && aFormValues.get(element).equals("checked")) {
					loanDetailsPageObject.clickOn("LoanDetails." + element);
				}
			}
		}
	}

	@Then("click on Update button")
	public void click_on_update_button() {
		loanDetailsPageObject.clickOn("LoanDetails.Update_Button");
	}

	@Then("He should see the updated product details on Loan details Page")
	public void he_should_see_the_updated_product_details_on_loan_details_page(Map<String, String> aFormValues) {
		loanDetailsPageObject.waitExplicit(5);
		for (Map.Entry<String, String> formFiled : aFormValues.entrySet()) {
			System.out.println("key: " + formFiled.getKey() + " value: " + formFiled.getValue());
			if (loanDetailsPageObject.isDisplayed("LoanDetails." + formFiled.getKey())) {
				Assert.assertEquals("Values are not equal for " + formFiled.getKey(), formFiled.getValue(),
						loanDetailsPageObject.getTextContents("LoanDetails." + formFiled.getKey()));
			}
		}
	}
	
	//----------------------------------------------------------
	@When("he click Delete icon on existing product details")
	public void he_click_delete_icon_on_existing_product_details() {
		loanDetailsPageObject.clickOn("LoanDetails.Delete_Button");
	}
	@Then("he should not be able to view Deleted Product on Loan details page")
	public void he_should_not_be_able_to_view_deleted_product_on_loan_details_page() {
		loanDetailsPageObject.isDisplayed("LoanDetails.TotalLoanAmount");
		Assert.assertEquals("Values are not equal for ","₹0",loanDetailsPageObject.getTextContents("LoanDetails.TotalLoanAmount"));
	}
	
	//------------------------------------------------------------
	
	@Then("Total Loan amount is {string}")
	public void total_loan_amount_is(String aAmount) {
		Assert.assertEquals("Values are not equal for Total Amount",aAmount,loanDetailsPageObject.getTextContents("LoanDetails.TotalLoanAmount"));
	}

}
