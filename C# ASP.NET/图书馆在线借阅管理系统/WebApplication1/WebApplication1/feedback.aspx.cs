using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MySql.Data.MySqlClient;
namespace WebApplication1
{
    public partial class feedback : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            HttpCookie cookie = Request.Cookies["userInfo1"];
            if (cookie == null)
            {
                Response.Redirect("Login.aspx");
                return;
            }
        }
        protected void Button1_Click1(object sender, EventArgs e)
        {
            Response.Redirect("index.aspx");
        }

        protected void Button2_Click(object sender, EventArgs e)
        {
            String message = this.TextBox1.Text.Trim();

            if(message.Length==0)
            {
                Response.Write("<script>alert('反馈信息不能为空,请重新填写')</script>");
                return;
            }
            MySqlConnection connection = new MySqlConnection("Database=librarydatabase;Data Source=127.0.0.1;UserId=root;Password=123;pooling= true;CharSet=UTF8;port=3306");
            MySqlCommand command;
            connection.Open();
            String rdn = getRandomNumber();
            HttpCookie cookie = Request.Cookies["userInfo1"];
            DateTime datetime = DateTime.Now;
            String sql1 = "insert into feedback (num,date,message,tag,userid) values('"+rdn+"','"+datetime+"','"+message+"','未处理','"+cookie.Values["UserName"]+"')";
            command = new MySqlCommand(sql1, connection);
            command.ExecuteNonQuery();
            connection.Close();
            Response.Write("<script>alert('提交信息成功，感激你的反馈，我们会根据你反馈信息进行相关的处理')</script>");
        }

        private String getRandomNumber()
        {
            String rdn = "";
            Random rd = new Random();
            for (int i = 0; i < 3; i++)
            {
                rdn += Convert.ToChar('A' + rd.Next(0, 26));
            }
            for (int i = 3; i < 6; i++)
            {
                rdn += "" + rd.Next(0, 10);
            }
            return rdn;
        }
    }
}