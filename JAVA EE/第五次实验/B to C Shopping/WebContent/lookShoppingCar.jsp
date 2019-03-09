<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mybean.Login"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="loginBean" class="mybean.Login" scope="session"/><!-- 使用loginBean 寿命为session  -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="head.txt" %>
<title>查看购物车</title>
</head>
<BODY background=image/back.jpg><font size=2>
<div align="center">
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

    LinkedList<String> car =loginBean.getCar();//从loginBean里面获得购物车信息,然后放到双向列表中
    if(car==null)
      out.print("<h2> 购物车没有物品.</h2>");
    else 
    {
       Iterator<String> iterator=car.iterator();//生成迭代器
       StringBuffer buyGoods = new StringBuffer();//创建一个字符变量,用于存储商品信息
       int n=0;
       double priceSum =0;
       out.print("购物车中的物品：<table border=2>");
       while(iterator.hasNext())//迭代器中是否存在下一个元素 
       {
           String goods=iterator.next();
           String showGoods="";
           n++;
           
           //购车车物品的后缀是“#价格数字”，比如“商品价格10000 #10000”
           
           int index = goods.lastIndexOf("#");//获取#的索引值，并保存到index中
           
           if(index!=-1)//若价格标签（#价格数字）存在，则执行计价操作
           {
        	  //将 #价格数字 作为子串取出，并转化为double类型，计算到总价格里面
              priceSum += Double.parseDouble(goods.substring(index+1));
              showGoods = goods.substring(0,index);//取出商品信息（0~#索引值的子串取出）
           }
           
           buyGoods.append(n+":"+showGoods);//把商品信息添加到buyGoods中
           
           String del="<form  action='deleteServlet' method = 'post'>"+
                     "<input type ='hidden' name='delete' value= "+goods+">"+//通过隐藏域隐藏商品信息，然后发送出去
                     "<input type ='submit'  value='删除' ></form>";//删除购物车商品
          
           out.print("<tr><td>"+showGoods+"</td>");//打印每件商品的信息
           out.print("<td>"+del+"</td></tr>");
       }
       out.print("</table>");
       
       //把所有购物车商品的信息和价格通过隐藏域发送到buyServlet，生成订单处理
       String orderForm = "<form action='buyServlet' method='post'>"+
              " <input type ='hidden' name='buy' value= "+buyGoods+" >"+ 
              " <input type ='hidden' name='price' value= "+priceSum+" >"+           
              "<input type ='submit'  value='生成订单'></form>";
       out.print(orderForm); 
    } 
%>
</div></font>
</BODY></HTML>
