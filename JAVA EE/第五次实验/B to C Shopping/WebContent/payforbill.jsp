<%@page import="mybean.Login"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.txt" %>
<%@ page import = "java.sql.*" %>
<jsp:useBean id= "loginBean" class="mybean.Login" scope="session" /><!-- 使用loginBean 寿命为session -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结账</title>
</head>
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
%>

<%
	String ispay = request.getParameter("pay");
	if ((ispay == null || ispay.length() == 0 || !ispay.equals("pay")))
	{
		out.print("<h4>无效操作</h4><br>");
		out.print("<h4>请前往订单页,点击前往结算</h4><br>");
		out.print("<a href = lookOrderForm.jsp>点击返回订单页</a>");
		return;
	}
%>
<body background=image/back.jpg>
<div align="center">
<%
	out.print("<table border = 2>");
	out.print("<tr>");
	out.print("<th width = 50>" + "订单号");
	out.print("<th width = 100>" + "信息");
	out.print("<th width = 50>" + "价格");
	out.print("</tr>");
	Connection con;
	Statement sql;
	ResultSet rs;
	try
	{
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "123");
		sql = con.createStatement();
		//在orderform里面按用户名查询用户订单信息
		String cdn = "select id,mess,sum from orderform where logname = '" + loginBean.getLogname()+"'and tag = 'non'";
		rs = sql.executeQuery(cdn);
		String money;
		float sum = 0;
		while (rs.next())
		{
			out.print("<tr>");
			out.print("<td>" + rs.getString(1) + "</td>");
			out.print("<td>" + rs.getString(2) + "</td>");
			money = rs.getString(3);
			out.print("<td>" +money+"</td>");
			sum += Float.parseFloat(money);
		}
		out.print("</table>");
		out.print("<h4>总金额"+sum+"</h4>");
	}
	catch(SQLException e)
	{
		out.print(e);
	}
%>
<%
	out.print("<h4>扫码付<h4>");
	out.print("<h3>支付宝&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
	"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"&nbsp;&nbsp;&nbsp;&nbsp;"+
			"微信</h3>");
	String pic = "<img src = 'image/" + "支付宝.jpg" + "'width = 200 height =200></img>";//定义一张图片
	out.print(pic+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
	pic = "<img src = 'image/" + "微信.jpg" + "'width = 200 height =200></img>";//定义一张图片
	out.print(pic);
%>
<form action = "express.jsp" method = "post">
<input type="hidden" name="comfirm" value ="pay"> 
<input type="submit" name="pay" value="完成支付">
</form>

</div>
</body>
</html>