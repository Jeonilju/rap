package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.CategoryLInfo;

public interface CategoryLIDao {
	void setDataSource(DataSource ds);	 
	void create(int Key, String categoryL);
	
	List<CategoryLInfo> select(int key);
	
	void delete(int key, String categoryL);
	void delete(int key);
}
