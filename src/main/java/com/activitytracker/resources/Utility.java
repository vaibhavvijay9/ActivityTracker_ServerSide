package com.activitytracker.resources;

import java.sql.*;
import java.util.ArrayList;

import com.activitytracker.bean.Task;
import com.activitytracker.bean.User;

public class Utility 
{
	// method
	public static User validateUser(String token)
	{
		User user = new User(); 
		
		String query="select username,name from users where username = (select username from sessions where token= ?)";
		try
		{
			Connection con=DBInfo.getConn();	
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, token);
			
			ResultSet res = ps.executeQuery();
			
			while(res.next())
			{
				user.setUsername(res.getString(1));
				user.setname(res.getString(2));
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		return user;
	}
	
	// method - get tasks
	public static ArrayList<Task> getTasks(String username, String duration)
	{
		// how to handle values other than below for duration, 
		// like in url if user explicitly writes /monthaaa
		ArrayList<Task> tasks = new ArrayList<Task>();
		
		String query = "";
		
		switch(duration)
		{
			case "today":
			case "day" :
				query = "select * from tasks where username=? and task_date=date_format(now(),'%y-%m-%d')";
				break;
			case "yesterday" :
				query = "select * from tasks where username=? and task_date=date_format(DATE_ADD(now(), INTERVAL -1 DAY),'%y-%m-%d')";
				break;
			case "tomorrow" :
				query = "select * from tasks where username=? and task_date=date_format(DATE_ADD(now(), INTERVAL 1 DAY),'%y-%m-%d')";
				break;
			case "week" :
				query = "select * from tasks where username=? and task_date between date_format(DATE_ADD(now(),INTERVAL 1-DAYOFWEEK(NOW()) DAY),'%y-%m-%d') and date_format(DATE_ADD(now(),INTERVAL 7-DAYOFWEEK(NOW()) DAY),'%y-%m-%d')";
				break;
			case "month" :
				query = "select * from tasks where username=? and task_date between date_format(LAST_DAY(now()) + INTERVAL 1 DAY - INTERVAL 1 MONTH,'%y-%m-%d') and date_format(LAST_DAY(now()),'%y-%m-%d')";
				break;	
		}
		try
		{
			Connection con=DBInfo.getConn();	
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, username);
			
			ResultSet res = ps.executeQuery();
			
			while(res.next())
			{
				Task task = new Task();
				task.setTaskId(res.getInt(1));
				task.setTaskDescription(res.getString(2));
				task.setTaskDate(res.getDate(3));
				task.setCompleted(res.getBoolean(4));
				
				tasks.add(task);
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
		}
		
		return tasks;
	}
}
