using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows.Forms;
using MySql.Data.MySqlClient;

namespace 第一次作业
{
    public partial class Form3 : Form
    {
        MySqlConnection connection;
        MySqlCommand command;
        DataSet dataset;
        MySqlDataAdapter adapter;
        BindingSource bindingsource = new BindingSource();
        MySqlDataReader reader;
        Form mainform;
        public Form3(MySqlConnection connection,Form mainform)
        {
            this.mainform = mainform;
            this.connection = connection;
            InitializeComponent();
        }

        private void tableLayoutPanel1_Paint(object sender, PaintEventArgs e)
        {

        }

        private void Form3_Load(object sender, EventArgs e)
        {
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.Width = 709;
            this.Height = 450;
            this.newBookPanel.Visible = true;
            this.ClassRadioButton11.Checked = true;
            this.newBookPanel.BringToFront();
            continueadd_Click(sender, e);
        }

        private void continueadd_Click(object sender, EventArgs e)
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
            this.IDnumberTextBox1.Text = rdn;
            this.booknameTextBox1.Text = "";
            this.bookmessageTextBox1.Text = "";
            this.publisherTextBox1.Text = "";
            this.priceTextBox1.Text = "";
            this.classTextBox1.Text = "";
            this.CountnumberTextBox1.Text = "";
            this.booknameTextBox1.Focus();
            this.continueadd.Enabled = false;
            this.warning11.Hide();
            this.warning12.Hide();
            this.warning13.Hide();
            this.warning14.Hide();
            this.warning15.Hide();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            if (this.warning11.Visible == true || this.warning12.Visible == true || this.warning13.Visible == true || this.warning14.Visible == true || this.warning15.Visible == true)
            {
                MessageBox.Show("信息有误，请重新填写","警告");
            }
            else
            {
                String id = this.IDnumberTextBox1.Text.Trim();
                String name = this.booknameTextBox1.Text.Trim();
                name = name.Replace("《", "");
                name = name.Replace("》", "");
                name = "《" + name.Trim() + "》";
                String message = this.bookmessageTextBox1.Text.Trim();
                String publisher = this.publisherTextBox1.Text.Trim();
                String price = this.priceTextBox1.Text.Trim();
                try
                {
                    double p = Convert.ToDouble(price);
                    price = "" + p;
                }
                catch (Exception ex)
                {
                    MessageBox.Show("价格输入格式有误！请重新输入");
                    this.priceTextBox1.Focus();
                    return;
                }
                String countnumber = this.CountnumberTextBox1.Text.Trim();
                try
                {
                    int i = Convert.ToInt32(countnumber);
                    countnumber = "" + i;
                }
                catch (Exception ex)
                {
                    MessageBox.Show("书本数目输入格式有误！请重新输入");
                    this.CountnumberTextBox1.Focus();
                    return;
                }
                String classify = "";
                if (ClassRadioButton11.Checked)
                    classify = "文学类";
                else if (ClassRadioButton12.Checked)
                    classify = "社科类";
                else if (ClassRadioButton13.Checked)
                    classify = "工具类";
                else if (ClassRadioButton14.Checked)
                    classify = this.classTextBox1.Text.Trim();
                String cmd;
                try
                {
                    cmd = "insert into booktable ( id,name,message,publisher,price,count,class) values ('" + id + "','" + name + "','" + message + "','" + publisher + "','" + price + "','" + countnumber + "','" + classify + "')";
                    command = new MySqlCommand(cmd, connection);
                    command.ExecuteNonQuery();
                }
                catch (MySqlException ex)
                {
                    MessageBox.Show("" + ex);
                }
                MessageBox.Show("添加信息成功");
                this.comfirm1.Enabled = false;
                this.continueadd.Enabled = true;
            }
        }

        private void radioButton4_CheckedChanged(object sender, EventArgs e)
        {
            this.classTextBox1.Enabled = true;
        }

        private void ClassRadioButton12_CheckedChanged(object sender, EventArgs e)
        {
            this.classTextBox1.Enabled = false;
        }

        private void ClassRadioButton13_CheckedChanged(object sender, EventArgs e)
        {
            this.classTextBox1.Enabled = false;
        }

        private void ClassRadioButton11_CheckedChanged(object sender, EventArgs e)
        {
            this.classTextBox1.Enabled = false;
        }

        private void priceTextBox1_TextChanged(object sender, EventArgs e)
        {
            String price = this.priceTextBox1.Text.Trim();
            if (price.Length == 0 || price.Equals(""))
            {
                this.warning14.Visible = true;
                this.warning14.Text = "不能为空，请输入价钱";
                this.comfirm1.Enabled = false;
            }
            else if (Regex.IsMatch(price, @"[^0123456789.]"))
            {
                this.warning14.Visible = true;
                this.warning14.Text = "不能为非数字";
                this.comfirm1.Enabled = false;
            }
            else
            {
                this.warning14.Visible = false;
                this.comfirm1.Enabled = true;
            }
        }

        private void booknameTextBox1_TextChanged(object sender, EventArgs e)
        {
            if (this.booknameTextBox1.Text.Trim().Length == 0)
            {
                this.warning11.Visible = true;
                this.warning11.Text = "不能为空";
                this.comfirm1.Enabled = false;
            }
            else
            {
                this.warning11.Visible = false;
                this.comfirm1.Enabled = true;
            }
        }

        private void bookmessageTextBox1_TextChanged(object sender, EventArgs e)
        {
            if (this.bookmessageTextBox1.Text.Trim().Length == 0)
            {
                this.warning12.Visible = true;
                this.warning12.Text = "不能为空";
                this.comfirm1.Enabled = false;
            }
            else
            {
                this.warning12.Visible = false;
                this.comfirm1.Enabled = true;
            }
        }

