package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.IAPInfo;

public interface IAPIDao {
	void setDataSource(DataSource ds);
	void create(String Key, String iap, int price_real, int price_main, int price_sub
			, int type, String image, String discription
			,String categoryL, String categoryM, String categoryS, String google_id);
	
	List<IAPInfo> select(String key);
	List<IAPInfo> select(String key, String categoryL);
	List<IAPInfo> select(String key, String categoryL, String categoryM);
	List<IAPInfo> select(String key, String categoryL, String categoryM, String categoryS);

	void delete(String key);
	void delete(String key, String categoryL);
	void delete(String key, String categoryL, String categoryM);
	void delete(String key, String categoryL, String categoryM, String categoryS);
}
