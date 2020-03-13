package com.techelevator.npgeek.cukes;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.junit.Assert;

public class NpGeeksIntegrationTest {
private static WebDriver webDriver;
	
	@BeforeClass
	public static void openWebBrowserForTesting() {
		
		String homeDir = System.getProperty("user.home"); 
		System.setProperty("webdriver.chrome.driver", homeDir+"/dev-tools/chromedriver/chromedriver");
		webDriver = new ChromeDriver();		
		webDriver.manage().window().maximize();
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
	public void check_HomePage() {
		WebElement home = webDriver.findElement(By.xpath("/html/body/nav/ul/li[1]/a"));
		WebElement submitSurvey = webDriver.findElement(By.xpath("/html/body/nav/ul/li[2]/a"));
		WebElement viewSurveys = webDriver.findElement(By.xpath("/html/body/nav/ul/li[3]/a"));
		Assert.assertEquals("Home", home.getText());
		Assert.assertEquals("Submit Survey", submitSurvey.getText());
		Assert.assertEquals("View Surveys", viewSurveys.getText());
		
		
	}
	@Test
	public void check_HomePage_HomeLink() {
		WebElement homeLink = webDriver.findElement(By.linkText("Home"));
		homeLink.click();
		
		WebElement home = webDriver.findElement(By.xpath("/html/body/nav/ul/li[1]/a"));		
		WebElement submitSurvey = webDriver.findElement(By.xpath("/html/body/nav/ul/li[2]/a"));
		WebElement viewSurveys = webDriver.findElement(By.xpath("/html/body/nav/ul/li[3]/a"));
		Assert.assertEquals("Home", home.getText());
		Assert.assertEquals("Submit Survey", submitSurvey.getText());
		Assert.assertEquals("View Surveys", viewSurveys.getText());
		
		
	}
	@Test
	public void check_HomePage_submitSurveyLink() {
		WebElement submitSurveyLink = webDriver.findElement(By.linkText("Submit Survey"));
		submitSurveyLink.click();
	
		WebElement favoritenpLabel = webDriver.findElement(By.xpath("/html/body/form/div[1]/label"));		
		Assert.assertEquals("Favorite National Park:", favoritenpLabel.getText());
	}
	@Test
	public void check_HomePage_viewSurveysLink() {
		WebElement viewSurveysLink = webDriver.findElement(By.linkText("View Surveys"));
		viewSurveysLink.click();
		WebElement surveyTableHeader = webDriver.findElement(By.xpath("/html/body/table/tbody/tr[1]/th[1]"));		
		Assert.assertEquals("National Park", surveyTableHeader.getText());
		
		
	}
	
	@Test
	public void check_navigatio_to_detail_page() throws InterruptedException {
		WebElement imageToSelect = webDriver.findElement(By.xpath("/html/body/section/table/tbody/tr[1]/td[1]/a/img"));
		imageToSelect.click();										
		TimeUnit.SECONDS.sleep(3);
		WebElement pageHeader = webDriver.findElement(By.tagName("h2"));
		Assert.assertEquals("Cuyahoga Valley National Park", pageHeader.getText());
		
		
	}

}
