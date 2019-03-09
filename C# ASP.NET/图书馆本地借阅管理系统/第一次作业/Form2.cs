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
    public partial class Form2 : Form
    {
        private MySqlConnection connection;
        private MySqlCommand command;
        private MySqlDataReader reader = null;
        Form form;//用于控制窗口1
        public Form2(MySqlConnection connection,object form)
        {
            InitializeComponent();
            this.connection = connection;//获取数据库连接
            this.form = (Form)form;
        }
        private void Form2_Load(object sender, EventArgs e)
        {
        }
        private void button1_Click(object sender, EventArgs e)
        {
            //设置显示大小
            this.Width = 1060;
            this.Height = 550;
            UserMessageTable.Width = 1060;
            UserMessageTable.Height = 550;
            //控件显示
            MenuPanel.Visible = false;
            this.ToolForUserTablePanel.Visible = true;
            clearUserMessageTable(UserMessageTable);//清空datagridview，即清空显示用户数据列表
            UserMessageTable.BringToFront();//置于顶层
            UserMessageTable.Visible = true;//显示列表
            String[] HeaderText = { "用户ID", "用户姓名", "用户类型", "借书总数", "已还书数目", "未还书数目", "信用情况", "借书上限", "目前还能借书", "欠款数目" };
            setTableHeaderText(HeaderText);//设置表格列数，写表头
            //写表格中的数据
            try
            {
                String[] message = new String[10];
                command = new MySqlCommand("select * from usertable ", connection);
                reader = command.ExecuteReader();
                while (reader.Read())
                {
                    for (int i = 0; i < 10; i++)
                    {
                        message[i] = reader.GetString(i).ToString();

                    }
                    WriteMessageToTable(message);
                }
                reader.Close();
            }
            catch (MySqlException ex)
            {
                MessageBox.Show("警告" + ex);
            }

        }

        /*private void HandleMenu(Boolean IsHid)
        {
            if(IsHid)
            {
                this.button1.Visible = false;
                this.button2.Visible = false;
                this.button3.Visible = false;
            }
            else
            {
                this.button1.Visible = true;
                this.button2.Visible = true;
                this.button3.Visible = true;
            }
        }*/

        //设置表格列数，写表头
        private void setTableHeaderText(String[] HeaderText = null)
        {
            UserMessageTable.ColumnCount = HeaderText.Length;
            for (int i = 0; i < HeaderText.Length; i++)
            {
                UserMessageTable.Columns[i].HeaderText = HeaderText[i];
            }
        }
        //写表格中的数据
        private void WriteMessageToTable(String[] message)
        {
            UserMessageTable.Rows.Add(message);
        }
        //修改数据
        private void MotifyButton_Click(object sender, EventArgs e)
        {
            command = new MySqlCommand("select id from usertable where id = '" + this.MotifyTextBox.Text + "'", connection);
            reader = command.ExecuteReader();
            if (reader.Read())
            {
                reader.Close();
                Form4 form = new Form4("修改用户信息",this.MotifyTextBox.Text,true,connection);
                form.ShowDialog();
                button1_Click(sender, e);
            }
            else
            {
                reader.Close();
                MessageBox.Show("ID不存在，请再次输入");
                this.MotifyTextBox.Focus();
            }
        }
        //退出
        private void ExitButton_Click(object sender, EventArgs e)
        {
            form.Visible = true;
            this.Close();
        }
        //搜索
        private void SearchButton_Click(object sender, EventArgs e)
        {
            String search = this.SearchtextBox.Text;
            try
            {


                //判断用户以何种方式查询
                Boolean isExistName = false;
                Boolean isExistID = false;
                command = new MySqlCommand("select name from usertable where name = '" + search + "'", connection);
                reader = command.ExecuteReader();
                isExistName = reader.Read();
                reader.Close();
                command = new MySqlCommand("select id from usertable where id = '" + search + "'", connection);
                reader = command.ExecuteReader();
                isExistID = reader.Read();
                reader.Close();

                //按照查询方式进行各项操作
                if (isExistName)
                {
                    //MessageBox.Show("存在");
                    clearUserMessageTable(UserMessageTable);//清空datagridview，即清空显示用户数据列表
                    String[] HeaderText = { "用户ID", "用户姓名", "用户类型", "借书总数", "已还书数目", "未还书数目", "信用情况", "借书上限", "目前还能借书", "欠款数目" };
                    setTableHeaderText(HeaderText);//设置表格列数，写表头
                    String[] message = new String[10];
                    command = new MySqlCommand("select * from usertable where name = '" + search + "'", connection);
                    reader = command.ExecuteReader();
                    while (reader.Read())
                    {
                        for (int i = 0; i < 10; i++)
                        {
                            message[i] = reader.GetString(i).ToString();

                        }
                        WriteMessageToTable(message);
                    }
                    reader.Close();
                }

                else if (isExistID)
                {
                    clearUserMessageTable(UserMessageTable);//清空datagridview，即清空显示用户数据列表
                    String[] HeaderText = { "用户ID", "用户姓名", "用户类型", "借书总数", "已还书数目", "未还书数目", "信用情况", "借书上限", "目前还能借书", "欠款数目" };
                    setTableHeaderText(HeaderText);//设置表格列数，写表头
                    String[] message = new String[10];
                    command = new MySqlCommand("select * from usertable where id = '" + search + "'", connection);
                    reader = command.ExecuteReader();
                    while (reader.Read())
                    {
                        for (int i = 0; i < 10; i++)
                        {
                            message[i] = reader.GetString(i).ToString();

                        }
                        WriteMessageToTable(message);
                    }
                    reader.Close();
                }
                else if ((search.ToLower()).Equals("all"))
                {
                    clearUserMessageTable(UserMessageTable);//清空datagridview，即清空显示用户数据列表
                    button1_Click(sender, e);
                }
                else
                {
                    MessageBox.Show("该用户不存在");
                }

            }
            catch (MySqlException ex)
            {
                MessageBox.Show("警告" + ex);
            }

        }

        private void clearUserMessageTable(DataGridView UserMessageTable)
        {
            if (UserMessageTable.DataSource != null)
            {

                DataTable dt = (DataTable)UserMessageTable.DataSource;

                dt.Rows.Clear();

                UserMessageTable.DataSource = dt;

            }
            else
            {
                UserMessageTable.Rows.Clear();

            }

        }

        private void DeleteButton_Click(object sender, EventArgs e)
        {
            String delete = this.textBox1.Text;
            try
            {
                //判断用户以何种方式删除
                Boolean isExistName = false;
                Boolean isExistID = false;
                command = new MySqlCommand("select name from usertable where name = '" + delete + "'", connection);
                reader = command.ExecuteReader();
                isExistName = reader.Read();
                reader.Close();
                command = new MySqlCommand("select id from usertable where id = '" + delete + "'", connection);
                reader = command.ExecuteReader();
                isExistID = reader.Read();
                reader.Close();

                //按照删除方式进行各项操作
                if (isExistName)
                {
                    //MessageBox.Show("存在");
                    command = new MySqlCommand("delete  from usertable where name = '" + delete + "'", connection);
                    command.ExecuteNonQuery();
                    button1_Click(sender, e);
                    MessageBox.Show("成功删除名为 " + delete + " 的用户");
                }

                else if (isExistID)
                {

                    command = new MySqlCommand("delete from usertable where id = '" + delete + "'", connection);
                    command.ExecuteNonQuery();
                    button1_Click(sender, e);
                    MessageBox.Show("成功删除ID为 " + delete + " 的用户");
                }

                else
                {
                    MessageBox.Show("该用户不存在");
                }
            }
            catch (MySqlException ex)
            {
                MessageBox.Show("错误警告：" + ex);
            }
        }

        private void AddButton_Click(object sender, EventArgs e)
        {
            Form4 form = new Form4("增加用户信息",null,false,connection);
            form.ShowDialog();
            button1_Click(sender,e);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            Form5 form = new Form5(connection);
            form.ShowDialog();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            this.Width = 709;
            this.Height = 450;
            MenuPanel.Visible = true;
            this.ToolForUserTablePanel.Visible = false;
            UserMessageTable.Visible = false;//显示列表
        }

        private void Form2_FormClosed(object sender, FormClosedEventArgs e)
        {
            form.Visible = true;
        }

        private void button3_Click(object sender, EventArgs e)
        {

        }
    }
}
