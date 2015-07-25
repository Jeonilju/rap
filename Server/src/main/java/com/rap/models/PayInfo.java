package com.rap.models;

import java.sql.Timestamp;

public class PayInfo {
	private int pk;
	private String key;
	private String username;
	private int type;
	private int price;
	private int item_pk;
	private Timestamp reg_date;
	
	public PayInfo(int pk, String key, String username, int type, int price, int item_pk, Timestamp reg_date){
		this.pk = pk;
		this.key = key;
		this.username = username;
		this.type = type;
		this.price = price;
		this.item_pk = item_pk;
		this.reg_date = reg_date;
	}
	
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getItem_pk() {
		return item_pk;
	}
	public void setItem_pk(int item_pk) {
		this.item_pk = item_pk;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
