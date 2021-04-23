package com.myclass.dto;

public class UserCoursesDto {
	private int userId;
	private int courseId;
	private int roleID;
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
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	
	public UserCoursesDto() {
		super();
	}
	public UserCoursesDto(int userId, int courseId, int roleID) {
		super();
		this.userId = userId;
		this.courseId = courseId;
		this.roleID = roleID;
	}
	
}
