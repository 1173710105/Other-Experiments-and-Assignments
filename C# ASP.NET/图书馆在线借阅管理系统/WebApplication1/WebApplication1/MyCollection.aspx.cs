using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MySql.Data.MySqlClient;
namespace WebApplication1
{
    public partial class MyCollection : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            HttpCookie cookie = Request.Cookies["userInfo1"];
            if (cookie == null)
            {
                Response.Redirect("Login.aspx");
            }
            String sql = "select * from mycollection where userid='" + cookie.Values["UserName"].ToString()+"'";
            updateGridView(sql);
        }

        private void updateGridView(String command)
        {
            try
            {
                string conn = "Database=librarydatabase;Data Source=127.0.0.1;User Id=root;Password=123;pooling=false;CharSet=utf8;port=3306";
                MySqlDataAdapter da = new MySqlDataAdapter(command, conn);
                DataSet ds = new DataSet();
                da.Fill(ds);
                DataView view = new DataView(ds.Tables[0]);
                view.Sort = "bookid ASC";
                GridView1.DataSource = view;
                GridView1.DataBind();
            }
            catch (MySqlException e1)
            {
                Response.Write("<script>alert('" + e1 + "')</script>");
            }
        }

        protected void GridView1_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if(e.CommandName=="borrow")
            {
                int i = Convert.ToInt32(e.CommandArgument);
                Session["BookID"] = GridView1.Rows[i].Cells[0].Text;
                Response.Redirect("SearchBook.aspx");
            }
            if(e.CommandName == "delete")
            {
                String sql;
                try
                {
                    int i = Convert.ToInt32(e.CommandArgument);
                    MySqlConnection connection = new MySqlConnection("Database=librarydatabase;Data Source=127.0.0.1;UserId=root;Password=123;pooling= true;CharSet=UTF8;port=3306");
                    MySqlCommand command;
                    connection.Open();
                    sql = "delete from mycollection where collectionid ='" + GridView1.Rows[i].Cells[1].Text + "'";
                    command = new MySqlCommand(sql, connection);
                    command.ExecuteNonQuery();
                    connection.Close();
                }
                catch(MySqlException e1)
                { }
                HttpCookie cookie = Request.Cookies["userInfo1"];
                sql = "select * from mycollection where userid='" + cookie.Values["UserName"].ToString() + "'";
                updateGridView(sql);
            }
        }

        protected void GridView1_RowDeleting(object sender, GridViewDeleteEventArgs e)
        {
            HttpCookie cookie = Request.Cookies["userInfo1"];
            String sql = "select * from mycollection where userid='" + cookie.Values["UserName"].ToString() + "'";
            updateGridView(sql);
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Response.Redirect("index.aspx");
        }
    }
}