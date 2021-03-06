package com.rap.iap;

import java.sql.Timestamp;

public class RAPIapInfo {
	private int pk;
	private String Name;
	private int price_real;
	private int price_main;
	private int price_sub;
	private int using_type;
	private String google_id;
	private RAPPriceType PriceType;
	private String imagePath;
	private String categoryl;
	private String categorym;
	private String categorys;
	private String description;
	private Timestamp reg_date;
	
	/**
	 * RAP에 등록된 Items의 데이터를 생성합니다.
	 * @param pk			아이탬의 고유 IDx 입니다.
	 * @param Name			아이탬의 이름
	 * @param PriceReal		현금으로 결제했을때의 가격
	 * @param PriceMain		Main 가상화폐를 통해 결제했을 때의 가격
	 * @param PriceSub		Sub 가상화폐를 통해 결제했을 때의 가격
	 * @param type			어떤 화폐를 통해 결제할지를 결정
	 * @param google_id		현금결제시 사용할 구글 아이템 ID
	 * @param imagePath		아이탬 이미지의 경로
	 * @param categoryL		대분류
	 * @param categoryM		중분류
	 * @param categoryS		소분류
	 * @param description	세부 설명
	 * */
	public RAPIapInfo(int pk, String Name, int PriceReal, int PriceMain, int PriceSub, int type, String google_id, String imagePath,
			String categoryL, String categoryM, String categoryS, String description){
		this.pk = pk;
		this.Name = Name;
		this.price_real = PriceReal;
		this.price_main = PriceMain;
		this.price_sub = PriceSub;
		this.using_type = type;
		this.google_id = google_id;
		this.imagePath = imagePath;
		this.categoryl = categoryL;
		this.categorym = categoryM;
		this.categorys = categoryS;
		this.description = description;
	}
	
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
		return price_real;
	}
	public void setPriceReal(int priceReal) {
		price_real = priceReal;
	}
	public int getPriceMain() {
		return price_main;
	}
	public void setPriceMain(int priceMain) {
		price_main = priceMain;
	}
	public int getPriceSub() {
		return price_sub;
	}
	public void setPriceSub(int priceSub) {
		price_sub = priceSub;
	}
	public int getType() {
		return using_type;
	}
	public void setType(int type) {
		this.using_type = type;
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
		return categoryl;
	}
	public void setCategoryL(String categoryL) {
		this.categoryl = categoryL;
	}
	public String getCategoryM() {
		return categorym;
	}
	public void setCategoryM(String categoryM) {
		this.categorym = categoryM;
	}
	public String getCategoryS() {
		return categorys;
	}
	public void setCategoryS(String categoryS) {
		this.categorys = categoryS;
	}
	public Timestamp getRegDate() {
		return reg_date;
	}
	public void setRegDate(Timestamp regDate) {
		this.reg_date = regDate;
	}

	public String getGoogle_id() {
		return google_id;
	}

	public void setGoogle_id(String google_id) {
		this.google_id = google_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
