using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MySql.Data.MySqlClient;

namespace 第一次作业
{

    public partial class Form1 : Form
    {
        private MySqlConnection connection;
        private MySqlCommand command;
        private MySqlDataReader reader = null;
        public Form1()
        {
            InitializeComponent();
        }
        private void Form1_Load(object sender, EventArgs e)
        {
            //连接数据库
            //string connString = "server=localhost;database=librarydatabase;uid=root;pwd=123";
            //connection = new MySqlConnection(connString);
            //MySqlConnection conn = new MySqlConnection(connString);

            connection = new MySqlConnection("Database=librarydatabase;Data Source=127.0.0.1;UserId=root;Password=123;pooling= true;CharSet=UTF8;port=3306");
            try
            {
                connection.Open();
                //MessageBox.Show("连接成功！", "测试结果");
            }
            catch (MySqlException ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
        //登录
        private void Login_Click(object sender, EventArgs e)
        {
            String username = this.username.Text;
            String password = this.password.Text;
            Boolean isExist = false;
            try
            {
                command = new MySqlCommand("select * from admin where count ='" + username + "' and password = '" + password + "'", connection);
                reader = command.ExecuteReader();
                isExist = reader.Read();
                reader.Close();
            }
            catch (MySqlException ex)
            {
                MessageBox.Show("警告" + ex);
            }
            if (isExist)
            {
                //MessageBox.Show("登陆成功", "提示信息");
                RemoveFirst();
            }
            else
            {
                this.feedback.Visible = true;
                this.feedback.Text = "登录失败，账号或密码错误";
            }
        }

        private void RemoveFirst()//删除登录界面控件,显示第二界面控件
        {
            this.Controls.Remove(this.Login);
            this.Controls.Remove(this.welcome);
            this.Controls.Remove(this.username);
            this.Controls.Remove(this.password);
            this.Controls.Remove(this.label1);
            this.Controls.Remove(this.label2);
            this.Controls.Remove(this.feedback);
            this.handlebook.Visible = true;
            this.handleuser.Visible = true;
            this.exit.Visible = true;
        }
        ~Form1()
        {
            //reader.Close();
            connection.Close();
        }

        private void exit_Click(object sender, EventArgs e)
        {
            this.connection.Close();
            this.Close();
        }

        private void handleuser_Click(object sender, EventArgs e)//处理用户信息
        {
            Form2 f = new Form2(connection,this);
            this.Visible = false;
            f.ShowDialog();
        }

        private void handlebook_Click(object sender, EventArgs e)//处理图书信息
        {
            Form3 f = new Form3(connection,this);
            this.Visible = false;
            f.ShowDialog();
        }

        private void Form1_FormClosed(object sender, FormClosedEventArgs e)
        {
            this.connection.Close();
        }
    }
}
