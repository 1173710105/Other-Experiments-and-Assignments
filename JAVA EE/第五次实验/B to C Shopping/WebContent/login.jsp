<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id = "loginBean" class = "mybean.Login" scope = "session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file = "head.txt" %>
<title>登录</title>
</head>
<BODY background = image/back.jpg>
<font size = 2>
<div align="center">
<table border = 2> 
<tr><th>登录</th></tr>
<FORM action = "loginServlet" Method = "post">
<tr><td>登录名称：<Input type = text name = "logname"></td></tr>
<tr><td>登录密码：<Input type = password name = "password"></td></tr>
</table>
<Input type = submit name = "g" value = "提交">
</form></div>
<div align = "center">
登录反馈信息：<jsp:getProperty name = "loginBean" property = "backNews"/>
<br>登录名称：<jsp:getProperty name = "loginBean" property = "logname"/>
</div></font>
</BODY></HTML>