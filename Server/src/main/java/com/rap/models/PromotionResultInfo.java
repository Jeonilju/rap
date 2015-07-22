package com.rap.models;

import java.sql.Timestamp;

public class PromotionResultInfo {
	private int pk;
	private String project_pk;
	private int promotion_pk;
	private String username;
	private Timestamp reg_date;
	
	public PromotionResultInfo(int pk, String project_pk, int promotion_pk, String username, Timestamp reg_date){
		this.pk = pk;
		this.project_pk = project_pk;
		this.promotion_pk = promotion_pk;
		this.username = username;
		this.reg_date = reg_date;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getProject_pk() {
		return project_pk;
	}

	public void setProject_pk(String project_pk) {
		this.project_pk = project_pk;
	}

	public int getPromotion_pk() {
		return promotion_pk;
	}

	public void setPromotion_pk(int promotion_pk) {
		this.promotion_pk = promotion_pk;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
