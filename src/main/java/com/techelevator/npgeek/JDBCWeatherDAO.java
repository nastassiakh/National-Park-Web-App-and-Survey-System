package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCWeatherDAO implements WeatherDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCWeatherDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getFiveDayForecast(String parkCode) {
		List<Weather> fiveDayWeather = new ArrayList<Weather>();

		String sqlForecast = "SELECT * FROM weather WHERE parkcode = ?";
		SqlRowSet forecastResults = jdbcTemplate.queryForRowSet(sqlForecast, parkCode);

		while (forecastResults.next()) {
			Weather weather = new Weather();
			weather.setParkCode(forecastResults.getString("parkcode"));
			weather.setDay(forecastResults.getLong("fivedayforecastvalue"));
			long low = forecastResults.getLong("low");
			weather.setLow(low);
			long high = forecastResults.getLong("high");
			weather.setHigh(high);
			String forecast = forecastResults.getString("forecast");
			if (forecast.equals("partly cloudy")) {
				forecast = "partlyCloudy";
			}
			weather.setForecast(forecast);
			String advisory = "";
			if (forecast.contains("snow")) {
				advisory = "Bring some snowshoes!";
			}
			if (forecast.contains("rain")) {
				advisory = "Pack some rain gear and wear waterproof shoes";
			}
			if (forecast.contains("thunderstorm")) {
				advisory = "Seek shelter and avoid hiking on exposed ridges";
			}
			if (forecast.contains("sunny")) {
				advisory = "Pack some sunblock";
			}
			String advisoryTemp = "";
			if(high > 75) {
				advisoryTemp = " Bring and extra gallon of water";
			}
			if((high - low) >= 20) {
				advisoryTemp = " Wear breathable layers";
			}
			if(low < 20) {
				advisoryTemp = " Frigid tempatures can be very dangerous";
			}
			weather.setAdvisory(advisory);
			weather.setAdvisoryTemp(advisoryTemp);
			fiveDayWeather.add(weather);
		}

		return fiveDayWeather;
	}

}
