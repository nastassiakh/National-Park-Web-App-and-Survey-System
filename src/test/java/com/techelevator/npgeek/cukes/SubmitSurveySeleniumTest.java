package com.techelevator.npgeek.cukes;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SubmitSurveySeleniumTest extends Hooks {
	
	private static WebDriver webDriver;
	
	@BeforeClass
	public static void openWebBrowserForTesting() {

		String homeDir = System.getProperty("user.home");

		System.setProperty("webdriver.chrome.driver", homeDir + "/dev-tools/chromedriver/chromedriver");
		webDriver = new ChromeDriver();
	}
	
	
	@Before
	public void openHomePage() throws InterruptedException {
		webDriver.get("http://localhost:8080/45-m3-java-capstone/");
		TimeUnit.SECONDS.sleep(3);
	}
	
	@AfterClass
	public static void closeWebBrowser() {
		webDriver.quit();;
	}


	@Test
	public void check_SurveyPage() {
		WebElement survey = webDriver.findElement(By.xpath("/html/body/form/div/div[1]/label"));
		
		WebElement vv =	webDriver.findElement(By.xpath("/html/body/form/div/div[2]/input"));
		Assert.assertEquals("Favorite National Park:", survey.getText());
	}
}
