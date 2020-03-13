package com.techelevator.npgeek;

import java.util.List;

public interface SurveyResultDao {
	public SurveyResult createSurveyResult(SurveyResult survey);
	
	public List<SurveyResult> getParksByRating();

	SurveyResult findSurveyById(long id);

}
