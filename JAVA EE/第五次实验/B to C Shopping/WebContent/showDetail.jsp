<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="head.txt"%>
<%@ page import="java.sql.*"%>
<%@ page import="mybean.Login"%>
<jsp:useBean id="loginBean" class="mybean.Login" scope="session" /><!-- 寿命为session的会话 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品详情</title>
</head>
<body background=image/back.jpg>
<cneter> 
<%
	//判断是否是非法操作，防止越权访问
 	if (loginBean == null)
 	{
 		response.sendRedirect("login.jsp");//重定向到登录界面
 		return;
 	}
 	else
 	{
 		//判断是否是非法操作，防止越权访问
 		boolean b = loginBean.getLogname() == null || loginBean.getLogname().length() == 0;
 		if (b)
 		{
 			response.sendRedirect("login.jsp");//重定向到登录界面
 			return;
 		}
 	}
	request.setCharacterEncoding("ISO8859-1");
	String numberID = new String (request.getParameter("detail").getBytes("ISO8859-1"),"UTF-8").trim();
	//out.print("<th>产品号"+numberID);
	if(numberID==null)
	{
		out.print("没有产品号，无法查看细节");
		return;
	}
	Connection con;
	Statement sql;
	ResultSet rs;
	try
	{
		//out.print("<th>产品号"+numberID);
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "123");
		sql = con.createStatement();
		String command = " select * from cosmeticForm where cosmetic_number = '"+numberID+"'";
		rs = sql.executeQuery(command);
		//out.print("<th>产品号"+numberID);
		out.print("<div align='center'>");
		out.print("<table border = 2>");
		out.print("<tr>");
		out.print("<th>产品号");
		out.print("<th>名称");
		out.print("<th>制造商");
		out.print("<th>价格");
		out.print("<th><font color = green>放入购物车</font>");
		out.print("</tr>");
		String picture = "welcome.jpg";
		String detailMess = "";
		while(rs.next())//判断数据集里面是否有从数据库里面读出来的数据
		{
			String number = rs.getString(1);//商品ID号
			String name = rs.getString(2);//商品名字
			String maker = rs.getString(3);//商品制造厂商
			String price = rs.getString(4);//商品价格
			detailMess = rs.getString(5);//商品信息
			picture = rs.getString(6);//商品图片
			
			String goods = "("+number+","+name+","+maker+","+price+")#"+price;//便于购物车计算价格,尾缀上"#价格值"
			goods = goods.replaceAll("\\p{Blank}", "");//去空格和制表符
			
			String button = "<form action = 'putGoodsServlet' method ='post'>"+
			"<input type = 'hidden' name = 'goods' value ="+goods+">"+//设置数据隐藏个域，将商品信息隐藏起来，发送到putGoodsServlet（进行加入购物车的处理）,根据隐藏域获得商品信息
			"<input type = 'submit' value = '放入购物车'></form>";
			out.print("<tr>");
			out.print("<td>"+number+"</td>");
			out.print("<td>"+name+"</td>");
			out.print("<td>"+maker+"</td>");
			out.print("<td>"+price+"</td>");
			out.print("<td>"+button+"</td>");
			out.print("</tr>");
		}
		picture = picture+".jpg";
		out.print("</table>");
		out.print("产品详情:<br>");
		out.print("<div align = center>"+detailMess+"<div>");//显示详细信息
		String pic = "<img src = 'image/"+picture+"'width = 260 height =200></img>";//定义一张图片
		out.print(pic);//显示图片
		out.print("</div>");
		con.close();
	}
	catch(SQLException e)
	{	
		out.println(e);
	}
%>
</cneter>
</body>
</html>