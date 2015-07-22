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

import com.rap.idao.CategorySIDao;
import com.rap.models.CategorySInfo;

@Repository
public class CategorySDao implements CategorySIDao{
   private static final Logger logger = LoggerFactory.getLogger(CategorySDao.class);
   
   private DataSource dataSource;
   private JdbcTemplate jdbcTemplate;
   public void setDataSource(DataSource ds) {
      dataSource = ds;
      this.jdbcTemplate = new JdbcTemplate(dataSource);
      logger.info("Updated DataSource ---> " + ds);
      logger.info("Updated jdbcTemplate ---> " + jdbcTemplate);      
   }
   public void create(String Key, int categoryM_pk, String categoryS) {
      jdbcTemplate.update("insert into categorys (project_key, categoryM_pk, categoryS) values (?, ?, ?)", new Object[] { Key, categoryM_pk, categoryS });
   }   
   public List<CategorySInfo> select(String key) {
      return jdbcTemplate.query("select * from categorys where project_key = ?",
             new Object[] { key }, new RowMapper<CategorySInfo>() {
             public CategorySInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
             {
                return new CategorySInfo(
                      resultSet.getInt("pk")
                      , resultSet.getString("project_key")
                      , resultSet.getString("categoryS")
                      , resultSet.getTimestamp("reg_date")
                      , resultSet.getInt("categoryM_pk"));
             }
          });
   }
   
   public List<CategorySInfo> select(String key, int categoryM_pk)
   {
      return jdbcTemplate.query("select * from categorys where categoryM_pk = ? and project_key = ?",
             new Object[] { categoryM_pk, key }, new RowMapper<CategorySInfo>() {
             public CategorySInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
             {
                return new CategorySInfo(
                      resultSet.getInt("pk")
                      , resultSet.getString("project_key")
                      , resultSet.getString("categoryS")
                      , resultSet.getTimestamp("reg_date")
                      , resultSet.getInt("categoryM_pk"));
             }
          });
   }
   
   public List<CategorySInfo> select(String key, int categoryM_pk, String categorys)
   {
      return jdbcTemplate.query("select * from categorys where categoryM_pk = ? and project_key = ? and categoryS = ?",
             new Object[] { categoryM_pk, key, categorys }, new RowMapper<CategorySInfo>() {
             public CategorySInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
             {
                return new CategorySInfo(
                      resultSet.getInt("pk")
                      , resultSet.getString("project_key")
                      , resultSet.getString("categoryS")
                      , resultSet.getTimestamp("reg_date")
                      , resultSet.getInt("categoryM_pk"));
             }
          });
   }
   
   public List<CategorySInfo> select(String key, String categoryL, String categoryM)
   {
      return jdbcTemplate.query("select * from categorys where "
            + "categoryM_pk = (select categorym.pk from categorym where project_key = ? and "
               + "categoryL_pk = (select categoryl.pk from categoryl where project_key = ? and categoryl = ?)"
               + " and categorym = ?"
            + ") and project_key = ?",
             new Object[] { key,key, categoryL, categoryM, key }, new RowMapper<CategorySInfo>() {
             public CategorySInfo mapRow(ResultSet resultSet, int rowNum) throws SQLException 
             {
                return new CategorySInfo(
                      resultSet.getInt("pk")
                      , resultSet.getString("project_key")
                      , resultSet.getString("categoryS")
                      , resultSet.getTimestamp("reg_date")
                      , resultSet.getInt("categoryM_pk"));
             }
          });
   }
   
   public void delete(String key, int categoryM_pk, String categoryS) {
      jdbcTemplate.update("delete from categorys where project_key = ? AND categoryM_pk = ? AND categoryS = ?",
              new Object[] { key, categoryM_pk,  categoryS});      
   }
   public void delete(String key) {
      jdbcTemplate.update("delete from categorys where project_key = ?",
              new Object[] { key });      
   }
}
