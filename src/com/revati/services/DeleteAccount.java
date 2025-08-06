package com.revati.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revati.dbcon.ConnectDB;

/**
 * Servlet implementation class DeleteAccount
 */
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAccount() {
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
			
			Connection con = ConnectDB.getConnect();
			
			PreparedStatement pst1 = con.prepareStatement("delete from accounts where accNo=?");
			pst1.setInt(1, accNo);
			int i =pst1.executeUpdate();
			if(i>0)
			{
				System.out.println(i+" Account Deleted Successfully...");
				response.sendRedirect("dashboard.html");
			}else
			{
				System.out.println("Failed to delete...!!");
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
