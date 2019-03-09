<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="loginBean" class="mybean.Login" scope="session" /><!-- 使用loginBean 寿命为session -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误页</title>
</head>
<body>
<%  
	//判断是否是非法操作，防止越权访问
	if(loginBean==null)
	{
        response.sendRedirect("login.jsp");//重定向到登录页面
        return;
    }
    else 
    {
		//判断是否是非法操作，防止越权访问
       boolean b =loginBean.getLogname()==null||loginBean.getLogname().length()==0;
       if(b)
       {  
    	    response.sendRedirect("login.jsp");//重定向到登录页面
       		return;
       }
    }
%>
	<%
		String command = request.getParameter("error");
		try
		{
			byte bb[] = command.getBytes("iso-8859-1");
			command = new String(bb);
			//System.out.print(command);
		} catch (Exception e)
		{
			//System.out.print(e);
		}
		if (command == null || command.length() == 0)
		{
			command = "";
		}
		if(command.equals("lookOrderForm"))
		{
			out.print("<h4>订单为空，无法支付</h4><br>");
			out.print("点击返回&nbsp;<a href= lookOrderForm.jsp>查看订单<a>");
			return;
		}
	%>
</body>
</html>