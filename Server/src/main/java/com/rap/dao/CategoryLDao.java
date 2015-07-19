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

import com.rap.idao.CategoryLIDao;
import com.rap.models.CategoryLInfo;

@Repository
public class CategoryLDao implements CategoryLIDao{

	private static final Logger logger = LoggerFactory.getLogger(CategoryLDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}

	public void create(String project_key, String categoryL) {
		jdbcTemplate.update("insert into categoryl (project_key, categoryl) values (?, ?)", new Object[] { project_key, categoryL });
	}

	public List<CategoryLInfo> select(String key) {
		return jdbcTemplate.query("select * from categoryl where project_key = ?",
		    	new Object[] { key }, new RowMapper<CategoryLInfo>() {
		    	public CategoryLInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new CategoryLInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("categoryl")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}

	public List<CategoryLInfo> select(String key, String categoryl) {
		return jdbcTemplate.query("select * from categoryl where project_key = ? and categoryl = ?",
		    	new Object[] { key, categoryl }, new RowMapper<CategoryLInfo>() {
		    	public CategoryLInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new CategoryLInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("categoryl")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}
	public void delete(String key, String categoryL) {
		jdbcTemplate.update("delete from categoryl where project_key = ? AND categoryl = ?",
		        new Object[] { key, categoryL });		
	}

	public void delete(String key) {
		jdbcTemplate.update("delete from categoryl where project_key = ?",
		        new Object[] { key });		
	}
}
