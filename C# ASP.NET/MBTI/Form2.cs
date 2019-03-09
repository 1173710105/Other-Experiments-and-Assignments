using System;
using System.IO;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MBTI测试版2
{
    public partial class Form2 : Form
    {
        string str_detail = Form1.STR;
        int exit_count = 1;
        public Form2()
        {
            InitializeComponent();
        }

        private void Form2_Load(object sender, EventArgs e)
        {
            this.button2.Enabled = false;
            this.button3.Enabled = false;
            this.button4.Enabled = false;
            this.button5.Enabled = false;
            this.button6.Enabled = false;
            this.button7.Enabled = false;
            this.button8.Enabled = false;
            this.button9.Enabled = false;
            this.button10.Enabled = false;
            this.button11.Enabled = false;
            this.button12.Enabled = false;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            button_visual(false);
            exit_count++;
            this.richTextBox1.Visible = true;
            this.exit.Visible = true;
            string temp = str_detail;
            temp = temp.Insert(4,"个性特征描述");
            file_read(temp);
            button_enable(2);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            exit_count++;
            button_visual(false);
            this.richTextBox1.Visible = true;
            this.exit.Visible = true;
            string temp = str_detail;
            temp = temp.Insert(4,"可能存在的盲点");
            file_read(temp);
            button_enable(3);
        }

        private void button3_Click(object sender, EventArgs e)
        {
            exit_count++;
            button_visual(false);
            this.richTextBox1.Visible = true;
            this.exit.Visible = true;
            string temp = str_detail;
            temp = temp.Insert(4,"功能运用");
            file_read(temp);
            button_enable(4);
        }

        private void button4_Click(object sender, EventArgs e)
        {
            exit_count++;
            button_visual(false);
            this.richTextBox1.Visible = true;
            this.exit.Visible = true;
            string temp = str_detail;
            temp = temp.Insert(4,"问题解决方式");
            file_read(temp);
            button_enable(5);
        }

        private void button5_Click(object sender, EventArgs e)
        {
            exit_count++;
            button_visual(false);
            this.richTextBox1.Visible = true;
            this.exit.Visible = true;
            string temp = str_detail;
            temp = temp.Insert(4, "工作中的优势");
            file_read(temp);
            button_enable(6);
        }

        private void button6_Click(object sender, EventArgs e)
        {
            exit_count++;
            button_visual(false);
            this.richTextBox1.Visible = true;
            this.exit.Visible = true;
            string temp = str_detail;
            temp = temp.Insert(4, "工作中的劣势");
            file_read(temp);
            button_enable(7);
        }

        private void button7_Click(object sender, EventArgs e)
        {
            exit_count++;
            button_visual(false);
            this.richTextBox1.Visible = true;
            this.exit.Visible = true;
            string temp = str_detail;
            temp = temp.Insert(4, "适合的岗位特质");
            file_read(temp);
            button_enable(8);
        }

        private void button8_Click(object sender, EventArgs e)
        {
            exit_count++;
            button_visual(false);
            this.richTextBox1.Visible = true;
            this.exit.Visible = true;
            string temp = str_detail;
            temp = temp.Insert(4, "对组织的贡献");
            file_read(temp);
            button_enable(9);
        }

        private void button9_Click(object sender, EventArgs e)
        {
            exit_count++;
            button_visual(false);
            this.richTextBox1.Visible = true;
            this.exit.Visible = true;
            string temp = str_detail;
            temp = temp.Insert(4, "领导风格");
            file_read(temp);
            button_enable(10);
        }

        private void button10_Click(object sender, EventArgs e)
        {
            exit_count++;
            button_visual(false);
            this.richTextBox1.Visible = true;
            this.exit.Visible = true;
            string temp = str_detail;
            temp = temp.Insert(4, "潜在缺陷");
            file_read(temp);
            button_enable(11);
        }

        private void button11_Click(object sender, EventArgs e)
        {
            exit_count++;
            button_visual(false);
            this.richTextBox1.Visible = true;
            this.exit.Visible = true;
            string temp = str_detail;
            temp = temp.Insert(4, "适合的工作环境");
            file_read(temp);
            button_enable(12);
        }

        private void button12_Click(object sender, EventArgs e)
        {
            exit_count++;
            button_visual(false);
            this.richTextBox1.Visible = true;
            this.exit.Visible = true;
            string temp = str_detail;
            temp = temp.Insert(4,"个人发展建议");
            file_read(temp);
        }

        void file_read(string str)
        {
            if (File.Exists(str))
            {
                FileStream fs = new FileStream(str, FileMode.Open, FileAccess.Read);
                StreamReader sr = new StreamReader(fs, Encoding.Default);
                richTextBox1.Text = sr.ReadToEnd();
                sr.Close();
                fs.Close();
            }
            else
            {
                label1.Visible = true;
                label1.Text = "找不到文件" + str;
            }
        }

        void button_visual(bool i)
        {
            if (i == true)
            {
                this.button1.Visible = true;
                this.button2.Visible = true;
                this.button3.Visible = true;
                this.button4.Visible = true;
                this.button5.Visible = true;
                this.button6.Visible = true;
                this.button7.Visible = true;
                this.button8.Visible = true;
                this.button9.Visible = true;
                this.button10.Visible = true;
                this.button11.Visible = true;
                this.button12.Visible = true;
                this.label1.Visible = false;
            }
            else
            {
                this.button1.Visible = false;
                this.button2.Visible = false;
                this.button3.Visible = false;
                this.button4.Visible = false;
                this.button5.Visible = false;
                this.button6.Visible = false;
                this.button7.Visible = false;
                this.button8.Visible = false;
                this.button9.Visible = false;
                this.button10.Visible = false;
                this.button11.Visible = false;
                this.button12.Visible = false;
            }
        }
 
        private void exit_Click_1(object sender, EventArgs e)
        {
            if (exit_count == 1)
            {
                this.Visible = false;
            }
            if (exit_count == 2)
            {
                this.richTextBox1.Clear();
                this.richTextBox1.Visible = false;
                exit_count--;
                button_visual(true);
            }
        }

        private void button_enable(int n)
        {
            for(int i =1;i<=n;i++)
            {
                switch (i)
                {
                    case 1:
                        button1.Enabled = true;
                        break;
                    case 2:
                        button2.Enabled = true;
                        break;
                    case 3:
                        button3.Enabled = true;
                        break;
                    case 4:
                        button4.Enabled = true;
                        break;
                    case 5:
                        button5.Enabled = true;
                        break;
                    case 6:
                        button6.Enabled = true;
                        break;
                    case 7:
                        button7.Enabled = true;
                        break;
                    case 8:
                        button8.Enabled = true;
                        break;
                    case 9:
                        button9.Enabled = true;
                        break;
                    case 10:
                        button10.Enabled = true;
                        break;
                    case 11:
                        button11.Enabled = true;
                        break;
                    case 12:
                        button12.Enabled = true;
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
