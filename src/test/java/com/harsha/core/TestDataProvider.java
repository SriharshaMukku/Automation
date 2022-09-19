package com.harsha.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;

import com.harsha.core.driver.DriverConfiguration;
/**
 * This class is useful to fetch the data from a file. 
 * @author Sriharsha
 *
 */
public class TestDataProvider {
	private static final Logger logger = LogManager.getLogger(TestDataProvider.class);
	@DataProvider (name = "data-provider")
    public DriverConfiguration[][] dpMethod(Method method){
		DriverConfiguration[][] configurationArray = new DriverConfiguration[0][0];
		configurationArray[0][0] = getConfiguration(method);
		
       return configurationArray;
    }
	
	private DriverConfiguration getConfiguration(Method method) {
		DriverConfiguration configuration = new DriverConfiguration();
		logger.debug("method.getName() ->"+method.getName());
		if("FilkpartAndroidTest".equals(method.getName())) {
			configuration.setEmulator(true);
			configuration.setServerUrl("http://127.0.0.1:4723/wd/hub");
			configuration.setDeviceName("emulator-5554");
			configuration.setOperatingSystem("android");
			configuration.setPlatformName("android");
			configuration.setPlatformVersion("10.0");
			configuration.setAppPackage("com.flipkart.android");
			configuration.setAppActivity("com.flipkart.android.activity.HomeFragmentHolderActivity");
		}
		return configuration;
	}
}
