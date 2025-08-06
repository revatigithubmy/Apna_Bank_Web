<%@page import="com.revati.dbcon.ConnectDB" %>
<%@page import="java.sql.*" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Specific</title>
<link rel="stylesheet" href="css/viewSpecific.css" />

</head>
<body>
		<form action="viewSpecific.jsp">
			<input type="text" name="accNo" placeholder="Enter account number here"></input>
			<input type="submit"></input>
		</form>
		
		<%
		try
		{
			
		String ano = request.getParameter("accNo");
			if(ano!=null)
			{
				%>
					<table>
					<tr>
					<th>Account No.</th>
					<th>Account Holder's Name</th>
					<th>Balance</th>
					</tr>
					
				<%
				Connection con = ConnectDB.getConnect();
				PreparedStatement ps = con.prepareStatement("select * from accounts where accNo=?");
				ps.setInt(1,Integer.parseInt(ano));
				ResultSet rs = ps.executeQuery();
				while(rs.next())
				{
					%>
						<tr>
						<td><%=rs.getInt(1)%></td>
						<td><%=rs.getString(2)%></td>
						<td><%=rs.getFloat(3)%></td>
						</tr>
				
					<%
						
					}
				}
			}
		catch(Exception e)
		{
		   e.printStackTrace();
		}
		%>
		
		 <a href="dashboard.html">Go to Dashboard</a>
		</table>
</body>
</html>