package com.rap.models;

import java.sql.Timestamp;

public class CategoryMInfo {
	private int pk;
	private String key;
	private String categoryM;
	private Timestamp reg_date;
	private int categotyL_pk;
	
	public CategoryMInfo(int pk, String key, String categorym, Timestamp reg_date, int categotyL_pk){
		this.pk = pk;
		this.key = key;
		this.categoryM = categorym;
		this.reg_date = reg_date;
		this.categotyL_pk = categotyL_pk;
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

	public String getCategoryM() {
		return categoryM;
	}

	public void setCategoryM(String categoryM) {
		this.categoryM = categoryM;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public int getCategotyL_pk() {
		return categotyL_pk;
	}

	public void setCategotyL_pk(int categotyL_pk) {
		this.categotyL_pk = categotyL_pk;
	}
	
}
