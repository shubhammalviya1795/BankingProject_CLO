package com.intellect.igcb.autotest.stepdefinition;

import java.util.Map;

import com.intellect.igcb.autotest.utility.ApplicationContext;
import io.cucumber.java.en.Given;





public class GenericStep {
	
	@Given("Following Elements Exists")
	public void follwing_Elements_Exists(Map<String, String> aObjectRepository) {
		ApplicationContext.getInstance().storeAttribute("OBJECT_REPOSITORY", aObjectRepository);
	}

}
