package com.harsha.apps.android;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.harsha.appmodels.screens.android.AmazonAddToCartScreen;
import com.harsha.appmodels.screens.android.AmazonHomeScreen;
import com.harsha.appmodels.screens.android.AmazonLanguageSelectionScreen;
import com.harsha.appmodels.screens.android.AmazonLoginScreen;
import com.harsha.appmodels.screens.android.AmazonPlaceOrderScreen;
import com.harsha.appmodels.screens.android.AmazonProductListScreen;
import com.harsha.apps.AmazonIndia;
import com.harsha.core.driver.BaseMobileApp;

import io.appium.java_client.AppiumDriver;

public class AmazonIndiaAndroid extends BaseMobileApp implements AmazonIndia {
	private static final Logger logger = LogManager.getLogger(AmazonIndiaAndroid.class);
	private AmazonLanguageSelectionScreen languageSelectionScreen;
	private AmazonLoginScreen loginScreen;
	private AmazonHomeScreen homeScreen;
	private AmazonProductListScreen productListScreen;
	private AmazonAddToCartScreen addToCartScreen;
	private AmazonPlaceOrderScreen placeOrderScreen;

	public AmazonIndiaAndroid(AppiumDriver driver) {
		super(driver);
	}

	@Override
	protected void buildApp() {
		languageSelectionScreen = new AmazonLanguageSelectionScreen(driver);
		loginScreen = new AmazonLoginScreen(driver);
		homeScreen = new AmazonHomeScreen(driver);
		productListScreen = new AmazonProductListScreen(driver);
		addToCartScreen = new AmazonAddToCartScreen(driver);
		placeOrderScreen = new AmazonPlaceOrderScreen(driver);
	}

	public void addToCartHighestPriceProduct(String productName) {
		languageSelectionScreen.selectLanguage("English");
		loginScreen.skipSignInOption();
		logger.debug("Before calling Homescreen");
		homeScreen.searchProductItem(productName);
		logger.debug("Before calling Product Screen");
		productListScreen.fecthHighestPriceProduct();
		logger.debug("Before calling Add to cart Screen");
		addToCartScreen.addProductToCart();
		logger.debug("Before calling Place Order Screen");
		placeOrderScreen.placeTheOrder();
	}
}
