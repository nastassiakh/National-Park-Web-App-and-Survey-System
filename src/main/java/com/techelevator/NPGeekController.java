package com.techelevator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techelevator.npgeek.WeatherDAO;

@Controller
public class NPGeekController {
	
	@Autowired
	private WeatherDAO weatherDao;
	
	@RequestMapping("/")
	public String displayHomepage() {
		return "homepage";
	}
	
	@RequestMapping("/parkdetail")
	public String displayParkdetail() {
		return "parkdetail";
	}
	
	@RequestMapping(path = "/parkdetail", method = RequestMethod.POST)
	public String getTemp() {
		return "redirect:/parkdetail";
	}
	
	@RequestMapping("/submitsurvey")
	public String viewSurveyInput() {
		return "surveyInput";
	}
	
	@RequestMapping(path = "/submitsurvey", method = RequestMethod.POST)
	public String submitSruvey() {
		return "redirect:/viewsurvey";
	}
	
	@RequestMapping("/viewsurvey")
	public String viewsurveys() {
		return "parkRating";
	}
	
	

}
