package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.PayInfo;

public interface PayIDao {
	void setDataSource(DataSource ds);
	void create(String Key, String name, int type, int price, int item_pk);
	
	List<PayInfo> select(String key);
	List<PayInfo> select(String key, String name);
	List<PayInfo> select(String key, int type);
	
	void deleteAll();
	void delete(String key);
}
