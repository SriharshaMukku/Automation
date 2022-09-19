package com.harsha.core;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumDriver;

public class W3cActions {
	private final static PointerInput FINGER = new PointerInput(PointerInput.Kind.TOUCH, "finger");

	public static void doSwipe(AppiumDriver driver, Point start, Point end, int duration) {
		Sequence swipe = new Sequence(FINGER, 1)
				.addAction(FINGER.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), start.getX(),
						start.getY()))
				.addAction(FINGER.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(FINGER.createPointerMove(Duration.ofMillis(duration), PointerInput.Origin.viewport(),
						end.getX(), end.getY()))
				.addAction(FINGER.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));
	}

	public static void doTap(AppiumDriver driver, Point point, int duration) {
		Sequence tap = new Sequence(FINGER, 1)
				.addAction(FINGER.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), point.getX(),
						point.getY()))
				.addAction(FINGER.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(new Pause(FINGER, Duration.ofMillis(duration)))
				.addAction(FINGER.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(tap));
	}

	public static void doScrollDown(AppiumDriver driver) {
		Dimension dimension = driver.manage().window().getSize();
		int start_x = (int) (dimension.width * 0.5);
		int start_y = (int) (dimension.height * 0.8);

		int end_x = (int) (dimension.width * 0.2);
		int end_y = (int) (dimension.height * 0.2);

		Point startPoint = new Point(start_x, start_y);
		Point endPoint = new Point(end_x, end_y);
		W3cActions.doSwipe(driver, startPoint, endPoint, 1000);
	}

	public static void doScrollUp(AppiumDriver driver) {
		Dimension dimension = driver.manage().window().getSize();
		int start_x = (int) (dimension.width * 0.5);
		int start_y = (int) (dimension.height * 0.2);

		int end_x = (int) (dimension.width * 0.2);
		int end_y = (int) (dimension.height * 0.8);

		Point startPoint = new Point(start_x, start_y);
		Point endPoint = new Point(end_x, end_y);
		W3cActions.doSwipe(driver, startPoint, endPoint, 1000);
	}
}
