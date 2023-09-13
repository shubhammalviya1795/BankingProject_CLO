package com.intellect.igcb.autotest;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty","json:report.json"},
        features = "src/test/resources/features"
        ,
        tags="@RegsitrationWithPAN"
        
)
public class CucumberTestSuite {}
