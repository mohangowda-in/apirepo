package org.restapi.restapitesting;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
public class DataProvdr {
	WebDriver driver;
	@Test //(dataProvider = "dataFeeder")
    public void showData() throws Exception{//String name, int pin) {
		//System.setProperty("webdriver.chrome.driver", "d:\\sel1\\chromedriver.exe");
		ChromeOptions crop = new ChromeOptions();
		//crop.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//driver.get("https://demoqa.com/login");
		//driver.findElement(By.id("test"));
		Logger log = LogManager.getLogger(this.getClass().getName());
		log.info("Test info message.....");
		log.error("Test error message.....");
		log.warn("Test warn message.....");
		log.fatal("Test fatal message.....");
		//driver.quit();		
		//System.out.println(name +"::"+pin);
		//Assert.assertEquals(false, false);
	}
	
	@AfterMethod
	public void tearDown(ITestResult ir) {
		System.out.println("Failed test status#########"+ ir.getStatus());
		if(ITestResult.FAILURE==ir.getStatus()) {
		try {
			Reporter.log("Taking screen shot of failure test");
			TakesScreenshot sc = ((TakesScreenshot)driver);
			File srcFile = sc.getScreenshotAs(OutputType.FILE);
			File destfile = new File("d:\\"+ir.getName()+".jpg");
			FileUtils.copyFile(srcFile, destfile);
			driver.quit();	
		}catch(Exception e) {
			
		}
		}
	}
	
	@DataProvider (name = "dataFeeder")
	public Object[][] supplyData(){
		Object[][] obj = { 
				{"Bangaluru",560043},
				{"Bhadravathi",577301},
				{"Bhuvaneshwar",879867}
		};
		return obj;
	}
	
}
