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

import com.rap.idao.CategoryMIDao;
import com.rap.models.CategoryMInfo;

@Repository
public class CategoryMDao implements CategoryMIDao{
	private static final Logger logger = LoggerFactory.getLogger(CategoryMDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}

	public void create(String Key, int categoryL_pk, String categoryM) {
		jdbcTemplate.update("insert into categorym (project_key, categorL_pk, categorym) values (?, ?, ?)", new Object[] { Key, categoryL_pk, categoryM });
	}
	
	//int pk, int key, String categorym, Timestamp reg_date, int categotyL_pk
	public List<CategoryMInfo> select(String key) {
		return jdbcTemplate.query("select * from categorym where project_key = ?",
		    	new Object[] { key }, new RowMapper<CategoryMInfo>() {
		    	public CategoryMInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new CategoryMInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getInt("project_key")
		    				, resultSet.getString("categorym")
		    				, resultSet.getTimestamp("reg_date")
		    				, resultSet.getInt("categorL_pk"));
		    	}
		    });
	}
	
	// TODO 쿼리문 작업해야됨
	public List<CategoryMInfo> select(String key, String categoryL){
		return null;
	}
	
	public void delete(String key, int categoryL_pk, String categoryM) {
		jdbcTemplate.update("delete from categorym where project_key = ? AND categoryL_pk = ? AND categorym = ?",
		        new Object[] { key, categoryL_pk,  categoryM});		
	}
	public void delete(String key) {
		jdbcTemplate.update("delete from categorym where project_key = ?",
		        new Object[] { key });		
	}
	
}
