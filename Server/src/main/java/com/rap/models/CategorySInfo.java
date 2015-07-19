package com.rap.models;

import java.sql.Timestamp;

public class CategorySInfo {
	private int pk;
	private String key;
	private String categoryS;
	private Timestamp reg_date;
	private int categotyM_pk;
	
	public CategorySInfo(int pk, String key, String categorys, Timestamp reg_date, int categotym_pk){
		this.pk = pk;
		this.key = key;
		this.categoryS = categorys;
		this.reg_date = reg_date;
		this.categotyM_pk = categotym_pk;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCategoryS() {
		return categoryS;
	}

	public void setCategoryS(String categoryS) {
		this.categoryS = categoryS;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public int getCategotyM_pk() {
		return categotyM_pk;
	}

	public void setCategotyM_pk(int categotyM_pk) {
		this.categotyM_pk = categotyM_pk;
	}
	
}
