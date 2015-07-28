package com.rap.models;

import java.sql.Timestamp;

public class TimeInfo {
	private int pk;
	private String project_key;
	private String name;
	private Timestamp start;
	private Timestamp end;
	
	public TimeInfo(int pk, String project_key, String name, Timestamp start, Timestamp end){
		this.pk = pk;
		this.project_key = project_key;
		this.name = name;
		this.start = start;
		this.end = end;
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
	public Timestamp getStart() {
		return start;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
}
