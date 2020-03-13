package com.techelevator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.JdbcParkDao;
import com.techelevator.npgeek.Park;


public class JDBCParkDAOIntegration extends DAOIntegrationTest {	
	
	private DataSource dataSource = getDataSource();
	private JdbcTemplate jdbc = new JdbcTemplate(dataSource);
	
	JdbcParkDao parkDao = new JdbcParkDao(dataSource);
	
	@Before
	public void create_new_park(){
		String sqlDeleteWeather = "DELETE FROM weather";
		jdbc.update(sqlDeleteWeather);
		String sqlDeletePark = "Delete FROM park";
		jdbc.update(sqlDeletePark);
		
		
		
		
	}
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	@Test
	public void check_get_all_parks() {
		Park newPark = new Park();
		newPark.setParkCode("SFRF");
		newPark.setParkName("SouthPark");
		newPark.setState("Arizona");
		newPark.setAcreage(354647474L);
		newPark.setElevation(5678L);
		newPark.setMilesOfTrail(134.9F);
		newPark.setNumberOfCampsites(200L);
		newPark.setClimate("tropical");
		newPark.setYearFounded(1970L);
		newPark.setAnnualVisitorCount(4675758L);
		newPark.setQuote("come enjoy outdoors");
		newPark.setQuoteSource("unknown");
		newPark.setDescription("Lots of outdoor activities");
		newPark.setNumberOfAnimalSpecies(300L);
		
		newPark.setEntryFee(20L);		
		
		parkDao.createPark(newPark);
		
		Park newPark1 = new Park();
		
		newPark1.setParkCode("FRFW");
		newPark1.setParkName("NorthPark");
		newPark1.setState("Arizona");
		newPark1.setAcreage(35464L);
		newPark1.setElevation(568L);
		newPark1.setMilesOfTrail(13.9F);
		newPark1.setNumberOfCampsites(20L);
		newPark1.setClimate("tropical");
		newPark1.setYearFounded(1970L);
		newPark1.setAnnualVisitorCount(46757L);
		newPark1.setQuote("come enjoy outdoors");
		newPark1.setQuoteSource("unknown");
		newPark1.setDescription("Lots of outdoor activities");
		newPark1.setNumberOfAnimalSpecies(300L);
		
		newPark1.setEntryFee(20L);		
		
		parkDao.createPark(newPark1);
		
		
	List<Park> parks =new ArrayList<Park>();
	parks =	parkDao.getAllParks();
	Assert.assertEquals(2, parks.size());
	assertValuesPark(newPark,parks.get(1));
	assertValuesPark(newPark1,parks.get(0));

	
		
	}
	@Test
	public void check_park_by_code() {
Park newPark1 = new Park();
		
		newPark1.setParkCode("FRFW");
		newPark1.setParkName("NorthPark");
		newPark1.setState("Arizona");
		newPark1.setAcreage(35464L);
		newPark1.setElevation(568L);
		newPark1.setMilesOfTrail(13.9F);
		newPark1.setNumberOfCampsites(20L);
		newPark1.setClimate("tropical");
		newPark1.setYearFounded(1970L);
		newPark1.setAnnualVisitorCount(46757L);
		newPark1.setQuote("come enjoy outdoors");
		newPark1.setQuoteSource("unknown");
		newPark1.setDescription("Lots of outdoor activities");
		newPark1.setNumberOfAnimalSpecies(300L);
		
		newPark1.setEntryFee(20L);		
		
		parkDao.createPark(newPark1);
		Park actualPark = parkDao.getParkByCode(newPark1.getParkCode());
		assertValuesPark(newPark1, actualPark);
		
	}
	private void assertValuesPark(Park expectedPark,Park ActualPark) {
		Assert.assertEquals(expectedPark.getParkCode(), ActualPark.getParkCode());
		Assert.assertEquals(expectedPark.getParkName(), ActualPark.getParkName());
		Assert.assertEquals(expectedPark.getState(), ActualPark.getState());
		Assert.assertEquals(expectedPark.getAcreage(), ActualPark.getAcreage());
		
		Assert.assertEquals(expectedPark.getElevation(), ActualPark.getElevation());
		
		Assert.assertEquals(expectedPark.getNumberOfCampsites(), ActualPark.getNumberOfCampsites());
		Assert.assertEquals(expectedPark.getClimate(), ActualPark.getClimate());
		Assert.assertEquals(expectedPark.getYearFounded(), ActualPark.getYearFounded());
		Assert.assertEquals(expectedPark.getAnnualVisitorCount(), ActualPark.getAnnualVisitorCount());
		Assert.assertEquals(expectedPark.getQuote(), ActualPark.getQuote());
		Assert.assertEquals(expectedPark.getQuoteSource(), ActualPark.getQuoteSource());
		Assert.assertEquals(expectedPark.getDescription(), ActualPark.getDescription());
		
		Assert.assertEquals(expectedPark.getEntryFee(), ActualPark.getEntryFee());
		Assert.assertEquals(expectedPark.getEntryFee(),ActualPark.getEntryFee());
		
		
	}

}
