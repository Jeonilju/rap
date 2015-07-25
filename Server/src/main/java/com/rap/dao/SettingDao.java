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

import com.rap.idao.SettingIDao;
import com.rap.models.PromotionInfo;
import com.rap.models.SettingInfo;

@Repository
public class SettingDao implements SettingIDao{
	private static final Logger logger = LoggerFactory.getLogger(SettingDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	public void create(String project_key, int grade_moneyl, int grade_moneym, int grade_moneys, int grade_timel, int grade_timem, int grade_times, String google_project_num ) {
		jdbcTemplate.update("insert into setting (project_key, grade_moneyl, grade_moneym, grade_moneys, grade_timel, grade_timem, grade_times, google_project_num ) values (?, ?, ?, ?, ?, ?, ?, ?)", new Object[] {project_key, grade_moneyl, grade_moneym, grade_moneys, grade_timel, grade_timem, grade_times, google_project_num });
	}

	public List<SettingInfo> selectFromProject(String project_key){
		return jdbcTemplate.query("select * from setting where project_key = ?",
		    	new Object[] { project_key }, new RowMapper<SettingInfo>() {
		    	public SettingInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new SettingInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getInt("grade_moneyl")
		    				, resultSet.getInt("grade_moneym")
		    				, resultSet.getInt("grade_moneys")
		    				, resultSet.getInt("grade_timel")
		    				, resultSet.getInt("grade_timem")
		    				, resultSet.getInt("grade_times")
		    				, resultSet.getString("google_project_num"));
		    	}
		    });
	}
}
