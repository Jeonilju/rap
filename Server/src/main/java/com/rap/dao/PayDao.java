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

import com.rap.idao.PayIDao;
import com.rap.models.PayInfo;

@Repository
public class PayDao implements PayIDao{
	private static final Logger logger = LoggerFactory.getLogger(PayDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	public void create(String project_key, String name, int type, int price, int item_pk) {
		jdbcTemplate.update("insert into log_pay (project_key, username, type, price, item_pk) values (?, ?, ?, ?, ?)"
				, new Object[] { project_key, name, type, price, item_pk});
	}
	public List<PayInfo> select(String project_key) {
		return jdbcTemplate.query("select * from log_pay where project_key = ?",
		    	new Object[] { project_key }, new RowMapper<PayInfo>() {
		    	public PayInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PayInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("username")
		    				, resultSet.getInt("type")
		    				, resultSet.getInt("price")
		    				, resultSet.getInt("item_pk")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}
	public List<PayInfo> select(String project_key, String name) {
		return jdbcTemplate.query("select * from log_pay where project_key = ? AND username = ?",
		    	new Object[] { project_key, name }, new RowMapper<PayInfo>() {
		    	public PayInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PayInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("username")
		    				, resultSet.getInt("type")
		    				, resultSet.getInt("price")
		    				, resultSet.getInt("item_pk")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}
	public List<PayInfo> select(String project_key, int type) {
		return jdbcTemplate.query("select * from log_pay where project_key = ? AND type = ?",
		    	new Object[] { project_key, type }, new RowMapper<PayInfo>() {
		    	public PayInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PayInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("username")
		    				, resultSet.getInt("type")
		    				, resultSet.getInt("price")
		    				, resultSet.getInt("item_pk")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}
	public void deleteAll() {
		jdbcTemplate.update("delete from log_pay");
	}
	public void delete(String key) {
		jdbcTemplate.update("delete from log_pay where project_key = ?", new Object[] { key });
	}

}
