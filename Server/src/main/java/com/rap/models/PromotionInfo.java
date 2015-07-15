package com.rap.models;

import java.sql.Timestamp;

public class PromotionInfo {
	private int pk;
	private String project_key;
	private String name;
	private String summary;
	private int grade_time;
	private int grade_using;
	
	public PromotionInfo(int pk, String project_key, String name, String summary, int grade_time, int grade_using){
		this.pk = pk;
		this.project_key = project_key;
		this.name = name;
		this.summary = summary;
		this.grade_time = grade_time;
		this.grade_using = grade_using;
	}

	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getProject_key() {
		return project_key;
	}

	public void setProject_key(String project_key) {
		this.project_key = project_key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getGrade_time() {
		return grade_time;
	}

	public void setGrade_time(int grade_time) {
		this.grade_time = grade_time;
	}

	public int getGrade_using() {
		return grade_using;
	}

	public void setGrade_using(int grade_using) {
		this.grade_using = grade_using;
	}
	/*
	@Override
	public String toString() {
		return "PK: " + this.pk
				+ " name: " + this.name
				+ " summary: " + this.name + "\n";
	}*/
}
