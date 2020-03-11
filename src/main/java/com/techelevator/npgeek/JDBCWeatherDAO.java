package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCWeatherDAO implements WeatherDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCWeatherDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getFiveDayForecast(String parkCode) {
		List<Weather> forecast = new ArrayList<Weather>();
		
			String sqlForecast = "SELECT * FROM weather WHERE parkcode = ?";
			SqlRowSet forecastResults = jdbcTemplate.queryForRowSet(sqlForecast, parkCode);
			
			while(forecastResults.next()) {
				Weather weather = new Weather();
				weather.setParkCode(forecastResults.getString("parkcode"));
				weather.setDay(forecastResults.getLong("fivedayforecastvalue"));
				weather.setLow(forecastResults.getLong("low"));
				weather.setHigh(forecastResults.getLong("high"));
				weather.setForecast(forecastResults.getString("forecast"));
				forecast.add(weather);
			}
			
		return forecast;
	}

}
