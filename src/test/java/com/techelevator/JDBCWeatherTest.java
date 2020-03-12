package com.techelevator;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCWeatherTest extends DAOIntegrationTest{
	
	private JdbcTemplate jdbcTemplate;

	
	public JDBCWeatherTest(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Test
	
	
	
	
	
	
}
