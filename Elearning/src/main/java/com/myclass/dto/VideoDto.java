package com.myclass.dto;


public class VideoDto {
	private int id;
	private String title;
	private String url;
	private int timeCount;
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
	public int getTimeCount() {
		return timeCount;
	}
	public void setTimeCount(int timeCount) {
		this.timeCount = timeCount;
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
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public VideoDto() {
		super();
	}
	
	public VideoDto(int id, String title, String url, int timeCount, int courseId) {
		super();
		this.id = id;
		this.title = title;
		this.timeCount = timeCount;
		this.url = url;
		this.courseId = courseId;
	}
	public VideoDto(int id, String title, String url, int timeCount, int courseId, String courseTitle) {
		super();
		this.id = id;
		this.title = title;
		this.timeCount = timeCount;
		this.url = url;
		this.courseId = courseId;
		this.courseTitle = courseTitle;
	}
	
	
	
}
