package mobile;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

public class ebay {
	static AndroidDriver driver = null;
	String nodePath = "C:/Progra~1/Appium/node.exe";
	 // Set path of your appium.js file. Set your path.
	 String appiumJSPath = "C:/Program Files/Appium/resources/app/node_modules/appium/build/lib/appium.js";

	 // This method Is responsible for starting appium server.
	 public void appiumStart() throws IOException, InterruptedException {
	  // Created object of apache CommandLine class.
	  // It will start command prompt In background.
	  CommandLine command = new CommandLine("cmd");
	  // Add different arguments In command line which requires to start appium server.
	  command.addArgument("/c");
	  command.addArgument(nodePath);
	  command.addArgument(appiumJSPath);
	  // Set Server address
	  command.addArgument("--address");
	  command.addArgument("127.0.0.1");
	  // Set Port
	  command.addArgument("--port");
	  command.addArgument("4723");
	  command.addArgument("--no-reset");
	  command.addArgument("--log");
	  // Set path to store appium server log file.
	  command.addArgument("D://appiumLogs.txt");
	  // Execute command line arguments to start appium server.
	  DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
	  DefaultExecutor executor = new DefaultExecutor();
	  executor.setExitValue(1);
	  executor.execute(command, resultHandler);
	  // Wait for 15 minutes so that appium server can start properly before going for test execution.
	  // Increase this time If face any error.
	  Thread.sleep(15000);
	 }

	 // This method Is responsible for stopping appium server.
	 public static void appiumStop() throws IOException {
	  // Add different arguments In command line which requires to stop appium server.
	  CommandLine command = new CommandLine("cmd");
	  command.addArgument("/c");
	  command.addArgument("taskkill");
	  command.addArgument("/F");
	  command.addArgument("/IM");
	  command.addArgument("node.exe");
	  // Execute command line arguments to stop appium server.
	  DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
	  DefaultExecutor executor = new DefaultExecutor();
	  executor.setExitValue(1);
	  executor.execute(command, resultHandler);
	 }
@BeforeTest
	
	public void testApp() throws Exception{
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"Appium");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,"7");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME,"jitun");
		dc.setCapability("appPackage","com.amazon.mShop.android.shopping");
		dc.setCapability("appActivity","com.amazon.mShop.splashscreen.StartupActivity");
		dc.setCapability(MobileCapabilityType.APP,"E:\\Eclipse Kepler WorkSpace\\RandomWork\\mobile\\src\\mobile\\apps\\Amazon_shopping.apk");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(url,dc);
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 300); 
		}
@Test(priority=1)	
public void performOrientation() throws InterruptedException {
 //Get and print current screen orientation.
 System.out.println("*--*--*-- Current screen orientation Is : " + driver.getOrientation());
 System.out.println("*--*--*-- Changing screen Orientation to LANDSCAPE.");
 //Changing screen Orientation to LANDSCAPE.
 driver.rotate(org.openqa.selenium.ScreenOrientation.LANDSCAPE);
 //Get and print screen orientation after changing It.
 System.out.println("*--*--*-- Now screen orientation Is : "+ driver.getOrientation());
 Thread.sleep(5000);
 System.out.println("*--*--*-- Changing screen Orientation to PORTRAIT.");
 //Changing screen Orientation to PORTRAIT.
 driver.rotate(org.openqa.selenium.ScreenOrientation.PORTRAIT);
 //Get and print screen orientation after changing It.
 System.out.println("*--*--*-- Now screen orientation Is : "+ driver.getOrientation());
 Thread.sleep(5000);
}
@Test(priority=2)	
public void testApp1() throws Exception{
	
	    ((AppiumDriver) driver).context("NATIVE_APP"); 
        System.out.println("App launched");
        Thread.sleep(5000);
        driver.findElement(By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text")).click();
        Thread.sleep(5000);
        //Searching for 64-inch tv
        driver.findElement(By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text")).sendKeys("65-inch tv");
        ((RemoteWebDriver) driver).executeScript("mobile:performEditorAction",ImmutableMap.of("action","Go"));
        WebElement el = driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("+ "new UiSelector().text(\"Mi TV 4X 163.9 cm (65 Inches) 4K Ultra HD Android LED TV (Black)\"));"));
        el.click();
        Thread.sleep(10000);
        //Verify the onscreen text
       Boolean iselementpresent = driver.findElementsByClassName("android.view.View").size()!=0;
        Assert.assertTrue(iselementpresent, "Targeted element Mi TV 4X 163.9 cm (65 Inches) 4K Ultra HD Android LED TV (Black) is not present on screen");
        System.out.println("Target elememt Mi TV 4X 163.9 cm (65 Inches) 4K Ultra HD Android LED TV (Black)is present on screen");
        WebElement el1 = driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView("+ "new UiSelector().text(\"Add to Cart\"));"));
        //Click on Add to Cart
        Thread.sleep(3000);
        driver.findElement(By.className("android.widget.Button")).click();
//        Thread.sleep(5000);
//        //driver.findElement(By.id("a-autoid-1-announce")).click();
       //driver.findElement(By.id("in.amazon.mShop.android.shopping:id/chrome_action_bar_cart_image")).click();
}
@AfterMethod
public void tearDown() {
	System.out.println("testing completed"); 
	}
}


