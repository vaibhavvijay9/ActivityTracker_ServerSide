package com.activitytracker.bean;

import java.sql.Date;

public class Task {
	private int taskId;
	private String taskDescription;
	private Date taskDate;
	private boolean isCompleted;
	
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
		return isCompleted;
	}
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}
	public Task() {
		super();
	}
	public Task(int taskId, String taskDescription, Date taskDate, boolean isCompleted) {
		super();
		this.taskId = taskId;
		this.taskDescription = taskDescription;
		this.taskDate = taskDate;
		this.isCompleted = isCompleted;
	}
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskDescription=" + taskDescription + ", taskDate=" + taskDate
				+ ", isCompleted=" + isCompleted + "]";
	}
}