        private void publisherTextBox1_TextChanged(object sender, EventArgs e)
        {
            if (this.publisherTextBox1.Text.Trim().Length == 0)
            {
                this.warning13.Visible = true;
                this.warning13.Text = "不能为空";
                this.comfirm1.Enabled = false;
            }
            else
            {
                this.warning13.Visible = false;
                this.comfirm1.Enabled = true;
            }
        }

        private void button5_Click(object sender, EventArgs e)
        {
            this.newBookPanel.Visible = false;
            this.newBookPanel.SendToBack();
        }

        private void CountnumberTextBox1_TextChanged(object sender, EventArgs e)
        {
            String number = this.CountnumberTextBox1.Text.Trim();
            if (number.Length == 0 || number.Equals(""))
            {
                this.warning15.Visible = true;
                this.warning15.Text = "不能为空，请输入数目";
                this.comfirm1.Enabled = false;
            }
            else if (Regex.IsMatch(number, @"[^0123456789]"))
            {
                this.warning15.Visible = true;
                this.warning15.Text = "不能为非整数";
                this.comfirm1.Enabled = false;
            }
            else
            {
                this.warning15.Visible = false;
                this.comfirm1.Enabled = true;
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {

            this.Width = 970;
            this.Height = 600;
            this.panel1.Width = 970;
            this.panel1.Height = 600;
            this.dataGridView1.Width = 942;
            this.dataGridView1.Height = 480;
            this.panel1.Visible = true;
            this.panel1.BringToFront();
            DataGridViewLoad("select * from booktable");
        }
        private void DataGridViewLoad(String command)
        {

            dataset = new DataSet();
            adapter = new MySqlDataAdapter();
            string query = command;
            adapter.SelectCommand = new MySqlCommand(query, connection);
            adapter.Fill(dataset, "booktable");
            bindingsource.DataSource = dataset.Tables["booktable"];
            dataGridView1.DataSource = bindingsource;
            dataGridView1.Columns[0].HeaderCell.Value = "图书编码";
            dataGridView1.Columns[1].HeaderCell.Value = "书名";
            dataGridView1.Columns[1].Width = 150;
            dataGridView1.Columns[2].HeaderCell.Value = "书本相关信息";
            dataGridView1.Columns[2].Width = 200;
            dataGridView1.Columns[3].HeaderCell.Value = "出版社";
            dataGridView1.Columns[3].Width = 150;
            dataGridView1.Columns[4].HeaderCell.Value = "价格（元）";
            dataGridView1.Columns[5].HeaderCell.Value = "剩余数目";
            dataGridView1.Columns[6].HeaderCell.Value = "类别";
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void button4_Click_1(object sender, EventArgs e)
        {
            String search = this.SearchtextBox.Text;
            try
            {
                //判断用户以何种方式查询
                Boolean isExistName = false;
                Boolean isExistID = false;
                //command = new MySqlCommand("select * from booktable where ( name LIKE '%" + search + "%')", connection);\
                command = new MySqlCommand("select * from booktable where ( name LIKE '%"+ search + "%')", connection);
                reader = command.ExecuteReader();
                isExistName = reader.Read();
                reader.Close();
                command = new MySqlCommand("select id from booktable where id = '" + search + "'", connection);
                reader = command.ExecuteReader();
                isExistID = reader.Read();
                reader.Close();

                //按照查询方式进行各项操作
                if (isExistName)
                {
                    DataGridViewLoad("select * from booktable where name like '%" + search + "%'");
                }
                else if (isExistID)
                {
                    DataGridViewLoad("select * from usertable where id = '" + search + "'");
                }
                else if ((search.ToLower()).Equals("all"))
                {
                    DataGridViewLoad("select * from booktable");
                }
                else
                {
                    MessageBox.Show("该书本不存在");
                }
            }
            catch (MySqlException ex)
            {
                MessageBox.Show("" + ex);
            }
        }

        private void button6_Click(object sender, EventArgs e)
        {
            String motify = this.motifytextBox.Text.Trim();
            motify = motify.Replace("《", "");
            motify = motify.Replace("》", ""); 
            motify = "《" + motify + "》";
            try
            {
                //判断用户以何种方式查询
                Boolean isExistName = false;
                Boolean isExistID = false;
                command = new MySqlCommand("select name from booktable where name  = '" + motify + "'", connection);
                reader = command.ExecuteReader();
                isExistName = reader.Read();
                reader.Close();
                command = new MySqlCommand("select id from booktable where id = '" + motify + "'", connection);
                reader = command.ExecuteReader();
                isExistID = reader.Read();
                reader.Close();

                //按照查询方式进行各项操作
                if (isExistName)
                {
                    Form6 form6 = new Form6(connection, motify, true);
                    form6.ShowDialog();
                    DataGridViewLoad("select * from booktable");
                }
                else if (isExistID)
                {
                    Form6 form6 = new Form6(connection, motify, false);
                    form6.ShowDialog();
                    DataGridViewLoad("select * from booktable");
                }
                else
                {
                    MessageBox.Show("该书本不存在");
                }
            }
            catch (MySqlException ex)
            {
                MessageBox.Show("" + ex);
            }

        }

        private void button7_Click(object sender, EventArgs e)
        {
            this.Width = 709;
            this.Height = 450;
            this.panel1.Visible = false;
        }

        private void button3_Click(object sender, EventArgs e)
        {
            this.Close();
            mainform.Visible = true;
        }

        private void Form3_FormClosed(object sender, FormClosedEventArgs e)
        {
            mainform.Visible = true;
        }
    }
}
