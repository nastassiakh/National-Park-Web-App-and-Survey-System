package com.techelevator.npgeek;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class SurveyResult {
	
	
	private Long surveyId ;
	
	@NotBlank(message = "Park Code is Required")
	@Size(max = 5, message = "Park Code Can't be over 5 characters")
	private String parkCode;
	
	@NotBlank(message = "Email is Required")
	@Email(message = "Email must be a valid email address")
	private String emailaddress;
	

	@NotBlank(message = "State is Required")
	@Size(max = 15, message = "State Can't be over 15 characters")
	private String state;
	
	@NotBlank(message = "Activity Level is Required")
	@Size(max = 15, message = "Activity Level Can't be over 15 characters")
	private String activityLevel;
	private String parkName;
	private int numberOfVotes;
	
	public int getNumberOfVotes() {
		return numberOfVotes;
	}
	public void setNumberOfVotes(int numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}
	public String getParkName() {
		return parkName;
	}
	public void setParkName(String parkName) {
		this.parkName = parkName;
	}
	public Long getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
	
	

}
