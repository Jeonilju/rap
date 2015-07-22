package com.rap.models;


public class OSInfo {
	private int y;
	private String name;
	public OSInfo(int y, String name) {
		super();
		this.y = y;
		this.name = name;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}