package com.techelevator;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	public String displayHomepage(ModelMap map) {
		List<Park> parks = parkDao.getAllParks();
		map.put("parks", parks);
		return "homepage";
	}

	@RequestMapping("/parkdetail")
	public String displayParkdetail(ModelMap map, @RequestParam String parkCode, HttpSession session) {
		List<Weather> forecast = weatherDao.getFiveDayForecast(parkCode, (String)session.getAttribute("tempScale"));
		map.put("forecast", forecast);
		Park park = parkDao.getParkByCode(parkCode);
		map.put("park", park);
		String pic = park.getParkCode().toLowerCase();
		map.put("parkpic", pic);
		session.setAttribute("parkCode", parkCode);
		return "parkdetail";
	}

	@RequestMapping(path = "/parkdetail", method = RequestMethod.POST)
	public String getTemp(HttpSession session, @RequestParam String tempScale) {
		session.setAttribute("tempScale", tempScale);
		return "redirect:/parkdetail?parkCode=" + session.getAttribute("parkCode");
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
