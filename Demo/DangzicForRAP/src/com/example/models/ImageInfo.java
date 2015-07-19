package com.example.models;

public class ImageInfo {
	
	private String area;
	private boolean isUsed;
	
	public ImageInfo(String area, boolean isUsed){
		this.area = area;
		this.isUsed = isUsed;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	
	
}
