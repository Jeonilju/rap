package com.rap.models;

import java.sql.Timestamp;

public class CategorySInfo {
	private int pk;
	private int key;
	private String categorys;
	private Timestamp reg_date;
	private int categotym_pk;
	
	public CategorySInfo(int pk, int key, String categorys, Timestamp reg_date, int categotym_pk){
		this.pk = pk;
		this.key = key;
		this.categorys = categorys;
		this.reg_date = reg_date;
		this.categotym_pk = categotym_pk;
	}
	
	// Getter
	public int getPk()	{	return pk;	}
	public String getCategoryM()	{	return categorys;	}
	public Timestamp getRegDate()	{	return reg_date;	}
	public int getKey()	{	return key;	}
	public int getCategotyMPk()	{	return categotym_pk;	}
	// Setter
	public void setPk(int pk)	{	this.pk = pk;	}
	public void setCategoryS(String categorys)	{	this.categorys = categorys;	}
	public void setRegDate(Timestamp reg_date)	{	this.reg_date = reg_date;	}
	public void setKey(int key)	{	this.key = key;	}
	public void setCategoryMPk(int categotym_pk)	{	this.categotym_pk = categotym_pk;	}
}
