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

import com.rap.analysismodels.DeletedmembercountInfo;
import com.rap.analysismodels.DeletedmembercountInfo;
import com.rap.models.DeleteuserrInfo;

@Repository
public class DeleteuserDao {
	private static final Logger logger = LoggerFactory.getLogger(DeleteuserDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	
	public void create(int project_key, String name, Timestamp reg_date){
		jdbcTemplate.update("insert into log_time (project_key, name, reg_date) values (?, ?, ?)", new Object[] { project_key, name, reg_date});
	}
	
	public List<DeleteuserrInfo> select(int project_key){
		return jdbcTemplate.query("select * from log_time where project_key = ?",
		    	new Object[] { project_key }, new RowMapper<DeleteuserrInfo>() {
		    	public DeleteuserrInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new DeleteuserrInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getInt("project_key")
		    				, resultSet.getString("name")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}
	
	
	public void deleteAll(){
		jdbcTemplate.update("delete from log_time");
	}
	
	public void delete(int project_key){
		jdbcTemplate.update("delete from log_time where project_key = ?", new Object[] { project_key });
	}
	
	
public List<DeletedmembercountInfo> count_deleted_member(String project_key, String type, Timestamp start) {
		
		logger.info("count new_member");
		// SELECT DISTINCT email FROM table;
		
		List<DeletedmembercountInfo> Deletedmembercount = null;
		List<DeletedmembercountInfo> result = new ArrayList<DeletedmembercountInfo>();
		if (type.equals("day")) {
			Deletedmembercount = jdbcTemplate.query(
					"select count(*),DATE(reg_date) AS ForDate from delete_user where project_key=? AND reg_date<TIMESTAMP(DATE_ADD(?, INTERVAL 7 day))AND DATE(reg_date)>=DATE(?) GROUP BY DATE(reg_date) ORDER BY ForDate",
					new Object[] { project_key, start,start}, new RowMapper<DeletedmembercountInfo>() {
						public DeletedmembercountInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new DeletedmembercountInfo(resultSet.getTimestamp("ForDate"), resultSet.getInt("count(*)"));
						}
					});
		
			int index=0;
			for (int i = 0; i < 7; i++) {			
				if(Deletedmembercount.size()>0&&Deletedmembercount.get(index).getStart().equals(start)){
					result.add(i,Deletedmembercount.get(index));
					if(index<Deletedmembercount.size()-1)
					index++;	
				}
				else{
					Timestamp c=new Timestamp(start.getTime());
					DeletedmembercountInfo a = new DeletedmembercountInfo(c, 0);
					result.add(i, a);
				}
				start.setDate(start.getDate()+1);
			}
		}
		
		if (type.equals("month")) {
			Deletedmembercount = jdbcTemplate.query(
					"select count(*),DATE(reg_date) AS ForDate from delete_user where project_key=? AND reg_date<TIMESTAMP(DATE_ADD(?, INTERVAL 7 month))AND DATE(reg_date)>=DATE(?) GROUP BY MONTH(reg_date) ORDER BY ForDate",
					new Object[] { project_key, start,start}, new RowMapper<DeletedmembercountInfo>() {
						public DeletedmembercountInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new DeletedmembercountInfo(resultSet.getTimestamp("ForDate"), resultSet.getInt("count(*)"));
						}
					});
			for (int i = 0; i < Deletedmembercount.size(); i++) {				
				logger.info("q"+"month : "+Deletedmembercount.get(i).getCount()+ " count : "+Deletedmembercount.get(i).getCount());
			}
			
			int index=0;
			
			for (int i = 0; i < 7; i++) {			
				if(Deletedmembercount.size()>0&&Deletedmembercount.get(index).getStart().getMonth()==start.getMonth()){
					result.add(i,Deletedmembercount.get(index));
					if(index<Deletedmembercount.size()-1)
						index++;					
				}
				else{
					Timestamp c=new Timestamp(start.getTime());
					DeletedmembercountInfo a = new DeletedmembercountInfo(c, 0);
					result.add(i, a);
				}
				start.setMonth(start.getMonth()+1);
				//logger.info(""+result.get(i).getCount());
			}
			
			
		}
		
		if (type.equals("year")) {
			Deletedmembercount = jdbcTemplate.query(
					"select count(*),DATE(reg_date) AS ForDate from delete_user where project_key=? AND reg_date<TIMESTAMP(DATE_ADD(?, INTERVAL 7 year))AND DATE(reg_date)>=DATE(?) GROUP BY YEAR(reg_date) ORDER BY ForDate",
					new Object[] { project_key, start,start}, new RowMapper<DeletedmembercountInfo>() {
						public DeletedmembercountInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new DeletedmembercountInfo(resultSet.getTimestamp("ForDate"), resultSet.getInt("count(*)"));
						}
					});
			for (int i = 0; i < Deletedmembercount.size(); i++) {				
				logger.info("q"+Deletedmembercount.get(i).getStart().getYear());
			}
			
			int index=0;
			
			for (int i = 0; i < 7; i++) {			
				if(Deletedmembercount.size()>0&&Deletedmembercount.get(index).getStart().getYear()==start.getYear()){
					result.add(i,Deletedmembercount.get(index));
					if(index<Deletedmembercount.size()-1)
						index++;					
				}
				else{
					Timestamp c=new Timestamp(start.getTime());
					DeletedmembercountInfo a = new DeletedmembercountInfo(c, 0);
					result.add(i, a);
				}
				start.setYear(start.getYear()+1);
				
			}
		}


		
		return result;
	}
}
