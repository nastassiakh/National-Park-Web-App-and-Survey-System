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
		String sqlGetParksByRating = "SELECT parkName ,count(parkName) AS number_of_votes FROM survey_result \n" + 
				"JOIN park on park.parkcode = survey_result.parkcode group by parkName ORDER BY count(parkName) DESC,parkName ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetParksByRating);
		
		
		while (results.next()) {
			newSurvey = mapRowToResult(results);
			surveyResults.add(newSurvey);
		}
		
		
		
		return null;

	}

	@Override
	public void createSurveyResult(SurveyResult survey) {
		Long surveyId = getNextId();
		String sqlInsertSurvey = "INSERT INTO survey_result(surveyId, parkCode, emailaddress, state, activityLevel) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sqlInsertSurvey, surveyId, survey.getParkCode(),survey.getEmailaddress(),survey.getState(),survey.getActivityLevel());
		
		survey.setSurveyId(surveyId);
		}
	private SurveyResult mapRowToResult(SqlRowSet results) {

		SurveyResult newSurvey = new SurveyResult();
		
		
		newSurvey.setParkName(results.getString("parkName"));
		newSurvey.setNumberOfVotes(results.getInt("number_of_votes FROM survey_result"));
		
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


