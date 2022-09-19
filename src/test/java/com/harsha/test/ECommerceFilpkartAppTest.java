package com.harsha.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.harsha.apps.Filpkart;
import com.harsha.apps.android.FlipkartAndroid;
import com.harsha.core.MobileBaseTest;

public class ECommerceFilpkartAppTest extends MobileBaseTest {
	private static final Logger logger = LogManager.getLogger(ECommerceFilpkartAppTest.class);
	private Filpkart filpkart;
	
	@Override
	public void buildTest(String buildApp) {
		logger.debug("buildTest buildApp -->" + buildApp);
		if ("android".equals(buildApp)) {
			filpkart = new FlipkartAndroid(driver);
		} else if ("ios".equals(buildApp)) {
			// Yet to implement
		}
	}

	@Test
	public void addToCartHighestPriceDevice() {
		logger.debug("Before addToCartHighestPriceDevice" );
		filpkart.addToCartHighestPriceProduct("IPhone");
		logger.debug("After addToCartHighestPriceDevice" );
	}

}
