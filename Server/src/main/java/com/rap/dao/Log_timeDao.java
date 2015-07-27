package com.rap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rap.analysismodels.OPcountInfo;
import com.rap.analysismodels.OPtimeInfo;
import com.rap.models.Log_timeInfo;

@Repository
public class Log_timeDao{
	private static final Logger logger = LoggerFactory.getLogger(Log_timeDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	public void create(String project_key, String name,Timestamp start,Timestamp end) {
		jdbcTemplate.update("insert into log_pay (project_key, name, start, end) values (?, ?, ?, ?, ?)"
				, new Object[] { project_key, name, start, end});
	}
	public List<Log_timeInfo> select(String project_key) {
		return jdbcTemplate.query("select * from log_pay where project_key = ?",
		    	new Object[] { project_key }, new RowMapper<Log_timeInfo>() {
		    	public Log_timeInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new Log_timeInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getTimestamp("start")
		    				, resultSet.getTimestamp("end")
		    				);
		    	}
		    });
	}
	public List<Log_timeInfo> select(String project_key, String name) {
		return jdbcTemplate.query("select * from log_pay where project_key = ? AND username = ?",
		    	new Object[] { project_key, name }, new RowMapper<Log_timeInfo>() {
		    	public Log_timeInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new Log_timeInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getTimestamp("start")
		    				, resultSet.getTimestamp("end")
		    				);
		    		}
		    });
	}
	public List<Log_timeInfo> select(String project_key, int type) {
		return jdbcTemplate.query("select * from log_pay where project_key = ? AND type = ?",
		    	new Object[] { project_key, type }, new RowMapper<Log_timeInfo>() {
		    	public Log_timeInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new Log_timeInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getTimestamp("start")
		    				, resultSet.getTimestamp("end")
		    				);
		    	}
		    });
	}
	public void deleteAll() {
		jdbcTemplate.update("delete from log_pay");
	}
	public void delete(String key) {
		jdbcTemplate.update("delete from log_pay where project_key = ?", new Object[] { key });
	}
	
	

	public List<OPcountInfo> count_operation_count(String project_key, String type, Timestamp start) {
		
		logger.info("count os" + "project_key : " + project_key + "type : " + type + "timestamp : " + start.toString());
		
		List<OPcountInfo> OPcount = null;
		List<OPcountInfo> result = new ArrayList<OPcountInfo>();
		if (type.equals("day")) {
			OPcount = jdbcTemplate.query(
					"select count(*),DATE(start) AS ForDate from log_time where project_key=? AND start<TIMESTAMP(DATE_ADD(?, INTERVAL 7 day))AND DATE(start)>=DATE(?) GROUP BY DATE(start) ORDER BY ForDate",
					new Object[] { project_key, start,start}, new RowMapper<OPcountInfo>() {
						public OPcountInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new OPcountInfo(resultSet.getTimestamp("ForDate"), resultSet.getInt("count(*)"));
						}
					});
		
			logger.info("test"+OPcount.toString());
			
			int index=0;
			for (int i = 0; i < 7; i++) {			
				if(OPcount.size()>0&&OPcount.get(index).getStart().equals(start)){
					result.add(i,OPcount.get(index));
					if(index<OPcount.size()-1)
					index++;	
				}
				else{
					Timestamp c=new Timestamp(start.getTime());
					OPcountInfo a = new OPcountInfo(c, 0);
					result.add(i, a);
				}
				start.setDate(start.getDate()+1);
			}
		}
		
		if (type.equals("month")) {
			OPcount = jdbcTemplate.query(
					"select count(*),DATE(start) AS ForDate from log_time where project_key=? AND start<TIMESTAMP(DATE_ADD(?, INTERVAL 7 month))AND DATE(start)>=DATE(?) GROUP BY MONTH(start) ORDER BY ForDate",
					new Object[] { project_key, start,start}, new RowMapper<OPcountInfo>() {
						public OPcountInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new OPcountInfo(resultSet.getTimestamp("ForDate"), resultSet.getInt("count(*)"));
						}
					});
			int index=0;
			
			for (int i = 0; i < 7; i++) {			
				if(OPcount.size()>0&&OPcount.get(index).getStart().getMonth()==start.getMonth()){
					result.add(i,OPcount.get(index));
					if(index<OPcount.size()-1)
						index++;					
				}
				else{
					Timestamp c=new Timestamp(start.getTime());
					OPcountInfo a = new OPcountInfo(c, 0);
					result.add(i, a);
				}
				start.setMonth(start.getMonth()+1);
			}
			
			
		}
		
		if (type.equals("year")) {
			OPcount = jdbcTemplate.query(
					"select count(*),DATE(start) AS ForDate from log_time where project_key=? AND start<TIMESTAMP(DATE_ADD(?, INTERVAL 7 year))AND DATE(start)>=DATE(?) GROUP BY YEAR(start) ORDER BY ForDate",
					new Object[] { project_key, start,start}, new RowMapper<OPcountInfo>() {
						public OPcountInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new OPcountInfo(resultSet.getTimestamp("ForDate"), resultSet.getInt("count(*)"));
						}
					});
			
			int index=0;
			
			for (int i = 0; i < 7; i++) {			
				if(OPcount.size()>0&&OPcount.get(index).getStart().getYear()==start.getYear()){
					result.add(i,OPcount.get(index));
					if(index<OPcount.size()-1)
						index++;					
				}
				else{
					Timestamp c=new Timestamp(start.getTime());
					OPcountInfo a = new OPcountInfo(c, 0);
					result.add(i, a);
				}
				start.setYear(start.getYear()+1);
			}
		
		}


		
		return result;
	}

	
	
	
	
public List<OPtimeInfo> count_operation_time(String project_key,Timestamp start) {
		
		logger.info("count os" + "project_key : " + project_key  + "timestamp : " + start.toString());
		
		List<OPtimeInfo> result = new ArrayList<OPtimeInfo>();
		 List<OPtimeInfo> OPcount = jdbcTemplate.query(
				 
					"select start AS ForDate,count(*) from log_time where project_key=? AND start<TIMESTAMP(DATE_ADD(?, INTERVAL 1 day)) AND DATE(start)>=DATE(?) GROUP BY hour(start) ORDER BY ForDate",
					new Object[] { project_key, start,start}, new RowMapper<OPtimeInfo>() {
						public OPtimeInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new OPtimeInfo(resultSet.getTimestamp("ForDate"),resultSet.getInt("count(*)"));
						}
					});
		
			int index=0;
			for (int i = 0; i < 24; i++) {			
				if(OPcount.size()>0&&OPcount.get(index).getStart().getHours()==start.getHours()){
					result.add(i,OPcount.get(index));
					if(index<OPcount.size()-1)
					index++;	
				}
				else{
					Timestamp c=new Timestamp(start.getTime());
					OPtimeInfo a = new OPtimeInfo(c,0);
					result.add(i, a);
				}
				start.setHours(start.getHours()+1);
			}
		
	

		
		return result;
	}
}