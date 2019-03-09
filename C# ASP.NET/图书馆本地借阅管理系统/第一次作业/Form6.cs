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
    public partial class Form6 : Form
    {
        MySqlConnection connection;
        MySqlCommand command;
        MySqlDataReader reader;
        String condition;
        Boolean isbyname;
        public Form6(MySqlConnection connection,String condition,Boolean isbyname)
        {
            InitializeComponent();
            this.connection = connection;
            this.condition = condition;
            this.isbyname = isbyname;
        }

        private void button1_Click(object sender, EventArgs e)
        {
        }

        private void Form6_Load(object sender, EventArgs e)
        {
            reset_Click(sender, e);
        }

        private void comfirm1_Click(object sender, EventArgs e)
        {
            if (this.warning11.Visible == true || this.warning12.Visible == true || this.warning13.Visible == true || this.warning14.Visible == true || this.warning15.Visible == true)
            {
                MessageBox.Show("信息有误，请重新填写", "警告");
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
                    cmd = "update booktable set name='" + name + "',message='" + message + "',publisher='" + publisher + "',price='" + price + "',count='" + countnumber + "',class='" + classify + "' where id='" + id + "'";
                    command = new MySqlCommand(cmd, connection);
                    command.ExecuteNonQuery();
                }
                catch (MySqlException ex)
                {
                    MessageBox.Show("" + ex);
                }
                MessageBox.Show("修改信息成功");
                this.comfirm1.Enabled = false;
            }
        }

        private void reset_Click(object sender, EventArgs e)
        {
            String cmd;
            if (isbyname)
            {
                cmd = "select * from booktable where name ='"+condition+"'";
            }
            else
            {
                cmd = "select * from booktable where id ='" + condition+"'";
            }
            try
            {
                command = new MySqlCommand(cmd, connection);
                reader = command.ExecuteReader();
                if (reader.Read())
                {
                    this.IDnumberTextBox1.Text = reader.GetString("id");
                    this.booknameTextBox1.Text = reader.GetString("name");
                    this.bookmessageTextBox1.Text = reader.GetString("message");
                    this.publisherTextBox1.Text = reader.GetString("publisher");
                    this.priceTextBox1.Text = reader.GetString("price");
                    this.CountnumberTextBox1.Text = reader.GetString("count");
                    String classify = reader.GetString("class");
                    if (classify.Equals("文学类"))
                    {
                        this.ClassRadioButton11.Checked = true;
                    }
                    else if (classify.Equals("社科类"))
                    {
                        this.ClassRadioButton12.Checked = true;
                    }
                    else if (classify.Equals("工具类"))
                    {
                        this.ClassRadioButton13.Checked = true;
                    }
                    else
                    {
                        this.ClassRadioButton14.Checked = true;
                        this.classTextBox1.Text = classify;
                    }
                    reader.Close();
                }
            }
            catch(MySqlException ex)
            {
                MessageBox.Show("" + ex);
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

        private void ClassRadioButton11_TextChanged(object sender, EventArgs e)
        {
            this.classTextBox1.Enabled = false;
        }

        private void ClassRadioButton12_CheckedChanged(object sender, EventArgs e)
        {
            this.classTextBox1.Enabled = false;
        }

        private void ClassRadioButton11_CheckedChanged(object sender, EventArgs e)
        {
            this.classTextBox1.Enabled = false;
        }

        private void ClassRadioButton13_CheckedChanged(object sender, EventArgs e)
        {
            this.classTextBox1.Enabled = false;
        }

        private void ClassRadioButton14_CheckedChanged(object sender, EventArgs e)
        {
            this.classTextBox1.Enabled = true;
        }

        private void button5_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void newBookPanel_Paint(object sender, PaintEventArgs e)
        {

        }
    }
}
