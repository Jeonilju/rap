package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.CategoryMInfo;

public interface CategoryMIDao {
	void setDataSource(DataSource ds);
	void create(String Key, int categoryL_pk, String categoryM);
	
	List<CategoryMInfo> select(String key);
	List<CategoryMInfo> select(String key, String categoryM);
	
	void delete(String key, int categoryL_pk, String categoryM);
	void delete(String key);
}
