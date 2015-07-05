package com.rap.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rap.idao.ProjectIDao;
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
	public void create(int pk, String project_name, String summary,
			String discription, int member_pk) {
		
	}
	public List<ProjectInfo> selectAll() {
		return null;
	}
	public void deleteAll() {
		
	}
	public void delete(String pk) {
		
	}
	
	
}
