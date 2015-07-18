package com.rap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rap.idao.ActivityIDao;
import com.rap.models.ActivityInfo;

@Repository
public class ActivityDao implements ActivityIDao {

	private static final Logger logger = LoggerFactory.getLogger(ActivityDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}

	public void create(String project_key, String name, String activity_name) {
		jdbcTemplate.update("insert into activity_log (project_key, name, activity_name) values (?, ?, ?)", new Object[] { project_key, name, activity_name });
	}
	
	public void create(String project_key, String name, String activity_name, String activityb_name) {
		jdbcTemplate.update("insert into activity_log (project_key, name, activity_name, activityb_name) values (?, ?, ?, ?)", new Object[] { project_key, name, activity_name, activityb_name });
	}

	public List<ActivityInfo> select(String project_key) {
		return jdbcTemplate.query("select * from activity_log where project_key = ?",
		    	new Object[] { project_key }, new RowMapper<ActivityInfo>() {
		    	public ActivityInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new ActivityInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getInt("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getString("activity_name")
		    				, resultSet.getString("activityb_name")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}

	public void deleteAll() {
		jdbcTemplate.update("delete from activity_log");
	}

	public void delete(String project_key) {
		jdbcTemplate.update("delete from activity_log where project_key = ?", new Object[] { project_key });
	}
}
