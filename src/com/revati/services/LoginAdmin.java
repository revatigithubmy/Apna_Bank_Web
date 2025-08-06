package com.revati.services;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revati.dbcon.ConnectDB;

/**
 * Servlet implementation class LoginAdmin
 */
public class LoginAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		try
		{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			Connection con = ConnectDB.getConnect();
			PreparedStatement ps3 = con.prepareStatement("select * from admininfo where email=? and password=?");
			ps3.setString(1, email);
			ps3.setString(2, password);
			ResultSet res = ps3.executeQuery();
			
			if(res.next())
			{
				response.sendRedirect("dashboard.html");
			}
			else
			{
				response.sendRedirect("index.html");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
