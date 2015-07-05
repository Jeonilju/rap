package com.rap.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rap.idao.CategoryLIDao;
import com.rap.models.CategoryLInfo;

@Repository
public class CategoryLDao implements CategoryLIDao{

	private static final Logger logger = LoggerFactory.getLogger(CategoryLDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void setDataSource(DataSource ds) 
	{
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);
	}
	
	@Override
	public void create(int Key, String categoryL) {
		
	}

	@Override
	public List<CategoryLInfo> select(int pk) {
		return null;
	}

	@Override
	public List<CategoryLInfo> selectAll() {
		return null;
	}

	@Override
	public void deleteAll() {
		
	}

	@Override
	public void delete(String categoryL) {
		
	}

	@Override
	public void delete(int pk) {
		// TODO Auto-generated method stub
		
	}

}
