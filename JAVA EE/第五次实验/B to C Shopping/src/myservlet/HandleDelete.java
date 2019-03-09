package myservlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import mybean.Login;


/**
 * Servlet implementation class HandleDelete
 */
@WebServlet("/deleteServlet")
public class HandleDelete extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleDelete()
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		request.setCharacterEncoding("ISO8859-1");
		String delete = new String(request.getParameter("delete").getBytes("ISO8859-1"),"UTF-8").trim();
		Login loginBean = null;
		HttpSession session = request.getSession(true);
		try
		{
			loginBean = (Login) session.getAttribute("loginBean");
			boolean b = loginBean.getLogname() == null || loginBean.getLogname().length() == 0;
			// 判断是否是非法操作，防止越权访问
			if (b)
			{
				response.sendRedirect("login.jsp");// 重定向到登录界面
			}
			LinkedList<String> car = loginBean.getCar();// 从用户里面获取购物车数组列表
			car.remove(delete);// 删除购物车数组列表里面索引值为delete的物品信息
		} catch (Exception e)
		{
			response.sendRedirect("login.jsp");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("lookShoppingCar.jsp");
		dispatcher.forward(request, response);
	}

}
