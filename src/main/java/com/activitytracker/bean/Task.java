package com.activitytracker.bean;

import java.sql.Date;

public class Task {
	private int taskId;
	private String taskDescription;
	private Date taskDate;
	private boolean completed;
	private String username;
	
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public Date getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Task() {
		super();
	}
	public Task(int taskId, String taskDescription, Date taskDate, boolean completed, String username) {
		super();
		this.taskId = taskId;
		this.taskDescription = taskDescription;
		this.taskDate = taskDate;
		this.completed = completed;
		this.username = username;
	}
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskDescription=" + taskDescription + ", taskDate=" + taskDate
				+ ", completed=" + completed + ", username=" + username + "]";
	}
}
