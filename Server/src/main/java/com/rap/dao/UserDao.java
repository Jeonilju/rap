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

import com.rap.idao.UserIDao;
import com.rap.models.UserInfo;

@Repository
public class UserDao implements UserIDao{
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	public void create(int project_key, String name) {
		logger.info("UserDao<create> project_key: " + project_key + ", name: " + name);
		jdbcTemplate.update("insert into user (project_key, name) values (?, ?)", new Object[] { project_key, name });
	}
	
	public void appCountInc(int project_key, String name){
		isExist(project_key, name);
		jdbcTemplate.update("update user set "
				+ " count = count + 1"
			+ " where project_key = ? AND name = ?", 
			new Object[]  { project_key, name});
	}
	
	public void setGCM(int project_key, String name, String gcm_id) {
		isExist(project_key, name);
		jdbcTemplate.update("update user set "
				+ " gcm_id = ?"
			+ " where project_key = ? AND name = ?", 
			new Object[]  { gcm_id, project_key, name});
	}
	public void setGradeTime(int project_key, String name, int grage_time) {
		isExist(project_key, name);
		
	}
	public void setGradeMoney(int project_key, String name, int grage_money) {
		isExist(project_key, name);
	}
	public void setPosition(int project_key, String name, double position_let,
			double position_lon) {
		isExist(project_key, name);
		jdbcTemplate.update("update user set "
				+ " position_let = ?,"
				+ " position_lon = ?"
			+ " where project_key = ? AND name = ?", 
			new Object[]  { position_let, position_lon, project_key, name});
	}
	public void setOsVersion(int project_key, String name, String os_version) {
		isExist(project_key, name);
		jdbcTemplate.update("update user set "
				+ " os_version = ?"
			+ " where project_key = ? AND name = ?", 
			new Object[]  { os_version, project_key, name});
	}
	public void setDevice(int project_key, String name, String device_vertion) {
		isExist(project_key, name);
		jdbcTemplate.update("update user set "
				+ " device_vertion = ?"
			+ " where project_key = ? AND name = ?", 
			new Object[]  { device_vertion, project_key, name});
	}
	public void setAge(int project_key, String name, int age) {
		isExist(project_key, name);
		jdbcTemplate.update("update user set "
				+ " age = ?"
			+ " where project_key = ? AND name = ?", 
			new Object[]  { age, project_key, name});
	}
	public void setSex(int project_key, String name, int sex){
		isExist(project_key, name);
		jdbcTemplate.update("update user set "
				+ " sex = ?"
			+ " where project_key = ? AND name = ?", 
			new Object[]  { sex, project_key, name});
	}
	public List<UserInfo> select(int project_key) {
		return jdbcTemplate.query("select * from user where project_key = ?",
		    	new Object[] { project_key }, new RowMapper<UserInfo>() {
		    	public UserInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new UserInfo(resultSet.getInt("pk")
		    				, resultSet.getInt("project_key")
		    				,resultSet.getString("gcm_id")
		    				, resultSet.getInt("grade_time")
		    				, resultSet.getInt("grade_money")
		    				, resultSet.getDouble("position_let")
		    				, resultSet.getDouble("position_lon")
		    				, resultSet.getInt("sex")
		    				, resultSet.getString("os_version")
		    				, resultSet.getString("device_version")
		    				, resultSet.getInt("age")
		    				, resultSet.getInt("count")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}
	
	public UserInfo selectUser(int project_key, String name){
		List<UserInfo> result = jdbcTemplate.query("select * from user where project_key=? AND name=?",
		    	new Object[] { project_key, name }, new RowMapper<UserInfo>() {
		    	public UserInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new UserInfo(resultSet.getInt("pk")
		    				, resultSet.getInt("project_key")
		    				,resultSet.getString("gcm_id")
		    				, resultSet.getInt("grade_time")
		    				, resultSet.getInt("grade_money")
		    				, resultSet.getDouble("position_let")
		    				, resultSet.getDouble("position_lon")
		    				, resultSet.getInt("sex")
		    				, resultSet.getString("os_version")
		    				, resultSet.getString("device_vertion")
		    				, resultSet.getInt("age")
		    				, resultSet.getInt("count")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
		
		if(result.size() == 1){
			return result.get(0);
		}
		else if(result.size() > 1){
			logger.info("UserDao<selectUser>: 결과값 1개 이상");
			return null;
		}
		else{
			logger.info("UserDao<selectUser>: 유저 없음");
			return null;
		}
	}
	
	public void deleteAll() {
		jdbcTemplate.update("delete from user");
	}
	public void delete(int project_key) {
		jdbcTemplate.update("delete from user where project_key = ?", new Object[] { project_key });
	}

	private void isExist(int project_key, String name){
		logger.info("UserDao<isExist>");
		
		if(selectUser(project_key, name) == null){
			logger.info("신규 유저");
			create(project_key, name);
		}
	}
	
}
