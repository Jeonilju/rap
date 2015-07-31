package com.rap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rap.idao.TimeIDao;
import com.rap.models.SettingInfo;
import com.rap.models.TimeInfo;

@Repository
public class TimeDao implements TimeIDao{
	private static final Logger logger = LoggerFactory.getLogger(TimeDao.class);
	
	@Autowired
	private SettingDao settingDao;
	
	@Autowired
	private UserDao userDao;
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	
	public void create(String project_key, String name, Timestamp start, Timestamp end){
		jdbcTemplate.update("insert into log_time (project_key, name, start, end) values (?, ?, ?, ?)", new Object[] { project_key, name, start, end});
		
		//SELECT sum(end - start) FROM rap.log_time where project_key = '1' and name = '681c2b85bc0ddacb';
		SettingInfo projectInfo = settingDao.selectFromProject(project_key).get(0);
		int usingTime = jdbcTemplate.queryForInt("select sum(end - start) from log_time where project_key='" + project_key + "' and name = '" + name + "'") / 3600;
		
		int time_L = projectInfo.getGrade_timel();
		int time_M = projectInfo.getGrade_timem();
		int time_S = projectInfo.getGrade_times();
		
		if(time_L < usingTime){
			// 1등급
			userDao.setGradeTime(project_key, name, 1);
		}
		else if(time_M < usingTime){
			// 2등급
			userDao.setGradeTime(project_key, name, 2);
		}
		else if(time_S < usingTime){
			// 3등급
			userDao.setGradeTime(project_key, name, 3);
		}
		else{
			// 4등급
			userDao.setGradeTime(project_key, name, 4);
		}
	}
	
	public List<TimeInfo> select(String project_key){
		return jdbcTemplate.query("select * from log_time where project_key = ?",
		    	new Object[] { project_key }, new RowMapper<TimeInfo>() {
		    	public TimeInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new TimeInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getTimestamp("start")
		    				, resultSet.getTimestamp("end"));
		    	}
		    });
	}
	
	public List<TimeInfo> select(String project_key, Timestamp start, Timestamp end, int sex, int age, int grade_time, int grade_using){
		String query = "";
		if(sex != 0){
			query += "AND user.sex = " + sex;
		}
		if(age != 0){
			query += " AND user.age >= " + sex + " AND user.age < " + (sex + 10);
		}
		if(grade_time != 0){
			query += " AND user.grade_time = " + grade_time;
		}
		if(grade_using != 0){
			query += " AND user.grade_money = " + grade_using;
		}
		
		return jdbcTemplate.query("select * from log_time JOIN user "
				+ "where log_time.project_key = ? AND log_time.start >= ? AND log_time.end <= ?"
				+ " AND log_time.name = user.name " + query,
		    	new Object[] { project_key, start, end }, new RowMapper<TimeInfo>() {
		    	public TimeInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new TimeInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getTimestamp("start")
		    				, resultSet.getTimestamp("end"));
		    	}
		    });
	}
	
	public void deleteAll(){
		jdbcTemplate.update("delete from log_time");
	}
	
	public void delete(String project_key){
		jdbcTemplate.update("delete from log_time where project_key = ?", new Object[] { project_key });
	}
}
