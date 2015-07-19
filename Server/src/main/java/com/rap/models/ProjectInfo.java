package com.rap.models;

import java.sql.Timestamp;

public class ProjectInfo {
	private String pk;
	private String project_name;
	private String summary;
	private String description;
	private int member_pk;
	private Timestamp reg_date;
	
	public ProjectInfo(String pk, String project_name, String summary, String description, int member_pk, Timestamp reg_date){
		this.pk = pk;
		this.project_name = project_name;
		this.summary = summary;
		this.description = description;
		this.member_pk = member_pk;
		this.reg_date = reg_date;
	}
	
	public String getPk() {
		return pk;
	}
	public void setPk(String pk) {
		this.pk = pk;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMember_pk() {
		return member_pk;
	}
	public void setMember_pk(int member_pk) {
		this.member_pk = member_pk;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
