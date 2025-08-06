<%@page import="com.revati.dbcon.ConnectDB" %>
<%@page import="java.sql.*" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ViewAll</title>
<link rel="stylesheet" href="css/viewAllAccounts.css" />

</head>
<body>
		<table>
			<tr>
			<th>Account No.</th>
			<th>Account Holder's Name</th>
			<th>Balance</th>
			</tr>
			
			<%
			try{
				Connection con = ConnectDB.getConnect();
				PreparedStatement ps = con.prepareStatement("select * from accounts");
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
				}//while-end
			}//try-end	
			catch(Exception e)
			{
				e.printStackTrace();
			}
			%>
			
		 <a href="dashboard.html">Go to Dashboard</a>
		</table>
</body>
</html>