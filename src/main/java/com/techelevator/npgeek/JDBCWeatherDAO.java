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
	public List<Weather> getFiveDayForecast(String parkCode, String tempScale) {
		List<Weather> fiveDayWeather = new ArrayList<Weather>();
/*
 *if (userTempSelection == c){
 *high = high * 1.8 + 32;
 *low = low * 1.8 + 32;
 *
 * */
		String sqlForecast = "SELECT * FROM weather WHERE parkcode = ?";
		SqlRowSet forecastResults = jdbcTemplate.queryForRowSet(sqlForecast, parkCode);

		while (forecastResults.next()) {
			Weather weather = new Weather();
			weather.setParkCode(forecastResults.getString("parkcode"));
			weather.setDay(forecastResults.getLong("fivedayforecastvalue"));
			weather.setLow(celsiusConversion(forecastResults.getLong("low"), tempScale));
			weather.setHigh(celsiusConversion(forecastResults.getLong("high"), tempScale));
			String forecast = forecastResults.getString("forecast");
			if (forecast.equals("partly cloudy")) {
				forecast = "partlyCloudy";
			}
			weather.setForecast(forecast);

			weather.setAdvisory(getWeatherAdvisory(parkCode, forecastResults.getLong("fivedayforecastvalue")));
			fiveDayWeather.add(weather);
		}

		return fiveDayWeather;
	}

	public String celsiusConversion(long temp, String tempScale) {
		String tempString = "";
		Long tempLong;
		if(tempScale == null) {
			tempScale = "F";
		}
		if (tempScale.equals("C")) {
			tempLong = (Long)(((temp - 32) * 5) / 9);
			tempString = tempLong.toString() + " " + tempScale;
			return tempString;
		} else {
			tempLong = (Long)temp;
			tempString = tempLong.toString() + " " + tempScale;
			return tempString;
		}
	}

	public String getWeatherAdvisory(String parkCode, long dayNum) {

		String sqlAdvisory = "SELECT low, high, forecast FROM weather WHERE parkcode = ? AND fivedayforecastvalue = ?";
		SqlRowSet advisoryResults = jdbcTemplate.queryForRowSet(sqlAdvisory, parkCode, dayNum);

		String advisory = "";
		String advisoryTemp = "";
		while (advisoryResults.next()) {

			long low = advisoryResults.getLong("low");
			long high = advisoryResults.getLong("high");
			String forecast = advisoryResults.getString("forecast");

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

			if (high > 75) {
				advisoryTemp = " Bring and extra gallon of water";
			}
			if ((high - low) >= 20) {
				advisoryTemp = " Wear breathable layers";
			}
			if (low < 20) {
				advisoryTemp = " Frigid tempatures can be very dangerous";
			}

		}
		return advisory + "\n" + advisoryTemp;
	}

}
