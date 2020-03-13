package com.techelevator.npgeek.cukes;

import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;


import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.DAOIntegrationTest;
import com.techelevator.npgeek.JDBCWeatherDAO;
import com.techelevator.npgeek.JdbcParkDao;

import org.junit.Assert;

public class NpGeeksIntegrationTest extends DAOIntegrationTest{
private static WebDriver webDriver;
private DataSource dataSource = getDataSource();
private JdbcTemplate jdbc = new JdbcTemplate(dataSource);

JDBCWeatherDAO weatherDao = new JDBCWeatherDAO(dataSource);

	
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
		webDriver.quit();
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
		WebElement imageToSelect1 = webDriver.findElement(By.xpath("html/body/section/table/tbody/tr[1]/td[2]/h2"));

		String str = imageToSelect1.getText(); 
		imageToSelect.click();										
		TimeUnit.SECONDS.sleep(3);
		WebElement pageHeader = webDriver.findElement(By.tagName("h2"));
		
		Assert.assertEquals(str, pageHeader.getText());
		
	}
	@Test
	public void check_detail_page_values() throws InterruptedException {
		WebElement imageToSelect = webDriver.findElement(By.xpath("/html/body/section/table/tbody/tr[1]/td[1]/a/img"));
		WebElement imageToSelect1 = webDriver.findElement(By.xpath("html/body/section/table/tbody/tr[1]/td[2]/h2"));
		String str = imageToSelect1.getText(); 
		imageToSelect.click();										
		TimeUnit.SECONDS.sleep(3);
		
		WebElement tempScalevalue = webDriver.findElement(By.xpath("/html/body/section/div/form/div/select"));
		
		Select select = new Select(tempScalevalue);
		WebElement option = select.getFirstSelectedOption();
		String tempScale = option.getAttribute("value");
		
		
		WebElement dayValue = webDriver.findElement(By.xpath("/html/body/section/table/tbody/tr/td[1]/p[1]"));
		WebElement low = webDriver.findElement(By.xpath("/html/body/section/table/tbody/tr/td[1]/p[2]"));
		WebElement high = webDriver.findElement(By.xpath("/html/body/section/table/tbody/tr/td[1]/p[3]"));
		WebElement advisory = webDriver.findElement(By.xpath("/html/body/section/table[2]/tbody/tr/td[1]/p[4]"));
		//WebElement advisoryTemp = webDriver.findElement(By.xpath("/html/body/section/table/tbody/tr/td[1]/p[1]"));
		Assert.assertEquals( "Day " +weatherDao.getFiveDayForecastByName(str,tempScale).get(0).getDay(),dayValue.getText());
		Assert.assertEquals("Low "+weatherDao.getFiveDayForecastByName(str, tempScale).get(0).getLow(), low.getText());
		Assert.assertEquals("High "+weatherDao.getFiveDayForecastByName(str, tempScale).get(0).getHigh(), high.getText());
		Assert.assertEquals(weatherDao.getWeatherAdvisoryByName(str, weatherDao.getFiveDayForecastByName(str,tempScale).get(0).getDay()), advisory.getText());
		
		
	}

}
