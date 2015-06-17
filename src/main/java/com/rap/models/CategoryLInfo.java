package com.rap.models;

import java.sql.Timestamp;

public class CategoryLInfo {
	private int pk;
	private int key;
	private String CategoryL;
	private Timestamp reg_date;
	
	public CategoryLInfo(int pk, int key, String CategoryL, Timestamp reg_date){
		this.pk = pk;
		this.key = key;
		this.CategoryL = CategoryL;
		this.reg_date = reg_date;
	}
	
	// Getter
	public int getPk()	{	return pk;	}
	public String getCategoryL()	{	return CategoryL;	}
	public Timestamp getRegDate()	{	return reg_date;	}
	public int getKey()	{	return key;	}
	// Setter
	public void setPk(int pk)	{	this.pk = pk;	}
	public void setCategoryL(String CategoryL)	{	this.CategoryL = CategoryL;	}
	public void setRegDate(Timestamp reg_date)	{	this.reg_date = reg_date;	}
	public void setKey(int key)	{	this.key = key;	}
}