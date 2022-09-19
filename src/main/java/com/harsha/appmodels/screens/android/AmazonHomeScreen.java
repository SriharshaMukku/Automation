package com.harsha.appmodels.screens.android;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.harsha.core.Utilities;
import com.harsha.core.driver.BaseMobileAppScreen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AmazonHomeScreen extends BaseMobileAppScreen {
	private static final Logger logger = LogManager.getLogger(AmazonHomeScreen.class);
	private WebDriverWait wait;
	private By searchHintViewLoc = By.id("in.amazon.mShop.android.shopping:id/chrome_search_hint_view");
	private By searchTextViewLoc = By.id("in.amazon.mShop.android.shopping:id/rs_search_src_text");
	
	
	public AmazonHomeScreen(AppiumDriver driver) {
		super(driver);
	}
	
	public void searchProductItem(String productName) {
		logger.debug("productName-->");
		wait = new WebDriverWait((WebDriver)driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchHintViewLoc));
		//Language selection Page - Start
		if(driver.findElements(searchHintViewLoc).size()>0) {
			driver.findElements(searchHintViewLoc).get(0).click();
			driver.findElement(searchTextViewLoc).sendKeys(productName); //
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
			Utilities.explicitWaitPeriod(3);
		}
	}

}
