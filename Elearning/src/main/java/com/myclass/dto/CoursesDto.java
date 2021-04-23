package com.myclass.dto;

import java.beans.Transient;

public class CoursesDto {
	private int id;
	private String title;
	private String image;
	private int letures_count; //Số bài giảng
	private int hour_count; 
	private int view_count;
	private float price;
	private float discount;
	private float promotionPrice;
	private String description;
	private String content;
	private int categoryId;
	private String categoryTitle;
	private String lastUpdate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getLetures_count() {
		return letures_count;
	}
	public void setLetures_count(int letures_count) {
		this.letures_count = letures_count;
	}
	public int getHour_count() {
		return hour_count;
	}
	public void setHour_count(int hour_count) {
		this.hour_count = hour_count;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	public float getPromotionPrice() {
		return promotionPrice;
	}
	public void setPromotionPrice(float promotionPrice) {
		this.promotionPrice = promotionPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	
	
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	
	public CoursesDto() {
		super();
	}
	public CoursesDto(int id, String title, String image, int letures_count, int hour_count, int view_count,
			float price, float discount, float promotionPrice, String description, String content, int categoryId,
			String lastUpdate) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.letures_count = letures_count;
		this.hour_count = hour_count;
		this.view_count = view_count;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
		this.content = content;
		this.categoryId = categoryId;
		this.lastUpdate = lastUpdate;
	}
	public CoursesDto(int id, String title, String image, int letures_count, int hour_count, int view_count,
			float price, float discount, float promotionPrice, String description, String content, int categoryId,
			String categoryTitle, String lastUpdate) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.letures_count = letures_count;
		this.hour_count = hour_count;
		this.view_count = view_count;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
		this.content = content;
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.lastUpdate = lastUpdate;
	}
	
	@Transient
	public String getImgPath() {
		if(image==null || id ==0)
			return null;
		return "/user-photos/"+id+"/"+image;
	}
	
	
}
