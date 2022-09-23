# automation

## Highlights:
This is a Java based mobile automation project contains the Add to cart a product for the Amazon India & Flipkart Apps from an Emulator & Real device

## Running Tests
Can be run from Eclipse, IntelliJ the way tests normally are run & By using maven test lifecycle method

## Uses TestNG and was built and tested on Eclipse & With maven


## Technology stack:
- TestNG 7.4 (Test Runner)
- Selenium 4.4+
- Appium Server
- Appium Client (8.2+)
- Appium Inspector (For Inspecting the Mobile Elements)
- ADB (Android Debug Bridge) - To install apks and find the package & activity details of an application
- Android SDK
- Android Studio (Chipmunk) - For Device managment (Virtual & Pyshical)
- Java 1.8 and Above
- Maven
- Surefire (Reporting)
- Log4j2
- Page Object Model

## Project Setup
- Install the JDK 1.8 and above and setup the JAVA_HOME & Path (bin) in Environment Variables (It can be User or System)
- Install the Maven 3.6+ and setup the MAVEN_HOME & Path (bin) in Environment Variables (It can be User or System)
- Install the Android SDK and setup the ANDROID_HOME & Path (platform-tools) in Environment Variables (It can be User or System)
- Environment variables

![image](https://user-images.githubusercontent.com/113914197/191896523-b62cd6de-dc89-4957-ab60-5c5dcc26b7e8.png)
![image](https://user-images.githubusercontent.com/113914197/191896614-f6a54373-b640-4004-a9a2-18acf7dc2b0c.png)

- Install the Appium Server
- Install the Appium Inspector
- Install Eclipse or IntelliJ (Or any other IDE)

### Android Studio Setup
- Open the Android Studio and add a Virtual device in Device managment
- Run the virtual device to launch the device in the Emulator
- Wait for the OS bootup (First time)

### ADB
- Open ADK in the Windows shell or command prompt and check for the available devices with command "adb devices"
- Download the latest APK (Here I have download Flipkart)
- Install the Flipkart APP with command "adb -s emulator-5554 install C:\XXXX\XXXXX\Android\APKs\FlipkartOnlineShoppingApp_v7.52.apk
- Install the Amazon India APP with command "adb -s emulator-5554 install C:\XXXX\XXXXX\Android\APKs\AmazonShopping_v24_17.apk
- Open Mobile Shell by using
  adb -s emulator-5554 shell
- Go to the Android studio Emulator and Open the Flipkart app
- Run the command "dumpsys window windows | grep -E mObscuringWindow" to find the package and activity of a Flipkart Application
  Sample output will be "com.flipkart.android/com.flipkart.android.activity.HomeFragmentHolderActivity"
