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
			ArrayList<Task> tasks = Utility.getTasks(user.getUsername(), param);
			
			return Response.status(200).entity(tasks).build();
		}
	}
}
