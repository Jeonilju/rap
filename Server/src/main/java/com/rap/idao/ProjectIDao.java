package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.ProjectInfo;

public interface ProjectIDao {
	void setDataSource(DataSource ds);
	
	void create(String pk, String project_name, String summary, String description, int member_pk);
	List<ProjectInfo> select(String pk);
	public List<ProjectInfo> selectFromMemberPK(int member_pk);
	void deleteAll();
	void delete(String pk);
}
