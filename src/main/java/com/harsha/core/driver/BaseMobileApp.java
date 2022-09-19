package com.harsha.core.driver;

import io.appium.java_client.AppiumDriver;

public abstract class BaseMobileApp {
	
	protected AppiumDriver driver;
	protected abstract void buildApp();
	
	public BaseMobileApp(AppiumDriver driver) {
        this.driver = driver;
        buildApp();
    }
}
