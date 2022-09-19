package com.harsha.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.harsha.apps.AmazonIndia;
import com.harsha.apps.android.AmazonIndiaAndroid;
import com.harsha.core.MobileBaseTest;

public class ECommerceAmazonAppTest extends MobileBaseTest {
	private static final Logger logger = LogManager.getLogger(ECommerceAmazonAppTest.class);
	private AmazonIndia amazonIndia;

	@Override
	public void buildTest(String buildApp) {
		logger.debug("buildTest buildApp -->" + buildApp);
		if ("android".equals(buildApp)) {
			amazonIndia = new AmazonIndiaAndroid(driver);
		} else if ("ios".equals(buildApp)) {
			// Yet to implement
		}
	}

	@Test
	public void addToCartHighestPriceDevice() {
		logger.debug("Start- addToCartHighestPriceDevice");
		amazonIndia.addToCartHighestPriceProduct("samsum mobiles");
		logger.debug("End- addToCartHighestPriceDevice");
	}
}
