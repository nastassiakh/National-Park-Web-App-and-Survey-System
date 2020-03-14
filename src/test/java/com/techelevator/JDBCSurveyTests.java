package com.techelevator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.npgeek.JDBCSurveyResultDao;
import com.techelevator.npgeek.SurveyResult;

import cucumber.api.java.Before;

public class JDBCSurveyTests extends DAOIntegrationTest {

	private JdbcTemplate jdbcTemplate;
	private JDBCSurveyResultDao surveyDao;
	private DataSource dataSource = getDataSource();
	SurveyResult newSurveyResult;
	Long surveyId = new Long(0);

	public JDBCSurveyTests() {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.surveyDao = new JDBCSurveyResultDao(dataSource);
	}

	@Before
	public void setUp() {
		
		

	}

	@Test
	public void test_create_survey_result() {

	    newSurveyResult = new SurveyResult();
		newSurveyResult.setParkCode("XXX");
		newSurveyResult.setEmailaddress("java@gmail.com");
		newSurveyResult.setState("Florida");
		newSurveyResult.setActivityLevel("Sedentary");

		newSurveyResult.setSurveyId((long) 900000000);

		surveyDao.createSurveyResult(newSurveyResult);// use create method from JDBC

		// Now we have to test that that all worked as expected
		// next we are pulling out the unique ID to look the new row up in the database
		// Then we make a new Department object from whatever we fetch from the database
		SurveyResult createdSurveyResult = surveyDao.findSurveyById(newSurveyResult.getSurveyId());

		// Then we make sure our object that pulled from the database is not null
		assertNotEquals(null, newSurveyResult.getParkCode());

		// Then we make sure the name of the object we created is the same as the object
		// we got from the database. Have helper method assertSurveysAreEqual down below
		assertSurveysAreEqual(newSurveyResult, createdSurveyResult);

	}

	@Test
	public void get_parks_by_rating() {

		SurveyResult newSurveyResult = new SurveyResult();
		newSurveyResult.setParkCode("DDD");
		newSurveyResult.setEmailaddress("jamba@gmail.com");
		newSurveyResult.setState("Wherever");
		newSurveyResult.setActivityLevel("Active");

		newSurveyResult.setSurveyId((long) 800000000);

		surveyDao.createSurveyResult(newSurveyResult);

		int newSurveyVotes = newSurveyResult.getNumberOfVotes();

		
		List<SurveyResult> surveyList = surveyDao.getParksByRating();

		SurveyResult testResult = surveyList.get(0);
		
		int firstSurveyVotes = testResult.getNumberOfVotes();
		
        boolean votes = false;
		if (firstSurveyVotes >= newSurveyVotes) {
           votes = true;
		}

		Assert.assertNotNull(surveyList);
		Assert.assertTrue(votes);

	}

	private void assertSurveysAreEqual(SurveyResult expected, SurveyResult actual) {
		assertEquals(expected.getSurveyId(), actual.getSurveyId());
		assertEquals(expected.getParkCode(), actual.getParkCode());
		assertEquals(expected.getEmailaddress(), actual.getEmailaddress());
		assertEquals(expected.getState(), actual.getState());
		assertEquals(expected.getActivityLevel(), actual.getActivityLevel());
	}

}
