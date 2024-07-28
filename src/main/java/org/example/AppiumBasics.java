package org.example;


import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.*;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class AppiumBasics extends BaseTest {


    @Test
    public void Appiumtest() throws InterruptedException, MalformedURLException, URISyntaxException {

//        configureAppium();
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();
        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();
        driver.findElement(By.id("android:id/checkbox")).click();
        driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
        String Alerttitle = driver.findElement(By.id("android:id/alertTitle")).getText();
        Assert.assertEquals(Alerttitle,"WiFi settings");
        driver.findElement(By.id("android:id/edit")).sendKeys("Abishek");
        driver.findElement(By.id("android:id/button1")).click();

        Thread.sleep(5000);
//        teardown();

    }

    @Test
    public void Longpress() throws InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();
       WebElement element =  driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
       longPressAction(element);
//        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
//                ImmutableMap.of("elementId",((RemoteWebElement)element).getId(),
//                        "duration",2000));

        String actual = driver.findElement(By.id("android:id/title")).getText();
        Assert.assertEquals(actual,"Sample menu");
       Assert.assertTrue( driver.findElement(By.id("android:id/title")).isDisplayed());

        Thread.sleep(2000);

    }

    @Test
    public void scrollGesture() throws InterruptedException {

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"));"));
        Thread.sleep(5000);
    }

    @Test
    public void swipeGesture(){

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();
        WebElement ele = driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]"));
      Assert.assertEquals(driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"),"true");
      //swipe

        swipeleft(ele);

        Assert.assertEquals(driver.findElement(AppiumBy.xpath("(//android.widget.ImageView)[1]")).getAttribute("focusable"),"false");
    }

    @Test
    public void dragdrop_Demo() throws InterruptedException {
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
       WebElement ele = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));

//        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
//                "elementId", ((RemoteWebElement) ele).getId(),
//                "endX", 621,
//                "endY", 607
//        ));
        dragdrop(ele,621,607);

        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText(),"Dropped!");


    }

}
