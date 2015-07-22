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

import com.rap.idao.IAPIDao;
import com.rap.models.CategoryLInfo;
import com.rap.models.IAPInfo;

@Repository
public class IAPDao implements IAPIDao{
	private static final Logger logger = LoggerFactory.getLogger(IAPDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	public void create(String project_key, String iap, int price_real, int price_main,
			int price_sub, int type, String image, String description,
			String categoryL, String categoryM, String categoryS) {
		jdbcTemplate.update("insert into iap (project_key, iap, price_real, price_main, price_sub, using_type, image, description, categoryl, categorym, categorys) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
				, new Object[] { project_key, iap,  price_real, price_main, price_sub, type, image, description, categoryL, categoryM, categoryS});
	}
	public List<IAPInfo> select(String key) {
		return jdbcTemplate.query("select * from iap where project_key = ?",
		    	new Object[] { key }, new RowMapper<IAPInfo>() {
		    	public IAPInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new IAPInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("iap")
		    				, resultSet.getInt("price_real")
		    				, resultSet.getInt("price_main")
		    				, resultSet.getInt("price_sub")
		    				, resultSet.getInt("using_type")
		    				, resultSet.getString("image")
		    				, resultSet.getString("description")
		    				, resultSet.getTimestamp("reg_date")
		    				, resultSet.getString("categoryl")
		    				, resultSet.getString("categorym")
		    				, resultSet.getString("categorys"));
		    	}
		    });
	}
	public List<IAPInfo> select(String key, String categoryL) {
		return jdbcTemplate.query("select * from iap where project_key = ? AND categoryl = ?",
		    	new Object[] { key, categoryL }, new RowMapper<IAPInfo>() {
		    	public IAPInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new IAPInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("iap")
		    				, resultSet.getInt("price_real")
		    				, resultSet.getInt("price_main")
		    				, resultSet.getInt("price_sub")
		    				, resultSet.getInt("using_type")
		    				, resultSet.getString("image")
		    				, resultSet.getString("description")
		    				, resultSet.getTimestamp("reg_date")
		    				, resultSet.getString("categoryl")
		    				, resultSet.getString("categorym")
		    				, resultSet.getString("categorys"));
		    	}
		    });
	}
	public List<IAPInfo> select(String key, String categoryL, String categoryM) {
		return jdbcTemplate.query("select * from iap where project_key = ? AND categoryl = ? AND categorym = ?",
		    	new Object[] { key, categoryL, categoryM }, new RowMapper<IAPInfo>() {
		    	public IAPInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new IAPInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("iap")
		    				, resultSet.getInt("price_real")
		    				, resultSet.getInt("price_main")
		    				, resultSet.getInt("price_sub")
		    				, resultSet.getInt("using_type")
		    				, resultSet.getString("image")
		    				, resultSet.getString("description")
		    				, resultSet.getTimestamp("reg_date")
		    				, resultSet.getString("categoryl")
		    				, resultSet.getString("categorym")
		    				, resultSet.getString("categorys"));
		    	}
		    });
	}
	public List<IAPInfo> select(String key, String categoryL, String categoryM,
			String categoryS) {
		return jdbcTemplate.query("select * from iap where project_key = ? AND categoryl = ? AND categorym = ? AND categorys = ?",
		    	new Object[] { key, categoryL, categoryM, categoryS }, new RowMapper<IAPInfo>() {
		    	public IAPInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new IAPInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("iap")
		    				, resultSet.getInt("price_real")
		    				, resultSet.getInt("price_main")
		    				, resultSet.getInt("price_sub")
		    				, resultSet.getInt("using_type")
		    				, resultSet.getString("image")
		    				, resultSet.getString("description")
		    				, resultSet.getTimestamp("reg_date")
		    				, resultSet.getString("categoryl")
		    				, resultSet.getString("categorym")
		    				, resultSet.getString("categorys"));
		    	}
		    });
	}
	public void delete(String key) {
		jdbcTemplate.update("delete from iap where project_key = ?",
		        new Object[] { key });	
	}
	public void delete(String key, String categoryL) {
		jdbcTemplate.update("delete from iap where project_key = ? AND categoryl = ?",
		        new Object[] { key, categoryL });	
	}
	public void delete(String key, String categoryL, String categoryM) {
		jdbcTemplate.update("delete from iap where project_key = ? AND categoryl = ? AND categorym = ?",
		        new Object[] { key, categoryL , categoryM});	
	}
	public void delete(String key, String categoryL, String categoryM,
			String categoryS) {
		jdbcTemplate.update("delete from iap where project_key = ? AND categoryl = ? AND categorym = ? AND categorys = ?",
		        new Object[] { key, categoryL , categoryM, categoryS});	
	}

}
