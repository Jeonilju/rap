package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.CategoryLInfo;

public interface CategoryLIDao {
	void setDataSource(DataSource ds);	 
	void create(String categoryL);
	
	List<CategoryLInfo> select(int pk);
	List<CategoryLInfo> selectAll();
	
	void deleteAll();
	void delete(String categoryL);
	void delete(int pk);
}
