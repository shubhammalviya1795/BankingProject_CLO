package com.intellect.igcb.autotest.pageobject;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.network.Network;
import org.openqa.selenium.devtools.v96.network.model.RequestId;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.intellect.igcb.autotest.stepdefinition.LoanDetailsSteps;
import com.intellect.igcb.autotest.utility.Util;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;

public class GenericPageObject extends PageObject {
	
	
	public boolean verifyPageTitle(String aTitle) {
		return getDriver().getTitle().equals(aTitle);
	}
	
	
	public String getTextContents(String aElement) {
		String tmpLocator = Util.getLocatorFromApplicationContextObjRepository(aElement);
		WebElement id = getDriver().findElement(By.xpath(tmpLocator));	
		return id.getText();
	}
	
	public String getTextValue(String aElement) {
		String tmpLocator = Util.getLocatorFromApplicationContextObjRepository(aElement);
		WebElement id = getDriver().findElement(By.xpath(tmpLocator));	
		return id.getAttribute("value");
	}
	
	public boolean isEnabled(String aElement) {
		String tmpLocator = Util.getLocatorFromApplicationContextObjRepository(aElement);
		WebElement id = getDriver().findElement(By.xpath(tmpLocator));	
		return id.isEnabled();
	}
	
	
	public void clickOn(String aElement) {
		String tmpLocator = Util.getLocatorFromApplicationContextObjRepository(aElement);
		WebElement id = getDriver().findElement(By.xpath(tmpLocator));		
		expectedCondition(id);		
		id.click();
		
	}
	
	public void clickOnUsingJS(String aElement) {
		String tmpLocator = Util.getLocatorFromApplicationContextObjRepository(aElement);
		WebElement id = getDriver().findElement(By.xpath(tmpLocator));		
		expectedCondition(id);		
		JavascriptExecutor executor = (JavascriptExecutor)getDriver();
		executor.executeScript("arguments[0].click();", id);		
	}
	
	public void bringInView(String aElement) {
		String tmpLocator = Util.getLocatorFromApplicationContextObjRepository(aElement);
		WebElement id = getDriver().findElement(By.xpath(tmpLocator));		
		expectedCondition(id);		
		JavascriptExecutor executor = (JavascriptExecutor)getDriver();
		executor.executeScript("arguments[0].scrollIntoView(true);",id);		
	}
	
	public boolean isDisplayed(String aElement) {
		String tmpLocator = Util.getLocatorFromApplicationContextObjRepository(aElement);
		WebElement id = getDriver().findElement(By.xpath(tmpLocator));
		return expectedCondition(id);		
	}	
	
	public void selectValueFor(String aElement, String aValue) {
		String tmpLocator = Util.getLocatorFromApplicationContextObjRepository(aElement);
		WebElement id = $(tmpLocator);
		id.click();		
		WebElement selectedValue = $("//*/li/span[text()='" + aValue + "']");			
		selectedValue.click();		
	}
	
	public void enterInputWithValue(String aElement, String aValue) {
		String tmpLocator = Util.getLocatorFromApplicationContextObjRepository(aElement);
		WebElement id = $(tmpLocator);
		if(expectedCondition(id)) {
			id.clear();			
			id.sendKeys(aValue);		
			id.sendKeys(Keys.TAB);			
		}
	}

	public void closeAllWindows() {
		Set<String> s = getDriver().getWindowHandles();
		Iterator<String> ite = s.iterator();

		while (ite.hasNext()) {
			String tmpWindowHandle = ite.next().toString();
			getDriver().switchTo().window(tmpWindowHandle);
			System.out.println("Window with follwing title is closing now : " + getDriver().getTitle());
			getDriver().close();
		}
		
		

	}

	public void closeBrowser() {
		getDriver().quit();
	}

	public boolean expectedCondition(WebElement element) {

		long time = System.currentTimeMillis();
		try {
			Thread.sleep(500);
			ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return element.isDisplayed();
				}
			};
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
			Boolean tmpResult = wait.until(expectation);
			return tmpResult;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(getElementDetails(element) + "Is Not Displayed");
			return false;
		} finally {
			System.out.println("Time req to find " + getElementDetails(element) + " is "
					+ (System.currentTimeMillis() - time) + "ms");
		}
	}

	public boolean expectedCondition(WebElement element, long implicitTime, long explicitTime) {

		long time = System.currentTimeMillis();
		try {
			Thread.sleep(implicitTime * 1000);
			ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return element.isDisplayed();
				}
			};
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(explicitTime));
			Boolean tmpResult = wait.until(expectation);
			return tmpResult;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(getElementDetails(element) + "Is Not Displayed");
			return false;
		} finally {
			System.out.println("parameterized Time req to find " + getElementDetails(element) + " is "
					+ (System.currentTimeMillis() - time) + "ms");
		}
	}

	public String getElementDetails(WebElement aWebElement) {
		StringBuffer tmpResult = new StringBuffer();
		tmpResult.append("Tag Name : ");
		tmpResult.append(aWebElement.getTagName());
		tmpResult.append(" Value : ");
		tmpResult.append(aWebElement.getAttribute("value"));
		tmpResult.append(" Id : ");
		tmpResult.append(aWebElement.getAttribute("id"));
		tmpResult.append(" Text : ");
		tmpResult.append(aWebElement.getText());
		tmpResult.append(" Name : ");
		tmpResult.append(aWebElement.getAttribute("name"));
		return tmpResult.toString();
	}
	
	public void waitExplicit(int seconds) {
		
		try {
			Thread.sleep(seconds *1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void captureRequestSelenium() {       
        DevTools devTool = getDevTools();
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
            }           
            
            System.out.println("------------------------------------------------------");

      });

  }

}
