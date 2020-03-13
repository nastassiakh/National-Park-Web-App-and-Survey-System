package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCSurveyResultDao implements SurveyResultDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCSurveyResultDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<SurveyResult> getParksByRating() {
		List<SurveyResult> surveyResults = new ArrayList<SurveyResult>();
		SurveyResult newSurvey = new SurveyResult();
		String sqlGetParksByRating = "SELECT parkName ,count(parkName),survey_result.parkcode FROM survey_result \n"
				+ "JOIN park on park.parkcode = survey_result.parkcode group by parkName,survey_result.parkcode ORDER BY count(parkName) DESC,parkName ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParksByRating);

		while (results.next()) {
			newSurvey = mapRowToResult(results);
			surveyResults.add(newSurvey);
		}

		return surveyResults;

	}

	@Override
	public SurveyResult createSurveyResult(SurveyResult survey) {
		Long surveyId = getNextId();
		String sqlInsertSurvey = "INSERT INTO survey_result(surveyId, parkCode, emailaddress, state, activityLevel) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sqlInsertSurvey, surveyId, survey.getParkCode(), survey.getEmailaddress(),
				survey.getState(), survey.getActivityLevel());

		survey.setSurveyId(surveyId);
		return survey;
	}
	
	@Override
	public SurveyResult findSurveyById(long surveyId) {
		SurveyResult theSurvey = null;
		String sqlSurveyById = "SELECT surveyId, parkCode, emailaddress, state, activityLevel " + "FROM survey_result " + "WHERE surveyId = ?";
		SqlRowSet results2 = jdbcTemplate.queryForRowSet(sqlSurveyById, surveyId);
		if (results2.next()) {
			theSurvey = mapRowToResult2(results2);
		}
		return theSurvey;
	}

	
	private SurveyResult mapRowToResult2(SqlRowSet results2) {
		SurveyResult newSurvey2 = new SurveyResult();
	    newSurvey2.setSurveyId(results2.getLong("surveyid"));
		newSurvey2.setState(results2.getString("state"));
		newSurvey2.setEmailaddress(results2.getString("emailaddress"));
		newSurvey2.setActivityLevel(results2.getString("activitylevel"));
		newSurvey2.setParkCode(results2.getString("parkcode"));
		return newSurvey2;
		
		
	}
	
	private SurveyResult mapRowToResult(SqlRowSet results) {

		SurveyResult newSurvey = new SurveyResult();

	    newSurvey.setParkName(results.getString("parkname"));
		newSurvey.setParkCode(results.getString("parkcode"));
		newSurvey.setNumberOfVotes(results.getInt("count"));
	
		return newSurvey;

	}

	private Long getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL(('seq_surveyid'::regclass))";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long surveyId = null;
		if (results.next()) {
			surveyId = results.getLong(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next forum post id from sequence");
		}
		return surveyId;
	}

}
