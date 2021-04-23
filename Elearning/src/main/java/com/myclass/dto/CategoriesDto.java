package com.myclass.dto;

import java.beans.Transient;

public class CategoriesDto {
	private int id;
	private String title;
	private String icon;
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public CategoriesDto() {
		super();
	}
	public CategoriesDto(int id, String title, String icon) {
		super();
		this.id = id;
		this.title = title;
		this.icon = icon;
	}
	@Transient
	public String getImgPath() {
		if(icon==null || id ==0)
			return null;
		return "/user-photos/"+id+"/"+icon;
	}
	
	
}
