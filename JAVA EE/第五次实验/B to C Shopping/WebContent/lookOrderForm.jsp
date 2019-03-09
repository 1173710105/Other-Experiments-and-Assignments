<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="loginBean" class="mybean.Login" scope="session" /><!-- 使用loginBean 寿命为session -->
<%@ page import="java.sql.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="head.txt"%>
<title>查看订单</title>
</head>
<body background=image/back.jpg>
<div align="center">
<%!
	String submit = null;
	Connection con;
	Statement sql;
	ResultSet rs;
	boolean IsEmpty = false;
%>
<%
		//判断是否是非法操作，防止越权访问
		if (loginBean == null)
		{
			response.sendRedirect("login.jsp");//重定向到主页
		} else
		{
			//判断是否是非法操作，防止越权访问
			boolean b = loginBean.getLogname() == null || loginBean.getLogname().length() == 0;
			if (b)
			{
				response.sendRedirect("login.jsp");//重定向到主页
			}
		}
%>
<form action="" method = "post" >
<input type = "submit" name = "submit" value = "查看当前订单">&nbsp;&nbsp;&nbsp;&nbsp;
<input type = "submit" name = "submit" value = "查看历史订单">
</form>


<% 
		
		submit = request.getParameter("submit");
		try
		{	
			byte bb[]=submit.getBytes("iso-8859-1");
			submit=new String(bb);
			//System.out.print(submit);
		}
		catch(Exception e)
		{
			//System.out.print(e);
		}
		if(submit==null||submit.length()==0)
		{
			submit="";
		}
		if(submit.equals("查看当前订单"))
		{
			float sum = 0f;//结算用的，总金额

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e){}
		
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "123");
			sql = con.createStatement();
			//在orderform里面按用户名查询用户订单信息
			String cdn = "select id,mess,sum from orderform where logname = '" + loginBean.getLogname()+"'and tag = 'non'";
			rs = sql.executeQuery(cdn);
			out.print("<h3>当前订单</h3>");
  			out.print("<table border = 2>");
			out.print("<tr>");
			out.print("<th width = 50>" + "订单号");
			out.print("<th width = 100>" + "信息");
			out.print("<th width = 50>" + "价格");
			out.print("<th width = 50>" + "删除订单");
			out.print("</tr>");
	
			String CountNumber = null;
			IsEmpty = rs.next();//查看是否有订单
			while (rs.next())
			{
				CountNumber = rs.getString(1);
				out.print("<tr>");
				out.print("<td>" + CountNumber + "</td>");
				out.print("<td>" + rs.getString(2) + "</td>");
				out.print("<td>" + rs.getString(3)+ "</td>");
%>

		<!-- 提交删除表单 -->
		<td><form action="" method="post">
				<input type="hidden" name='deletecount' value=<%= CountNumber%>>
				<Input type="submit" name="g" value="删除订单">
			</form></td>


		<%
				out.print("<tr>");
			}
				
			out.print("</table>");
		%>
		<% if(IsEmpty) {%>
		<!--  提交结账表单 -->
		<form action="payforbill.jsp" method="post">
			<input type="hidden" name="pay" value ="pay"> 
			<input type="submit" name="g" value="前往结算">
		</form>
		<%} 
		else{
		%>
		<form action="error.jsp" method="post">
			<input type="hidden" name="error" value ="lookOrderForm"> 
			<input type="submit" name="g" value="前往结算">
		</form>
		
		<%}
			rs.close();
			sql.close();
			con.close();
		} catch (Exception e)
		{
			out.print(e);
		}
		%>
		
		
		<%
			String DeleteCount = null;
			DeleteCount = request.getParameter("deletecount");
			if (DeleteCount != null && DeleteCount.length() > 0)
			{
				try
				{
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "123");
					sql = con.createStatement();
					String command = "delete from orderform where id ='" + DeleteCount + "'";
					sql.executeUpdate(command);
					sql.close();
					con.close();
					response.sendRedirect("lookOrderForm.jsp");//刷新网页
				} catch (Exception e)
				{
					out.print(e);
				}
			}
		}
		
		else if (submit.equals("查看历史订单"))
		{
			try
			{
				float sum = 0;
				float money;
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "123");
				sql = con.createStatement();
				//在orderform里面按用户名查询用户订单信息
				String cdn = "select id,logname,mess,sum,expressnum from orderform where logname = '" + loginBean.getLogname()+"'and tag = 'paid'";
				rs = sql.executeQuery(cdn);
				out.print("<h3>历史订单</h3>");
	  			out.print("<table border = 2>");
				out.print("<tr>");
				out.print("<th width = 50>" + "订单号");
				out.print("<th width = 50>" + "用户名");
				out.print("<th width = 100>" + "信息");
				out.print("<th width = 50>" + "价格");
				out.print("<th width = 50>" + "快递单号");
				out.print("</tr>");
				while(rs.next())
				{
					out.print("<tr>");
					out.print("<td>" + rs.getString(1) + "</td>");
					out.print("<td>" + rs.getString(2) + "</td>");
					out.print("<td>" + rs.getString(3) + "</td>");
					money = Float.parseFloat(rs.getString(4));
					out.print("<td>" + money + "</td>");
					out.print("<td>" + rs.getString(5) + "</td>");
					out.print("</tr>");
					sum += money;
				}
				out.print("<h3>总花费金额"+sum+"元</h3>");
			} 
			catch (SQLException ee)
			{
				System.out.print(ee);
			}
		}
		%>
	</div>
</body>
</html>