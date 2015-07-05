package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.ProjectInfo;

public interface ProjectIDao {
	void setDataSource(DataSource ds);
	
	void create(int pk, String project_name, String summary, String discription, int member_pk);
	List<ProjectInfo> selectAll();
	void deleteAll();
	void delete(String pk);
}
