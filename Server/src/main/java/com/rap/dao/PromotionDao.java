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

import com.rap.idao.PromotionIDao;
import com.rap.models.PromotionInfo;

@Repository
public class PromotionDao implements PromotionIDao{
	
	private static final Logger logger = LoggerFactory.getLogger(PromotionDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	public void create(String project_key, String name, String summary, int grade_time, int grade_money, String target_activity) {
		jdbcTemplate.update("insert into promotion (project_key, name, summary, grade_time, grade_money, target_activity) values (?, ?, ?, ?, ?, ?)", new Object[] { project_key, name, summary, grade_time, grade_money, target_activity });
	}

	public List<PromotionInfo> selectFromProject(String project_key){
		return jdbcTemplate.query("select * from promotion where project_key = ? order by reg_date desc",
		    	new Object[] { project_key }, new RowMapper<PromotionInfo>() {
		    	public PromotionInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PromotionInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getString("summary")
		    				, resultSet.getInt("grade_time")
		    				, resultSet.getInt("grade_money")
		    				, resultSet.getString("target_activity"));
		    	}
		    });
	}
	
	public PromotionInfo selectFromProject(String project_key, String promotion_name){
		List<PromotionInfo> result= jdbcTemplate.query("select * from promotion where project_key = ? and name = ? order by reg_date desc",
		    	new Object[] { project_key,promotion_name }, new RowMapper<PromotionInfo>() {
		    	public PromotionInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PromotionInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getString("summary")
		    				, resultSet.getInt("grade_time")
		    				, resultSet.getInt("grade_money")
		    				, resultSet.getString("target_activity"));
		    	}
		    });
		
		if(result.size() == 1)
			return result.get(0);
		else if(result.size() > 1)
		{
			logger.info("프로모션 중복 에러");
			return result.get(0);
		}
		else
		{
			logger.info("해당 프로모션 존재 X");
			return null;
		}
	}
	
	public List<PromotionInfo> selectAll(){
		return jdbcTemplate.query("select * from promotion order by reg_date desc",
		    	new Object[] {  }, new RowMapper<PromotionInfo>() {
		    	public PromotionInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PromotionInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getString("summary")
		    				, resultSet.getInt("grade_time")
		    				, resultSet.getInt("grade_money")
		    				, resultSet.getString("target_activity"));
		    	}
		    });
	}

	public void update(String name, String summary, int grade_time, int grade_money, String project_key){
		jdbcTemplate.update("update promotion set name=?,summary=?,grade_time=?,grade_money=? where project_key=?", new Object[] {name, summary, grade_time,grade_money, project_key });
	}
	
	public void deleteAll() {
		jdbcTemplate.update("delete from promotion");
	}
	public void delete(int pk) {
		jdbcTemplate.update("delete from promotion where pk = ?", new Object[] { pk });
	}
	
	
}
