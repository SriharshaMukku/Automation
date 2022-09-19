package com.harsha.appmodels.screens.android;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.harsha.core.Utilities;
import com.harsha.core.driver.BaseMobileAppScreen;

import io.appium.java_client.AppiumDriver;

public class FlipkartPlaceOrderScreen extends BaseMobileAppScreen {
	private WebDriverWait wait;
	private By placeOrderBtn = By.xpath("//android.view.ViewGroup/android.widget.TextView[@text='Place order']");
	
	public FlipkartPlaceOrderScreen(AppiumDriver driver) {
		super(driver);
	}
	
	public void placeOrder() {
		/*Wait for the Add to Cart*/
		wait = new WebDriverWait((WebDriver)driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(placeOrderBtn));
		driver.findElement(placeOrderBtn).click();
		Utilities.explicitWaitPeriod(5);
	}
	//Place order
	
}
