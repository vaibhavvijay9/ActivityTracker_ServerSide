package com.activitytracker.resources;

import java.sql.*;

public class DBInfo 
{
	public static Connection con;
	static
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	public static Connection getConn()
	{
		try
		{
			con=DriverManager.getConnection("jdbc:mysql://remotemysql.com:3306/cQZmzGh0iL","cQZmzGh0iL","6LhvEPNaJb");	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	public static void close()
	{
		try
		{
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
