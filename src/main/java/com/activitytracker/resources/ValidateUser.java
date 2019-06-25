package com.activitytracker.resources;

import java.sql.*;

import com.activitytracker.bean.User;

public class ValidateUser 
{
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
}
