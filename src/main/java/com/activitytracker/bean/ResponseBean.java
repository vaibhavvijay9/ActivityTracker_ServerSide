package com.activitytracker.bean;

public class ResponseBean 
{
	private String message;
	private String token;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public ResponseBean() {
		super();
	}
	public ResponseBean(String message, String token) {
		super();
		this.message = message;
		this.token = token;
	}
	@Override
	public String toString() {
		return "ResponseBean [message=" + message + ", token=" + token + "]";
	}
}
