package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.ActivityInfo;

public interface ActivityIDao {
	void setDataSource(DataSource ds);
	void create(int key, String user_id, String activity_name);
	void create(int key, String user_id, String activity_name, String activityb_name);
	List<ActivityInfo> select(int key);
	void deleteAll();
	void delete(int key);
}
