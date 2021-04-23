package com.myclass.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity //Khai báo cho Hibernate biết lớp này ánh xạ đến 1 bản trong DB
@Table(name ="categories")
public class Categories {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "icon")
	private String icon;

	public int getId() {
		return id;
	}
	
	@OneToMany(mappedBy = "mtoCategories", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Courses> courses;

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
	
	public Categories() {
		super();
	}

	public Categories(int id, String title, String icon) {
		super();
		this.id = id;
		this.title = title;
		this.icon = icon;
	}

	public List<Courses> getCourses() {
		return courses;
	}

	public void setCourses(List<Courses> courses) {
		this.courses = courses;
	}

	
	
}
