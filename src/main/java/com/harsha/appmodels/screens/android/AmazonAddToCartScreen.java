package com.harsha.appmodels.screens.android;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.harsha.core.Utilities;
import com.harsha.core.W3cActions;
import com.harsha.core.driver.BaseMobileAppScreen;

import io.appium.java_client.AppiumDriver;

public class AmazonAddToCartScreen extends BaseMobileAppScreen {
	private static final Logger logger = LogManager.getLogger(FilpkartProductListScreen.class);
	private By addToCartLoc = By.xpath("//android.widget.Button[@resource-id='add-to-cart-button']");

	public AmazonAddToCartScreen(AppiumDriver driver) {
		super(driver);
	}

	public void addProductToCart() {
		List<WebElement> findAddToCart = driver.findElements(addToCartLoc);
		logger.debug("findAddToCart size:" + findAddToCart.size());
		if (findAddToCart.size() > 0) {
			findAddToCart.get(0).click();
		} else {
			Utilities.takesScreenshot(driver, "ProductDetails");
			W3cActions.doScrollDown(driver);
			recursiveFunctionToFindAddToCart();
		}
	}

	/**
	 * Recursive function to find the Add To Cart Button.
	 */
	private void recursiveFunctionToFindAddToCart() {
		List<WebElement> findAddToCart = driver.findElements(addToCartLoc);
		logger.debug("recursiveFunctionToFindAddToCart findAddToCart size:" + findAddToCart.size());
		if (findAddToCart.size() > 0) {
			findAddToCart.get(0).click();
		} else {
			Utilities.takesScreenshot(driver, "ProductDetails");
			W3cActions.doScrollDown(driver);
			recursiveFunctionToFindAddToCart();
		}
	}
}
