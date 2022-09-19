package com.harsha.appmodels.screens.android;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.harsha.core.Utilities;
import com.harsha.core.driver.BaseMobileAppScreen;

import io.appium.java_client.AppiumDriver;

public class FilpkartAddToCartScreen extends BaseMobileAppScreen {
	private WebDriverWait wait;
	private String addAndGoToCartBtn = "//android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='%s']";
	private String productValidationLoc = "//android.widget.TextView[@text='%s']";

	public FilpkartAddToCartScreen(AppiumDriver driver) {
		super(driver);
	}

	public void addProductToCart(String selectedProduct) {
		/* Wait for the Add to Cart */
		wait = new WebDriverWait((WebDriver) driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath(String.format(addAndGoToCartBtn, "Add to cart"))));
		validateProduct(selectedProduct);
		driver.findElement(By.xpath(String.format(addAndGoToCartBtn, "Add to cart"))).click();
		wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath(String.format(addAndGoToCartBtn, "Go to cart"))));
		driver.findElement(By.xpath(String.format(addAndGoToCartBtn, "Go to cart"))).click();
	}

	private void validateProduct(String selectedProduct) {
		Utilities.takesScreenshot(driver, "FinalProduct");
		Assert.assertEquals(driver.findElements(By.xpath(String.format(productValidationLoc, selectedProduct))).size(),
				1);
	}

}
