<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mybean.DataByPage" %>
<%@ page import="com.sun.rowset.*" %>
<jsp:useBean id="dataBean" class="mybean.DataByPage" scope="session"/>
<!-- dataBean的寿命是Session 在会话开始之后关闭之前，dataBean不会释放所占据的内容，都是同一个dataBean，保持一致性-->
<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<%@ include file = "head.txt" %>
	<title>浏览商品</title>
</HEAD>
<HTML><Body background=image/back.jpg>
<center>
<BR>当前显示的内容是：
  <table border = 2 >
  <tr>
    <th>商品图片</th>
    <th>商品标识号</th>
    <th>商品名称</th>
    <th>商品制造商</th>
    <th>商品价格</th>
    <th>查看详情</th>
    <td><font color=blue>添加到购物车</font></td>
  </tr>
<jsp:setProperty name="dataBean" property="pageSize" param="pageSize"/>
<jsp:setProperty name="dataBean" property="currentPage" param="currentPage"/>
<%    
      CachedRowSetImpl rowSet = dataBean.getRowSet();//从dataBean里面读取一个CachedRowSetImpl对象，该对象已在Servlet里面写好了
      //离线缓冲区，减少数据库压力
      if(rowSet==null)
      {
         out.print("没有任何查询信息，无法浏览");
         return;  
      }

      rowSet.last(); 
      
      int totalRecord=rowSet.getRow();//记录一共有多少行，多少条记录
      
      out.println("全部记录数"+totalRecord); //全部记录数
      
      int pageSize=dataBean.getPageSize();  //每页显示的记录数
      
      int totalPages = dataBean.getTotalPages();//从dataBean里面读取一个总页数

      //设置总页数
      if(totalRecord%pageSize==0)
           totalPages = totalRecord/pageSize;
      else
           totalPages = totalRecord/pageSize+1;
      
      //将没页显示的行数，总页数写到dataBean里面
      dataBean.setPageSize(pageSize);
      dataBean.setTotalPages(totalPages);
      
      if(totalPages>=1) //判断有没有消息，有消息总页数大于一
      	{
         if(dataBean.getCurrentPage()<1)
              dataBean.setCurrentPage(dataBean.getTotalPages());//将不和里的页数强制转为最后一页，防止出错
         
         if(dataBean.getCurrentPage()>dataBean.getTotalPages())//当前页数大于总页数，返回第一页
              dataBean.setCurrentPage(1);

         int index=(dataBean.getCurrentPage()-1)*pageSize+1;//定位数据，确定当前页应该显示哪几条信息

         rowSet.absolute(index);  //查询位置移动到currentPage页起始位置
         
         boolean boo = true;

         for(int i=1;i<=pageSize&&boo;i++) 
         {
            String number=rowSet.getString(1);
            String name=rowSet.getString(2);
            String maker=rowSet.getString(3);
            String price=rowSet.getString(4);
            String picture = rowSet.getString(6)+".jpg";
            String pic = "<img src = 'image/"+picture+"'width = 100 height =80></img>";//定义一张图片
            String goods ="("+number+","+name+","+maker+","+price+")#"+price;//便于购物车计算价格,尾缀上"#价格值"
            goods = goods.replaceAll("\\p{Blank}","");//去掉goods中的空格或制表符  字符
            String button="<form  action='putGoodsServlet' method = 'post'>"+
                     "<input type ='hidden' name='goods' value= "+goods+">"+ //设置数据隐藏个域，将商品信息隐藏起来，发送到putGoodsServlet（进行加入购物车的处理）,根据隐藏域获得商品信息
                     "<input type ='submit'  value='放入购物车' ></form>";
            
            String detail="<form  action='showDetail.jsp' method = 'post'>"+
                     "<input type ='hidden' name='detail' value= "+number+">"+//设置数据隐藏个域,发送到showDetail.jsp，在数数据库里面按number查找相应的数据信息
                     "<input type ='submit'  value='查看细节' ></form>";

            //蒋欣一表格的形式打印出来
            out.print("<tr>");
            out.print("<td>"+pic+"</td>");
            out.print("<td>"+number+"</td>");
            out.print("<td>"+name+"</td>");
            out.print("<td>"+maker+"</td>");
            out.print("<td>"+price+"</td>");
            out.print("<td>"+detail+"</td>");
            out.print("<td>"+button+"</td>");
            out.print("</tr>");

            boo=rowSet.next();//数据集里的下一行是否还有数据
         }
     }
%>
 </table>
 <BR>每页最多显示<jsp:getProperty name="dataBean" property="pageSize"/>条信息
 <BR>当前显示第<Font color=blue>
     <jsp:getProperty name="dataBean" property="currentPage"/>
   </Font>页,共有
   <Font color=blue><jsp:getProperty name="dataBean" property="totalPages"/>
   </Font>页。
<Table>
  <tr>
  	<td><FORM action="" method=post>
          <Input type=hidden name="currentPage" value= "<%=dataBean.getCurrentPage()-1 %>">
           <Input type=submit name="g" value="上一页"></FORM></td><!--  按一次 ，通过隐藏域中的Value实现减一操作-->
    <td><FORM action="" method=post>
          <Input type=hidden name="currentPage"
           value="<%=dataBean.getCurrentPage()+1 %>">
          <Input type=submit name="g" value="下一页"></FORM></td><!--  按一次 ，通过隐藏域中的Value实现加一操作-->
 </tr>

 <tr>
 	<td> <FORM action="" method=post>
          每页显示<Input type=text name="pageSize" value =1 size=3>
          条记录<Input type=submit name="g" value="确定"></FORM></td><!--设置每页显示商品信息条数 -->
    <td> <FORM action="" method=post>
           输入页码：<Input type=text name="currentPage" size=2 ><!-- 直接按用户输入的也是显示 -->
           <Input type=submit name="g" value="提交"></FORM></td>
</tr>
</Table>
</Center>
</BODY></HTML>