package com.rap.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.rap.idao.CategoryLIDao;
import com.rap.models.CategoryLInfo;

@Repository
public class CategoryLDao implements CategoryLIDao{

	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(String categoryL) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CategoryLInfo> select(int pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryLInfo> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String categoryL) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int pk) {
		// TODO Auto-generated method stub
		
	}

}
