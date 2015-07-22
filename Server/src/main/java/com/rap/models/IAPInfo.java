package com.rap.models;

import java.sql.Timestamp;

public class IAPInfo {
	private int pk;
	private String name;
	private String key;
	private int price_real;
	private int price_main;
	private int price_sub;
	private int using_type;
	private String google_id;
	private String imagePath;
	private String description;
	private Timestamp reg_date;
	private String categoryl;
	private String categorym;
	private String categorys;
	
	public IAPInfo(int pk, String key, String iap
			, int price_real, int price_main, int price_sub, int using_type, String google_id
			, String imagePath, String description, Timestamp reg_date
			, String categoryl, String categorym, String categorys) {
		this.pk = pk;
		this.key = key;
		this.name = iap;
		this.price_real = price_real;
		this.price_main = price_main;
		this.price_sub = price_sub;
		this.google_id = google_id;
		this.using_type = using_type;
		this.imagePath = imagePath;
		this.description = description;
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getIap() {
		return name;
	}
	public void setIap(String iap) {
		this.name = iap;
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
		return imagePath;
	}
	public void setImage(String image) {
		this.imagePath = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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

	public String getGoogle_id() {
		return google_id;
	}

	public void setGoogle_id(String google_id) {
		this.google_id = google_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
