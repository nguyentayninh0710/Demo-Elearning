package com.myclass.common;

public class ServiceInfo {
	private boolean status;
	private String message;
	private int id;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ServiceInfo(boolean status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public ServiceInfo() {
		
	}
	public ServiceInfo(boolean status, String message, int id) {
		super();
		this.status = status;
		this.message = message;
		this.id = id;
	}
	
	
}
