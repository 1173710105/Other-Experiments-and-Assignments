<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>浏览商品</title>
<HEAD><%@ include file = "head.txt" %></HEAD>
</head>
<BODY background = image/back.jpg><font size = 2>
<div align = "center">
<% 
	try
	{ 
		Class.forName("com.mysql.jdbc.Driver");
	}
	catch (Exception e){}
	Connection con;
	Statement sql;
	ResultSet rs;
	try{
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop","root","123");	 
	 sql = con.createStatement();
	 rs = sql.executeQuery("SELECT * FROM classify ");
	 //选择列表
	 out.print("<form action = 'queryServlet' method = 'post'>");
	 out.print("<select name = 'fenleiNumber'>");//选择列表，选择后将选择信息提交到到queryServlet
	 while(rs.next()){
	 int id = rs.getInt(1);
	 //System.out.print(id);
	 String name = rs.getString(2);
	 out.print("<option value = " + id + ">" + name + "</option>");
	 }
	 out.print("</select>");
	 
	 out.print("<input type = 'submit' value = '提交'>");
	 out.print("</form>");
	 con.close();
	}
	catch (SQLException e){
		out.print(e);
	}
%>
</div></font>
</BODY></HTML>