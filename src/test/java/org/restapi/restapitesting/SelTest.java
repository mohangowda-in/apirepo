package org.restapi.restapitesting;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;


public class SelTest {
	WebDriver driver;
	String title="";
	Logger log;
	ExtentReports extrep;
	ExtentTest extest;
	
	
	@BeforeSuite
	public void setup() {
		extrep = new ExtentReports();
		ExtentSparkReporter sparkrep = new ExtentSparkReporter(System.getProperty("user.dir")+"/report/testreport.html");
		extrep.attachReporter(sparkrep);
	}
	
	@AfterMethod
	public void teardown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
            extest.fail(MarkupHelper.createLabel(result.getName() + "Test Failed", ExtentColor.RED));
            extest.fail(result.getThrowable());
            String screencastPath = null;
            
		extrep.flush();
		}
	}
	
	//@Test
	public void getActions() {
		
		driver = new ChromeDriver();
		driver.get("https://demoqa.com/automation-practice-form");
		//System.out.println("total size=="+driver.findElements(By.linkText("Home")).size());
		WebElement ele = driver.findElement(By.cssSelector("input[id=\"gender-radio-1\"]"));
		
		//driver.findElement(By..name(""))
//		System.out.println("Page text ===="+txt);
		//Assert.assertEquals(driver.getTitle(), "Rediff.com: News | Rediffmail | Stock Quotes | Shopping");
	}
	
    //@Test
	public void startTestinOpenBrowser() {
		ChromeOptions optns = new ChromeOptions();
		optns.setExperimentalOption("debuggerAddress", "localhost:8292");
		WebDriver driver = new ChromeDriver(optns);
		driver.get("https://www.weatherapi.com/");
	}
    
    
	//@Test	
  public void firefoxProfile() {
	ProfilesIni pi = new ProfilesIni();  
	FirefoxProfile fxprofile = new FirefoxProfile();
	fxprofile = pi.getProfile("automationprofile");
	fxprofile.setAcceptUntrustedCertificates(true);
//	options.setPreference("browser.startup.homepage", "https://www.demoqa.com");
//	options.setAcceptUntrustedCertificates(true);
	FirefoxOptions opns = new FirefoxOptions();
	opns.setProfile(fxprofile);
	//opns.addPreference("browser.startup.page", 1);
	//opns.addPreference("browser.startup.homepage", "https://www.ndtv.com");
	opns.setAcceptInsecureCerts(true);
	//opns.setProfile(options);
	WebDriver driver = new FirefoxDriver(opns);
	driver.get("https://www.weatherapi.com/");
	System.out.println("##############"+driver.getTitle());
  }
  
  
  //@Test
  public void handleMultipleTabs(){
	  //System.setProperty("webdriver.chrome.driver", "d:\\sel1\\chromedriver.exe");
	  try {
	  WebDriver driver = new ChromeDriver();
	  String parentwindow = driver.getWindowHandle();
	  driver.get("https://www.ndtv.com");
	  WebDriver newtab = driver.switchTo().newWindow(WindowType.TAB);
	  newtab.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	  newtab.close();
	  driver.switchTo().window(parentwindow);
	  System.out.println("PARENT WINDOW TITLE#####"+driver.getTitle());
	  driver.close();
	  }catch(Exception e) {
		  System.out.println(e);
	  }
  }
  @Test(priority = 1)
  public void getIncognito() {
	  
	  extest = extrep.createTest("get incognito browser window");
	  extest.info("Starting application");
	  ChromeOptions options = new ChromeOptions();
	  //options.addArguments("--remote-allow-origins=*");
	  options.addArguments("--incognito");
	  DesiredCapabilities capabelity = new DesiredCapabilities();
	 capabelity.setCapability(ChromeOptions.CAPABILITY, options);
	  options.merge(capabelity);
	  log  = LogManager.getLogger(SelTest.class);
	  WebDriverManager.chromedriver().setup();
	 driver = new ChromeDriver(options);
	  //String parentwindow = driver.getWindowHandle();
	  log.info("Chromedriver initialized");
	  driver.get("https://www.ndtv.com");
	  if(!driver.findElements(By.id("testme")).isEmpty()) {
		  System.out.println("Element present");
	  }
	  log.info("Browser opened and accessed the url");
	  //driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	  //driver.quit();
  }
  
  @Test(priority = 2)
  public void getPageTitle() {
	  extest = extrep.createTest("get page title");
	  extest.info("Page title");
	  title = driver.getTitle();
	  log.info("Feching the page title");
	  System.out.println("Page title====="+title);
	  
  }
  
  @Test(priority = 3)
  public void verifyTitle() {
	  try {
	  extest = extrep.createTest("verify the title");
	  extest.info("verify the title");
	  System.out.println("Verifying the portal title....");
	  log.info("verifying the page title");
	  Assert.assertTrue(false);//.assertEquals(title,"Get Latest News, India News, Breaking News, Today's News - NDTV.com");
	  driver.quit();
	  }catch(AssertionError e) {
		  extest.fail(e);
		  driver.quit();
	  }
  }
}
