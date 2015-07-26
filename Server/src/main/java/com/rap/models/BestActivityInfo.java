package com.rap.models;

import java.sql.Timestamp;

public class BestActivityInfo {
	
	private String activity_name;
	private int count;
	public String getActivity_name() {
		return activity_name;
	}
	public void setActivity_name(String activity_name) {
		this.activity_name = activity_name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public BestActivityInfo(String activity_name, int count) {
		super();
		this.activity_name = activity_name;
		this.count = count;
	}
	
}
