package com.revati.services;

import java.io.IOException;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revati.dbcon.ConnectDB;

/**
 * Servlet implementation class DepositMoney
 */
public class DepositMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositMoney() {
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
			double amt = Double.parseDouble(request.getParameter("amt"));
			if(amt > 0.0)
			{
				Connection con = ConnectDB.getConnect();
				PreparedStatement ps4 = con.prepareStatement("select * from accounts where accNo=?");
				ps4.setInt(1,accNo);
				ResultSet rs3 = ps4.executeQuery();
				if(rs3.next())
				{
				name = rs3.getString(2);
				double balance = rs3.getDouble(3)+amt;
					PreparedStatement ps2 = con.prepareStatement("update accounts set balance=? where accNo=?");
					ps2.setDouble(1, balance);
					ps2.setInt(2, accNo);
					int i = ps2.executeUpdate();
					if(i>0)
					{
						System.out.println("Deposited Succesfully....");
						response.sendRedirect("dashboard.html");
					}
					else
					{
						System.out.println("Failed to deposit...!!");
						response.sendRedirect("error.html");
					}
				}
				else
				{
					System.err.println("Account not found...!!!");	
					response.sendRedirect("error.html");
				}
			}
			else
			{
				System.err.println("Negative Value Not Allowed...!!!");
				response.sendRedirect("error.html");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response.sendRedirect("error.html");
		}
		
	}

}
