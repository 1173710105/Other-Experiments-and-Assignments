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
    public partial class Form1 : Form
    {
        int exit_count = 0;//用于记录第一页的退出情况
        int latitude = 0;//用于记录维度
        double a_count = 0;//记录A选项的权重
        double b_count = 0;//记录B选项的权重
        int test_num = 1;//记录题号
        double[] a_cnt = new double[49];
        double[] b_cnt = new double[49];
        public static string STR;
        struct calculate
        {
            public double e_cnt;
            public double i_cnt;
            public double s_cnt;
            public double n_cnt;
            public double t_cnt;
            public double f_cnt;
            public double j_cnt;
            public double p_cnt;
            public calculate(double E_cnt,double I_cnt,double S_cnt,double N_cnt,double T_cnt,double F_cnt,double J_cnt,double P_cnt)
            {
                this.e_cnt = E_cnt;
                this.i_cnt = I_cnt;
                this.s_cnt = S_cnt;
                this.n_cnt = N_cnt;
                this.t_cnt = T_cnt;
                this.f_cnt = F_cnt;
                this.j_cnt = J_cnt;
                this.p_cnt = P_cnt;
            }
        };

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            this.BackgroundImage = Image.FromFile("第二幕壁纸.jpg");
            this.button2.Visible = true;
            this.button1.Visible = true;
            this.first.Visible = false;
            this.second.Visible = false;
            this.third.Visible = false;
            this.fourth.Visible = false;
            this.EIXT.Visible = false;
            this.Type.Visible = false;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.button2.Visible = false;
            this.button1.Visible = false;
            this.first.Visible = true;
            this.second.Visible = true;
            this.third.Visible = true;
            this.fourth.Visible = true;
            this.EIXT.Visible = true;
            this.Type.Visible = true;
            exit_count = 1;
            this.BackgroundImage = null;
            richTextBox1_TextChanged(sender, e);
        }

        private void richTextBox1_TextChanged(object sender, EventArgs e)
        {
            this.richTextBox1.Visible = true;
            FileStream fs = new FileStream("MBTI性格类型介绍.txt", FileMode.Open, FileAccess.Read);
            richTextBox1.Font = new Font("黑体", 12);
            StreamReader sr = new StreamReader(fs, Encoding.Default);
            string str;
            str = sr.ReadToEnd();
            richTextBox1.Text = str;
            sr.Close();
            fs.Close();
        }

        private void button7_Click(object sender, EventArgs e)
        {
            if (exit_count == 1)
            {
                richTextBox1.Clear();
                richTextBox1.Visible = false;
                Form1_Load(sender, e);
            }
            else if (exit_count == 2)
            {
                exit_count = 1;
                latitude_type.Clear();
                latitude_type.Visible = false;
                richTextBox1.Visible = true;
                this.first.Visible = true;
                this.second.Visible = true;
                this.third.Visible = true;
                this.fourth.Visible = true;
                this.Type.Visible = true;
            }
            else if(exit_count==3)
            {
                rull_text.Clear();
                rull_text.Visible = false;
                button2_Click(sender, e);
            }
            else if(exit_count==4)
            {
                this.endoftheprog.Visible = true;
                this.type_detail.Visible = true;
                this.type_result.Visible = true;
                this.EIXT.Visible = false;
                this.type_text.Clear();
                this.type_text.Visible = false;
            }
            else if (exit_count==5)
            {
                this.type_result.Visible = true;
                this.endoftheprog.Visible = true;
                this.type_detail.Visible = true;
                this.EIXT.Visible = false;
            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            latitude = 2;
            exit_count = 2;
            类型以及维度_TextChanged(sender, e);
        }

        private void first_Click(object sender, EventArgs e)
        {
            latitude = 1;
            exit_count = 2;
            类型以及维度_TextChanged(sender, e);
        }

        private void third_Click(object sender, EventArgs e)
        {
            latitude = 3;
            exit_count = 2;
            类型以及维度_TextChanged(sender, e);
        }

        private void fourth_Click(object sender, EventArgs e)
        {
            latitude = 4;
            exit_count = 2;
            类型以及维度_TextChanged(sender, e);
        }

        private void 类型以及维度_TextChanged(object sender, EventArgs e)
        {
            this.first.Visible = false;
            this.second.Visible = false;
            this.third.Visible = false;
            this.fourth.Visible = false;
            this.Type.Visible = false;
            this.latitude_type.Clear();
            this.latitude_type.Visible = true;
            this.richTextBox1.Visible = false;
            FileStream fs;
            if (latitude == 1)
            {
                fs = new FileStream("第一维度.txt", FileMode.Open, FileAccess.Read);
            }
            else if (latitude == 2)
            {
                fs = new FileStream("第二维度.txt", FileMode.Open, FileAccess.Read);
            }
            else if (latitude == 3)
            {
                fs = new FileStream("第三维度.txt", FileMode.Open, FileAccess.Read);
            }
            else if (latitude == 4)
            {
                fs = new FileStream("第四维度.txt", FileMode.Open, FileAccess.Read);
            }
            else
            {
                fs = new FileStream("十六种性格类型表.txt", FileMode.Open, FileAccess.Read);
            }
            StreamReader sr = new StreamReader(fs, Encoding.Default);
            string str = sr.ReadToEnd();
            latitude_type.Text = str;
            sr.Close();
            fs.Close();
        }

        private void Type_Click(object sender, EventArgs e)
        {
            latitude = 5;
            exit_count = 2;
            类型以及维度_TextChanged(sender, e);
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.button1.Visible = false;
            this.EIXT.Visible = false;
            this.button2.Visible = false;
            this.enter_the_text.Visible = true;
            this.rule.Visible = true;
        }

        private void rule_Click(object sender, EventArgs e)
        {
            exit_count = 3;
            this.rule.Visible = false;
            this.enter_the_text.Visible = false;
            FileStream fs = new FileStream("MBTI开头.txt", FileMode.Open, FileAccess.Read);
            StreamReader sr = new StreamReader(fs, Encoding.Default);
            string str = sr.ReadToEnd();
            rull_text.Text = str;
            rull_text.Visible = true;
            this.EIXT.Visible = true;
            sr.Close();
            fs.Close();
        }

        private void rull_text_TextChanged(object sender, EventArgs e)
        {

        }

        private void enter_the_text_Click(object sender, EventArgs e)
        {
            this.test.Visible = true;
            this.answer2.Visible = true;
            this.answer1.Visible = true;
            this.answer3.Visible = true;
            this.previous.Visible = true;
            this.next_test.Visible = true;
            this.comfirm1.Visible = true;
            this.a_weight.Visible = true;
            this.b_weight.Visible = true;
            this.enter_the_text.Visible = false;
            this.rule.Visible = false;
            label1.Visible = true;
            label2.Visible = true;
            MBTI_test(1);
        }

        private void Button1_LostFocus(object sender, EventArgs e)
        {
            throw new NotImplementedException();
        }

        private void button4_Click_1(object sender, EventArgs e)
        {
            if (test_num > 48)
            {

            }
            else
                this.next_test.Enabled = true;
        }

        public bool bolNum(string temp)//用于判断是不是数字
        {
            for (int i = 0; i < temp.Length; i++)
            {
                byte tempByte = Convert.ToByte(temp[i]);
                if ((tempByte < 48 || tempByte > 57) && tempByte != '.')
                {
                    return false;
                }
            }
            return true;
        }

        public void MBTI_test(int k)
        {
            if (k == 1)
                this.previous.Enabled = false;
            else
                this.previous.Enabled = true;
            if (k >= 48)
                this.next_test.Enabled = false;
            else
                this.next_test.Enabled = true;
            int i = 1;//记录题号
            this.answer1.Checked = false;
            this.answer2.Checked = false;
            this.answer3.Checked = false;
            this.comfirm1.Enabled = false;
            this.next_test.Enabled = false;
            FileStream fs = new FileStream("MBTI测试试题.txt", FileMode.Open, FileAccess.Read);
            StreamReader sr = new StreamReader(fs, Encoding.Default);
            while (i != k)
            {
                sr.ReadLine();
                sr.ReadLine();
                sr.ReadLine();
                i++;
            }
            this.test.Text = sr.ReadLine();
            this.answer1.Text = sr.ReadLine();
            this.answer2.Text = sr.ReadLine();
            if (k == 48)
            {
                fs.Close();
                sr.Close();
            }
        }

        private void answer1_CheckedChanged(object sender, EventArgs e)
        {
            this.a_weight.Text = "10.0";
            this.b_weight.Text = "0.0";
            a_count = 10.0;
            b_count = 0.0;
            this.comfirm1.Enabled = true;
            this.a_weight.Enabled = false;
            this.b_weight.Enabled = false;
            this.comfirm1.Focus();
        }

        private void answer2_CheckedChanged(object sender, EventArgs e)
        {
            this.a_weight.Text = "0.0";
            this.b_weight.Text = "10.0";
            a_count = 0.0;
            b_count = 10.0;
            this.comfirm1.Enabled = true;
            this.a_weight.Enabled = false;
            this.b_weight.Enabled = false;
            this.comfirm1.Focus();
        }

        private void answer3_CheckedChanged(object sender, EventArgs e)
        {
            this.button1.Visible = false;
            this.button2.Visible = false;
            this.a_weight.Enabled = true;
            this.b_weight.Enabled = true;
            this.comfirm1.Enabled = true;
            this.a_weight.Focus();
        }

        private void previous_Click(object sender, EventArgs e)
        {
            test_num--;
            a_weight.Enabled = false;
            b_weight.Enabled = false;
            this.answer3.Checked = false;
            MBTI_test(test_num);
        }

        private void next_test_Click(object sender, EventArgs e)
        {
            test_num++;
            this.answer3.Checked = false;
            a_weight.Enabled = false;
            b_weight.Enabled = false;
            MBTI_test(test_num);
        }

        private void a_weight_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (e.KeyChar == (char)13)
            {
                if (bolNum(a_weight.Text))
                {
                    a_count = Convert.ToDouble(a_weight.Text);
                    if (a_count > 10)
                    {
                        a_count = 10;
                        b_count = 0;
                    }
                    else if (a_count < 0)
                    {
                        a_count = 0;
                        b_count = 10;
                    }
                    else
                    {
                        b_count = 10 - a_count;
                    }
                    a_weight.Text = a_count.ToString();
                    b_weight.Text = b_count.ToString();
                    b_weight.Focus();
                }
                else
                {
                    MessageBox.Show("不要调皮，请输入数字");
                    a_weight.Clear();
                    b_weight.Clear();
                }
            }
        }

        private void b_weight_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (e.KeyChar == (char)13)
            {
                if (bolNum(b_weight.Text))
                {
                    b_count = Convert.ToDouble(b_weight.Text);
                    if (b_count > 10)
                    {
                        b_count = 10;
                        a_count = 0;
                    }
                    else if (b_count < 0)
                    {
                        b_count = 0;
                        a_count = 10;
                    }
                    else
                    {
                        a_count = 10 - b_count;
                    }
                    a_weight.Text = a_count.ToString();
                    b_weight.Text = b_count.ToString();
                    this.comfirm1.Focus();
                }
                else
                {
                    MessageBox.Show("不要调皮，请输入数字");
                    a_weight.Clear();
                    b_weight.Clear();
                }
            }
        }

        private void comfirm1_Click(object sender, EventArgs e)
        {
            if (bolNum(a_weight.Text) && bolNum(b_weight.Text))
            {
                if ((a_count + b_count) != 10.0)
                {
                    if (a_count > 10.0)
                    {
                        a_count = 10.0;
                        b_count = 0.0;
                    }
                    else if (a_count < 0.0)
                    {
                        a_count = 0.0;
                        b_count = 10.0;
                    }
                    else
                    {
                        b_count = 10.0 - a_count;
                    }
 
                    if (b_count > 10.0)
                    {
                        b_count = 10.0;
                        a_count = 0;
                    }
                    else if (b_count < 0.0)
                    {
                        a_count = 10.0;
                        b_count = 0.0;
                    }
                    else
                    {
                        a_count = 10.0 - b_count;
                    }
                    a_cnt[test_num] = a_count;
                    b_cnt[test_num] = b_count;
                }
                if (test_num < 48)
                {
                    this.next_test.Enabled = true;
                    this.next_test.Focus();
                }
                else
                {
                    this.next_test.Enabled = false;
                }
                if (test_num == 48)
                {
                    this.text_result1.Enabled = true;
                    this.text_result1.Visible = true;
                    this.text_result1.Focus();
                }
                a_weight.Text = a_count.ToString();
                b_weight.Text = b_count.ToString();
            }
            else
            {
                if (!bolNum(a_weight.Text))
                {
                    MessageBox.Show("不要调皮，请输入数字");
                    a_weight.Clear();
                    b_weight.Clear();
                    a_weight.Focus();
                }
                else
                {
                    MessageBox.Show("不要调皮，请输入数字");
                    b_weight.Clear();
                    a_weight.Clear();
                    b_weight.Focus();
                }
            }
        }

        private void text_result_Click(object sender, EventArgs e)
        {
            this.comfirm1.Visible = false;
            this.next_test.Visible = false;
            this.previous.Visible = false;
            this.test.Visible = false;
            this.label1.Visible = false;
            this.label2.Visible = false;
            this.answer1.Visible = false;
            this.answer2.Visible = false;
            this.answer3.Visible = false;
            this.a_weight.Visible = false;
            this.b_weight.Visible = false;
            this.text_result1.Visible = false;
            this.type_result.Visible = true;
            this.type_detail.Visible = true;
            this.endoftheprog.Visible = true;
            calculate result = new calculate(0, 0, 0, 0, 0, 0, 0, 0);
            Analyze(ref result, a_cnt, b_cnt);
            STR = Comfirm_Type(ref result);
            /*label3.Text = result.e_cnt.ToString();
            label4.Text = result.i_cnt.ToString();
            label5.Text = result.s_cnt.ToString();
            label6.Text = result.n_cnt.ToString();
            label7.Text = result.t_cnt.ToString();
            label8.Text = result.f_cnt.ToString();
            label9.Text = result.j_cnt.ToString();
            label10.Text = result.p_cnt.ToString();
            label11.Text = STR;*/
        }

        private void Analyze(ref calculate result,double[] a,double[] b)
        {
            int i=1;
            while(i<=48)
            {
                if(i%4==1)
                {
                    result.e_cnt += a[i];
                    result.i_cnt += b[i];
                }
                else if(i%4==2)
                {
                    result.s_cnt += a[i];
                    result.n_cnt += b[i];
                }
                else if(i%4==3)
                {
                    result.t_cnt += a[i];
                    result.f_cnt += b[i];
                }
                else
                {
                    result.j_cnt += a[i];
                    result.p_cnt += b[i];
                }
                i++;
            }
        }

        private string Comfirm_Type(ref calculate result)
        {
            string str = "EEEE";
            if (result.e_cnt <= result.i_cnt)
                str = "I";
            else
                str = "E";
            if (result.s_cnt <= result.n_cnt)
                str = string.Concat(str, "N");
            else
                str = string.Concat(str, "S");
            if (result.t_cnt <= result.f_cnt)
                str = string.Concat(str, "F");
            else
                str = string.Concat(str, "T");
            if (result.j_cnt <= result.p_cnt)
                str = string.Concat(str, "P");
            else
                str = string.Concat(str, "J");
            str = string.Concat(str, ".txt");
            return str;
        }

        private void endoftheprog_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void type_result_Click(object sender, EventArgs e)
        {
            this.type_result.Visible = false;
            this.endoftheprog.Visible = false;
            this.type_detail.Visible = false;
            this.EIXT.Visible = true;
            this.type_detail.Enabled = true;
            this.type_text.Visible = true;
            exit_count = 4;
            FileStream fs = new FileStream(STR, FileMode.Open, FileAccess.Read);
            StreamReader sr = new StreamReader(fs, Encoding.Default);
            type_text.Text = sr.ReadToEnd();
            sr.Close();
            fs.Close();          
        }

        private void type_detail_Click(object sender, EventArgs e)
        {
            /*this.type_result.Visible = false;
            this.endoftheprog.Visible = false;
            this.type_detail.Visible = false;
            this.EIXT.Visible = true;
            exit_count = 5;*/
            Form2 a = new Form2();
            a.ShowDialog();
        }
        
    }
}
