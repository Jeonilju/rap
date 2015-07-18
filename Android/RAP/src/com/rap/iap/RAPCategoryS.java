package com.rap.iap;

import java.sql.Timestamp;

public class RAPCategoryS {
	private int pk;
	private String categoryS;
	private Timestamp regDate;
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public String getCategoryS() {
		return categoryS;
	}
	public void setCategoryS(String categoryS) {
		this.categoryS = categoryS;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	
}
