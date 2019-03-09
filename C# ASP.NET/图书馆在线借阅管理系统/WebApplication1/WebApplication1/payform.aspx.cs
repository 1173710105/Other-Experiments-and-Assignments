using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MySql.Data.MySqlClient;

namespace WebApplication1
{
    public partial class payform : System.Web.UI.Page
    {   
        protected void Page_Load(object sender, EventArgs e)
        {
            HttpCookie cookie = Request.Cookies["userInfo1"];
            String IsValid = cookie.Values["UserName"].ToString();
            if (IsValid==null||IsValid.Length==0)
            {
                Response.Redirect("Login.aspx");
            }
            if (Convert.ToBoolean(Session["Membership"]))
            {
                this.Label3.Text = "开通会员：" + Session["MembershipFee"] + " 元";
            }
            if(Convert.ToBoolean(Session["payfee"]))
            {
                this.Label3.Text = "罚款金额：" + Session["Fine"] + " 元";
            }
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            HttpCookie cookie = Request.Cookies["userInfo1"];
            MySqlConnection connection = new MySqlConnection("Database=librarydatabase;Data Source=127.0.0.1;UserId=root;Password=123;pooling= true;CharSet=UTF8;port=3306");
            MySqlCommand command;
            if (Convert.ToBoolean(Session["Membership"]))
            {
                MySqlDataReader reader = null;
                try
                {
                    connection.Open();
                    command = new MySqlCommand("select * from usertable where id = '" + cookie.Values["UserName"].ToString() + "'", connection);
                    reader = command.ExecuteReader();
                    //String capacity = reader
                    int capacity = 0;
                    int leftsum = 0;
                    if (reader.Read())
                    {
                        capacity = Convert.ToInt32(reader.GetString(8)) + 5;
                        leftsum = Convert.ToInt32(reader.GetString(9)) + 5;
                    }
                    reader.Close();
                    String sql1 = "UPDATE usertable SET tag = '会员',capacity = '"+capacity+"',leftsum = '"+leftsum+"' WHERE(id='"+ cookie.Values["UserName"].ToString() + "')";
                    command = new MySqlCommand(sql1, connection);
                    command.ExecuteNonQuery();
                    //Response.Write("<script>('开通会员成功')</script>");
                    Session["Membership"] = false;
                    ClientScript.RegisterStartupScript(typeof(string), "print", "<script>alert('开通会员成功 ');window.location.href ='SelfMessage.aspx'</script>");
                    connection.Close();
                }
                catch (MySqlException e1)
                {
                    connection.Close();
                    Response.Write("<script>alert('" + e1 + "')</script>");
                }
            }
            if (Convert.ToBoolean(Session["payfee"]))
            {
                try
                {
                    connection.Open();
                    String sql1 = "UPDATE usertable SET own ='0' WHERE(id='" + cookie.Values["UserName"].ToString() + "')";
                    command = new MySqlCommand(sql1, connection);
                    command.ExecuteNonQuery();
                    //Response.Write("<script>alert('缴纳罚款成功')</script>");
                    Session["payfee"] = false;
                    ClientScript.RegisterStartupScript(typeof(string), "print", "<script>alert('缴纳罚款成功');window.location.href ='SelfMessage.aspx'</script>");
                    connection.Close();
                }
                catch(MySqlException e1)
                {

                }
            }
        }
    }
}