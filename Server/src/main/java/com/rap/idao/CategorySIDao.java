package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.CategorySInfo;

public interface CategorySIDao {
	void setDataSource(DataSource ds);
	void create(int Key, int categoryM_pk, String categoryS);
	
	List<CategorySInfo> select(int key);
	
	void delete(int key, int categoryM_pk, String categoryS);
	void delete(int key);
}
