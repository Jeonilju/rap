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

import com.rap.idao.CategoryMIDao;
import com.rap.models.CategoryMInfo;

@Repository
public class CategoryMDao implements CategoryMIDao{
   private static final Logger logger = LoggerFactory.getLogger(CategoryMDao.class);
   
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplate;
   public void setDataSource(DataSource ds) {
      dataSource = ds;
      this.jdbcTemplate = new JdbcTemplate(dataSource);
      logger.info("Updated DataSource ---> " + ds);
      logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);      
   }

   public void create(String Key, int categoryL_pk, String categoryM) {
      jdbcTemplate.update("insert into categorym (project_key, categoryL_pk, categoryM) values (?, ?, ?)", new Object[] { Key, categoryL_pk, categoryM });
   }
   
   //int pk, int key, String categorym, Timestamp reg_date, int categotyL_pk
   public List<CategoryMInfo> select(String key) {
      return jdbcTemplate.query("select * from categorym where project_key = ?",
             new Object[] { key }, new RowMapper<CategoryMInfo>() {
             public CategoryMInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
             {
                return new CategoryMInfo(
                      resultSet.getInt("pk")
                      , resultSet.getString("project_key")
                      , resultSet.getString("categoryM")
                      , resultSet.getTimestamp("reg_date")
                      , resultSet.getInt("categoryL_pk"));
             }
          });
   }
   
   public List<CategoryMInfo> select(String key, int categoryL_pk){
      return jdbcTemplate.query("select * from categorym where categoryL_pk = ? and project_key = ?",
             new Object[] { categoryL_pk,key }, new RowMapper<CategoryMInfo>() {
             public CategoryMInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
             {
                return new CategoryMInfo(
                      resultSet.getInt("pk")
                      , resultSet.getString("project_key")
                      , resultSet.getString("categoryM")
                      , resultSet.getTimestamp("reg_date")
                      , resultSet.getInt("categoryL_pk"));
             }
          });
   }
   public List<CategoryMInfo> select(String key, int categoryL_pk, String categoryM){
	      return jdbcTemplate.query("select * from categorym where categoryL_pk = ? and project_key = ? and categoryM = ?",
	             new Object[] { categoryL_pk,key,categoryM }, new RowMapper<CategoryMInfo>() {
	             public CategoryMInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
	             {
	                return new CategoryMInfo(
	                      resultSet.getInt("pk")
	                      , resultSet.getString("project_key")
	                      , resultSet.getString("categoryM")
	                      , resultSet.getTimestamp("reg_date")
	                      , resultSet.getInt("categoryL_pk"));
	             }
	          });
	   }
   
   public List<CategoryMInfo> select(String key, String categoryL){
      return jdbcTemplate.query("select * from categorym where categoryL_pk = (select categoryl.pk from categoryl where project_key = ? and categoryl = ?)",
             new Object[] { key, categoryL }, new RowMapper<CategoryMInfo>() {
             public CategoryMInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
             {
                return new CategoryMInfo(
                      resultSet.getInt("pk")
                      , resultSet.getString("project_key")
                      , resultSet.getString("categoryM")
                      , resultSet.getTimestamp("reg_date")
                      , resultSet.getInt("categoryL_pk"));
             }
          });
   }
   
   public void delete(String key, int categoryL_pk, String categoryM) {
      jdbcTemplate.update("delete from categorym where project_key = ? AND categoryL_pk = ? AND categoryM = ?",
              new Object[] { key, categoryL_pk,  categoryM});      
   }
   public void delete(String key) {
      jdbcTemplate.update("delete from categorym where project_key = ?",
              new Object[] { key });      
   }
   
}
