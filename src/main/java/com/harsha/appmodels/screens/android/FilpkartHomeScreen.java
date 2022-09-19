package com.harsha.appmodels.screens.android;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.harsha.core.Utilities;
import com.harsha.core.W3cActions;
import com.harsha.core.driver.BaseMobileAppScreen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class FilpkartHomeScreen extends BaseMobileAppScreen {
	private WebDriverWait wait;
	private By searchPrdLocator = By.xpath("//android.view.ViewGroup[descendant::android.widget.TextView[@text='Brand Mall']]//android.view.ViewGroup[2]");
	private By brandMall = By.xpath("//android.view.ViewGroup[descendant::android.widget.TextView[@text='Brand Mall']]");
	
	private By editPrdLocator = By.xpath("//android.widget.FrameLayout[@resource-id='com.flipkart.android:id/main_content']"
			+ "//android.widget.EditText[@text='Search for Products, Brands and More']");
	    
	public FilpkartHomeScreen(AppiumDriver driver) {
		super(driver);
	}
	
	public void searchProductItem(String productName) {
		/*Wait for the Home page load by locating Brand Mall ICON*/
		wait = new WebDriverWait((WebDriver)driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(brandMall));
		findSearchProductLocator();
		enterProductInfo(productName);
	}
	/**
	 * Due to screen alerts the search product information is not clickable/accessible hence checking the condition twice
	 */
	private void findSearchProductLocator() {
		List<WebElement> searchElemts = driver.findElements(searchPrdLocator);
		
		if(searchElemts.size()>0) {
			searchElemts.get(0).click();
		}else {
			List<WebElement> brandMallElemts = driver.findElements(brandMall);
			if(brandMallElemts.size()>0) {
				Point source = brandMallElemts.get(0).getLocation();
				W3cActions.doTap(driver, source, 100);
			}
			
			List<WebElement> searchElemts1 = driver.findElements(searchPrdLocator);
			if(searchElemts1.size()>0) {
				searchElemts1.get(0).click();
			}
		}
	}
	private void enterProductInfo(String productName) {
		if(driver.findElements(editPrdLocator).size()>0) {
			WebElement editPrdLoc = driver.findElements(editPrdLocator).get(0);
			editPrdLoc.sendKeys(productName);
			((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
			Utilities.explicitWaitPeriod(3);
		}
	}

}
