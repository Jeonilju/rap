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

import com.rap.idao.CategorySIDao;
import com.rap.models.CategorySInfo;

@Repository
public class CategorySDao implements CategorySIDao{
	private static final Logger logger = LoggerFactory.getLogger(CategorySDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	public void create(String Key, int categoryM_pk, String categoryS) {
		jdbcTemplate.update("insert into categorys (project_key, categoryM_pk, categorys) values (?, ?, ?)", new Object[] { Key, categoryM_pk, categoryS });
	}	
	public List<CategorySInfo> select(String key) {
		return jdbcTemplate.query("select * from categorys where project_key = ?",
		    	new Object[] { key }, new RowMapper<CategorySInfo>() {
		    	public CategorySInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new CategorySInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getInt("project_key")
		    				, resultSet.getString("categorys")
		    				, resultSet.getTimestamp("reg_date")
		    				, resultSet.getInt("categoryM_pk"));
		    	}
		    });
	}
	
	// TODO 쿼리문 작성해야됨
	public List<CategorySInfo> select(String key, String categoryL, String categoryM)
	{
		return null;
	}
	
	public void delete(String key, int categoryM_pk, String categoryS) {
		jdbcTemplate.update("delete from categorys where project_key = ? AND categoryM_pk = ? AND categorys = ?",
		        new Object[] { key, categoryM_pk,  categoryS});		
	}
	public void delete(String key) {
		jdbcTemplate.update("delete from categorys where project_key = ?",
		        new Object[] { key });		
	}
}
