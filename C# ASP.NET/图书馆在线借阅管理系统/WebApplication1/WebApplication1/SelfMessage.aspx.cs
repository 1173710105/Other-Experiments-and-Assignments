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
    public partial class SelfMessage : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            HttpCookie cookie = Request.Cookies["userInfo1"];
            if (cookie == null)
            {
                Response.Redirect("Login.aspx");
                return;
            }
            try
            { 
                String id = cookie.Values["UserName"].ToString();
                string conn = "Database=librarydatabase;Data Source=127.0.0.1;User Id=root;Password=123;pooling=false;CharSet=utf8;port=3306";
                String command = "select * from usertable where id ='" + id + "'";
                //Label1.Text =id +  command;
                MySqlDataAdapter da = new MySqlDataAdapter(command, conn);
                DataSet ds = new DataSet();
                da.Fill(ds);
                DataView view = new DataView(ds.Tables[0]);
                view.Sort = "id DESC";
                GridView1.DataSource = view;
                
                
                //GridView1.Columns[0].HeaderText = "";
                /*
                GridView1.Columns[0].HeaderText = "账号";
                GridView1.Columns[1].HeaderText = "密码";
                GridView1.Columns[2].HeaderText = "用户名";
                GridView1.Columns[3].HeaderText = "用户类型";
                GridView1.Columns[4].HeaderText = "借书总次数";
                GridView1.Columns[5].HeaderText = "未还书数目";
                GridView1.Columns[6].HeaderText = "已还书数目";
                GridView1.Columns[7].HeaderText = "信用记录";
                GridView1.Columns[8].HeaderText = "借书总数上限";
                GridView1.Columns[9].HeaderText = "现在能借书数目";
                GridView1.Columns[10].HeaderText = "欠款情况";*/
                if (!IsPostBack)
                {
                    GridView1.DataBind();
                }
            }
            catch (Exception e1)
            {
            }
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            Response.Redirect("index.aspx");
        }
        
        protected void GridView1_RowCommand(object sender, GridViewCommandEventArgs e)
        {
            if(e.CommandName == "upgrace")
            {
                int i = Convert.ToInt32(e.CommandArgument);
                String IsMemberShip = GridView1.Rows[i].Cells[3].Text;
                if(IsMemberShip=="会员")
                {
                    Response.Write("<script>alert('已经开通会员，不需要再次开通')</script>");
                }
                else
                {
                    Session["Membership"] = true;
                    Session["MembershipFee"] = "15";
                    Response.Redirect("payform.aspx");
                }
            }
            if(e.CommandName == "payfee")
            {
                int i = Convert.ToInt32(e.CommandArgument);
                String IsNil = GridView1.Rows[i].Cells[10].Text;
                if (IsNil == "0")
                {
                    Response.Write("<script>alert('现无欠费记录，无需缴纳罚款')</script>");
                }
                else
                {
                    Session["payfee"] = true;
                    Session["Fine"] = GridView1.Rows[i].Cells[10].Text;
                    Response.Redirect("payform.aspx");
                }
            }
        }
    }
}