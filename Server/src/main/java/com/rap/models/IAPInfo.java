package com.rap.models;

import java.sql.Timestamp;

public class IAPInfo {
	private int pk;
	private int key;
	private String iap;
	private int price_real;
	private int price_main;
	private int price_sub;
	private int using_type;
	private String image;
	private String discription;
	private Timestamp reg_date;
	private String categoryl;
	private String categorym;
	private String categorys;
	
	public IAPInfo(int pk, int key, String iap
			, int price_real, int price_main, int price_sub, int using_type
			, String image, String discription, Timestamp reg_date
			, String categoryl, String categorym, String categorys) {
		this.pk = pk;
		this.key = key;
		this.iap = iap;
		this.price_real = price_real;
		this.price_main = price_main;
		this.price_sub = price_sub;
		this.using_type = using_type;
		this.image = image;
		this.discription = discription;
		this.reg_date = reg_date;
		this.categoryl = categoryl;
		this.categorym = categorym;
		this.categorys = categorys;
	}
	
	public int getPk() {
		return pk;
	}
	public void setPk(int pk) {
		this.pk = pk;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getIap() {
		return iap;
	}
	public void setIap(String iap) {
		this.iap = iap;
	}
	public int getPrice_real() {
		return price_real;
	}
	public void setPrice_real(int price_real) {
		this.price_real = price_real;
	}
	public int getPrice_main() {
		return price_main;
	}
	public void setPrice_main(int price_main) {
		this.price_main = price_main;
	}
	public int getPrice_sub() {
		return price_sub;
	}
	public void setPrice_sub(int price_sub) {
		this.price_sub = price_sub;
	}
	public int getUsing_type() {
		return using_type;
	}
	public void setUsing_type(int using_type) {
		this.using_type = using_type;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getCategoryl() {
		return categoryl;
	}
	public void setCategoryl(String categoryl) {
		this.categoryl = categoryl;
	}
	public String getCategorym() {
		return categorym;
	}
	public void setCategorym(String categorym) {
		this.categorym = categorym;
	}
	public String getCategorys() {
		return categorys;
	}
	public void setCategorys(String categorys) {
		this.categorys = categorys;
	}
	
}
