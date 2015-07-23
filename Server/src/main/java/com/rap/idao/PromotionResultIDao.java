package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.PromotionResultInfo;

public interface PromotionResultIDao {
	void setDataSource(DataSource ds);
	
	void create(String project_pk, int promotion_pk, String username);
	List<PromotionResultInfo> selectAll();
	List<PromotionResultInfo> select(String project_pk);
	List<PromotionResultInfo> select(String project_pk, int promotion_pk);
	
	void delete(String pk);
}
