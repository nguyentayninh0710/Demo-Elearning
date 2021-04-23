package com.myclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity //Khai báo cho Hibernate biết lớp này ánh xạ đến 1 bản trong DB
@Table(name ="user_courses")
public class UserCourses {
	
	@Column(name = "user_id")
	@Id
	private int userId;
	
	@Column(name = "course_id")
	private int courseId;
	
	@Column(name = "role_id")
	private int roleId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public UserCourses() {
		super();
	}

	public UserCourses(int userId, int courseId, int roleId) {
		super();
		this.userId = userId;
		this.courseId = courseId;
		this.roleId = roleId;
	}
	
	
}
