package com.rap.models;

public class ActivityInfo {
	private int pk;
	private int key;
	private int user_id;
	private String activity_name;
	
	public ActivityInfo(int pk, int key, int user_id, String activity_name){
		this.pk = pk;
		this.key = key;
		this.user_id = user_id;
		this.activity_name = activity_name;
	}
	
	// Getter
	public int getPk()	{	return pk;	}
	public int getKey()	{	return key;	}
	public int getUserId()	{	return user_id;	}
	public String getActivityName()	{	return activity_name;	}
	
	// Setter
	public void setPk(int pk)	{	this.pk = pk;	}
	public void setKey(int key)	{	this.key = key;	}
	public void setUserId(int user_id)	{	this.user_id = user_id;	}
	public void setActivityName(String activity_name)	{	this.activity_name = activity_name;	}
}
