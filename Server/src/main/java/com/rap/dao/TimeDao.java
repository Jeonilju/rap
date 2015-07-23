package com.rap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rap.idao.TimeIDao;
import com.rap.models.TimeInfo;

@Repository
public class TimeDao implements TimeIDao{
	private static final Logger logger = LoggerFactory.getLogger(TimeDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	
	public void create(int project_key, String name, Timestamp start, Timestamp end){
		jdbcTemplate.update("insert into log_time (project_key, name, start, end) values (?, ?, ?, ?)", new Object[] { project_key, name, start, end});
	}
	
	public List<TimeInfo> select(int project_key){
		return jdbcTemplate.query("select * from log_time where project_key = ?",
		    	new Object[] { project_key }, new RowMapper<TimeInfo>() {
		    	public TimeInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new TimeInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getInt("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getTimestamp("start")
		    				, resultSet.getTimestamp("end"));
		    	}
		    });
	}
	
	public List<TimeInfo> select(int project_key, Timestamp start, Timestamp end){
		return jdbcTemplate.query("select * from log_time where project_key = ? AND start >= ? AND end <= ?",
		    	new Object[] { project_key, start, end }, new RowMapper<TimeInfo>() {
		    	public TimeInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new TimeInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getInt("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getTimestamp("start")
		    				, resultSet.getTimestamp("end"));
		    	}
		    });
	}
	
	public void deleteAll(){
		jdbcTemplate.update("delete from log_time");
	}
	
	public void delete(int project_key){
		jdbcTemplate.update("delete from log_time where project_key = ?", new Object[] { project_key });
	}
}
