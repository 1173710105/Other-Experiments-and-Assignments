package myservlet;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import mybean.Login;

import java.sql.*;

/**
 * Servlet implementation class HandleBuyGoods
 */
@WebServlet("/buyServlet") // 处理用户在购物车里面生成订单的这一选项
public class HandleBuyGoods extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleBuyGoods()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(ServletConfig config) throws ServletException
	{
		// TODO Auto-generated method stub
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		Login loginBean = null;
		HttpSession session = request.getSession(true);
		try
		{
			loginBean = (Login) session.getAttribute("loginBean");// 从session里面过去Login对象loginbean
			
			// 判断是否是非法操作，防止越权访问
			if(loginBean==null)
			{
				response.sendRedirect("login.jsp");// 从定向到登录界面
				return;
			}
			
			// 判断是否是非法操作，防止越权访问
			boolean b = loginBean.getLogname() == null || loginBean.getLogname().length() == 0;
			if (b)
			{
				response.sendRedirect("login.jsp");// 从定向到登录界面
				return;
			}
		} 
		catch (Exception e)
		{
			response.sendRedirect("login.jsp");// 从定向到登录界面
			return ;
		}

		request.setCharacterEncoding("ISO8859-1");
		String buyGoodsMess =new String(request.getParameter("buy").getBytes("ISO8859-1"),"UTF-8").trim();// 获取订单信息
		
		if (buyGoodsMess == null || buyGoodsMess.length() == 0)
		{
			fail(request, response, "购物车没有物品，无法生成订单");
			return;
		}
		
		String price =new String(request.getParameter("price").getBytes("ISO8859-1"),"UTF-8").trim();
		if (price == null || price.length() == 0)
		{
			fail(request, response, "没有计算价格和，无法生成订单");
			return;
		}

		float sum = Float.parseFloat(price);
		try
		{
			// 将订单信息写入数据库的orderform表中
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "123");
			PreparedStatement sql;// 预处理语句，帮数据库减压
			String insertCondition = "insert into orderform values(?,?,?,?,?,?)";
			sql = con.prepareStatement(insertCondition);
			sql.setInt(1, 0);// 数据库中的表orderform第一列的id可以自增
			sql.setString(2, loginBean.getLogname());
			sql.setString(3, buyGoodsMess);
			sql.setFloat(4, sum);
			sql.setString(5, "non");
			sql.setString(6, "non");
			sql.executeUpdate();// 更新数据库的orderform表
			LinkedList<String> car = loginBean.getCar();
			car.clear();// 生成订单成功，清空购物车
			success(request, response, "生成订单成功");
			con.close();
		} catch (Exception e)
		{
			fail(request, response, "生成订单失败"+e);
		}
	}
	
	//生成订单失败的时候，显示的提示信息
		public void fail(HttpServletRequest request, HttpServletResponse response, String backNews)
		{
			response.setContentType("text/html;charset = UTF-8");
			try
			{
				PrintWriter out = response.getWriter();
				out.println("<html><body background = image/back.jpg >");
				out.println("<h4>" + backNews + "</h4>");
				out.println("返回主页:");
				out.println("<a href = index.jsp>主页</a>");
				out.println("</body></html>");
			} 
			catch (Exception e)
			{
				// TODO: handle exception
			}
		}
		
		//生成订单成功的时候，显示的提示信息
		public void success(ServletRequest request,ServletResponse response,String backNews)
		{
			response.setContentType("text/html;charset = UTF-8");
			try
			{
				PrintWriter out = response.getWriter();
				out.println("<html><body background = image/back.jpg >");
	         	out.println("<h4>"+backNews+"</h4>") ;
	         	out.println("返回主页<br>");
	         	out.println("<br><a href =index.jsp>主页</a>");
	         	out.println("查看订单<br>");
	         	out.println("<br><a href =lookOrderForm.jsp>查看订单</a>");
	         	out.println("</body></html>");
			} 
			catch (Exception e)
			{
				// TODO: handle exception
			}
		}
	
	
}
