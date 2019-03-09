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
    public partial class Form5 : Form
    {
        MySqlConnection connection;
        MySqlDataAdapter adapter;
        DataSet dataset;
        BindingSource bindingsource = new BindingSource();
        public Form5(MySqlConnection connection)
        {
            this.connection = connection;
            InitializeComponent();
        }

        private void Form5_Load(object sender, EventArgs e)
        {
            this.Text = "读者图书图书借还情况";
            this.Width = 1000;
            dataGridView1.Width = 1000;
            DataGridViewLoad("select * from userborrowbook");
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            String search = this.textBox1.Text.Trim();
            Boolean isNum = false;
            try
            {
                Double.Parse(search);
                isNum = true;
            }
            catch(Exception)
            {
                isNum = false;
            }
            if(isNum)
            {
                DataGridViewLoad("select * from userborrowbook where (id = '" + search + "')");
            }
            else
            {
                DataGridViewLoad("select * from userborrowbook where (count = '" + search + "')");
            }
        }
        private void DataGridViewLoad(String command)
        {
            dataset = new DataSet();
            adapter = new MySqlDataAdapter();
            string query = command;
            adapter.SelectCommand = new MySqlCommand(query, connection);
            adapter.Fill(dataset, "userborrowbook");
            bindingsource.DataSource = dataset.Tables["userborrowbook"];
            dataGridView1.DataSource = bindingsource;
            dataGridView1.Columns[0].HeaderCell.Value = "书本编号";
            dataGridView1.Columns[1].HeaderCell.Value = "书名";
            dataGridView1.Columns[1].Width = 150;
            dataGridView1.Columns[2].HeaderCell.Value = "书本相关信息";
            dataGridView1.Columns[2].Width = 200;
            dataGridView1.Columns[3].HeaderCell.Value = "借出日期";
            dataGridView1.Columns[4].HeaderCell.Value = "归还日期";
            dataGridView1.Columns[5].HeaderCell.Value = "归还与否";
            dataGridView1.Columns[6].HeaderCell.Value = "读者账号";
            dataGridView1.Columns[7].HeaderCell.Value = "读者姓名";
        }
    }
}
