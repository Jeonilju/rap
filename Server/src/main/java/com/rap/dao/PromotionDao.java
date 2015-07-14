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
import com.rap.models.ProjectInfo;
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
	public void create(String project_key, String name, String summary, int grade_time, int grade_using) {
		jdbcTemplate.update("insert into promotion (project_key, name, summary, grade_time, grade_using) values (?, ?, ?, ?, ?)", new Object[] { project_key, name, summary, grade_time, grade_using });
	}

	public List<PromotionInfo> selectFromProject(String project_key){
		return jdbcTemplate.query("select * from promotion where project_key = ?",
		    	new Object[] { project_key }, new RowMapper<PromotionInfo>() {
		    	public PromotionInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PromotionInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getString("summary")
		    				, resultSet.getInt("grade_time")
		    				, resultSet.getInt("grade_using"));
		    	}
		    });
	}
	public List<PromotionInfo> selectAll(){
		return jdbcTemplate.query("select * from promotion",
		    	new Object[] {  }, new RowMapper<PromotionInfo>() {
		    	public PromotionInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PromotionInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getString("summary")
		    				, resultSet.getInt("grade_time")
		    				, resultSet.getInt("grade_using"));
		    	}
		    });
	}
	
	public void deleteAll() {
		jdbcTemplate.update("delete from promotion");
	}
	public void delete(int pk) {
		jdbcTemplate.update("delete from promotion where pk = ?", new Object[] { pk });
	}
	
	
}
