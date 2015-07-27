package com.rap.models;

import java.sql.Timestamp;

public class DeleteuserrInfo {
	private int pk;
	private int project_key;
	private String name;
	private Timestamp reg_date;
	public DeleteuserrInfo(int pk, int project_key, String name, Timestamp reg_date) {
		super();
		this.pk = pk;
		this.project_key = project_key;
		this.name = name;
		this.reg_date = reg_date;
	}
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public int getProject_key() {
		return project_key;
	}
	public void setProject_key(int project_key) {
		this.project_key = project_key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	

	
}