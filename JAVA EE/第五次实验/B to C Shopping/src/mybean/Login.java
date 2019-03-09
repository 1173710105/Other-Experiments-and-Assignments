package mybean;
import java.util.*;

public class Login{
	String logname = "";//记录会员名字
	String backNews = "未登录";//记录登录反馈信息
	LinkedList<String> car;//购物车
	public Login ()
	{
		car = new LinkedList<String>();
	}
	public void setLogname (String logname){
		this.logname = logname;
	}
	public String getLogname (){
		return logname;
	}
	public void setBackNews (String s){
		backNews = s;
	}
	public String getBackNews (){
		return backNews;
	}
	public LinkedList<String> getCar (){
		return car;
	}
}