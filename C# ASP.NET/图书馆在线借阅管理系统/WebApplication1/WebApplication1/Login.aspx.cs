using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MySql.Data.MySqlClient;
namespace WebApplication1
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            HttpCookie cookie = Request.Cookies["userInfo1"];
            if (cookie != null)
            {
                Response.Redirect("index.aspx");
            }
        }

        protected void Login1_Authenticate(object sender, AuthenticateEventArgs e)
        {
            bool Authenticated = false;
            Authenticated = SiteLevelCustomAuthenticationMethod(Login1.UserName, Login1.Password);
            e.Authenticated = Authenticated;
            if (Authenticated == true)
            {
                HttpCookie cookie = new HttpCookie("userInfo1");
                cookie.Values["UserName"] = Login1.UserName;
                cookie.Values["Password"] = Login1.Password;
                //cookie.Expires = DateTime.MaxValue;
                cookie.Expires = System.DateTime.Now.AddDays(1);//设置过期时间  1小时
                Response.Cookies.Add(cookie);
                Session["ID"] = Login1.UserName;
                Response.Redirect("index.aspx");
            }
        }
        private bool SiteLevelCustomAuthenticationMethod(string UserName, string Password)
        {
            bool boolReturnValue = false;
            // Insert code that implements a site-specific custom 
            // authentication method here.
            // This example implementation always returns false.
            string strConnection = "Database = librarydatabase; Data Source = 127.0.0.1; UserId = root; Password = 123; pooling = true; CharSet = UTF8; port = 3306";
            MySqlConnection Connection = new MySqlConnection(strConnection);
            String strmysql = "Select * From usertable";
            MySqlCommand command = new MySqlCommand(strmysql, Connection);
            MySqlDataReader Dr;
            Connection.Open();
            Dr = command.ExecuteReader();
            while (Dr.Read())
            {
                if ((UserName == Dr["id"].ToString()) & (Password == Dr["password"].ToString()))
                {
                    boolReturnValue = true;
                }
            }
            Dr.Close();
            return boolReturnValue;
        }

        protected void LoginButton_Click(object sender, EventArgs e)
        {

        }

        protected void LoginButton_Click1(object sender, EventArgs e)
        {
            AuthenticateEventArgs ee = new AuthenticateEventArgs();
        }
    }
}