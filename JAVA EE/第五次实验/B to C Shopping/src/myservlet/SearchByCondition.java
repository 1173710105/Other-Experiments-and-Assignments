package myservlet;

import javax.servlet.annotation.WebServlet;
import com.sun.rowset.*;

import mybean.DataByPage;

import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class SearchByCondition
 */
@WebServlet("/searchByConditionServlet")
public class SearchByCondition extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	CachedRowSetImpl rowSet = null;// 定义一个结果缓冲集

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchByCondition()
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
		}
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
		String searchMess = new String(request.getParameter("searchMess").getBytes("ISO8859-1"),"UTF-8");// 获取用户输入的搜索信息
		String radioMess = new String(request.getParameter("radio").getBytes("ISO8859-1"),"UTF-8");// 获取用户选择的搜索方式
		
		//System.out.println(searchMess);
		//System.out.println(radioMess);
		
		// 判断是否符合查询条件
		if (searchMess == null || searchMess.length() == 0)
		{
			fail(request, response, "没有查询信息，无法查询");
			return;
		}
		String condition = "";
		
		//按商品标签搜索
		if (radioMess.equals("cosmetic_number"))
		{
			condition = "SELECT * FROM cosmeticform where cosmetic_number ='" + searchMess + "'";
		} 
		
		//按商品名称搜索，可模糊搜索
		else if (radioMess.equals("cosmetic_name"))
		{
			// 模糊搜索，like ‘% %’
			condition = "SELECT * FROM cosmeticform where cosmetic_name LIKE '%" + searchMess + "%'";
		} 
		
		//按商品价格搜索
		else if (radioMess.equals("cosmetic_price"))
		{
			double max = 0, min = 0;
			String regex = "[^0123456789.]";// 写正则表达式
			String[] priceMess = searchMess.split(regex);// 用正则表达分割价格字符串，将非数字的字符全去掉
			if (priceMess.length == 1)// 只输入一个价格
			{
				max = min = Double.parseDouble(priceMess[0]);
			} else if (priceMess.length == 2)// 输入了两个价格，分割成最大价格和最小价格
			{
				min = Double.parseDouble(priceMess[0]);
				max = Double.parseDouble(priceMess[1]);

				// 避免防用户输入格式有误造成最大最小值颠倒的情况，交换最大最小值
				if (max < min)
				{
					double t = max;
					max = min;
					min = t;
				}
			} else
			{
				fail(request, response, "输入的价格格式有错误");
				return;
			}
			// 写sql语句
			condition = "SELECT * FROM cosmeticform where " + "cosmetic_price <= " + max + " AND cosmetic_price>="+ min;
		}

		HttpSession session = request.getSession(true);// 创建Session
		Connection con = null;
		DataByPage dataBean = null;
		try
		{
			dataBean = (DataByPage) session.getAttribute("dataBean");// 获取一个DataByPage对象dataBean，用于分页显示

			if (dataBean == null)
			{
				dataBean = new DataByPage(); // 创建Javabean对象
				session.setAttribute("dataBean", dataBean);
			}
		} catch (Exception exp)
		{
			dataBean = new DataByPage();
			session.setAttribute("dataBean", dataBean);
		}
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop", "root", "123");

			// 返回一个滚动的结果集，数据库变化时，结果集同时变化，但结果集里面的数据改变了不影响数据库里面的数据
			Statement sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sql.executeQuery(condition);

			rowSet = new CachedRowSetImpl(); // 创建结果缓冲行集对象
			rowSet.populate(rs);// 将结果集里面的数据赋值给结果缓冲行集
			dataBean.setRowSet(rowSet); // 结果缓冲行集数据存储在dataBean中
			con.close(); // 关闭连接
		} catch (SQLException exp)
		{
		}
		response.sendRedirect("byPageShow.jsp");// 重定向到byPageShow.jsp
	}

	// 提示查询失败，并告诉失败原因
	public void fail(HttpServletRequest request, HttpServletResponse response, String backNews)
	{
		try
		{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			out.println("<h2>" + backNews + "</h2>");
			out.println("返回：");
			out.println("<a href =searchGoods.jsp>查询化妆品</a>");
			out.println("</body></html>");
		} catch (IOException exp)
		{
		}
	}

}
