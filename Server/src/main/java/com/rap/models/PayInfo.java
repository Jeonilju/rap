package com.rap.models;

import java.sql.Timestamp;

public class PayInfo {
	private int pk;
	private int key;
	private int user_pk;
	private int type;
	private int price;
	private int item_pk;
	private Timestamp reg_date;
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
	public int getUser_pk() {
		return user_pk;
	}
	public void setUser_pk(int user_pk) {
		this.user_pk = user_pk;
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
