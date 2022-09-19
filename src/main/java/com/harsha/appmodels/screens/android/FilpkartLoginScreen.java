package com.harsha.appmodels.screens.android;

import org.openqa.selenium.By;

import com.harsha.core.driver.BaseMobileAppScreen;

import io.appium.java_client.AppiumDriver;

public class FilpkartLoginScreen extends BaseMobileAppScreen {
	private By customerBackBtnLocator = By
			.xpath("//android.widget.ImageView[@resource-id='com.flipkart.android:id/custom_back_icon']");

	public FilpkartLoginScreen(AppiumDriver driver) {
		super(driver);
	}

	public void cancelLoginOption() {
		if (driver.findElements(customerBackBtnLocator).size() > 0) {
			driver.findElements(customerBackBtnLocator).get(0).click();
		}
	}

}
