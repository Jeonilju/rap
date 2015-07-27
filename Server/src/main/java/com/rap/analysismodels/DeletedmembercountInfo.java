package com.rap.analysismodels;

import java.sql.Timestamp;

public class DeletedmembercountInfo {
	
	private Timestamp reg_date;	
	private int count;
	public Timestamp getStart() {
		return reg_date;
	}
	public void setStart(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public DeletedmembercountInfo(Timestamp reg_date, int count) {
		super();
		this.reg_date = reg_date;
		this.count = count;
	}
	
	
}