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

public class FilpkartProductListScreen extends BaseMobileAppScreen {
	private static final Logger logger = LogManager.getLogger(FilpkartProductListScreen.class);
	private WebDriverWait wait;
	private By allowLocationPermissionFrame = By.id("com.flipkart.android:id/permission_title");
	private By allowLocationNotnowBtn = By.id("com.flipkart.android:id/not_now_button");
	/*private By productsGNView = By.id("com.flipkart.android:id/gnView");
	private By productGroupByScroolView = By
			.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup["
					+ " descendant::android.widget.TextView[@text='View All Variants' ]] ");
	private By fetchPriceTextLoc = By.xpath(
			"//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[descendant::android.widget.TextView[@index='10']]");
	private By productsScrollViewGroupLoc = By
			.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup");
	private By productsScrollViewGroupLocLevel3 = By
			.xpath("//android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup");
	private By productsScrollViewGroupLocLevel4 = By
			.xpath("//android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup");
	private String productsTextLocator = "//android.widget.TextView[@index='%s']";*/
	private By productsDialogContent = By.id("com.flipkart.android:id/dialog_content");
	private By textContainsInrCurrencyLoc = By.xpath("//android.widget.TextView[contains(@text,'₹')]");
	private By viewAllVariantsLoc = By.xpath("//android.widget.TextView[@text='View All Variants']");
	//private String highestPriceProductLoc = "//android.view.ViewGroup[descendant::android.widget.TextView[@text='%s']]";
	private String highestPriceProductNameLoc = "//android.view.ViewGroup[descendant::android.widget.TextView[@text='%s']]/android.widget.TextView[contains(@text,'%s')]";

	public FilpkartProductListScreen(AppiumDriver driver) {
		super(driver);
	}

	public void handleLocationPermissionFrame() {
		Utilities.explicitWaitPeriod(5);
		if (driver.findElements(allowLocationPermissionFrame).size() > 0) {
			driver.findElement(allowLocationNotnowBtn).click();
			Utilities.explicitWaitPeriod(3);
		}
	}

	public String findHighestPriceProduct() {
		// Click the View All Variants View Group to load all the products
		if (driver.findElements(viewAllVariantsLoc).size() > 1) {
			driver.findElements(viewAllVariantsLoc).get(1).click();
			Utilities.explicitWaitPeriod(3);
		}
		// Wait for Dialog content com.flipkart.android:id/dialog_content
		wait = new WebDriverWait((WebDriver) driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productsDialogContent));
		Set<String> priceDetails = fetchProductPricesByScrolling();
		logger.debug("priceDetails -->" + priceDetails);
		String highestPrice = Utilities.findHighestPriceFlipkart(priceDetails);
		logger.debug("highestPrice -->" + highestPrice);
		String highestPricedProduct = findHighestPriceProductViewByScrolling(highestPrice);
		logger.debug("highestPricedProduct -->" + highestPricedProduct);
		Utilities.explicitWaitPeriod(5);
		// fetchProductScroolViewGroup();
		// selectHighestPriceProduct();
		return highestPricedProduct;
	}

	private Set<String> fetchProductPricesByScrolling() {
		Set<String> priceDetails = new HashSet<String>();
		List<WebElement> priceProducts = driver.findElements(textContainsInrCurrencyLoc);
		logger.debug("textContainsInrCurrency -->" + priceProducts.size());
		for (WebElement priceElemt : priceProducts) {
			if (StringUtils.isNotBlank(priceElemt.getAttribute("text"))) {
				priceDetails.add(priceElemt.getAttribute("text"));
			}
		}
		// Hard coding the number of scrolls to minimize the execution time
		int scrollTimes = 4;
		for (int i = 0; i < scrollTimes; i++) {
			W3cActions.doScrollDown(driver);
			priceProducts = driver.findElements(textContainsInrCurrencyLoc);
			logger.debug("textContainsInrCurrency -->" + priceProducts.size());
			for (WebElement priceElemt : priceProducts) {
				if (StringUtils.isNotBlank(priceElemt.getAttribute("text"))) {
					priceDetails.add(priceElemt.getAttribute("text"));
				}
			}
		}
		return priceDetails;
	}

	private String findHighestPriceProductViewByScrolling(String highestPrice) {
		String productName = null;
		Utilities.takesScreenshot(driver, "ProductDetails");
		List<WebElement> highestPriceProductNames = driver
				.findElements(By.xpath(String.format(highestPriceProductNameLoc, highestPrice, "APPLE")));
		logger.debug("highestPriceProductNames -->" + highestPriceProductNames.size());
		if (highestPriceProductNames.size() > 0) {
			// Fetch the product Name
			WebElement elemt = highestPriceProductNames.get(0);
			productName = elemt.getAttribute("text");
			logger.debug("productName -->" + productName);
			elemt.click();
		} else {
			// Hard coding the number of scrolls to minimize the execution time
			int scrollTimes = 4;
			for (int i = 0; i < scrollTimes; i++) {
				W3cActions.doScrollUp(driver);
				Utilities.takesScreenshot(driver, "ProductDetails");
				highestPriceProductNames = driver
						.findElements(By.xpath(String.format(highestPriceProductNameLoc, highestPrice, "APPLE")));
				logger.debug("highestPriceProductNames -->" + highestPriceProductNames.size());
				if (highestPriceProductNames.size() > 0) {
					// Fetch the product Name
					WebElement elemt = highestPriceProductNames.get(0);
					productName = elemt.getAttribute("text");
					logger.debug("productName -->" + productName);
					elemt.click();
					break;
				}
			}
		}
		return productName;
	}

}
