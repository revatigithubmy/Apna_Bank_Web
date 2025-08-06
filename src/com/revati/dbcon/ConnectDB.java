package com.revati.dbcon;

import java.sql.*;
import java.sql.DriverManager;

public class ConnectDB {
		static Connection con =null;
		public static Connection getConnect()
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver"); //load the driver	
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/apnabank","root","");
				System.out.println("Connection Established, con ="+con);
			}
			catch(Exception e)
			{
				System.out.println("Failed to connect...!!");
				e.printStackTrace();
			}
			return con;
		}
}
