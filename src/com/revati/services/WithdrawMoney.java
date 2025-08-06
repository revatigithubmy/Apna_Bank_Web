package com.revati.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revati.dbcon.ConnectDB;

/**
 * Servlet implementation class WithdrawMoney
 */
public class WithdrawMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WithdrawMoney() {
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
			double wamt = Double.parseDouble(request.getParameter("wamt"));
			if(wamt > 0)
			{
				Connection con = ConnectDB.getConnect();
				PreparedStatement ps1 = con.prepareStatement("select * from accounts where accNo=?");
				ps1.setInt(1,accNo);
				ResultSet rs1 = ps1.executeQuery();
				if(rs1.next())
				{
					name = rs1.getString(2);
				double	balance = rs1.getDouble(3);
					if(balance >= wamt)
					{
						balance = balance-wamt;
						PreparedStatement ps2 = con.prepareStatement("update accounts set balance=? where accNo=?");
						ps2.setDouble(1, balance);
						ps2.setInt(2, accNo);
						int i = ps2.executeUpdate();
						if(i>0)
						{
							System.out.println("Withdraw Succesfully....");
							response.sendRedirect("dashboard.html");
						}
						else
						{
							System.out.println("Failed to withdraw...!!");
							response.sendRedirect("error.html");

						}
					}else
					{
						System.err.println("Not Sufficient Balance...!!!");
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
