package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.CategoryLInfo;

public interface CategoryLIDao {
	void setDataSource(DataSource ds);	 
	void create(String Key, String categoryL);
	
	List<CategoryLInfo> select(String key);
	
	void delete(String key, String categoryL);
	void delete(String key);
}
