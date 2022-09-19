package com.harsha.appmodels.screens.android;

import org.openqa.selenium.By;

import com.harsha.core.Utilities;
import com.harsha.core.driver.BaseMobileAppScreen;

import io.appium.java_client.AppiumDriver;

public class FilpkartLanguageSelectionScreen extends BaseMobileAppScreen {
	
	private String lanSelectionLocator = "//android.widget.RelativeLayout[@resource-id='com.flipkart.android:id/locale_icon_layout' " + 
			"	and descendant::android.widget.TextView[" + 
			"		@resource-id='com.flipkart.android:id/tv_subtext' and @text='%s']" + 
			"	]//android.widget.ImageView[@resource-id='com.flipkart.android:id/iv_checkbox']";
	private By continueBtnLanSelLocator = By.xpath("//android.widget.LinearLayout[@resource-id='com.flipkart.android:id/ll_select_btn']");
	    
	public FilpkartLanguageSelectionScreen(AppiumDriver driver) {
		super(driver);
	}
	
	public void selectLanguage(String language) {
		if(driver.findElements(By.xpath(String.format(lanSelectionLocator, language))).size()>0) {
			driver.findElements(By.xpath(String.format(lanSelectionLocator, language))).get(0).click();
			Utilities.explicitWaitPeriod(3);
		}
		if(driver.findElements(continueBtnLanSelLocator).size()>0) {
			driver.findElements(continueBtnLanSelLocator).get(0).click();
			Utilities.explicitWaitPeriod(3);
		}
    }
}
