package com.rap.models;

import java.sql.Timestamp;

public class UserInfo {
	private int pk;
	private String key;
	private String gcm_id;
	private int grade_time;
	private int grade_money;
	private double position_let;
	private double position_lon;
	private int gender;
	private String os_version;
	private String divice_version;
	private int age;
	private Timestamp reg_date;
	private int count;
	private int virtual_main;
	private int virtual_sub;
	public UserInfo(int pk, String key, String gcm_id
			, int grade_time, int grade_money
			, double position_let, double position_lon
			, int gender, String os_version, String divice_version
			, int age, int count, int virtual_main, int virtual_sub, Timestamp reg_date){
		
		this.pk = pk;
		this.key = key;
		this.gcm_id = gcm_id;
		this.grade_time = grade_time;
		this.grade_time = grade_time;
		this.grade_money = grade_money;
		this.position_let = position_let;
		this.position_lon = position_lon;
		this.gender = gender;
		this.os_version = os_version;
		this.divice_version = divice_version;
		this.age = age;
		this.count = count;
		this.virtual_main = virtual_main;
		this.virtual_sub = virtual_sub;
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
	public String getGcm_id() {
		return gcm_id;
	}
	public void setGcm_id(String gcm_id) {
		this.gcm_id = gcm_id;
	}
	public int getGrade_time() {
		return grade_time;
	}
	public void setGrade_time(int grade_time) {
		this.grade_time = grade_time;
	}
	public int getGrade_money() {
		return grade_money;
	}
	public void setGrade_money(int grade_money) {
		this.grade_money = grade_money;
	}
	public double getPosition_let() {
		return position_let;
	}
	public void setPosition_let(double position_let) {
		this.position_let = position_let;
	}
	public double getPosition_lon() {
		return position_lon;
	}
	public void setPosition_lon(double position_lon) {
		this.position_lon = position_lon;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getOs_version() {
		return os_version;
	}
	

	public void setOs_version(String os_version) {
		this.os_version = os_version;
	}
	public String getDivice_version() {
		return divice_version;
	}
	public void setDivice_version(String divice_version) {
		this.divice_version = divice_version;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getVirtual_main() {
		return virtual_main;
	}

	public void setVirtual_main(int virtual_main) {
		this.virtual_main = virtual_main;
	}

	public int getVirtual_sub() {
		return virtual_sub;
	}

	public void setVirtual_sub(int virtual_sub) {
		this.virtual_sub = virtual_sub;
	}
	
	
}
