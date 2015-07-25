package com.rap.models;

public class SettingInfo {
	private int pk;
	private String key;
	private int grade_moneyl;
	private int grade_moneym;
	private int grade_moneys;
	private int grade_timel;
	private int grade_timem;
	private int grade_times;
	private String google_project_num;
	public SettingInfo(int pk, String key, int grade_moneyl, int grade_moneym, int grade_moneys, int grade_timel,
			int grade_timem, int grade_times, String google_project_num) {
		super();
		this.pk = pk;
		this.key = key;
		this.grade_moneyl = grade_moneyl;
		this.grade_moneym = grade_moneym;
		this.grade_moneys = grade_moneys;
		this.grade_timel = grade_timel;
		this.grade_timem = grade_timem;
		this.grade_times = grade_times;
		this.google_project_num = google_project_num;
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
	public int getGrade_moneyl() {
		return grade_moneyl;
	}
	public void setGrade_moneyl(int grade_moneyl) {
		this.grade_moneyl = grade_moneyl;
	}
	public int getGrade_moneym() {
		return grade_moneym;
	}
	public void setGrade_moneym(int grade_moneym) {
		this.grade_moneym = grade_moneym;
	}
	public int getGrade_moneys() {
		return grade_moneys;
	}
	public void setGrade_moneys(int grade_moneys) {
		this.grade_moneys = grade_moneys;
	}
	public int getGrade_timel() {
		return grade_timel;
	}
	public void setGrade_timel(int grade_timel) {
		this.grade_timel = grade_timel;
	}
	public int getGrade_timem() {
		return grade_timem;
	}
	public void setGrade_timem(int grade_timem) {
		this.grade_timem = grade_timem;
	}
	public int getGrade_times() {
		return grade_times;
	}
	public void setGrade_times(int grade_times) {
		this.grade_times = grade_times;
	}
	public String getGoogle_project_num() {
		return google_project_num;
	}
	public void setGoogle_project_num(String google_project_num) {
		this.google_project_num = google_project_num;
	}
	
}
