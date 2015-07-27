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

import com.rap.analysismodels.IAPamountInfo;
import com.rap.analysismodels.IAPamountInfo;
import com.rap.idao.PayIDao;
import com.rap.models.PayInfo;

@Repository
public class PayDao implements PayIDao{
	private static final Logger logger = LoggerFactory.getLogger(PayDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	public void create(String project_key, String name, int type, int price, int item_pk) {
		jdbcTemplate.update("insert into log_pay (project_key, username, type, price, item_pk) values (?, ?, ?, ?, ?)"
				, new Object[] { project_key, name, type, price, item_pk});
	}
	public List<PayInfo> select(String project_key) {
		return jdbcTemplate.query("select * from log_pay where project_key = ?",
		    	new Object[] { project_key }, new RowMapper<PayInfo>() {
		    	public PayInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PayInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("username")
		    				, resultSet.getInt("type")
		    				, resultSet.getInt("price")
		    				, resultSet.getInt("item_pk")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}
	public List<PayInfo> select(String project_key, String name) {
		return jdbcTemplate.query("select * from log_pay where project_key = ? AND username = ?",
		    	new Object[] { project_key, name }, new RowMapper<PayInfo>() {
		    	public PayInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PayInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("username")
		    				, resultSet.getInt("type")
		    				, resultSet.getInt("price")
		    				, resultSet.getInt("item_pk")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}
	public List<PayInfo> select(String project_key, int type) {
		return jdbcTemplate.query("select * from log_pay where project_key = ? AND type = ?",
		    	new Object[] { project_key, type }, new RowMapper<PayInfo>() {
		    	public PayInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PayInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("username")
		    				, resultSet.getInt("type")
		    				, resultSet.getInt("price")
		    				, resultSet.getInt("item_pk")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}
	public void deleteAll() {
		jdbcTemplate.update("delete from log_pay");
	}
	public void delete(String key) {
		jdbcTemplate.update("delete from log_pay where project_key = ?", new Object[] { key });
	}

	
	
	
	
	
	
	
	
	
public List<IAPamountInfo> count_IAP_amount(String project_key, String type, Timestamp start) {
		
		logger.info("count new_member");
		// SELECT DISTINCT email FROM table;
		
		List<IAPamountInfo> OPcount = null;
		List<IAPamountInfo> result = new ArrayList<IAPamountInfo>();
		if (type.equals("day")) {
			OPcount = jdbcTemplate.query(
					
					"SELECT DATE(reg_date) as ForDate, sum(price) as amount FROM rap.log_pay where project_key=?"
					+ "AND reg_date<TIMESTAMP(DATE_ADD(?, INTERVAL 7 day))AND DATE(reg_date)>=DATE(?) GROUP BY DATE(reg_date) ORDER BY ForDate",
					new Object[] { project_key, start,start}, new RowMapper<IAPamountInfo>() {
						public IAPamountInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new IAPamountInfo(resultSet.getTimestamp("ForDate"), resultSet.getInt("amount"));
						}
					});
		
			int index=0;
			for (int i = 0; i < 7; i++) {			
				if(OPcount.size()>0&&OPcount.get(index).getReg_date().equals(start)){
					result.add(i,OPcount.get(index));
					if(index<OPcount.size()-1)
					index++;	
				}
				else{
					Timestamp c=new Timestamp(start.getTime());
					IAPamountInfo a = new IAPamountInfo(c, 0);
					result.add(i, a);
				}
				start.setDate(start.getDate()+1);
			}
		}
		
		if (type.equals("month")) {
			OPcount = jdbcTemplate.query(
					"SELECT DATE(reg_date) as ForDate, sum(price) as amount FROM rap.log_pay where project_key=?"
					+ "AND reg_date<TIMESTAMP(DATE_ADD(?, INTERVAL 7 month))AND DATE(reg_date)>=DATE(?) GROUP BY MONTH(reg_date) ORDER BY ForDate",
					new Object[] { project_key, start,start}, new RowMapper<IAPamountInfo>() {
						public IAPamountInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new IAPamountInfo(resultSet.getTimestamp("ForDate"), resultSet.getInt("amount"));
						}
					});
			for (int i = 0; i < OPcount.size(); i++) {				
				logger.info("q"+"month : "+OPcount.get(i).getAmount()+ " count : "+OPcount.get(i).getAmount());
			}
			
			int index=0;
			
			for (int i = 0; i < 7; i++) {			
				if(OPcount.size()>0&&OPcount.get(index).getReg_date().getMonth()==start.getMonth()){
					result.add(i,OPcount.get(index));
					if(index<OPcount.size()-1)
						index++;					
				}
				else{
					Timestamp c=new Timestamp(start.getTime());
					IAPamountInfo a = new IAPamountInfo(c, 0);
					result.add(i, a);
				}
				start.setMonth(start.getMonth()+1);
				//logger.info(""+result.get(i).getAmount());
			}
			
			
		}
		
		if (type.equals("year")) {
			OPcount = jdbcTemplate.query(
					"SELECT DATE(reg_date) as ForDate, sum(price) as amount FROM rap.log_pay where project_key=?"
					+ "AND reg_date<TIMESTAMP(DATE_ADD(?, INTERVAL 7 year))AND DATE(reg_date)>=DATE(?) GROUP BY YEAR(reg_date) ORDER BY ForDate",
					new Object[] { project_key, start,start}, new RowMapper<IAPamountInfo>() {
						public IAPamountInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new IAPamountInfo(resultSet.getTimestamp("ForDate"), resultSet.getInt("amount"));
						}
					});
			for (int i = 0; i < OPcount.size(); i++) {				
				logger.info("q"+OPcount.get(i).getReg_date().getYear());
			}
			
			int index=0;
			
			for (int i = 0; i < 7; i++) {			
				if(OPcount.size()>0&&OPcount.get(index).getReg_date().getYear()==start.getYear()){
					result.add(i,OPcount.get(index));
					if(index<OPcount.size()-1)
						index++;					
				}
				else{
					Timestamp c=new Timestamp(start.getTime());
					IAPamountInfo a = new IAPamountInfo(c, 0);
					result.add(i, a);
				}
				start.setYear(start.getYear()+1);
				//logger.info("year"+result.get(i).getAmount());
			}
		}


		
		return result;
	}
}
