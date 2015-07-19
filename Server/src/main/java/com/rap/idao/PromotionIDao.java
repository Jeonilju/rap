package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.PromotionInfo;

public interface PromotionIDao {
	void setDataSource(DataSource ds);
	void create(String project_key, String name, String summary, int grade_time, int grade_using);
	List<PromotionInfo> selectFromProject(String project_key);
	List<PromotionInfo> selectAll();
	void deleteAll();
	void delete(int pk);
}
