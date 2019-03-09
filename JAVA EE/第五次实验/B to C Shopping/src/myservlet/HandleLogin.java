package myservlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import mybean.*;

import java.sql.*;

/**
 * Servlet implementation class HandleLogin
 */
@WebServlet("/loginServlet")
public class HandleLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleLogin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException
    {
    	// TODO Auto-generated method stub
    	super.init(config);
    	try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception e){}
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con;
		Statement sql;
		request.setCharacterEncoding("ISO8859-1");
		String logname = new String(request.getParameter("logname").getBytes("ISO8859-1"),"UTF-8").trim();
		String password = new String(request.getParameter("password").getBytes("ISO8859-1"),"UTF-8").trim();
		//String uri = "jdbc:mysql//127.0.0.1/shop?" + "user = root&password =
		//&characterEncoding = UTF-8";
		//System.out.println(logname);
		//System.out.println(password);
		boolean boo = (logname.length()>0)&&(password.length()>0);//判断密码和用户名输入是否合法
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop","root","123");
			String condition = "select * from user where logname = '" + logname +"'and password ='" + password + "'";
			sql = con.createStatement();
			if(boo)
			{
				ResultSet rs = sql.executeQuery(condition);
				boolean m = rs.next();//判断是否有名字和密码都对应上的用户
				//System.out.println(m);
				if(m == true)
				{
					success(request, response, logname, password);//登录成功，储存用户信息
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);
				}
				else
				{
					String backNews = "您输入的用户名不存在，或密码不匹配";
					fail(request, response, logname, password,backNews);
				}
			}
			else{
				String backNews = "请输入用户名和密码";
				fail(request, response, logname, password,backNews);
			}
			con.close();
		}
		catch (SQLException exp)
		{
			String backNews = "" + exp;
			fail(request, response, logname, password,backNews);
		}
	}
	
	public void success (HttpServletRequest request, HttpServletResponse response, String logname, String password)
	{
		Login loginBean = null;
		HttpSession session = request.getSession(true);
		try
		{
			loginBean = (Login)session.getAttribute("loginBean");
			if(loginBean == null)
			{
				loginBean = new Login();
				//loginBean =(Login)session.getAttribute("loginBean");
			}
			String name = loginBean.getLogname();//通过判断logname是否与Session的loginBean对象中的logname相等与否，来判断用户是否之前已经登录
			if(name.equals(logname))
			{
				//已经登录过的用户再次登录，就不必要做过多处理
				loginBean.setBackNews(logname + "已经登录了");
				//loginBean.setLogname(logname);
			}
			else
			{
				//用户的登录首次登录，要写Session
				loginBean.setBackNews(logname + "登陆成功");
				loginBean.setLogname(logname);
				session.setAttribute("loginBean", loginBean);//把对象loginBean放进Session里面，对象里面还有购物车 ，后面能用到
			}
		}
		catch (Exception ee)
		{
			loginBean = new Login();
			loginBean.setBackNews(logname + "登陆成功");
			loginBean.setLogname(logname);
			session.setAttribute("loginBean", loginBean);
		}
	}
	
	public void fail (HttpServletRequest request, HttpServletResponse response, String logname, String password,String backNews)
	{
		response.setContentType("text/html;charset = UTF-8");
		try
		{
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<h4>登录反馈结果:登录失败<br></h4>");
			out.println("<h4>密码或者账号错误<br></h4>");
			out.println("登录名称："+logname+"<br>");
			out.println("登录密码："+password+"<br>");
			out.println("返回登录页面或主页<br>");
			out.println("<a href = login.jsp>登录页面</a>");
			out.println("<br><a href = index.jsp>主页</a>");
			out.println("</body></html>");
		}
		catch (IOException exp){}
	}
}
