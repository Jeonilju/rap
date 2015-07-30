package com.rap.analysismodels;


public class EdgeInfo {
	private String from;
	private String to;
	private Integer count;
	public EdgeInfo(String from, String to, Integer count) {
		super();
		this.from = from;
		this.to = to;
		this.count = count;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	
}