package com.harsha.appmodels.screens.android;

import org.openqa.selenium.By;

import com.harsha.core.driver.BaseMobileAppScreen;

import io.appium.java_client.AppiumDriver;

public class AmazonLoginScreen extends BaseMobileAppScreen {
	private By skipSingInBtnLoc = By.id("in.amazon.mShop.android.shopping:id/skip_sign_in_button");

	public AmazonLoginScreen(AppiumDriver driver) {
		super(driver);
	}

	public void skipSignInOption() {
		if (driver.findElements(skipSingInBtnLoc).size() > 0) {
			driver.findElements(skipSingInBtnLoc).get(0).click();
		}
	}
}
