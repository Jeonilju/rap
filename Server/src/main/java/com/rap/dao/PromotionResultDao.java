package com.rap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.rap.idao.PromotionResultIDao;
import com.rap.models.PromotionResultInfo;

public class PromotionResultDao implements PromotionResultIDao{

	private static final Logger logger = LoggerFactory.getLogger(PromotionResultDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}

	public void create(String project_pk, int promotion_pk, String username) {
		jdbcTemplate.update("insert into promotion_result (project_key, promotion_pk, username) values (?, ?, ?)"
				, new Object[] { project_pk, promotion_pk, username });
	}

	public List<PromotionResultInfo> selectAll() {
		return jdbcTemplate.query("select * from promotion_result"
				, new RowMapper<PromotionResultInfo>() {
		    	public PromotionResultInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PromotionResultInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getInt("promotion_pk")
		    				, resultSet.getString("username")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}

	public List<PromotionResultInfo> select(String project_pk) {
		return jdbcTemplate.query("select * from promotion_result where project_key = ?" ,
		    	new Object[] { project_pk }
				, new RowMapper<PromotionResultInfo>() {
		    	public PromotionResultInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PromotionResultInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getInt("promotion_pk")
		    				, resultSet.getString("username")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}

	public List<PromotionResultInfo> select(String project_pk, int promotion_pk) {
		return jdbcTemplate.query("select * from promotion_result where project_key = ? and promotion_pk = ?" ,
		    	new Object[] { project_pk, promotion_pk }
				, new RowMapper<PromotionResultInfo>() {
		    	public PromotionResultInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PromotionResultInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getInt("promotion_pk")
		    				, resultSet.getString("username")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}

	public void delete(String pk) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("delete from promotion_result where project_key = ?",
		        new Object[] { pk });
	}

}
