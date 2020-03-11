package com.techelevator.npgeek;

public class Weather {
	private String parkCode;
	private Long day;
	private Long low;
	private Long high;
	private String forecast;
	private String advisory;
	private String advisoryTemp;
	
	
	
	public String getAdvisoryTemp() {
		return advisoryTemp;
	}
	public void setAdvisoryTemp(String advisoryTemp) {
		this.advisoryTemp = advisoryTemp;
	}
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
	public Long getLow() {
		return low;
	}
	public void setLow(Long low) {
		this.low = low;
	}
	public Long getHigh() {
		return high;
	}
	public void setHigh(Long high) {
		this.high = high;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	
	

}
