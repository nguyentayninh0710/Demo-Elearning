package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity //Khai báo cho Hibernate biết lớp này ánh xạ đến 1 bản trong DB
@Table(name ="videos")
public class Video {
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "time_count")
	private int timeCount;
	
	@Column(name = "course_id")
	private int courseId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "courses", updatable = false, insertable = false)
	private Courses mtoCoursesFromVideo;

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
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Video() {
		super();
	}

	public Video(int id, String title, String url, int timeCount, int courseId) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.timeCount = timeCount;
		this.courseId = courseId;

	}

	

	
	
}
