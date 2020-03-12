package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.JDBCWeatherDAO;
import com.techelevator.npgeek.Weather;


public class JDBCWeatherIntegrationTest extends DAOIntegrationTest{
	
	private JdbcTemplate jdbc;
	private JDBCWeatherDAO weatherDAO;
	private DataSource dataSource = getDataSource();

	public JDBCWeatherIntegrationTest() {
		this.jdbc = new JdbcTemplate(dataSource);
		this.weatherDAO = new JDBCWeatherDAO(dataSource);
	}
	
	@Before
	public void makeTestParkAndWeather() {
		String sqlMakeNewPark = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) " 
				+ "VALUES ('TST', 'Test Park', 'TS', 1, 1, 1, 1, 'Nice', 1, 1, 'This place rocks', 'Tom Medvitz', 'This place rocks', 1, 1); ";
		jdbc.update(sqlMakeNewPark);
		
		String sqlMakeNewWeather = "INSERT INTO weather (parkcode, fivedayforecastvalue, low, high, forecast) " 
				+ "VALUES ('TST', 1, 74, 76, 'sunny'), "
				+ "('TST', 2, 19, 21, 'snow'), "
				+ "('TST', 3, 50, 71, 'rain'), "
				+ "('TST', 4, 69, 70, 'thunderstorms'), "
				+ "('TST', 5, 64, 65, 'partly cloudy'); ";
		jdbc.update(sqlMakeNewWeather);
		
	}

	@Test
	public void conversion_test_F() {
		long temp = 32;
		String tempScale = "F";
		
		String output = weatherDAO.celsiusConversion(temp, tempScale);
		
		assertEquals("32 F", output);
	}
	
	@Test
	public void conversion_test_C() {
		long temp = 32;
		String tempScale = "C";
		
		String output = weatherDAO.celsiusConversion(temp, tempScale);
		
		assertEquals("0 C", output);
	}
	
	@Test
	public void conversion_test_null() {
		long temp = 32;
		String tempScale = null;
		
		String output = weatherDAO.celsiusConversion(temp, tempScale);
		
		assertEquals("32 F", output);
	}
	
	@Test
	public void weather_advisory_snow() {
		String output = weatherDAO.getWeatherAdvisory("TST", 2);
		assertEquals("Bring some snowshoes! Frigid tempatures can be very dangerous", output);
	}
	
	@Test
	public void weather_advisory_rain() {
		String output = weatherDAO.getWeatherAdvisory("TST", 3);
		assertEquals("Pack some rain gear and wear waterproof shoes! Wear breathable layers", output);
	}
	
	@Test
	public void weather_advisory_thunderstorm() {
		String output = weatherDAO.getWeatherAdvisory("TST", 4);
		assertEquals("Seek shelter and avoid hiking on exposed ridges!", output);
	}
	
	@Test
	public void weather_advisory_sunny() {
		String output = weatherDAO.getWeatherAdvisory("TST", 1);
		assertEquals("Pack some sunblock! Bring an extra gallon of water", output);
	}
	
	@Test
	public void weather_advisory_cloudy() {
		String output = weatherDAO.getWeatherAdvisory("TST", 5);
		assertEquals("", output);
	}
	
	@Test
	public void get_weather_forecast_F() {
		List<Weather> output = weatherDAO.getFiveDayForecast("TST", "F");
		
		assertEquals(5, output.size());
		assertEquals("76 F", output.get(0).getHigh());
		assertEquals("Bring some snowshoes! Frigid tempatures can be very dangerous", output.get(1).getAdvisory());
		assertEquals("rain", output.get(2).getForecast());
		assertEquals("69 F", output.get(3).getLow());
		assertEquals("TST", output.get(4).getParkCode());
		assertEquals("partlyCloudy", output.get(4).getForecast());
	}
	
	@Test
	public void get_weather_forecast_C() {
		List<Weather> output = weatherDAO.getFiveDayForecast("TST", "C");
		
		assertEquals(5, output.size());
		
		assertEquals("Pack some sunblock! Bring an extra gallon of water", output.get(0).getAdvisory());
		assertEquals("snow", output.get(1).getForecast());
		assertEquals("10 C", output.get(2).getLow());
		assertEquals("TST", output.get(3).getParkCode());
		assertEquals("18 C", output.get(4).getHigh());
		assertEquals("1", output.get(0).getDay());
	}
	
	
	
	
	
	
}
