package mybean;

public class Register
{
	private String logname = "";//注册昵称
	private String password ="";//用户密码
	private String phone = "";//电话
	private String address = "";//地址
	private String realname = "";//真实姓名
	private String backNews = "请输入信息";//注册信息返回提示

	public void setLogname (String logname){
		this.logname = logname;
	}
	public String getLogname (){
		return logname;
	}
	public void setPhone (String phone){
		this.phone = phone;
	}
	public String getPhone (){
		return phone;
	}
	public void setAddress (String address){
		this.address = address;
	}
	public String getAddress (){
		return address;
	}
	public void setRealname (String realname){
		this.realname = realname;
	}
	public String getRealname (){
		return realname;
	}
	public void setBackNews (String backNews){
		this.backNews= backNews;
	}
	public String getBackNews (){
		return backNews;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
}