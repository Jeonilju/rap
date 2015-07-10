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

import com.rap.idao.Virtual_MainIDao;
import com.rap.models.ActivityInfo;
import com.rap.models.Virtual_MainInfo;

@Repository
public class Virtual_MainDao implements Virtual_MainIDao{
	private static final Logger logger = LoggerFactory.getLogger(Virtual_MainDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	public void create(String project_key, String name, int price, String image, String description) {
		jdbcTemplate.update("insert into virsual_main (project_key, name, price, image, description) values (?, ?, ?, ?, ?)"
				, new Object[] { project_key, name, price, image, description});
	}
	public List<Virtual_MainInfo> select(String project_key) {
		return jdbcTemplate.query("select * from virsual_main where project_key = ?",
		    	new Object[] { project_key }, new RowMapper<Virtual_MainInfo>() {
		    	public Virtual_MainInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new Virtual_MainInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getInt("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getInt("price")
		    				, resultSet.getString("image")
		    				, resultSet.getString("discription")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}
	public void deleteAll() {
		jdbcTemplate.update("delete from virsual_main");
	}
	public void delete(String project_key) {
		jdbcTemplate.update("delete from virsual_main where project_key = ?", new Object[] { project_key });
	}

}
