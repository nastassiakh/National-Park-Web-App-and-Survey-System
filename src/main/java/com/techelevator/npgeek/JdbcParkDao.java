package com.techelevator.npgeek;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.ssg.model.forum.ForumPost;

public class JdbcParkDao implements ParkDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcParkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		String sqlGetAllParks = "SELECT * FROM park";
		SqlRowSet result =	jdbcTemplate.queryForRowSet(sqlGetAllParks);
		List <Park> parks = new ArrayList <Park>();
		Park newPark = new Park();
		while (result.next()) {
			newPark = mapRowToPark(result);
			parks.add(newPark);
		}
		return parks;
		
		
	}
	
	@Override
	public Park getParkByCode(String parkCode) {
		Park newPark = new Park();
		String sqlGetParkByCode = "SELECT * FROM park WHERE parkcode = ? ";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetParkByCode,parkCode);
		if (result.next()) {
			newPark = mapRowToPark(result);
		}
		return newPark;
	}
	
	private Park mapRowToPark(SqlRowSet result) {

		Park newPark = new Park();
		newPark.setParkCode(result.getString("parkcode"));
		newPark.setParkName(result.getString("parkname"));
		newPark.setState(result.getString("state"));
		newPark.setAcreage(result.getLong("acreage"));
		newPark.setElevation(result.getLong("elevationinfeet"));
		newPark.setMilesOfTrail(result.getFloat("milesoftrail"));
		newPark.setNumberOfCampsites(result.getLong("numberofcampsites"));
		newPark.setClimate(result.getString("climate"));
		newPark.setYearFounded(result.getLong("yearfounded"));
		newPark.setAnnualVisitorCount(result.getLong("annualvisitorcount"));
		newPark.setQuote(result.getString("inspirationalquote"));
		newPark.setQuoteSource(result.getString("inspirationalquotesource"));
		newPark.setDescription(result.getNString("parkdescription"));
		newPark.setEntryFee(result.getLong("entryfee"));
		newPark.setNumberOfAnimalSpecies(result.getLong("numberofanimalspecies"));
		
		return newPark;

	}

	

	
	
}
