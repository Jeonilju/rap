package com.rap.idao;

import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import com.rap.models.TimeInfo;

public interface TimeIDao {
	void setDataSource(DataSource ds);
	void create(String Key, String name, Timestamp start, Timestamp end);
	
	List<TimeInfo> select(String key);
	List<TimeInfo> select(String key, Timestamp start, Timestamp end, int sex, int age, int grade_time, int grade_using);
	
	void deleteAll();
	void delete(String pk);
}
