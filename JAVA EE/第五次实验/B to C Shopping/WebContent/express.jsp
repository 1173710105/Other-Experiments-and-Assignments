<%@page import="java.util.concurrent.CountDownLatch"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.txt" %>
<jsp:useBean id= "loginBean" class="mybean.Login" scope="session" /><!-- 使用loginBean 寿命为session -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>完成支付</title>
</head>
<%!
	boolean bb = false;
	String countnum = null;
%>
<%
	//判断是否是非法操作，防止越权访问
	if (loginBean == null)
	{
		response.sendRedirect("login.jsp");//重定向到登录页面
		return;
	} 
	else
	{
		//判断是否是非法操作，防止越权访问
		boolean b = loginBean.getLogname() == null || loginBean.getLogname().length() == 0;
		if (b)
		{
			response.sendRedirect("login.jsp");//重定向到登录页面
			return;
		}
	}
	String pay = request.getParameter("comfirm");
	if(pay==null||pay.length()==0)
	{
		out.print("<h4>请前往订单页,点击前往结算</h4><br>");
		out.print("<a href = lookOrderForm.jsp>点击返回订单页</a>");
		return;
	}
%>
<%
	//生成订单号
	int count = (int) (Math.random() * 90000) + 10000;
	int alpha = (int) (Math.random() * 26);
	char c1 = (char) ('A' + alpha);
	alpha = (int) (Math.random() * 26);
	char c2 = (char) ('A' + alpha);
	alpha = (int) (Math.random() * 26);
	char c3 = (char) ('A' + alpha);
	countnum = c1 + "" + c2 + "" + c3 + "" + Integer.toString(count);
%>
<%
try
{
	// 将订单信息写入数据库的orderform表中
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "123");
	Statement statement = con.createStatement();
	String command = "update orderform set tag = 'paid',expressnum='"+countnum+"' where logname='"+loginBean.getLogname()+"' and tag = 'non'";
	statement.executeUpdate(command);
	con.close();
	bb = true;
}
catch(SQLException e)
{
	out.print(e);
}
%>
<%
	if(bb)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "123");
			Statement statement = con.createStatement();
			String command = "select * from user where logname = '"+loginBean.getLogname()+"'";
			ResultSet resultset = statement.executeQuery(command);
			while(resultset.next())
			{
				out.print("<div align='center'>");
				out.print("<h2>交易完成</h2>");
				out.print("<h3>客户信息</h3>");
				out.print("<h4>姓名："+resultset.getString("realname")+"&nbsp;&nbsp;");
				out.print("电话："+resultset.getString("phone")+"&nbsp;&nbsp;");
				out.print("地址："+resultset.getString("address")+"<br></h4>");
				out.print("<h3>快递信息</h3>");
				out.print("<h4>逆风快递真诚为你服务<br>");
				out.print("快递订单号："+countnum+"&nbsp;&nbsp;");
				out.print("快递小哥：老王&nbsp;&nbsp;");
				out.print("电话：1213812580<br>");
				out.print("届时请留意查收</h4><br>");
				out.print("<h5><a href = index.jsp>返回主页</a></h5>");
			}
			con.close();
		}
		catch(SQLException ee)
		{
			out.print(ee);
		}
	}
%>
<body>
</body>
</html>