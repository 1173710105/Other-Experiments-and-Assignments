using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication1
{
    public partial class index : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            HttpCookie cookie = Request.Cookies["userInfo1"];
            if (cookie == null)
            { 
                Response.Redirect("Login.aspx");
            }
            else
            {
                string UserName = cookie.Values["UserName"];
                String PassWord = cookie.Values["PassWord"];
                Session["ID"] = UserName;
            }
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Response.Redirect("BorrowedMessage.aspx");
        }

        protected void Button6_Click(object sender, EventArgs e)
        {
            Response.Redirect("SelfMessage.aspx");
        }

        protected void Button3_Click(object sender, EventArgs e)
        {
            Response.Redirect("MyCollection.aspx");
        }

        protected void Button2_Click(object sender, EventArgs e)
        {
            Response.Redirect("SearchBook.aspx");
        }

        protected void Button4_Click(object sender, EventArgs e)
        {
            Response.Redirect("feedback.aspx");
        }

        protected void Button8_Click(object sender, EventArgs e)
        {
            HttpCookie cookie = Request.Cookies["userInfo1"];
            cookie.Expires = DateTime.Now.AddDays(-1);
            Response.Cookies.Add(cookie);
            ClientScript.RegisterStartupScript(typeof(string), "print", "<script>alert('注销成功');window.location.href ='Login.aspx'</script>");
        }
    }
}