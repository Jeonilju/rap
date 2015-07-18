package com.rap.iap;

import java.sql.Timestamp;

public class RAPCategoryM {
	private int pk;
	private String categoryM;
	private Timestamp regDate;
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public String getCategoryM() {
		return categoryM;
	}
	public void setCategoryM(String categoryM) {
		this.categoryM = categoryM;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	
}
