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

import com.rap.idao.MemberIDao;
import com.rap.models.MemberInfo;

@Repository
public class MemberDao implements MemberIDao{
	private static final Logger logger = LoggerFactory.getLogger(MemberDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	public void create(String email, String password) {
		jdbcTemplate.update("insert into member (email, password) values (?, ?)", new Object[] { email, password });
	}
	
	public List<MemberInfo> selectAll() {
		return jdbcTemplate.query("select * from member ", new RowMapper<MemberInfo>() {
		    	public MemberInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new MemberInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("email")
		    				, resultSet.getString("password")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}
	
	public void deleteAll() {
		jdbcTemplate.update("delete from member");		
	}
	
	public void delete(String email) {
		jdbcTemplate.update("delete from member where email = ?",
		        new Object[] { email });	
	}
	
}
