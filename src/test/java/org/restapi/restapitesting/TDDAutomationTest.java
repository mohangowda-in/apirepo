package org.restapi.restapitesting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TDDAutomationTest {
	@Test 
	public void test01() {
		System.setProperty("webdriver.chrome.driver", "d:\\sel1\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		//driver.get("http://sis-bdp.eastus.cloudapp.azure.com:8400/#/login");
		//driver.manage().timeouts().implicitlyWait(1,TimeUnit.MINUTES);
		//driver.findElement(By.id("#email")).sendKeys("mohan");
		//driver.findElement(By.id("#password")).sendKeys("asdf112");
		driver.findElement(By.xpath("//span[contains(text(),'Sign In')]")).click();
		
	}

}
