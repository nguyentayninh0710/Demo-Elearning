package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity //Khai báo cho Hibernate biết lớp này ánh xạ đến 1 bản trong DB
@Table(name ="targets")
public class Targets {
	@Column(name = "id")
	@Id
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "course_id")
	private int courseId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "courses", updatable = false, insertable = false)
	private Courses mtoCoursesFromTarget;

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

	public Targets() {
		
	}
	
	public Targets(int id, String title, int courseId) {
		super();
		this.id = id;
		this.title = title;
		this.courseId = courseId;
	}
	
	
}
