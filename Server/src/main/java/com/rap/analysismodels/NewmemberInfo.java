package com.rap.analysismodels;

import java.sql.Timestamp;

public class NewmemberInfo {
	
	private Timestamp start;	
	private int count;
	public Timestamp getStart() {
		return start;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public NewmemberInfo(Timestamp start, int count) {
		super();
		this.start = start;
		this.count = count;
	}
	
	
}