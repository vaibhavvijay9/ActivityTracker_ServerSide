package com.activitytracker.api;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.activitytracker.bean.ResponseBean;
import com.activitytracker.bean.User;
import com.activitytracker.resources.DBInfo;
import com.activitytracker.resources.Utility;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

@Path("/user")
public class UserResource 
{	
	@GET
	public Response getMsg()
	{
		String message = "Hey Jersey!!";
		
		return Response.status(200).entity(message).build();
	}
	
	@POST
	@Path("/signUp")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signUp(User user)
	{
		String base64EncodedPassword = null;
		try 
		{
			base64EncodedPassword = Base64.getEncoder().encodeToString((user.getPassword()).getBytes("utf-8"));
		}
		catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		String query="insert into users values(?,?,?)";
		int dbResponse=0;		//dbResponse = 0  : FAILURE
		try
		{
			Connection con=DBInfo.getConn();	
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, base64EncodedPassword);
			ps.setString(3, user.getName());
			
			dbResponse=ps.executeUpdate();	//dbResponse = 1 : SUCCESS
			
			con.close();
		}
		catch(MySQLIntegrityConstraintViolationException cve)
		{
			dbResponse = 2;		//dbResponse = 2 : user already exists FAILURE
		}
		catch(Exception e)
		{
			e.printStackTrace();	//dbResponse = 1 : FAILURE
		}
		
		return Response.status(200).entity(dbResponse).build();
	}
		
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User user)
	{
		String base64EncodedPassword = null;
		String token = "";
		
		ResponseBean responseBean = new ResponseBean();
		responseBean.setMessage("failure");
		responseBean.setToken("");
		try 
		{
			base64EncodedPassword = Base64.getEncoder().encodeToString((user.getPassword()).getBytes("utf-8"));
		}
		catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		String query="select * from users where username=? and password=?";
		try
		{
			Connection con=DBInfo.getConn();	
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, user.getUsername());
			ps.setString(2, base64EncodedPassword);
			
			ResultSet res = ps.executeQuery();
			
			while(res.next())
			{
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); 
				String formattedDate = sdf.format(date);
				
				token = res.getString(1) + formattedDate;
				String base64EncodedToken = Base64.getEncoder().encodeToString((token).getBytes("utf-8"));
				
				String query1="insert into sessions values(?,?)";
				PreparedStatement ps1 = con.prepareStatement(query1);
				ps1.setString(1, res.getString(1));
				ps1.setString(2, base64EncodedToken);
				ps1.executeUpdate();

				responseBean.setMessage("success");
				responseBean.setToken(base64EncodedToken);
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Response.status(200).entity(responseBean).build();
	}
	
	@POST
	@Path("/logOut")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response logOut(User user)
	{
		String query="delete from sessions where username = ?";
		int dbResponse=0;
		try
		{
			Connection con=DBInfo.getConn();	
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, user.getUsername());
			
			dbResponse=ps.executeUpdate();
			
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return Response.status(200).entity(dbResponse).build();
	}
	
	//get user
	@GET
	@Path("/getuser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTask(@HeaderParam("authToken") String authToken)
	{
		User user = Utility.validateUser(authToken);
		
		if(user.getUsername() == null)
		{
			return Response.status(Status.UNAUTHORIZED).build();
		}
		else
		{	
			return Response.status(200).entity(user).build();
		}
	}
}
