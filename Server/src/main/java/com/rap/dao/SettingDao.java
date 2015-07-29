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
	
	public void updateGradeTime(int grade_timel,int grade_timem,int grade_times,String project_key){
		jdbcTemplate.update("update setting set grade_timel=?,grade_timem=?,grade_times=? where project_key=?", new Object[] {grade_timel, grade_timem, grade_times, project_key });
	}
	
	public void updateGradeMoney(int grade_moneyl,int grade_moneym,int grade_moneys,String project_key){
		jdbcTemplate.update("update setting set grade_moneyl=?,grade_moneym=?,grade_moneys=? where project_key=?", new Object[] {grade_moneyl, grade_moneym, grade_moneys, project_key });
	}
	public void updateGoogleProjectNum(String google_project_num,String project_key){
		jdbcTemplate.update("update setting set google_project_num=? where project_key=?", new Object[] {google_project_num, project_key });
	}
	
	public SettingInfo selectSettingInfo(String project_key){
		System.out.println("project_key :" + project_key);
		List<SettingInfo> result= jdbcTemplate.query("select * from setting where project_key = ?",
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
		
		if(result.size() == 1){
			System.out.println("Pass");
			return result.get(0);
		}
		else if(result.size() > 1){
			logger.info("프로젝트 중복키 버그 발생");
			System.out.println("Fail2");
			return result.get(0);
		}
		else{
			logger.info("프로젝트 키 없음 버그 발생");
			System.out.println("Fail3");
			return null;
		}
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
