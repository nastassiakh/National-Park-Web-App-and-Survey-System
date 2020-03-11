package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCSurveyResultDao implements SurveyResultDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCSurveyResultDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<SurveyResult> getParksByRating() {
		List<SurveyResult> surveyResults = new ArrayList<SurveyResult>();
		return null;

	}

	@Override
	public void createSurveyResult(SurveyResult survey) {
		Long surveyId = getNextId();
		String sqlInsertSurvey = "INSERT INTO survey_result(surveyId, parkCode, email, state, activityLevel) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sqlInsertSurvey, surveyId, survey.getParkCode(),survey.getEmail(),survey.getState(),survey.getActivityLevel());
		
		survey.setSurveyId(surveyId);
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


