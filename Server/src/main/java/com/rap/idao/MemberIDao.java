package com.rap.idao;

import java.util.List;

import javax.sql.DataSource;

import com.rap.models.MemberInfo;

public interface MemberIDao {
	void setDataSource(DataSource ds);
	
	void create(String email, String password);
	List<MemberInfo> selectAll();
	void deleteAll();
	void delete(String email);
}
