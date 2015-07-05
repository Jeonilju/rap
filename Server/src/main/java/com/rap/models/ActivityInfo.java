package com.rap.models;

import java.sql.Timestamp;

public class ActivityInfo {
	private int pk;
	private int key;
	private String user_id;
	private String activity_name;
	private String activityb_name;
	private Timestamp reg_date;
	
	public ActivityInfo(int pk, int key, String user_id, String activity_name, String activityb_name, Timestamp reg_date){
		this.pk = pk;
		this.key = key;
		this.user_id = user_id;
		this.activity_name = activity_name;
		this.activityb_name = activityb_name;
		this.reg_date = reg_date;
	}
	
	public String getActivityb_name() {
		return activityb_name;
	}

	public void setActivityb_name(String activityb_name) {
		this.activityb_name = activityb_name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getActivity_name() {
		return activity_name;
	}

	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
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

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	
	
}
