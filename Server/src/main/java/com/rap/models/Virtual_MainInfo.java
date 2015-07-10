package com.rap.models;

import java.sql.Timestamp;

public class Virtual_MainInfo {
	private int pk;
	private int key;
	private String name;
	private int price;
	private String image;
	private String discription;
	private Timestamp reg_date;
	
	public Virtual_MainInfo( int pk, int key, String name, int price, String image, String discription, Timestamp reg_date)
	{
		this.pk = pk;
		this.key = key;
		this.name = name;
		this.price = price;
		this.image = image;
		this.discription = discription;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
