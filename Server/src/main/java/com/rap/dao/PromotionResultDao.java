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

import com.rap.analysismodels.NewmemberInfo;
import com.rap.analysismodels.Promotion_resultInfo;
import com.rap.idao.PromotionResultIDao;
import com.rap.models.PromotionResultInfo;

public class PromotionResultDao implements PromotionResultIDao{

	private static final Logger logger = LoggerFactory.getLogger(PromotionResultDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}

	public void create(String project_pk, int promotion_pk, String username) {
		jdbcTemplate.update("insert into promotion_result (project_key, promotion_pk, username) values (?, ?, ?)"
				, new Object[] { project_pk, promotion_pk, username });
	}

	public List<PromotionResultInfo> selectAll() {
		return jdbcTemplate.query("select * from promotion_result"
				, new RowMapper<PromotionResultInfo>() {
		    	public PromotionResultInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PromotionResultInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getInt("promotion_pk")
		    				, resultSet.getString("username")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}

	public List<PromotionResultInfo> select(String project_pk) {
		return jdbcTemplate.query("select * from promotion_result where project_key = ?" ,
		    	new Object[] { project_pk }
				, new RowMapper<PromotionResultInfo>() {
		    	public PromotionResultInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PromotionResultInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getInt("promotion_pk")
		    				, resultSet.getString("username")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}

	public List<PromotionResultInfo> select(String project_pk, int promotion_pk) {
		return jdbcTemplate.query("select * from promotion_result where project_key = ? and promotion_pk = ?" ,
		    	new Object[] { project_pk, promotion_pk }
				, new RowMapper<PromotionResultInfo>() {
		    	public PromotionResultInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new PromotionResultInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getInt("promotion_pk")
		    				, resultSet.getString("username")
		    				, resultSet.getTimestamp("reg_date"));
		    	}
		    });
	}

	public void delete(String pk) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("delete from promotion_result where project_key = ?",
		        new Object[] { pk });
	}
	
	
	
	
	
	
public List<Promotion_resultInfo> count_promotion_result(String project_key, Timestamp start,String promotion) {
		
		logger.info("count promotion_result");
		// SELECT DISTINCT email FROM table;
		
		List<Promotion_resultInfo> OPcount = null;
		List<Promotion_resultInfo> result = new ArrayList<Promotion_resultInfo>();

		
		//쿼리문
			OPcount = jdbcTemplate.query(
					"select name,DATE(reg_date) AS reg_date,count(*)  from promotion" +" inner join promotion_result on promotion.pk=promotion_result.promotion_pk "
						+"where promotion.project_key=?"
						+ "AND promotion.name=? AND reg_date<TIMESTAMP(DATE_ADD(?, INTERVAL 7 day))AND DATE(reg_date)>=DATE(?) GROUP BY DATE(reg_date) ORDER BY reg_date",
					new Object[] { project_key, promotion,start,start}, new RowMapper<Promotion_resultInfo>() {
						public Promotion_resultInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
							return new Promotion_resultInfo(resultSet.getTimestamp("reg_date"), resultSet.getInt("count(*)"));
						}
					});
		
			int index=0;
			
			
			
			//로그 찍기
			for (int i = 0; i < OPcount.size(); i++) {
				logger.info("day : " + OPcount.get(i).getStart() +" count : " +OPcount.get(i).getCount());			
			}
			
			//result 배열에 넣기
			for (int i = 0; i < 7; i++) {			
				if(OPcount.size()>0&&OPcount.get(index).getStart().equals(start)){
					result.add(i,OPcount.get(index));
					if(index<OPcount.size()-1)
					index++;	
				}
				else{
					Timestamp c=new Timestamp(start.getTime());
					Promotion_resultInfo a = new Promotion_resultInfo(c, 0);
					result.add(i, a);
				}
				start.setDate(start.getDate()+1);
			}
	
		return result;
	}
	

}
