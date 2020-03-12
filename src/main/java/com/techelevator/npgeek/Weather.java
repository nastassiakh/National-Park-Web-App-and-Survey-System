package com.techelevator.npgeek;

public class Weather {
	private String parkCode;
	private Long day;
	private String low;
	private String high;
	private String forecast;
	private String advisory;
	
	

	public String getAdvisory() {
		return advisory;
	}
	public void setAdvisory(String advisory) {
		this.advisory = advisory;
	}
	public String getParkCode() {	
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public Long getDay() {
		return day;
	}
	public void setDay(Long day) {
		this.day = day;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	
	

}
