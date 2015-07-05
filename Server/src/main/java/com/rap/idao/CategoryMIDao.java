package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.CategoryMInfo;

public interface CategoryMIDao {
	void setDataSource(DataSource ds);
	void create(int Key, int categoryL_pk, String categoryM);
	
	List<CategoryMInfo> select(int key);
	
	void delete(int key, int categoryL_pk, String categoryM);
	void delete(int key);
}
