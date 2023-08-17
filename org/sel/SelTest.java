package org.restapi.restapitesting;

import org.testng.annotations.Test;
import static org.openqa.selenium.*;

import org.openqa.selenium.WebDriver;

public class SelTest {
  @Test
  public void sel() {
	  System.setProperty("webdriver.chrome.driver", "c:\\sel1\\chromedriver.exe");
	  WebDriver wd = new ChromeDriver();
	  wd.get("ndtv.com");
  }
}
