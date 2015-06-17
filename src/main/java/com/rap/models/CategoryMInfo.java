package com.rap.models;

import java.sql.Timestamp;

public class CategoryMInfo {
	private int pk;
	private int key;
	private String categorym;
	private Timestamp reg_date;
	private int categotyL_pk;
	
	public CategoryMInfo(int pk, int key, String categorym, Timestamp reg_date, int categotyL_pk){
		this.pk = pk;
		this.key = key;
		this.categorym = categorym;
		this.reg_date = reg_date;
		this.categotyL_pk = categotyL_pk;
	}
	
	// Getter
	public int getPk()	{	return pk;	}
	public String getCategoryM()	{	return categorym;	}
	public Timestamp getRegDate()	{	return reg_date;	}
	public int getKey()	{	return key;	}
	public int getCategotyLPk()	{	return categotyL_pk;	}
	// Setter
	public void setPk(int pk)	{	this.pk = pk;	}
	public void setCategoryM(String categorym)	{	this.categorym = categorym;	}
	public void setRegDate(Timestamp reg_date)	{	this.reg_date = reg_date;	}
	public void setKey(int key)	{	this.key = key;	}
	public void setCategoryLPk(int categotyL_pk)	{	this.categotyL_pk = categotyL_pk;	}
}
