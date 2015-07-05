package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.UserInfo;

public interface UserIDao {
	void setDataSource(DataSource ds);
	
	void create(int project_key, String name);
	void appCountInc(int project_key, String name);
	void setGCM(int project_key, String name, String gcm_id);
	void setGradeTime(int project_key, String name, int grage_time);
	void setGradeMoney(int project_key, String name, int grage_money);
	void setPosition(int project_key, String name, double position_let, double position_lon);
	void setOsVersion(int project_key, String name, String os_version);
	void setDevice(int project_key, String name, String device_vertion);
	void setAge(int project_key, String name, int age);
	void setSex(int project_key, String name, int sex);
	
	List<UserInfo> select(int project_key);
	UserInfo selectUser(int project_key, String name);
	void deleteAll();
	void delete(int project_key);
}
