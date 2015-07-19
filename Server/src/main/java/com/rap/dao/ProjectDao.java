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

import com.rap.idao.ProjectIDao;
import com.rap.models.ActivityInfo;
import com.rap.models.ProjectInfo;

@Repository
public class ProjectDao implements ProjectIDao{
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	public void create(String pk, String project_name, String summary,
			String description, int member_pk) {
		jdbcTemplate.update("insert into projects (pk, project_name, summary, description, member_pk) values (?, ?, ?, ?, ?)", new Object[] { pk, project_name, summary, description, member_pk });
	}
	
	public List<ProjectInfo> select(String pk){
		return jdbcTemplate.query("select * from projects where pk = ?",
		    	new Object[] { pk }, new RowMapper<ProjectInfo>() {
		    	public ProjectInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new ProjectInfo(
		    				resultSet.getString("pk")
		    				, resultSet.getString("project_name")
		    				, resultSet.getString("summary")
		    				, resultSet.getString("description")
		    				, resultSet.getInt("member_pk")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}
	
	public List<ProjectInfo> selectFromMemberPK(int member_pk)
	{
		return jdbcTemplate.query("select * from projects where member_pk = ?",
		    	new Object[] { member_pk }, new RowMapper<ProjectInfo>() {
		    	public ProjectInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new ProjectInfo(
		    				resultSet.getString("pk")
		    				, resultSet.getString("project_name")
		    				, resultSet.getString("summary")
		    				, resultSet.getString("description")
		    				, resultSet.getInt("member_pk")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}
	public void deleteAll() {
		jdbcTemplate.update("delete from projects");
	}
	public void delete(String pk) {
		jdbcTemplate.update("delete from projects where pk = ?", new Object[] { pk });
	}
	
	
}
