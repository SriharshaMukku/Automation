package com.harsha.apps.android;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.harsha.appmodels.screens.android.FilpkartAddToCartScreen;
import com.harsha.appmodels.screens.android.FilpkartHomeScreen;
import com.harsha.appmodels.screens.android.FilpkartLanguageSelectionScreen;
import com.harsha.appmodels.screens.android.FilpkartLoginScreen;
import com.harsha.appmodels.screens.android.FilpkartProductListScreen;
import com.harsha.appmodels.screens.android.FlipkartPlaceOrderScreen;
import com.harsha.apps.Filpkart;
import com.harsha.core.driver.BaseMobileApp;

import io.appium.java_client.AppiumDriver;

public class FlipkartAndroid extends BaseMobileApp implements Filpkart {
	private static final Logger logger = LogManager.getLogger(FlipkartAndroid.class);
	private FilpkartLanguageSelectionScreen languageSelectionScreen;
	private FilpkartLoginScreen loginScreen;
	private FilpkartHomeScreen homeScreen;
	private FilpkartProductListScreen productListScreen;
	private FilpkartAddToCartScreen addToCartScreen;
	private FlipkartPlaceOrderScreen placeOrderScreen;

	public FlipkartAndroid(AppiumDriver driver) {
		super(driver);
	}

	@Override
	protected void buildApp() {
		languageSelectionScreen = new FilpkartLanguageSelectionScreen(driver);
		loginScreen = new FilpkartLoginScreen(driver);
		homeScreen = new FilpkartHomeScreen(driver);
		productListScreen = new FilpkartProductListScreen(driver);
		addToCartScreen = new FilpkartAddToCartScreen(driver);
		placeOrderScreen = new FlipkartPlaceOrderScreen(driver);
	}

	public void addToCartHighestPriceProduct(String productName) {
		languageSelectionScreen.selectLanguage("English");
		loginScreen.cancelLoginOption();
		logger.debug("Before calling Homescreen");
		homeScreen.searchProductItem(productName);
		logger.debug("After calling Homescreen and handleLocation");
		productListScreen.handleLocationPermissionFrame();
		logger.debug("Before calling Product Screen");
		String selectedProduct = productListScreen.findHighestPriceProduct();
		logger.debug("Before calling Add to cart Screen");
		addToCartScreen.addProductToCart(selectedProduct);
		logger.debug("Before calling Place Order Screen");
		placeOrderScreen.placeOrder();
		//Time being calling cancel option
		loginScreen.cancelLoginOption();
	}

}
