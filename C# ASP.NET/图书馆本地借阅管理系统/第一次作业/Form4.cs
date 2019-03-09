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
using System.Text.RegularExpressions;
namespace 第一次作业
{
    public partial class Form4 : Form
    {
        String  ID;
        Boolean IsMotify;
        Boolean IsFirst = true;//判断是否是第一次加载
        MySqlConnection connection;
        MySqlCommand command;
        MySqlDataReader reader = null;

        public Form4(String text,String ID,Boolean IsMotify,MySqlConnection connection)
        {
            InitializeComponent();
            this.Text = text;
            this.ID = ID;
            this.IsMotify = IsMotify;
            this.connection = connection;
        }

        private void Form4_Load(object sender, EventArgs e)
        {
           
            if (ID==null&&IsMotify==false)
            {
                ID = GetLastOneID();
                this.CountText.Text = ID;
                this.radioButton1.Checked = true;
            }
            if(ID!=null&&IsMotify)
            {
                this.IDLabel.Text = ID;
                try
                {
                    command = new MySqlCommand("select * from usertable where id = '" + ID.Trim() + "'", connection);
                    reader = command.ExecuteReader();
                    if (reader.Read())
                    { 
                        if (this.NameText2.Text.Trim().Length==0)
                        {
                            this.NameText2.Text = reader.GetString(1);
                        }
                        if (this.SumText2.Text.Trim().Length == 0)
                        {
                            this.SumText2.Text = reader.GetString(3);
                        }
                        if (IsFirst)
                        {
                            String tag = this.reader.GetString("tag").Trim();
                            if (tag.Equals("普通用户") && this.tag21.Checked == false)
                            {
                                this.tag21.Checked = true;
                            }
                            else if (tag.Equals("会员") && this.tag22.Checked == false)
                            {
                                this.tag22.Checked = true;
                            }
                            String credit = this.reader.GetString(6).Trim();
                            if (credit.Equals("良好") && this.credit21.Checked == false)
                            {
                                this.credit21.Checked = true;
                            }
                            if (credit.Equals("中等") && this.credit22.Checked == false)
                            {
                                this.credit22.Checked = true;
                            }
                            if (credit.Equals("较差") && this.credit23.Checked == false)
                            {
                                this.credit23.Checked = true;
                            }
                            IsFirst = false;
                        }
                        if (this.BorrowedText2.Text.Trim().Length == 0)
                        {
                            this.BorrowedText2.Text = reader.GetString(4);
                        }
                        if (this.RetuenText2.Text.Trim().Length == 0)
                        {
                            this.RetuenText2.Text = reader.GetString(5);
                        }
                        if (this.CapacityText2.Text.Trim().Length == 0)
                        {
                            this.CapacityText2.Text = reader.GetString(7);
                        }
                        if (this.LeftText2.Text.Trim().Length == 0)
                        {
                            this.LeftText2.Text = reader.GetString(8);
                        }
                        if (this.OwnText2.Text.Trim().Length == 0)
                        {
                            this.OwnText2.Text = reader.GetString(9);
                        }
                    }
                }
                catch(MySqlException ex)
                {
                    MessageBox.Show("警告：" + ex);
                }
                reader.Close();
                this.MotiflyPanel.BringToFront();
                MotiflyPanel.Visible = true;
            }
        }
        private String GetLastOneID()
        {
            try
            {
                command = new MySqlCommand("select * from usertable order by id desc LIMIT 1", connection);
                reader = command.ExecuteReader();
                String temp = null;
                while (reader.Read())
                {
                    temp = reader.GetString(0).ToString();
                }
                reader.Close();
                int i = Convert.ToInt32(temp)+1;
                temp = i.ToString();
                return temp;
            }
            catch (MySqlException e)
            {
                MessageBox.Show(""+e);
                return null;
            }
        }

        private void comfirm_Click(object sender, EventArgs e)
        {
            String count = ID;
            String name = NameText.Text;
            String tag = null;
            String cmd = null;
            if (radioButton1.Checked)
            {
                tag = "普通用户";
                cmd = "insert into usertable ( id,name,tag) values ('" + ID + "','" + name + "','" + tag + "')";
            }
            else
            {
                tag = "会员";
                cmd = "insert into usertable ( id,name,tag,capacity,leftsum ) values ('" + ID + "','" + name + "','" + tag + "','15','15' )";
            }
            if (name==null||name.Length==0)
            {
                MessageBox.Show("用户数据不完整");
            }
            else
            {
                try
                {
                    command = new MySqlCommand(cmd, connection);
                    command.ExecuteNonQuery();
                    MessageBox.Show("增加数据成功");
                    this.Close();
                }
                catch(MySqlException ex)
                {
                    MessageBox.Show(""+ex);
                }
            }
            

        }
        private void panel2_Paint(object sender, PaintEventArgs e)
        {

        }
   
