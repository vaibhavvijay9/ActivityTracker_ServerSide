package com.activitytracker.bean;

import java.sql.Date;

public class Tasks {
	private int taskId;
	private String taskDescription;
	private Date taskDate;
	
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
	public Tasks() {
		super();
	}
	public Tasks(int taskId, String taskDescription, Date taskDate) {
		super();
		this.taskId = taskId;
		this.taskDescription = taskDescription;
		this.taskDate = taskDate;
	}
	@Override
	public String toString() {
		return "Tasks [taskId=" + taskId + ", taskDescription=" + taskDescription + ", taskDate=" + taskDate + "]";
	}
}
