package com.techelevator.npgeek;

import java.util.List;

public interface WeatherDAO {
	
	public List<Weather> getFiveDayForecast(String parkCode, String tempScale);
	public List<Weather> getFiveDayForecastByName(String parkName,String tempScale);
	public String getWeatherAdvisoryByName(String parkname, long dayNum);
	

}