        private void exit2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void exit1_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            String name = this.NameText2.Text.Trim();
            String tag = null;
            if(this.tag21.Checked)
            {
                tag = "普通用户";
            }
            if(this.tag22.Checked)
            {
                tag = "会员";
            }
            String sum = this.SumText2.Text.Trim();
            String borrowed = this.BorrowedText2.Text.Trim();
            String returned = this.RetuenText2.Text.Trim();
            String credit = null;
            if(this.credit21.Checked)
            {
                credit = "良好";
            }
            if (this.credit22.Checked)
            {
                credit = "中等";
            }
            if (this.credit23.Checked)
            {
                credit = "较差";
            }
            String capacity = this.CapacityText2.Text.Trim();
            String left = this.LeftText2.Text.Trim();
            //MessageBox.Show(left);
             String own = this.OwnText2.Text.Trim();

            try
            {
                //sql语句太长了会不行的
                //String sql = "UPDATE usertable SET id ='" + ID + "',name='" + name + "',tag='" + tag + "',sum='" + sum + "',borrowed='" + borrowed + "',returned='" + returned + "',credit='" + credit + "',capacity='" + capacity + "',left='" + left + "',own='" + own + "' WHERE(id='" + ID.Trim() + "')";
                String sql1 = "UPDATE usertable SET id ='" + ID + "',name='" + name + "',tag='" + tag + "',sum='" + sum + "',borrowed='" + borrowed + "',returned='" + returned + "' WHERE(id='" + ID.Trim() + "')";
                command = new MySqlCommand(sql1, connection);
                command.ExecuteNonQuery();
                //MessageBox.Show(sql1);
                String sql2 = "UPDATE usertable SET credit='" + credit + "',capacity='" + capacity + "',leftsum='" + left + "',own='" + own + "' WHERE(id='" + ID.Trim() + "')";
                command = new MySqlCommand(sql2, connection);
                command.ExecuteNonQuery();
                MessageBox.Show("修改成功");
                this.exit2.Focus();
            }
            catch(MySqlException ex)
            {
                
                MessageBox.Show(""+ex);
            }
            
        }

        private void Update2_Click(object sender, EventArgs e)
        {
            if(this.NameText2.Text.Trim().Length==0)
            {
                MessageBox.Show("名字不能为空", "警告", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                this.NameText2.Focus();
                return;
            }
            if(this.OwnText2.Text.Trim().Length==0)
            {
                MessageBox.Show("欠款金额不能为空", "警告", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                this.NameText2.Focus();
                return;
            }

            {
                int capacity = 0;
                if (this.tag21.Checked)
                {
                    capacity = 10;
                }
                else
                {
                    capacity = 15;
                }
                if(this.credit21.Checked)
                {
                    capacity += 5;
                }
                if(this.credit23.Checked)
                {
                    capacity -= 5;
                }
                int borrowed = Convert.ToInt32(this.BorrowedText2.Text.Trim());
                int left = Convert.ToInt32(this.LeftText2.Text.Trim());
                left = capacity - borrowed;
                this.CapacityText2.Text = ""+capacity;
                this.LeftText2.Text = ""+left;
                this.comfirm2.Enabled = true;
                this.comfirm2.Focus();
            }
            
        }
        private void OwnText2_TextChanged(object sender, EventArgs e)
        {
            if(OwnText2.Focus()==true)
            {
                this.comfirm2.Enabled = false;
            }
            TipTextDigit("请输入数字,支持浮点数",450,178);
        }

        private Boolean TipTextDigit(String Tip,int x,int y)
        {
            if (this.OwnText2.Text.Trim().Length==0)
            {
                this.TipText2.Visible = true;
                this.TipText2.Text = Tip;
                this.TipText2.Location = new Point(x, y);
                return false;
            }
            if(Regex.IsMatch(this.OwnText2.Text, @"[^0123456789.]"))
            {
                this.TipText2.Visible = true;
                this.TipText2.Text = Tip;
                this.TipText2.Location = new Point(x, y);
                return false;
            }
            else
            {
                this.TipText2.Visible = false;
                return true;
            }         
        }

        private void NameText2_TextChanged(object sender, EventArgs e)
        {
            if(this.NameText2.Focus()==true)
            {
                this.comfirm2.Enabled = false;
            }
            if(this.NameText2.Text.Trim().Length == 0)
            {
                this.NameTip2.Visible = true;
            }
            else
            {
                this.NameTip2.Visible = false;
            }
        }

        private void tag21_CheckedChanged(object sender, EventArgs e)
        {
            this.comfirm2.Enabled = false;
        }

        private void tag22_CheckedChanged(object sender, EventArgs e)
        {
            this.comfirm2.Enabled = false;
        }

        private void credit21_CheckedChanged(object sender, EventArgs e)
        {
            this.comfirm2.Enabled = false;
        }

        private void credit22_CheckedChanged(object sender, EventArgs e)
        {
            this.comfirm2.Enabled = false;
        }

        private void credit23_CheckedChanged(object sender, EventArgs e)
        {
            this.comfirm2.Enabled = false;
        }
    }
}
