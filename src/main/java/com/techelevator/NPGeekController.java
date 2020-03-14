package com.techelevator;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		// Get either F or C from session
		String tempType = (String) session.getAttribute("tempScale");
		// run getFiveDayForecast and get correct weather number
		List<Weather> forecast = weatherDao.getFiveDayForecast(parkCode, tempType);
		
		
		map.put("forecast", forecast);
		Park park = parkDao.getParkByCode(parkCode);
		map.put("park", park);
		String pic = park.getParkCode().toLowerCase();
		map.put("parkpic", pic);
		session.setAttribute("parkCode", parkCode);
		map.put("detailURL", "parkdetail?parkCode=" + parkCode);
		
		return "parkdetail";
	}

	@RequestMapping(path = "/parkdetail", method = RequestMethod.POST)
	public String getTemp(HttpSession session, @RequestParam String tempScale, @RequestParam String detailURL) {
		//pull tempScale out of dropdown menu and put it into the session
		session.setAttribute("tempScale", tempScale);
		return "redirect:/" + detailURL;
	}

	@RequestMapping("/submitsurvey")
	public String viewSurveyInput(Model modelHolder) {
		if (!modelHolder.containsAttribute("surveyModel")) {
			modelHolder.addAttribute("surveyModel", new SurveyResult());
		}
		return "surveyInput";
	}

	
	@RequestMapping(path = "/submitsurvey", method = RequestMethod.POST)
	public String submitSruvey(@Valid @ModelAttribute("surveyModel") SurveyResult survey, BindingResult result,
			RedirectAttributes flash) {

		if (result.hasErrors()) {
			flash.addFlashAttribute("surveyModel", survey);
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "surveyModel", result);
			return "redirect:/submitsurvey";
		} 
		
		surveyResultDao.createSurveyResult(survey);
		
		return "redirect:/viewsurvey";
	}

	@RequestMapping("/viewsurvey")
	public String viewsurveys(ModelMap map) {
		List<SurveyResult> surveyResult = surveyResultDao.getParksByRating();

		map.addAttribute("surveyMap", surveyResult);

		return "parkRating";

	}

}
