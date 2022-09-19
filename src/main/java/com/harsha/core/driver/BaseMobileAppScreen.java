package com.harsha.core.driver;

import io.appium.java_client.AppiumDriver;

public abstract class BaseMobileAppScreen {
	protected AppiumDriver driver;

	public BaseMobileAppScreen(AppiumDriver driver) {
		this.driver = driver;
	}
}
