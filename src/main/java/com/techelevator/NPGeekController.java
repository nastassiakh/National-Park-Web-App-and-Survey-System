package com.techelevator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.npgeek.Park;
import com.techelevator.npgeek.ParkDao;
import com.techelevator.npgeek.SurveyResult;
import com.techelevator.npgeek.SurveyResultDao;
import com.techelevator.npgeek.Weather;
import com.techelevator.npgeek.WeatherDAO;

@Controller
public class NPGeekController {

	@Autowired
	private WeatherDAO weatherDao;
	
	@Autowired
	private ParkDao parkDao;
	
	@Autowired
	private SurveyResultDao surveyResultDao;
	
	

	@RequestMapping("/")
	public String displayHomepage() {
		return "homepage";
	}

	@RequestMapping("/parkdetail")
	public String displayParkdetail(ModelMap map, @RequestParam String parkCode) {
		List<Weather> forecast = weatherDao.getFiveDayForecast(parkCode);
		map.put("forecast", forecast);
		Park park = parkDao.getParkByCode(parkCode);
		map.put("park", park);
		String pic = park.getParkCode().toLowerCase();
		map.put("parkpic", pic);
		
		
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
	public String submitSruvey(SurveyResult survey) {
		surveyResultDao.createSurveyResult(survey);
		return "redirect:/viewsurvey";
	}

	@RequestMapping("/viewsurvey")
	public String viewsurveys() {
		return "parkRating";
	}

}
