package com.rap.analysismodels;

import java.sql.Timestamp;

public class IAPamountInfo {
	
	private Timestamp reg_date;
	private int amount;
	public IAPamountInfo(Timestamp reg_date, int amount) {
		super();
		this.reg_date = reg_date;
		this.amount = amount;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}