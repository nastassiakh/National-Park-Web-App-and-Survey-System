package com.techelevator.npgeek;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCParkRatingDao implements ParkRatingDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCParkRatingDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getParksByRating() {
		// TODO Auto-generated method stub
		return null;
	}

}
