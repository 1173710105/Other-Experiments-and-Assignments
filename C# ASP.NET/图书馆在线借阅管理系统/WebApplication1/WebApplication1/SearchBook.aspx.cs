using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication1
{
    public partial class SearchBook : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            HttpCookie cookie = Request.Cookies["userInfo1"];
            if (cookie == null)
            {
                Response.Redirect("Login.aspx");
                return;
            }
            String sql;
            if (Session["BookID"]!=null&&Session["BookID"].ToString().Length!=0)
            {
                sql = "select * from booktable where id = '" + Session["BookID"].ToString() + "'";
                Session["BookID"] = null;
            }
            else
            {
                sql = "select * from booktable ";
            }
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
                view.Sort = "id ASC";
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
            MySqlConnection connection = new MySqlConnection("Database=librarydatabase;Data Source=127.0.0.1;UserId=root;Password=123;pooling= true;CharSet=UTF8;port=3306");
            MySqlCommand command;
            MySqlDataReader reader = null;
            HttpCookie cookie = Request.Cookies["userInfo1"];

            
            if (e.CommandName == "borrow")
            {
                connection.Open();

                //检查借书数量是否达到上限
                command = new MySqlCommand("select leftsum from usertable where id = '" + cookie.Values["UserName"].ToString() + "'", connection);
                reader = command.ExecuteReader();
                if (reader.Read())
                {
                    int leftsum = Convert.ToInt32(reader["leftsum"]);
                    if (leftsum <= 0)
                    {
                        Response.Write("<script>alert('你的借阅数量已经到达上限，请归还书籍以后再进行借阅')</script>");
                        reader.Close();
                        return;
                    }
                    reader.Close();
                }

                int i = Convert.ToInt32(e.CommandArgument);
                String IsEmpty = GridView1.Rows[i].Cells[5].Text;
                if (IsEmpty == "0")
                {
                    Response.Write("<script>alert('该书库存为空，无法借阅')</script>");
                }
                else
                {
                    String rdn = getRandomNumber();
                    String[] BookMessage = new String[3];
                    for (int j = 0; j < BookMessage.Length; j++)
                    {
                        BookMessage[j] = GridView1.Rows[i].Cells[j].Text;
                    }
                    DateTime datalend = DateTime.Now;
                    DateTime deadline = datalend.AddMonths(1);
                    String name = null;
                    try
                    {
                        command = new MySqlCommand("select name from usertable where id = '" +cookie.Values["UserName"].ToString() + "'", connection);
                        reader = command.ExecuteReader();
                        if (reader.Read())
                        {
                            name = reader["name"].ToString();
                        }
                        reader.Close();
                    }
                    catch (MySqlException e1){}

                    //更新读者借书信息
                    String sql1 = "insert into userborrowbook(bookid, num, name, message, datalend, deadline, returned, id, count) values('" + BookMessage[0] + "','" + rdn + "','" + BookMessage[1] + "','" + BookMessage[2] + "','" + datalend + "','" + deadline + "','未还','"+ cookie.Values["UserName"].ToString() + "','"+name+"')";
                    command = new MySqlCommand(sql1, connection);
                    command.ExecuteNonQuery();

                    //更新图书信息
                    String count = (Convert.ToInt32(IsEmpty) - 1).ToString();
                    String sql2 = "UPDATE booktable SET count = '"+count+"' where id = '"+ GridView1.Rows[i].Cells[0].Text+"'";
                    command = new MySqlCommand(sql2, connection);
                    command.ExecuteNonQuery();

                    //更新读这个人信息
                    String[] quantity = new String[3];
                    command = new MySqlCommand("select sum,borrowed,leftsum from usertable where id = '" + cookie.Values["UserName"].ToString() + "'", connection);
                    reader = command.ExecuteReader();
                    if (reader.Read())
                    {
                        quantity[0] = (Convert.ToInt32(reader["sum"])+1).ToString();
                        quantity[1] = (Convert.ToInt32(reader["borrowed"]) + 1).ToString();
                        quantity[2] = (Convert.ToInt32(reader["leftsum"]) - 1).ToString();
                    }
                    reader.Close();
                    String sql3 = "UPDATE usertable SET sum = '" + quantity[0] + "',borrowed ='"+quantity[1]+"',leftsum ='"+quantity[2]+"' where id = '" + cookie.Values["UserName"].ToString() + "'";
                    command = new MySqlCommand(sql3, connection);
                    command.ExecuteNonQuery();
                    
                    connection.Close();
                    Response.Write("<script>alert('借阅成功')</script>");
                    GridView1.Rows[i].Cells[5].Text = count ;
                }
            }
            
            if(e.CommandName == "collection")
            {
                connection.Open();

                int i = Convert.ToInt32(e.CommandArgument);
                String rdn = getRandomNumber();
                String[] BookMessage = new String[4];
                BookMessage[0] = GridView1.Rows[i].Cells[0].Text;
                BookMessage[1] = GridView1.Rows[i].Cells[1].Text;
                BookMessage[2] = GridView1.Rows[i].Cells[2].Text;
                BookMessage[3] = GridView1.Rows[i].Cells[6].Text;
                String sql1 = "insert into mycollection(bookid, collectionid, name, message, class, userid) values('" + BookMessage[0] + "','" + rdn + "','" + BookMessage[1] + "','" + BookMessage[2] +"','"+BookMessage[3]+"','"+cookie.Values["UserName"].ToString()+"')";
                command = new MySqlCommand(sql1, connection);
                command.ExecuteNonQuery();
                Response.Write("<script>alert('收藏成功')</script>");
                connection.Close();
            }
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

        protected void Button1_Click(object sender, EventArgs e)
        {
            Response.Redirect("index.aspx");
        }

        protected void Button2_Click(object sender, EventArgs e)
        {
            String method = DropDownList1.SelectedItem.Text;
            String BookClass = DropDownList2.SelectedItem.Text;
            String SearchText = TextBox1.Text.Trim();
            String sql = null;
            if(method=="搜索方式")
            {
                Response.Write("<script>alert('搜索信息不完整，操作无效')</script>");
                return;
            }
            if(method!="图书类别"&&SearchText.Length==0)
            {
                Response.Write("<script>alert('搜索信息不完整，操作无效')</script>");
                return;
            }
            if(method=="图书编号")
            {
                sql = "select * from booktable where (id='" + SearchText.Trim() + "')";
            }
            else if(method == "图书书名")
            {
                sql = "select * from booktable where ( name LIKE '%" + SearchText + "%')";
            }
            else if(method == "图书类别")
            {
                if(BookClass == "图书类别")
                {
                    Response.Write("<script>alert('请选择图书类别，完善搜索信息')</script>");
                }
                else if (BookClass == "所有")
                {
                    sql = "select * from booktable";
                }
                else if (BookClass == "其他")
                {
                    //sql = "select * from booktable where 'class' not in ('文学类','工具类','社科类')";
                    //volume_number not in (4000, 8000, 12000, 16000)
                    sql = "select * from booktable where ( class !='文学类' and class !='工具类' and class!='社科类')";
                }
                else
                {
                    sql = "select * from booktable where class = '"+ BookClass + "'";
                }
            }
            updateGridView(sql);
        }

        protected void GridView1_DataBinding(object sender, EventArgs e)
        {
            GridView1.DataBind();
        }
    }
}