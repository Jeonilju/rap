package com.rap.models;

import java.sql.Timestamp;

public class CategoryLInfo {
	private int pk;
	private String key;
	private String categoryL;
	private Timestamp reg_date;
	
	public CategoryLInfo(int pk, String key, String CategoryL, Timestamp reg_date){
		this.pk = pk;
		this.key = key;
		this.categoryL = CategoryL;
		this.reg_date = reg_date;
	}
	
	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
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

	public String getCategoryL() {
		return categoryL;
	}

	public void setCategoryL(String categoryL) {
		this.categoryL = categoryL;
	}

}