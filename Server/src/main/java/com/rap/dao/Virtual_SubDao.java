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

import com.rap.idao.Virtual_SubIDao;
import com.rap.models.Virtual_MainInfo;
import com.rap.models.Virtual_SubInfo;

@Repository
public class Virtual_SubDao implements Virtual_SubIDao{
	private static final Logger logger = LoggerFactory.getLogger(Virtual_SubDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	public void create(String project_key, String name, int price, String image, String description) {
		jdbcTemplate.update("insert into virtual_sub (project_key, name, price, image, description) values (?, ?, ?, ?, ?)"
				, new Object[] { project_key, name, price, image, description});
	}
	public List<Virtual_SubInfo> select(String project_key) {
		return jdbcTemplate.query("select * from virtual_sub where project_key = ?",
		    	new Object[] { project_key }, new RowMapper<Virtual_SubInfo>() {
		    	public Virtual_SubInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new Virtual_SubInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getInt("price")
		    				, resultSet.getString("image")
		    				, resultSet.getString("description")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}
	public Virtual_SubInfo selectOne(String project_key) {
		List<Virtual_SubInfo> result = jdbcTemplate.query("select * from virtual_sub where project_key = ?",
		    	new Object[] { project_key }, new RowMapper<Virtual_SubInfo>() {
		    	public Virtual_SubInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new Virtual_SubInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getInt("price")
		    				, resultSet.getString("image")
		    				, resultSet.getString("description")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
		
		if(result.size() == 1){
			return result.get(0);
		}
		else if(result.size() > 1){
			logger.info("가상화폐 main 정보 조회시 데이터 중복 버그");
			return result.get(0);
		}
		else{
			return null;
		}
	}
	public List<Virtual_SubInfo> select(String project_key, String name) {
		return jdbcTemplate.query("select * from virtual_sub where project_key = ? and name = ?",
		    	new Object[] { project_key,name }, new RowMapper<Virtual_SubInfo>() {
		    	public Virtual_SubInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new Virtual_SubInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getInt("price")
		    				, resultSet.getString("image")
		    				, resultSet.getString("description")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}
	public void update(String project_key, String name, String description) {
		jdbcTemplate.update("update virtual_sub set name=?, description=? where project_key=?",
				 new Object[] { name, description, project_key});
	}
	public void deleteAll() {
		jdbcTemplate.update("delete from virtual_sub");
	}
	public void delete(String project_key) {
		jdbcTemplate.update("delete from virtual_sub where project_key = ?", new Object[] { project_key });
	}

}
