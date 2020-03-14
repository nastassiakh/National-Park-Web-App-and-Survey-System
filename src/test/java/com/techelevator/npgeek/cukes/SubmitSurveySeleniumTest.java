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
import org.openqa.selenium.support.ui.Select;

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
	public void check_SurveyPage() throws InterruptedException {
		// home page webelement
		WebElement home = webDriver.findElement(By.xpath("/html/body/nav/ul/li[1]/a"));
		
		//go to submitSurvey page
		WebElement submitSurveyLink = webDriver.findElement(By.linkText("Submit Survey"));
		submitSurveyLink.click();
		
		//find element "Favorite National Park:" by path
		WebElement survey = webDriver.findElement(By.xpath("/html/body/form/div/div[1]/label"));
		String str2 = survey.getText();
		
		//park
		WebElement parkDrop = webDriver.findElement(By.xpath("/html/body/form/div/div[1]/select"));
		Select select = new Select(parkDrop);
		select.selectByValue("CVNP");
		TimeUnit.SECONDS.sleep(3);
		
		String str = parkDrop.getText();
		
		//email
		WebElement vv =	webDriver.findElement(By.xpath("/html/body/form/div/div[2]/input"));
		vv.sendKeys("aa@gmail.com");
		TimeUnit.SECONDS.sleep(3);
		
		//state
		WebElement dropDown = webDriver.findElement(By.xpath("/html/body/form/div/div[3]/select"));
		Select select2 = new Select(dropDown);
		select2.selectByValue("Alabama");
		TimeUnit.SECONDS.sleep(3);
		
        //activity
		WebElement radioButton = webDriver.findElement(By.xpath("/html/body/form/div/div[4]/div/input[2]"));
		radioButton.click();
		TimeUnit.SECONDS.sleep(3);
		
		//submit
		WebElement submitButton = webDriver.findElement(By.xpath("/html/body/form/div/input"));
		submitButton.click();
		TimeUnit.SECONDS.sleep(3);
		
		
		WebElement surveyResultElement = webDriver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[2]"));

		Assert.assertEquals(str2, survey.getText());
		
		Assert.assertEquals(str, surveyResultElement.getText());
	}
}

