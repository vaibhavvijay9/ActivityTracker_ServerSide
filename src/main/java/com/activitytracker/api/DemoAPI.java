package com.activitytracker.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/hello")
public class DemoAPI 
{
	//URL - http://localhost:8083/ActivityTracker_ServerSide/api/hello
	@GET
	public Response getMsg()
	{
		String message = "Hey Jersey!!";
		
		return Response.status(200).entity(message).build();
	}
	
	//URL - http://localhost:8083/ActivityTracker_ServerSide/api/hello/vaibhav
	@GET
	@Path("/{param}")
	public Response greetUser(@PathParam("param") String name)
	{
		String message = "Hey " + name;
		
		return Response.status(200).entity(message).build();
	}
	
}
