package com.rap.iap;

import java.sql.Timestamp;

public class RAPIapInfo {
	private int pk;
	private String Name;
	private int PriceReal;
	private int PriceMain;
	private int PriceSub;
	private int type;
	private RAPPriceType PriceType;
	private String imagePath;
	private String categoryL;
	private String categoryM;
	private String categoryS;
	private Timestamp regDate;
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getPriceReal() {
		return PriceReal;
	}
	public void setPriceReal(int priceReal) {
		PriceReal = priceReal;
	}
	public int getPriceMain() {
		return PriceMain;
	}
	public void setPriceMain(int priceMain) {
		PriceMain = priceMain;
	}
	public int getPriceSub() {
		return PriceSub;
	}
	public void setPriceSub(int priceSub) {
		PriceSub = priceSub;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public RAPPriceType getPriceType() {
		return PriceType;
	}
	public void setPriceType(RAPPriceType priceType) {
		PriceType = priceType;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getCategoryL() {
		return categoryL;
	}
	public void setCategoryL(String categoryL) {
		this.categoryL = categoryL;
	}
	public String getCategoryM() {
		return categoryM;
	}
	public void setCategoryM(String categoryM) {
		this.categoryM = categoryM;
	}
	public String getCategoryS() {
		return categoryS;
	}
	public void setCategoryS(String categoryS) {
		this.categoryS = categoryS;
	}
	public Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	
}
