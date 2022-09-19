package com.harsha.appmodels.screens.android;

import org.openqa.selenium.By;

import com.harsha.core.driver.BaseMobileAppScreen;

import io.appium.java_client.AppiumDriver;

public class AmazonPlaceOrderScreen extends BaseMobileAppScreen {
	private By proceedToCheckout = By.xpath("//android.widget.Button[@resource-id='a-autoid-1-announce']");

	public AmazonPlaceOrderScreen(AppiumDriver driver) {
		super(driver);
	}

	public void placeTheOrder() {
		if (driver.findElements(proceedToCheckout).size() > 0) {
			driver.findElements(proceedToCheckout).get(0).click();
		}
	}
}
