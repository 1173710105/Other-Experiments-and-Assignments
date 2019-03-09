package myservlet;

import java.io.*;
import com.sun.rowset.*;
import mybean.DataByPage;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class QueryAllRecord
 */
@WebServlet("/queryServlet")
public class QueryAllRecord extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	CachedRowSetImpl rowSet = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public QueryAllRecord()
	{
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
		} catch (Exception e)
		{
			// TODO: handle exception
		}
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
		request.setCharacterEncoding("ISO8859-1");
		String idNumber = new String(request.getParameter("fenleiNumber").getBytes("ISO8859-1"),"UTF-8");// 获取分类number
		
		if (idNumber == null)
		{
			idNumber = "0";
		}
		
		int id = Integer.parseInt(idNumber);
		HttpSession session = request.getSession(true);
		Connection con = null;
		DataByPage dataBean = null;//定义了一个DataByPage的对象dataBean
		
		try
		{
			dataBean = (DataByPage) session.getAttribute("dataBean");// 从Session里面获取一个databean对象
			if (dataBean == null)// 判断这个对象是否存在
			{
				dataBean = new DataByPage();
				session.setAttribute("dataBean", dataBean);// 往Session里面写入一个databean对象
			}

		} catch (Exception e)
		{
			// TODO: handle exception
			dataBean = new DataByPage();
			session.setAttribute("dataBean", dataBean);// 往Session里面写入一个databean对象
		}
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "123");
			Statement sql = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sql.executeQuery("Select * from cosmeticform where id =" + id);
			rowSet = new CachedRowSetImpl();// new 一个 缓冲行集 对象

			// 关键了这一步
			rowSet.populate(rs);// 根据ResultSet对象生成CachedRowSet类型的对象,并赋值给rowSet
			dataBean.setRowSet(rowSet);// 往dataBean里面写rowSet
			con.close();// 关闭数据库连接
		} catch (Exception e)
		{
		}
		response.sendRedirect("byPageShow.jsp");// 重定向到byPageShow.jsp
	}
}
