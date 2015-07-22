package com.rap.models;

import java.sql.Timestamp;

public class Virtual_SubInfo {
	private int pk;
	private String key;
	private String name;
	private int price;
	private String image;
	private String description;
	private Timestamp reg_date;
	
	public Virtual_SubInfo( int pk, String key, String name, int price, String image, String description, Timestamp reg_date)
	{
		this.pk = pk;
		this.key = key;
		this.name = name;
		this.price = price;
		this.image = image;
		this.description = description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
