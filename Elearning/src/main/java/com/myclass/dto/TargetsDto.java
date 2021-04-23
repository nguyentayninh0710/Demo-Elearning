package com.myclass.dto;

public class TargetsDto {
	private int id;
	private String title;
	private int courseId;
	private String courseTitle;
	
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
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public TargetsDto() {
		super();
	}
	public TargetsDto(int id, String title, int courseId) {
		super();
		this.id = id;
		this.title = title;
		this.courseId = courseId;
	}
	public TargetsDto(int id, String title, int courseId, String courseTitle) {
		super();
		this.id = id;
		this.title = title;
		this.courseId = courseId;
		this.courseTitle = courseTitle;
	}
	
	
}
