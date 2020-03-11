package com.techelevator.npgeek;

import java.util.List;

public interface SurveyResultDao {
	public void createSurveyResult(SurveyResult survey);
	
	public List<SurveyResult> getParksByRating();

}
