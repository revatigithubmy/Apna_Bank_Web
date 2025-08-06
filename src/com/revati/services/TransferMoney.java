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
 * Servlet implementation class TransferMoney
 */
public class TransferMoney extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferMoney() {
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
		int SenderNo, reciverNo;
		double senderbal, rbal, tamt, balance;
		try
		{
		     SenderNo = Integer.parseInt(request.getParameter("sccNo"));
			 reciverNo = Integer.parseInt(request.getParameter("rccNo"));
			 tamt = Double.parseDouble(request.getParameter("tamt"));
	        if(tamt>0)
	        {
	        	Connection con = ConnectDB.getConnect();
				PreparedStatement sen1 = con.prepareStatement("select * from accounts where accNo=?");
				sen1.setInt(1,SenderNo);
				ResultSet rss1 = sen1.executeQuery();
				
				if(rss1.next())
				{
				String	name = rss1.getString(2);
					senderbal = rss1.getDouble(3);
					if(senderbal>=tamt)
					{
						balance = senderbal - tamt;
						PreparedStatement s2 = con.prepareStatement("update accounts set balance=? where accNo=?");
						s2.setDouble(1, balance);
						s2.setInt(2, SenderNo);
						s2.executeUpdate();
						
						PreparedStatement r1 = con.prepareStatement("select * from accounts where accNo=?");
						r1.setInt(1, reciverNo);
						ResultSet rsr1 = r1.executeQuery();
						
						if(rsr1.next())
						{
							name = rsr1.getString(2);
							balance = rsr1.getDouble(3) + tamt;
							PreparedStatement ps2 = con.prepareStatement("update accounts set balance=? where accNo=?");
							ps2.setDouble(1, balance);
							ps2.setInt(2, reciverNo);
							int i = ps2.executeUpdate();
							if(i>0)
							{
								System.out.println("Money Transfer Succesfully....");
								response.sendRedirect("dashboard.html");
							}
							else
							{
								System.err.println("Failed to Transfer Money...!!");
								response.sendRedirect("error.html");
							}
						}
						else{
							System.err.println("Receiver is Not Found..!");
							response.sendRedirect("error.html");
						}
					}
					else{
						System.err.println("Balance is Not Sufficient");
						response.sendRedirect("error.html");
					}
				}
				else{
					System.err.println("Senders Account is Not Found");
					response.sendRedirect("error.html");
				}
	        }
	        else{
	        	System.err.println("Negative Amount is Not Allowed..!");
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
