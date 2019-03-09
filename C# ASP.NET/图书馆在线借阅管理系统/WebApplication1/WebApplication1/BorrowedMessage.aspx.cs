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
    public partial class BorrowedMessage : System.Web.UI.Page
    {
        
        protected void Page_Load(object sender, EventArgs e)
        {
            HttpCookie cookie = Request.Cookies["userInfo1"];
            if (cookie == null)
            {
                Response.Redirect("Login.aspx");
            }
            updateGridView(cookie.Values["UserName"].ToString());
        }
        

        protected void GridView1_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName == "ContinueBorrow")
            {
                int i = Convert.ToInt32(e.CommandArgument);
                string IsReturn = GridView1.Rows[i].Cells[7].Text;
                if (IsReturn.Equals("已还"))
                {
                    Response.Write("<script>alert('该图书已还，不需要续借')</script>");
                }
                else
                {
                    DateTime DataDeadline = Convert.ToDateTime(GridView1.Rows[i].Cells[5].Text);
                    DateTime DataBorrow = Convert.ToDateTime(GridView1.Rows[i].Cells[4].Text);
                    int diff = DateSubtract(DataBorrow, DataDeadline);
                    if(diff+30>65)
                    {
                        Response.Write("<script>alert('该图书续借次数达上限，不能再续借')</script>");
                    }
                    else
                    {
                        DataDeadline  = DataDeadline.AddMonths(1);
                        MySqlConnection connection  = new MySqlConnection("Database=librarydatabase;Data Source=127.0.0.1;UserId=root;Password=123;pooling= true;CharSet=UTF8;port=3306");
                        MySqlCommand command;
                        try
                        {
                            connection.Open();
                            String sql1 = "UPDATE userborrowbook SET deadline = '"+DataDeadline+"'WHERE(id='" + GridView1.Rows[i].Cells[8].Text +"' and datalend ='"+ GridView1.Rows[i].Cells[4].Text + "' and num = '"+ GridView1.Rows[i].Cells[1].Text + "')";
                            command = new MySqlCommand(sql1, connection);
                            command.ExecuteNonQuery();
                            connection.Close();
                            GridView1.Rows[i].Cells[5].Text = DataDeadline.ToString();
                            Response.Write("<script>alert('续借成功')</script>");
                        }
                        catch(MySqlException e1)
                        {
                            connection.Close();
                            Response.Write("<script>alert('"+e1+"')</script>");
                        }
                    }
                }
                
            }
            if(e.CommandName == "Return")
            {
                int i = Convert.ToInt32(e.CommandArgument);
                string IsReturned = GridView1.Rows[i].Cells[7].Text;
                if(IsReturned=="已还")
                {
                    Response.Write("<script>alert('该书已归还，无需再次操作')</script>");
                }
                else if(IsReturned=="未还")
                {
                    DateTime dateTime = DateTime.Now;
                    MySqlConnection connection = new MySqlConnection("Database=librarydatabase;Data Source=127.0.0.1;UserId=root;Password=123;pooling= true;CharSet=UTF8;port=3306");
                    MySqlCommand command;
                    MySqlDataReader reader = null;
                    try
                    {
                        //更新借阅表
                        connection.Open();
                        String sql1 = "UPDATE userborrowbook SET datareturn = '" + dateTime+ "', returned = '已还' WHERE(id='" + GridView1.Rows[i].Cells[8].Text + "' and datalend ='" + GridView1.Rows[i].Cells[4].Text + "' and returned ='未还' and num ='"+ GridView1.Rows[i].Cells[1].Text + "')";
                        command = new MySqlCommand(sql1, connection);
                        command.ExecuteNonQuery();
                        GridView1.Rows[i].Cells[6].Text = dateTime.ToString();
                        GridView1.Rows[i].Cells[7].Text = "已还";
                        Response.Write("<script>alert('图书已归还')</script>");

                        //更新读者个人信息表
                        String[] quantity = new String[3];
                        command = new MySqlCommand("select borrowed,returned,leftsum from usertable where id = '" + GridView1.Rows[i].Cells[8].Text + "'", connection);
                        reader = command.ExecuteReader();
                        if (reader.Read())
                        {
                            quantity[0] = (Convert.ToInt32(reader["borrowed"]) - 1).ToString();
                            quantity[1] = (Convert.ToInt32(reader["returned"]) + 1).ToString();
                            quantity[2] = (Convert.ToInt32(reader["leftsum"]) + 1).ToString();

                        }
                        reader.Close();
                        String sql2 = "UPDATE usertable SET borrowed = '"+quantity[0]+"',returned = '" + quantity[1] + "',leftsum ='" + quantity[2] + "' where id = '" + GridView1.Rows[i].Cells[8].Text + "'";
                        command = new MySqlCommand(sql2, connection);
                        command.ExecuteNonQuery();

                        //更新图书表
                        command = new MySqlCommand("select count from booktable where id = '" + GridView1.Rows[i].Cells[0].Text + "'", connection);
                        reader = command.ExecuteReader();
                        String count = null;
                        if (reader.Read())
                        {
                            count = (Convert.ToInt32(reader["count"]) + 1).ToString();
                        }
                        reader.Close();
                        String sql3 = "UPDATE booktable SET count='"+count+"'  where id = '" + GridView1.Rows[i].Cells[0].Text + "'";
                        command = new MySqlCommand(sql3, connection);
                        command.ExecuteNonQuery();

                        connection.Close();
                    }
                    catch (MySqlException e1)
                    {
                        connection.Close();
                        Response.Write("<script>alert('" + e1 + "')</script>");

                    }
                }

            }
        }
     
        private int DateSubtract(DateTime startTime, DateTime endTime)
        {
            int dateDiff = 0;
            try
            {
                TimeSpan ts = endTime - startTime;
                dateDiff = ts.Days;
            }
            catch
            {
            }
            return dateDiff;
        }
        private void updateGridView(String id)
        {
            try
            {
                string conn = "Database=librarydatabase;Data Source=127.0.0.1;User Id=root;Password=123;pooling=false;CharSet=utf8;port=3306";
                String command = "select * from userborrowbook where id ='" + id + "'";
                MySqlDataAdapter da = new MySqlDataAdapter(command, conn);
                DataSet ds = new DataSet();
                da.Fill(ds);
                DataView view = new DataView(ds.Tables[0]);
                view.Sort = "datalend DESC";
                GridView1.DataSource = view;
                if (!IsPostBack)
                {
                    GridView1.DataBind();
                }
            }
            catch (MySqlException e1)
            {
                Response.Write("<script>alert('" + e1 + "')</script>");
            }
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Response.Redirect("index.aspx");
        }
    }
}