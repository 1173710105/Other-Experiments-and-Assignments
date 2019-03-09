package myservlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import mybean.*;
import java.sql.*;

/**
 * Servlet implementation class HandleRegister
 */
@WebServlet("/registerServlet")
public class HandleRegister extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HandleRegister()
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
		// String uri = "jdbc:mysql://127.0.0.1/shop?" + "user = root&password = &
		// charaterEncoding = UTF-8";
		Connection con;
		PreparedStatement sql;
		// request.setCharacterEncoding("UTF-8");
		Register userBean = new Register();// 创建Register对象userbean
		request.setAttribute("userBean", userBean);// 写到request里面，这样userBean周期为request
		request.setCharacterEncoding("ISO8859-1");
		//new String(request.getParameter("realname").getBytes("ISO8859-1"),"UTF-8").trim();
		String logname = new String(request.getParameter("logname").getBytes("ISO8859-1"),"UTF-8").trim();
		String password = new String(request.getParameter("password").getBytes("ISO8859-1"),"UTF-8").trim();
		String again_password = new String(request.getParameter("again_password").getBytes("ISO-8859-1"),"UTF-8").trim();
		String phone = new String(request.getParameter("phone").getBytes("ISO8859-1"),"UTF-8").trim();
		String address = new String(request.getParameter("address").getBytes("ISO8859-1"),"UTF-8").trim();
		String realname = new String(request.getParameter("realname").getBytes("ISO8859-1"),"UTF-8").trim();
		
		// 如实合法注册信息，则对注册信息进行处理
		boolean b = false;
		b = logname==null||logname.length()==0||password==null||password.length()==0||
			again_password==null||again_password.length()==0||phone==null||phone.length()==0||
			address==null||address.length()==0||realname==null||realname.length()==0;
		if(b)
		{
			userBean.setBackNews("注册信息不完整，注册失败");
			RequestDispatcher dispatcher = request.getRequestDispatcher("inputRegisterMess.jsp");// 两次密码不一样，转跳到注册页面，重新注册
			dispatcher.forward(request, response);
			return;
		}
		
		if (!password.equals(again_password))
		{
			userBean.setBackNews("两次密码不同，注册失败");
			RequestDispatcher dispatcher = request.getRequestDispatcher("inputRegisterMess.jsp");// 两次密码不一样，转跳到注册页面，重新注册
			dispatcher.forward(request, response);
			return;
		}

		String backNews = "";
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "123");
			String insertCondition = "INSERT INTO user VALUES (?,?,?,?,?)";
			sql = con.prepareStatement(insertCondition);

			// 往数据库user表中写入用户注册信息
			sql.setString(1, logname);
			sql.setString(2, password);
			sql.setString(3, phone);
			sql.setString(4, address);
			sql.setString(5, realname);
			int m = sql.executeUpdate();// 判断注册信息写入数据库是否成功
			if (m != 0)
			{
				// 注册成功，userbean里面写入用户注册信息
				backNews = "注册成功";
				userBean.setBackNews(backNews);
				userBean.setLogname(logname);
				userBean.setPassword(password);
				userBean.setPhone(phone);
				userBean.setAddress(address);
				userBean.setRealname(realname);
				request.setAttribute("userBean", userBean);// 更新request中的userBean
			}
			con.close();
		} catch (SQLException exp)
		{
			// 错误提示，昵称已经被占用
			backNews = "该会员名已被使用，请您更换名字";
			userBean.setBackNews(backNews);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("inputRegisterMess.jsp");
		dispatcher.forward(request,response);
		/*response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<br>" + logname);
		out.println("<br>" + password);
		out.println("<br>" + phone);
		out.println("<br>" + address);
		out.println("<br>" + realname);
		System.out.println(address);
		System.out.println(realname);*/
	}

}
