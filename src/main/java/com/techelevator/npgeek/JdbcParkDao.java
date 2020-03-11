package com.techelevator.npgeek;

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
		while (result.next()) {
			ForumPost Park = new ForumPost();
			post.setId(result.getLong("id"));
			post.setUsername(results.getString("username"));
			post.setSubject(results.getString("subject"));
			post.setMessage(results.getString("message"));
//			post.setDatePosted(results.getTimestamp("post_date").toLocalDateTime());
			allPosts.add(post);
		}
		return Park;
		
		
	}
	private Park mapRowToPark(SqlRowSet result) {

		Park newPark = new Park();
		newPark.setParkId(result.getLong("park_id"));
		newPark
		
		return newPark;

	}
	
}
