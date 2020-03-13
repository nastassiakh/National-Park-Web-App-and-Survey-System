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

	Long surveyId = new Long(0);

	public JDBCSurveyTests() {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.surveyDao = new JDBCSurveyResultDao(dataSource);
	}

	@Before
	public void setUp() {//creating mocking survey in table survey_result to be able test it
		//String sqlDeleteSurvey = "DELETE from survey_result";
		/*
		 * String sqlSelectNextId = "SELECT NEXTVAL(('seq_surveyid'::regclass))";
		 * SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		 * results.next(); // advances to the first row surveyId = results.getLong(1);
		 * // returns the integer value of the first column of table (i.e. index 1)
		 * 
		 * String sqlInsertSurvey =
		 * "INSERT INTO survey_result(surveyid, parkcode, emailaddress, state, activitylevel) VALUES (?, 'GPG', 'xxx@gmail.com', 'PA', 'Extremely Active')"
		 * ; jdbcTemplate.update(sqlInsertSurvey, surveyId);
		 */

		//surveyDao = new JDBCSurveyResultDao(dataSource);
		
		//jdbcTemplate.update(qlDeleteSurvey);
	}

	@Test
	public void test_create_survey_result() {

		SurveyResult newSurveyResult = new SurveyResult();
		newSurveyResult.setParkCode("XXX");
		newSurveyResult.setEmailaddress("java@gmail.com");
		newSurveyResult.setState("Florida");
		newSurveyResult.setActivityLevel("Sedentary");
		
        newSurveyResult.setSurveyId((long)900000000);
	
        surveyDao.createSurveyResult(newSurveyResult);//use create method from JDBC
        
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
	 public void get_parks_by_rating() { List<SurveyResult> surveyList =
	 surveyDao.getParksByRating();
	 
	 Assert.assertNotNull(surveyList); Assert.assertEquals(1, surveyList.size());
	 // Department dept = deptList.get(0); Assert.assertEquals(testDepartmentName,
	 deptList.get(0).getName()); 
	 }
	 
	 
	 
	
	private void assertSurveysAreEqual(SurveyResult expected, SurveyResult actual) {
		assertEquals(expected.getSurveyId(), actual.getSurveyId());
		assertEquals(expected.getParkCode(), actual.getParkCode());
		assertEquals(expected.getEmailaddress(), actual.getEmailaddress());
		assertEquals(expected.getState(), actual.getState());
		assertEquals(expected.getActivityLevel(), actual.getActivityLevel());
	}

}
