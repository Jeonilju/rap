package com.rap.iap;

import java.sql.Timestamp;

public class RAPCategoryL {
	private int pk;
	private String categoryL;
	private Timestamp regDate;
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public String getCategoryL() {
		return categoryL;
	}
	public void setCategoryL(String categoryL) {
		this.categoryL = categoryL;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	
}
