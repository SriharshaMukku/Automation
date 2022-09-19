package com.harsha.core;

import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.AppiumDriver;

public class Utilities {
	private static final Logger logger = LogManager.getLogger(Utilities.class);

	public static void explicitWaitPeriod(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException exe) {
			/*
			 * In order to know the tread interruption, logging the error message and not re
			 * throwing the error message to continue to get the test scenario execution.
			 */
			logger.error("Error occured during explicitWaitPeriod ->" + exe.getMessage());
		}
	}

	public static void takesScreenshot(AppiumDriver driver, String screenName) {
		try {
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss");
			String strDate = dateFormat.format(date);
			File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// Time being hard coded the file path
			File destFile = new File(
					"F:\\Harsha\\AppiumAutomation\\WS\\automation\\target\\" + screenName + strDate + ".png");
			FileUtils.copyFile(file, destFile);

		} catch (Exception exe) {
			logger.error("Error occured during takesScreenshot ->" + exe.getMessage());
		}
	}

	/***
	 * This method is to find the highest price from a Indian currency display
	 * 
	 * @param priceDetails
	 * @return price
	 */
	public static String findHighestPrice(Set<String> priceDetails) {
		Set<Integer> highestPrice = new HashSet<Integer>();
		for (String priceDet : priceDetails) {
			int price = findPrice(priceDet);
			if (price != 0) {
				highestPrice.add(price);
			}
		}
		Integer maxPrice = Collections.max(highestPrice);
		DecimalFormat IndianCurrencyFormat = new DecimalFormat("₹##,##,###");
		return IndianCurrencyFormat.format(maxPrice);
	}

	private static Integer findPrice(String priceDetails) {
		int priceAmt = 0;
		String price = null;
		String[] priceArray = priceDetails.split("M.R.P.:");
		if (priceArray.length > 0) {
			price = priceArray[0];
			String[] currenArray = price.split("₹");
			if (currenArray.length > 1) {
				price = currenArray[1];
			}
			price = price.replace(",", "");
		}
		try {
			priceAmt = price == null ? 0 : Integer.valueOf(price);
		} catch (NumberFormatException exe) {

		}
		return priceAmt;
	}
	
	public static String findHighestPriceFlipkart(Set<String> priceDetails) {
		Set<Integer> highestPrice = new HashSet<Integer>();
		for (String priceDet : priceDetails) {
			int price = findPriceFilpkart(priceDet);
			if (price != 0) {
				highestPrice.add(price);
			}
		}
		Integer maxPrice = Collections.max(highestPrice);
		return indianCurrencyConvert(maxPrice);
	}
	
	private static Integer findPriceFilpkart(String priceDetails) {
		int priceAmt = 0;
		String price = null;
		String[] currenArray = priceDetails.split("₹");
		if (currenArray.length > 1) {
			price = currenArray[1];
			price = price.replace(",", "");
		}
		try {
			priceAmt = price == null ? 0 : Integer.valueOf(price);
		} catch (NumberFormatException exe) {

		}
		return priceAmt;
	}
	/*For Filpkart IPhone case -  temporary fix*/
	private static String indianCurrencyConvert(Integer highestPrice) {
		String convertedValue = null;
		String highPrice = String.valueOf(highestPrice);
		DecimalFormat indianCurrencyFormat = new DecimalFormat("₹##,##,###");
		convertedValue = indianCurrencyFormat.format(highestPrice);
		if(highPrice.length()==6) {
			StringBuilder sb = new StringBuilder();
			sb.append(convertedValue.charAt(0));
			sb.append(convertedValue.charAt(1));
			sb.append(",");
			sb.append(convertedValue.charAt(2));
			sb.append(convertedValue.charAt(3));
			sb.append(convertedValue.charAt(4));
			sb.append(convertedValue.charAt(5));
			sb.append(convertedValue.charAt(6));
			sb.append(convertedValue.charAt(7));
			convertedValue = sb.toString();
		}
		return convertedValue;
	}
}
