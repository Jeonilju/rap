package com.rap.idao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.rap.models.CategoryMInfo;

public interface CategoryMIDao {
	   public void setDataSource(DataSource ds);

	   public void create(String Key, int categoryL_pk, String categoryM);
	   
	   //int pk, int key, String categorym, Timestamp reg_date, int categotyL_pk
	   public List<CategoryMInfo> select(String key);
	   public List<CategoryMInfo> select(String key, int categoryL_pk);
	   public List<CategoryMInfo> select(String key, String categoryL);
	   
	   public void delete(String key, int categoryL_pk, String categoryM);
	   public void delete(String key);
	   
}
