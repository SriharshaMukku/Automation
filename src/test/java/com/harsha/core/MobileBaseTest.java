package com.harsha.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.harsha.core.driver.DriverConfiguration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 * Base Test contains the driver setup information
 * @author Sriharsha
 *
 */
public abstract class MobileBaseTest {
	private static final Logger logger = LogManager.getLogger(MobileBaseTest.class);
	protected AppiumDriver driver;

	protected abstract void buildTest(String platformName);

	@BeforeClass
	@Parameters({ "appiumserver", "emulator", "deviceName", "platformName", "platformVersion", "appPackage",
			"appActivity" })
	public void setUpDriver(String appiumserver, boolean emulator, String deviceName, String platformName,
			String platformVersion, String appPackage, String appActivity) {
		DriverConfiguration configuration = fetchDriverConfiguration(appiumserver, emulator, deviceName, platformName,
				platformVersion, appPackage, appActivity);
		logger.debug("MobileBaseTest configuration -->" + configuration);
		logger.debug("MobileBaseTest platformName -->" + platformName);
		driver = new AndroidDriver(fetchAppiumServer(appiumserver), fecthCapabilities(configuration));
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(15 * 1000));
		buildTest(platformName);
	}

	@AfterClass
	public void tearDown() throws Exception {
		if (driver != null) {
			driver.quit();
		}
	}

	private URL fetchAppiumServer(String appiumserver) {
		URL serverUrl = null;
		try {
			serverUrl = new URL(appiumserver);
		} catch (MalformedURLException exe) {
			logger.error("Unable to fetchAppiumServer ", exe);
		}
		return serverUrl;
	}

	private DriverConfiguration fetchDriverConfiguration(String appiumserver, boolean emulator, String deviceName,
			String platformName, String platformVersion, String appPackage, String appActivity) {
		logger.debug("fetchDriverConfiguration appiumserver -->" + appiumserver);
		logger.debug("fetchDriverConfiguration emulator -->" + emulator);
		DriverConfiguration configuration = new DriverConfiguration();
		configuration.setEmulator(emulator);
		configuration.setServerUrl(appiumserver);
		configuration.setDeviceName(deviceName);
		configuration.setOperatingSystem(platformName);
		configuration.setPlatformName(platformName);
		configuration.setPlatformVersion(platformVersion);
		configuration.setAppPackage(appPackage);
		configuration.setAppActivity(appActivity);
		return configuration;
	}

	private DesiredCapabilities fecthCapabilities(DriverConfiguration configuration) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		if ("android".equals(configuration.getOperatingSystem())) {
			capabilities.setCapability("deviceName", configuration.getDeviceName());
			capabilities.setCapability("platformName", configuration.getPlatformName());
			capabilities.setCapability("platformVersion", configuration.getPlatformVersion());
			if (configuration.isEmulator()) {
				capabilities.setCapability("appium:appPackage", configuration.getAppPackage());
				capabilities.setCapability("appium:appActivity", configuration.getAppActivity());
			} else {
				capabilities.setCapability("package", configuration.getAppPackage());
				capabilities.setCapability("activity", configuration.getAppActivity());
			}
		} else if ("ios".equals(configuration.getOperatingSystem())) {
			// TO-DO need to update iOS details
			capabilities.setCapability("deviceName", "");
			capabilities.setCapability("platformName", "");
			capabilities.setCapability("platformVersion", "");
			capabilities.setCapability("package", "");
			capabilities.setCapability("activity", "");
		} else {
			// TO-DO Other platforms like Windows Mobile and Desktop
		}

		return capabilities;
	}
}
