package com.rap.idao;

import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import com.rap.models.TimeInfo;

public interface TimeIDao {
	void setDataSource(DataSource ds);
	void create(int Key, String name, Timestamp start, Timestamp end);
	
	List<TimeInfo> select(int key);
	List<TimeInfo> select(int key, Timestamp start, Timestamp end);
	
	void deleteAll();
	void delete(int pk);
}
