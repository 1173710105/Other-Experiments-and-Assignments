package myservlet;

import javax.servlet.annotation.WebServlet;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import mybean.Login;
import java.util.*;


/**
 * Servlet implementation class PutGoodsToCar
 */
@WebServlet("/putGoodsServlet")
public class PutGoodsToCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PutGoodsToCar() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		request.setCharacterEncoding("ISO8859-1");
		String goods = new String(request.getParameter("goods").getBytes("ISO8859-1"),"UTF-8").trim();//获取货物信息
		//System.out.println(goods);
		Login loginBean = null;
		HttpSession session = request.getSession(true);
		try
		{
			loginBean = (Login) session.getAttribute("loginBean");//从session里面过去Login对象loginbean
			//判断是否是非法操作，防止越权访问
			boolean b = loginBean.getLogname()==null || loginBean.getLogname().length() == 0;
			if (b)
			{
				response.sendRedirect("login.jsp");//从定向到登录界面
				return;
			}
			
			LinkedList<String> car =loginBean.getCar();//从用户里面获取购物车数组列表
			car.add(goods);//往购物车里面添加货物
			speakSomeMess(request,response,goods);//显示货物信息
		}
		catch(Exception e)
		{
			response.sendRedirect("login.jsp");
		}
	}
	
	//向页面显示购物车中的一件货物的信息
	public void speakSomeMess(HttpServletRequest request,HttpServletResponse response,String goods)
	{
		response.setContentType("text/html;charset = GB2312");
		try
		{
			int index = goods.lastIndexOf("#");//获取#的索引值，并保存到index中
			String showGoods = goods.substring(0,index);//取出商品信息（0~#索引值的子串取出）
			PrintWriter out = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");
			out.println("<html><body background = image/back.jpg >");
			out.println("<title>放入购物车<title>");
			out.println("<h4>"+showGoods+"已经加入购物车<br></h4>");
			out.println("查看购物车或返回浏览商品<br>");
			out.println("<a href = lookGoods.jsp>继续浏览商品 </a>");
			out.println("<a href = lookShoppingCar.jsp>查看购物车</a>");
			out.println("</body></html>");
		}
		catch (Exception e) 
		{
		}
	}
}
