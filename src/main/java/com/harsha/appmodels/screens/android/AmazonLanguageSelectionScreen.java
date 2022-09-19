package com.harsha.appmodels.screens.android;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.harsha.core.Utilities;
import com.harsha.core.driver.BaseMobileAppScreen;

import io.appium.java_client.AppiumDriver;

public class AmazonLanguageSelectionScreen extends BaseMobileAppScreen {
	private WebDriverWait wait;
	private By continueBtnLanSelLocator = By.id("in.amazon.mShop.android.shopping:id/continue_button");
	private String lanSelLocator = "//android.widget.ImageView[@content-desc='%s']";
	    
	public AmazonLanguageSelectionScreen(AppiumDriver driver) {
		super(driver);
	}
	
	public void selectLanguage(String language) {
		if("English".equals(language)) {
			language = "Select English";
		}
		if(driver.findElements(By.xpath(String.format(lanSelLocator, language))).size()>0) {
			driver.findElements(By.xpath(String.format(lanSelLocator, language))).get(0).click();
			
			wait = new WebDriverWait((WebDriver)driver, Duration.ofSeconds(20));
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(continueBtnLanSelLocator));
			driver.findElement(continueBtnLanSelLocator).click();
			Utilities.explicitWaitPeriod(3);
		}
    }
}
