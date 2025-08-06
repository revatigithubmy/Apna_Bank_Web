package com.revati.services;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revati.dbcon.ConnectDB;

/**
 * Servlet implementation class AddAccount
 */
public class AddAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAccount() {
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
			int accNo = Integer.parseInt(request.getParameter("accNo"));
			String name = request.getParameter("name");
			double balance = Double.parseDouble(request.getParameter("balance"));
			
			Connection con = ConnectDB.getConnect();
			
			PreparedStatement ps1 = con.prepareStatement("select * from accounts where accNo=?");
			ps1.setInt(1, accNo);
			ResultSet res = ps1.executeQuery(); 
			if(res.next())
			{
				System.out.println("Account Number Already Present in System...!!!");
				response.sendRedirect("error.html");
			}else
			{
				PreparedStatement ps2 = con.prepareStatement("insert into accounts values(?,?,?)");
				ps2.setInt(1, accNo);
				ps2.setString(2, name);
				ps2.setDouble(3, balance);
				int i = ps2.executeUpdate();
				if(i>0)
				{
					System.out.println(i+" Record Inserted....!!");
					response.sendRedirect("dashboard.html");
				}else
				{
					System.out.println("Failed to insert...!!");
					response.sendRedirect("error.html");
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("error.html");

		}
	}
	

}
