package com.harsha.appmodels.screens.android;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.harsha.core.Utilities;
import com.harsha.core.W3cActions;
import com.harsha.core.driver.BaseMobileAppScreen;

import io.appium.java_client.AppiumDriver;

public class AmazonProductListScreen extends BaseMobileAppScreen {
	private static final Logger logger = LogManager.getLogger(FilpkartProductListScreen.class);
	private WebDriverWait wait;
	private By shopByPriceLoc = By.xpath("//android.view.View[@text='SHOP BY PRICE']");
	private By resultsViewLoc = By.xpath("//android.view.View[@text='RESULTS']");
	private By priceFinderLoc = By.xpath("//android.view.View[contains(@text,'M.R.P.')]");
	private String highestPriceViewLoc = "//android.view.View[descendant::android.view.View[contains(@text,'%s')]]";
	private String productDetailsLoc = "//android.view.View[descendant::android.view.View[contains(@text,'%s')]]/android.view.View[contains(@text,'%s')]";

	public AmazonProductListScreen(AppiumDriver driver) {
		super(driver);
	}

	public void fecthHighestPriceProduct() {
		wait = new WebDriverWait((WebDriver) driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(resultsViewLoc));

		/*
		 * Collect the Product Price details by scrolling the screen until driver finds
		 * SHOP BY PRICE
		 */
		Set<String> productPriceDetails = collectProductPriceDetails();
		logger.debug("productPriceDetails -->" + productPriceDetails);
		String highestPrice = Utilities.findHighestPrice(productPriceDetails);
		/*
		 * Select the Highest price product. Scrolling until the driver finds the
		 * product or Top of the screen
		 */
		selectHighestPriceProductByScroll(highestPrice);
	}

	/**
	 * Collect the Product Price Details by scrolling the screen
	 * 
	 * @return set of products
	 */
	private Set<String> collectProductPriceDetails() {
		Set<String> priceDetails = new HashSet<String>();
		List<WebElement> findProductPriceDetails = driver.findElements(priceFinderLoc);
		for (WebElement productPrice : findProductPriceDetails) {
			if (StringUtils.isNotBlank(productPrice.getAttribute("Text"))) {
				priceDetails.add(productPrice.getAttribute("Text"));
			}
		}
		recursiveFunctionPriceDetails(priceDetails);
		return priceDetails;
	}

	/**
	 * recursive function to fetch the products
	 * 
	 * @param priceDetails
	 * @return boolean
	 */
	private boolean recursiveFunctionPriceDetails(Set<String> priceDetails) {
		if (driver.findElements(shopByPriceLoc).size() > 0) {
			return false;
		} else {
			W3cActions.doScrollDown(driver);
			List<WebElement> findProductPriceDetails = driver.findElements(priceFinderLoc);
			for (WebElement productPrice : findProductPriceDetails) {
				if (StringUtils.isNotBlank(productPrice.getAttribute("Text"))) {
					priceDetails.add(productPrice.getAttribute("Text"));
				}
			}
			return recursiveFunctionPriceDetails(priceDetails);
		}
	}

	/**
	 * selects the Highest priced product by scrolling the screen
	 * 
	 * @param highestPrice
	 */
	private void selectHighestPriceProductByScroll(String highestPrice) {
		List<WebElement> findProductPriceDetails = driver
				.findElements(By.xpath(String.format(highestPriceViewLoc, highestPrice)));
		logger.debug("findProductPriceDetails size-->" + findProductPriceDetails.size());
		if (findProductPriceDetails.size() > 0) {
			selectHighestPriceProduct(highestPrice);
		} else {
			recursiveFunctionProductSelection(highestPrice);
		}
	}

	/**
	 * select the highest priced product
	 * 
	 * @param highestPrice
	 * @return productName
	 */
	private String selectHighestPriceProduct(String highestPrice) {
		List<WebElement> findProductDetails = driver
				.findElements(By.xpath(String.format(productDetailsLoc, highestPrice, "Samsung")));
		logger.debug("findProductDetails size-->" + findProductDetails.size());
		String productName = null;
		for (WebElement product : findProductDetails) {
			if (StringUtils.isNotBlank(product.getAttribute("text"))) {
				productName = product.getAttribute("text");
				logger.debug("Product Name: " + productName);
				logger.debug("Product Price: " + highestPrice);
				Utilities.takesScreenshot(driver, "HighestPriceProductDetails");
				product.click();
				Utilities.explicitWaitPeriod(2);
				break;
			}
		}
		return productName;
	}

	/**
	 * recursive function to select the matched product
	 * 
	 * @param highestPrice
	 * @return boolean
	 */
	private boolean recursiveFunctionProductSelection(String highestPrice) {
		if (driver.findElements(resultsViewLoc).size() > 0) {
			return false;
		} else {
			W3cActions.doScrollUp(driver);
			List<WebElement> findProductPriceDetails = driver
					.findElements(By.xpath(String.format(highestPriceViewLoc, highestPrice)));
			if (findProductPriceDetails.size() > 0) {
				selectHighestPriceProduct(highestPrice);
				return false;
			} else {
				return recursiveFunctionProductSelection(highestPrice);
			}
		}
	}

}
