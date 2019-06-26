package com.activitytracker.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.activitytracker.bean.Task;
import com.activitytracker.bean.User;
import com.activitytracker.resources.DBInfo;
import com.activitytracker.resources.ValidateUser;

@Path("/task")
public class TaskResource 
{
	@GET
	@Path("gettask/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTasks(@HeaderParam("authToken") String authToken, @PathParam("param") String param)
	{
		User user = ValidateUser.validateUser(authToken);
		
		ArrayList<Task> tasks = new ArrayList<Task>();
		
		if(user.getUsername() == null)
		{
			return Response.status(Status.UNAUTHORIZED).build();
		}
		else
		{
			//date or string param will be recieved from front or should be generated here?
			String query="select * from tasks where username = ?";
			try
			{
				Connection con=DBInfo.getConn();	
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1, user.getUsername());
				
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
			
			return Response.status(200).entity(tasks).build();
		}
	}
}
