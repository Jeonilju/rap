package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.Virtual_MainInfo;

public interface Virtual_MainIDao {
	void setDataSource(DataSource ds);
	void create(String project_key, String name, int price, String image, String description);
	List<Virtual_MainInfo> select(String project_key);
	void deleteAll();
	void delete(String project_key);
}
