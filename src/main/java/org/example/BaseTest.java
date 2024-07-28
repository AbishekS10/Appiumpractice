package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

public class BaseTest {

   public  AppiumDriverLocalService service;
   public AndroidDriver driver;
   @BeforeClass
    public void configureAppium() throws URISyntaxException, MalformedURLException {
         service = new AppiumServiceBuilder().withAppiumJS(new File("C:\\Users\\Hp\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("AbishekPhone1");
        options.setApp("C:\\Users\\Hp\\IdeaProjects\\appiumPractice01\\src\\test\\java\\Resource\\ApiDemos-debug.apk");

         driver = new AndroidDriver(new URI("http://127.0.0.1:4723/").toURL(),  options);
         driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void longPressAction(WebElement element){

        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId",((RemoteWebElement)element).getId(),
                        "duration",2000));
    }

    public void swipeleft(WebElement ele){

        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement)ele).getId(),
                        "direction", "left",
                        "percent", 0.50
                ));
    }

    public void dragdrop(WebElement ele , int x, int y){

        ((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "endX", x,
                "endY", y
        ));
    }

    @AfterClass
    public void teardown()
    {
        driver.quit();
        service.stop();
    }
}
