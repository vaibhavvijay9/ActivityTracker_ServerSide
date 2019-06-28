package com.activitytracker.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.activitytracker.bean.Task;
import com.activitytracker.bean.User;
import com.activitytracker.resources.DBInfo;
import com.activitytracker.resources.Utility;

@Path("/task")
public class TaskResource 
{
	@GET
	@Path("gettask/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTasks(@HeaderParam("authToken") String authToken, @PathParam("param") String param)
	{
		User user = Utility.validateUser(authToken);
		
		if(user.getUsername() == null)
		{
			return Response.status(Status.UNAUTHORIZED).build();
		}
		else
		{
			if(param.equals("today") || param.equals("day") || param.equals("yesterday") 
				|| param.equals("tomorrow") || param.equals("week") || param.equals("month"))
			{
				ArrayList<Task> tasks = Utility.getTasks(user.getUsername(), param);
				return Response.status(200).entity(tasks).build();
			}
			else
			{
				return Response.status(Status.BAD_REQUEST).build();
			}
		}
	}
	
	@POST
	@Path("/addtask")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTask(@HeaderParam("authToken") String authToken, Task task)
	{
		User user = Utility.validateUser(authToken);
		
		if(user.getUsername() == null)
		{
			return Response.status(Status.UNAUTHORIZED).build();
		}
		else
		{
			String query = "insert into tasks(task_description,task_date,is_completed,username) values(?,?,?,?)";
			int isAdded = 0;
			try
			{
				Connection con=DBInfo.getConn();	
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1, task.getTaskDescription());
				ps.setDate(2, task.getTaskDate());
				ps.setBoolean(3, false);
				ps.setString(4, task.getUsername());
				
				isAdded = ps.executeUpdate();
				
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			
			return Response.status(200).entity(isAdded).build();
		}
	}
	
	@PUT
	@Path("/updatetask")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTask(@HeaderParam("authToken") String authToken, Task task)
	{
		User user = Utility.validateUser(authToken);
		
		if(user.getUsername() == null)
		{
			return Response.status(Status.UNAUTHORIZED).build();
		}
		else
		{
			String query = "update tasks set task_description=?,task_date=? where task_id=?";
			int isUpdated = 0;
			try
			{
				Connection con=DBInfo.getConn();	
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1, task.getTaskDescription());
				ps.setDate(2, task.getTaskDate());
				ps.setInt(3, task.getTaskId());
				
				isUpdated = ps.executeUpdate();
				
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			
			return Response.status(200).entity(isUpdated).build();
		}
	}
	
	@DELETE
	@Path("/deletetask/{taskId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTask(@HeaderParam("authToken") String authToken, @PathParam("taskId") int taskId)
	{
		User user = Utility.validateUser(authToken);
		
		if(user.getUsername() == null)
		{
			return Response.status(Status.UNAUTHORIZED).build();
		}
		else
		{
			String query = "delete from tasks where task_id=?";
			int isDeleted = 0;
			try
			{
				Connection con=DBInfo.getConn();	
				PreparedStatement ps=con.prepareStatement(query);
				ps.setInt(1, taskId);
				
				isDeleted = ps.executeUpdate();
				
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			
			return Response.status(200).entity(isDeleted).build();
		}
	}
	
	@PUT
	@Path("/updatetaskstatus")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTaskStatus(@HeaderParam("authToken") String authToken, Task task)
	{
		User user = Utility.validateUser(authToken);
		
		if(user.getUsername() == null)
		{
			return Response.status(Status.UNAUTHORIZED).build();
		}
		else
		{
			String query = "update tasks set is_completed=? where task_id=?";
			int isUpdated = 0;
			try
			{
				Connection con=DBInfo.getConn();	
				PreparedStatement ps=con.prepareStatement(query);
				ps.setBoolean(1, task.isCompleted());
				ps.setInt(2, task.getTaskId());
				
				isUpdated = ps.executeUpdate();
				
				con.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();	
			}
			
			return Response.status(200).entity(isUpdated).build();
		}
	}
}
