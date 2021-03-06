package com.rap.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.rap.analysismodels.SalesRankingInfo;
import com.rap.idao.IAPIDao;
import com.rap.models.IAPInfo;

@Repository
public class IAPDao implements IAPIDao{
	private static final Logger logger = LoggerFactory.getLogger(IAPDao.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource ds) {
		dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		logger.info("Updated DataSource ---> " + ds);
		logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);		
	}
	public void create(String project_key, String iap, int price_real, int price_main,
			int price_sub, int type, String image, String description,
			String categoryL, String categoryM, String categoryS, String google_id) {
		jdbcTemplate.update("insert into iap (project_key, iap, price_real, price_main, price_sub, using_type, image, description, categoryl, categorym, categorys, google_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
				, new Object[] { project_key, iap,  price_real, price_main, price_sub, type, image, description, categoryL, categoryM, categoryS, google_id});
	}

	public void update(String iap, int price_real, int price_main,
			int price_sub, int type, String description, String google_id, int pk, String project_key) {
		jdbcTemplate.update("update iap set iap=?,price_real=?,price_main=?,price_sub=?,using_type=?,description=?,google_id=? where pk=? and project_key=?"
				, new Object[] { iap, price_real, price_main, price_sub, type, description, google_id, pk, project_key});
	}
	
	public List<IAPInfo> select(String key) {
		return jdbcTemplate.query("select * from iap where project_key = ? order by reg_date desc",
		    	new Object[] { key }, new RowMapper<IAPInfo>() {
		    	public IAPInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new IAPInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("iap")
		    				, resultSet.getInt("price_real")
		    				, resultSet.getInt("price_main")
		    				, resultSet.getInt("price_sub")
		    				, resultSet.getInt("using_type")
		    				, resultSet.getString("google_id")
		    				, resultSet.getString("image")
		    				, resultSet.getString("description")
		    				, resultSet.getTimestamp("reg_date")
		    				, resultSet.getString("categoryl")
		    				, resultSet.getString("categorym")
		    				, resultSet.getString("categorys"));
		    	}
		    });
	}
	public List<IAPInfo> select(String key, String categoryL) {
		return jdbcTemplate.query("select * from iap where project_key = ? AND categoryl = ? order by reg_date desc",
		    	new Object[] { key, categoryL }, new RowMapper<IAPInfo>() {
		    	public IAPInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new IAPInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("iap")
		    				, resultSet.getInt("price_real")
		    				, resultSet.getInt("price_main")
		    				, resultSet.getInt("price_sub")
		    				, resultSet.getInt("using_type")
		    				, resultSet.getString("google_id")
		    				, resultSet.getString("image")
		    				, resultSet.getString("description")
		    				, resultSet.getTimestamp("reg_date")
		    				, resultSet.getString("categoryl")
		    				, resultSet.getString("categorym")
		    				, resultSet.getString("categorys"));
		    	}
		    });
	}
	public List<IAPInfo> select(String key, String categoryL, String categoryM) {
		return jdbcTemplate.query("select * from iap where project_key = ? AND categoryl = ? AND categorym = ? order by reg_date desc",
		    	new Object[] { key, categoryL, categoryM }, new RowMapper<IAPInfo>() {
		    	public IAPInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new IAPInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("iap")
		    				, resultSet.getInt("price_real")
		    				, resultSet.getInt("price_main")
		    				, resultSet.getInt("price_sub")
		    				, resultSet.getInt("using_type")
		    				, resultSet.getString("google_id")
		    				, resultSet.getString("image")
		    				, resultSet.getString("description")
		    				, resultSet.getTimestamp("reg_date")
		    				, resultSet.getString("categoryl")
		    				, resultSet.getString("categorym")
		    				, resultSet.getString("categorys"));
		    	}
		    });
	}
	public List<IAPInfo> select(String key, String categoryL, String categoryM,
			String categoryS) {
		return jdbcTemplate.query("select * from iap where project_key = ? AND categoryl = ? AND categorym = ? AND categorys = ? order by reg_date desc",
		    	new Object[] { key, categoryL, categoryM, categoryS }, new RowMapper<IAPInfo>() {
		    	public IAPInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new IAPInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("iap")
		    				, resultSet.getInt("price_real")
		    				, resultSet.getInt("price_main")
		    				, resultSet.getInt("price_sub")
		    				, resultSet.getInt("using_type")
		    				, resultSet.getString("google_id")
		    				, resultSet.getString("image")
		    				, resultSet.getString("description")
		    				, resultSet.getTimestamp("reg_date")
		    				, resultSet.getString("categoryl")
		    				, resultSet.getString("categorym")
		    				, resultSet.getString("categorys"));
		    	}
		    });
	}

	public IAPInfo select(String key, String categoryL, String categoryM,
			String categoryS, String itemname) {
		List<IAPInfo> result = jdbcTemplate.query("select * from iap where project_key = ? AND categoryl = ? AND categorym = ? AND categorys = ? AND iap = ? order by reg_date desc",
		    	new Object[] { key, categoryL, categoryM, categoryS, itemname }, new RowMapper<IAPInfo>() {
		    	public IAPInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new IAPInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("iap")
		    				, resultSet.getInt("price_real")
		    				, resultSet.getInt("price_main")
		    				, resultSet.getInt("price_sub")
		    				, resultSet.getInt("using_type")
		    				, resultSet.getString("google_id")
		    				, resultSet.getString("image")
		    				, resultSet.getString("description")
		    				, resultSet.getTimestamp("reg_date")
		    				, resultSet.getString("categoryl")
		    				, resultSet.getString("categorym")
		    				, resultSet.getString("categorys"));
		    	}
		    });
		
		if(result.size() == 1)
			return result.get(0);
		else if(result.size() > 1)
		{
			logger.info("아이템 중복 에러");
			return result.get(0);
		}
		else
		{
			logger.info("해당 아이템 존재 X");
			return null;
		}
	}
	
	public IAPInfo selectItem(String key, int item_pk){
		List<IAPInfo> result = jdbcTemplate.query("select * from iap where project_key = ? AND pk = ? order by reg_date desc",
		    	new Object[] { key, item_pk }, new RowMapper<IAPInfo>() {
		    	public IAPInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new IAPInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("iap")
		    				, resultSet.getInt("price_real")
		    				, resultSet.getInt("price_main")
		    				, resultSet.getInt("price_sub")
		    				, resultSet.getInt("using_type")
		    				, resultSet.getString("google_id")
		    				, resultSet.getString("image")
		    				, resultSet.getString("description")
		    				, resultSet.getTimestamp("reg_date")
		    				, resultSet.getString("categoryl")
		    				, resultSet.getString("categorym")
		    				, resultSet.getString("categorys"));
		    	}
		    });
		
		if(result.size() == 1){
			return result.get(0);
		}
		else if(result.size() > 1){
			logger.info("버그: 갯수가 1개 이상");
			return result.get(0);
		}
		else {
			logger.info("버그: 갯수가 1개도 없음");
			return null;
		}
	}
	
	public void delete(String key) {
		jdbcTemplate.update("delete from iap where project_key = ?",
		        new Object[] { key });	
	}
	public void delete(String key, String categoryL) {
		jdbcTemplate.update("delete from iap where project_key = ? AND categoryl = ?",
		        new Object[] { key, categoryL });	
	}
	public void delete(String key, String categoryL, String categoryM) {
		jdbcTemplate.update("delete from iap where project_key = ? AND categoryl = ? AND categorym = ?",
		        new Object[] { key, categoryL , categoryM});	
	}
	public void delete(String key, String categoryL, String categoryM,
			String categoryS) {
		jdbcTemplate.update("delete from iap where project_key = ? AND categoryl = ? AND categorym = ? AND categorys = ?",
		        new Object[] { key, categoryL , categoryM, categoryS});	
	}
	
	public List<IAPInfo> getRankingCount(String project_key, Timestamp start_date, Timestamp end_date, int sex, int age, int grade_time, int grade_using){
		
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
		
		
		return jdbcTemplate.query("select *, count(iap.pk) from iap, log_pay, user "
				+ "where iap.project_key = ? "
				+ "AND log_pay.project_key = ? "
				+ "AND user.project_key = ?"
				+ "AND iap.pk = log_pay.item_pk "
				+ "AND log_pay.username = user.name "
				+ "AND log_pay.reg_date >= ? "
				+ "AND log_pay.reg_date <= ? "
				+ query
				+ " group by iap.pk"
				+ " order by count(iap.pk) DESC",
		    	new Object[] { project_key, project_key, project_key, start_date, end_date }, new RowMapper<IAPInfo>() {
		    	public IAPInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new IAPInfo(
		    				resultSet.getInt("pk")
		    				, resultSet.getString("project_key")
		    				, resultSet.getString("iap")
		    				, resultSet.getInt("price_real")
		    				, resultSet.getInt("price_main")
		    				, resultSet.getInt("price_sub")
		    				, resultSet.getInt("using_type")
		    				, resultSet.getString("google_id")
		    				, resultSet.getString("image")
		    				, resultSet.getString("description")
		    				, resultSet.getTimestamp("reg_date")
		    				, resultSet.getString("categoryl")
		    				, resultSet.getString("categorym")
		    				, resultSet.getString("categorys")
		    				, resultSet.getInt("count(iap.pk)"));
		    	}
		    });
		
	}

	public void delete(int pk, String key) {
		jdbcTemplate.update("delete from iap where pk = ? and project_key = ?",
		        new Object[] { pk, key });	
	}
	
	public List<SalesRankingInfo> countsales_ranking(String project_key) {
		return jdbcTemplate.query("select iap,count(*) AS count from iap inner join log_pay on iap.pk=log_pay.item_pk where iap.project_key=?  GROUP BY iap ORDER BY count desc",
		    	new Object[] { project_key}, new RowMapper<SalesRankingInfo>() {
		    	public SalesRankingInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
		    	{
		    		return new SalesRankingInfo(
		    				resultSet.getString("iap"),
		    				resultSet.getInt("count"));   			
		    	}
		    });
	}
	
	
	
	
	
	

}
