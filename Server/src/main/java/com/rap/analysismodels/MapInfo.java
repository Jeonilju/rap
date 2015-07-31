package com.rap.analysismodels;

import java.sql.Timestamp;

public class MapInfo {
	private String area;
	private int count;
	public MapInfo(String area, int count) {
		super();
		this.area = area;
		this.count = count;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
	
}