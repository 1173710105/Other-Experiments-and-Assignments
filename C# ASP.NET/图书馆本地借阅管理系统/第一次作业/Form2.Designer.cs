namespace 第一次作业
{
    partial class Form2
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.Windows.Forms.DataGridViewCellStyle dataGridViewCellStyle1 = new System.Windows.Forms.DataGridViewCellStyle();
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.UserMessageTable = new System.Windows.Forms.DataGridView();
            this.SearchtextBox = new System.Windows.Forms.TextBox();
            this.SearchButton = new System.Windows.Forms.Button();
            this.AddButton = new System.Windows.Forms.Button();
            this.DeleteButton = new System.Windows.Forms.Button();
            this.MotifyButton = new System.Windows.Forms.Button();
            this.ToolForUserTablePanel = new System.Windows.Forms.Panel();
            this.button4 = new System.Windows.Forms.Button();
            this.MotifyTextBox = new System.Windows.Forms.TextBox();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.MenuPanel = new System.Windows.Forms.Panel();
            this.ExitButton = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.UserMessageTable)).BeginInit();
            this.ToolForUserTablePanel.SuspendLayout();
            this.MenuPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.Font = new System.Drawing.Font("微软雅黑", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.button1.Location = new System.Drawing.Point(65, 116);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(287, 61);
            this.button1.TabIndex = 0;
            this.button1.Text = "读者个人信息管理";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // button2
            // 
            this.button2.Font = new System.Drawing.Font("微软雅黑", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.button2.Location = new System.Drawing.Point(258, 218);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(287, 61);
            this.button2.TabIndex = 1;
            this.button2.Tag = "";
            this.button2.Text = "读者图书的借还情况";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // UserMessageTable
            // 
            this.UserMessageTable.BackgroundColor = System.Drawing.Color.White;
            this.UserMessageTable.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.UserMessageTable.CellBorderStyle = System.Windows.Forms.DataGridViewCellBorderStyle.Raised;
            this.UserMessageTable.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewCellStyle1.Alignment = System.Windows.Forms.DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle1.BackColor = System.Drawing.SystemColors.Window;
            dataGridViewCellStyle1.Font = new System.Drawing.Font("微软雅黑", 10.5F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            dataGridViewCellStyle1.ForeColor = System.Drawing.SystemColors.ControlText;
            dataGridViewCellStyle1.SelectionBackColor = System.Drawing.SystemColors.Highlight;
            dataGridViewCellStyle1.SelectionForeColor = System.Drawing.SystemColors.HighlightText;
            dataGridViewCellStyle1.WrapMode = System.Windows.Forms.DataGridViewTriState.False;
            this.UserMessageTable.DefaultCellStyle = dataGridViewCellStyle1;
            this.UserMessageTable.Location = new System.Drawing.Point(0, 108);
            this.UserMessageTable.Name = "UserMessageTable";
            this.UserMessageTable.RowTemplate.Height = 30;
            this.UserMessageTable.RowTemplate.ReadOnly = true;
            this.UserMessageTable.Size = new System.Drawing.Size(1043, 621);
            this.UserMessageTable.TabIndex = 3;
            this.UserMessageTable.Visible = false;
            // 
            // SearchtextBox
            // 
            this.SearchtextBox.Font = new System.Drawing.Font("微软雅黑", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.SearchtextBox.Location = new System.Drawing.Point(15, 11);
            this.SearchtextBox.Name = "SearchtextBox";
            this.SearchtextBox.Size = new System.Drawing.Size(175, 31);
            this.SearchtextBox.TabIndex = 4;
            this.SearchtextBox.Text = "按用户名或者ID查询";
            // 
            // SearchButton
            // 
            this.SearchButton.Font = new System.Drawing.Font("微软雅黑", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.SearchButton.Location = new System.Drawing.Point(258, 5);
            this.SearchButton.Name = "SearchButton";
            this.SearchButton.Size = new System.Drawing.Size(94, 42);
            this.SearchButton.TabIndex = 5;
            this.SearchButton.Text = "查询";
            this.SearchButton.UseVisualStyleBackColor = true;
            this.SearchButton.Click += new System.EventHandler(this.SearchButton_Click);
            // 
            // AddButton
            // 
            this.AddButton.Font = new System.Drawing.Font("微软雅黑", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.AddButton.Location = new System.Drawing.Point(588, 5);
            this.AddButton.Name = "AddButton";
            this.AddButton.Size = new System.Drawing.Size(133, 42);
            this.AddButton.TabIndex = 6;
            this.AddButton.Text = "增加用户信息";
            this.AddButton.UseVisualStyleBackColor = true;
            this.AddButton.Click += new System.EventHandler(this.AddButton_Click);
            // 
            // DeleteButton
            // 
            this.DeleteButton.Font = new System.Drawing.Font("微软雅黑", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.DeleteButton.Location = new System.Drawing.Point(258, 64);
            this.DeleteButton.Name = "DeleteButton";
            this.DeleteButton.Size = new System.Drawing.Size(94, 42);
            this.DeleteButton.TabIndex = 7;
            this.DeleteButton.Text = "删除";
            this.DeleteButton.UseVisualStyleBackColor = true;
            this.DeleteButton.Click += new System.EventHandler(this.DeleteButton_Click);
            // 
            // MotifyButton
            // 
            this.MotifyButton.Font = new System.Drawing.Font("微软雅黑", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.MotifyButton.Location = new System.Drawing.Point(588, 64);
            this.MotifyButton.Name = "MotifyButton";
            this.MotifyButton.Size = new System.Drawing.Size(133, 42);
            this.MotifyButton.TabIndex = 8;
            this.MotifyButton.Text = "修改用户信息";
            this.MotifyButton.UseVisualStyleBackColor = true;
            this.MotifyButton.Click += new System.EventHandler(this.MotifyButton_Click);
            // 
            // ToolForUserTablePanel
            // 
            this.ToolForUserTablePanel.Controls.Add(this.button4);
            this.ToolForUserTablePanel.Controls.Add(this.MotifyTextBox);
            this.ToolForUserTablePanel.Controls.Add(this.textBox1);
            this.ToolForUserTablePanel.Controls.Add(this.MotifyButton);
            this.ToolForUserTablePanel.Controls.Add(this.DeleteButton);
            this.ToolForUserTablePanel.Controls.Add(this.AddButton);
            this.ToolForUserTablePanel.Controls.Add(this.SearchButton);
            this.ToolForUserTablePanel.Controls.Add(this.SearchtextBox);
            this.ToolForUserTablePanel.Location = new System.Drawing.Point(0, 1);
            this.ToolForUserTablePanel.Name = "ToolForUserTablePanel";
            this.ToolForUserTablePanel.Size = new System.Drawing.Size(1028, 144);
            this.ToolForUserTablePanel.TabIndex = 9;
            this.ToolForUserTablePanel.Visible = false;
            // 
            // button4
            // 
            this.button4.Font = new System.Drawing.Font("微软雅黑", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.button4.Location = new System.Drawing.Point(821, 64);
            this.button4.Name = "button4";
            this.button4.Size = new System.Drawing.Size(75, 42);
            this.button4.TabIndex = 1;
            this.button4.Text = "返回";
            this.button4.UseVisualStyleBackColor = true;
            this.button4.Click += new System.EventHandler(this.button4_Click);
            // 
            // MotifyTextBox
            // 
            this.MotifyTextBox.Font = new System.Drawing.Font("微软雅黑", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.MotifyTextBox.Location = new System.Drawing.Point(425, 70);
            this.MotifyTextBox.Name = "MotifyTextBox";
            this.MotifyTextBox.Size = new System.Drawing.Size(119, 31);
            this.MotifyTextBox.TabIndex = 9;
            this.MotifyTextBox.Text = "按用户ID修改";
            // 
            // textBox1
            // 
            this.textBox1.Font = new System.Drawing.Font("微软雅黑", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.textBox1.Location = new System.Drawing.Point(15, 70);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(175, 31);
            this.textBox1.TabIndex = 8;
            this.textBox1.Text = "按用户名或者ID删除";
            // 
            // MenuPanel
            // 
            this.MenuPanel.Controls.Add(this.ExitButton);
            this.MenuPanel.Controls.Add(this.button1);
            this.MenuPanel.Controls.Add(this.button2);
            this.MenuPanel.Location = new System.Drawing.Point(-1, -13);
            this.MenuPanel.Name = "MenuPanel";
            this.MenuPanel.Size = new System.Drawing.Size(1043, 629);
            this.MenuPanel.TabIndex = 10;
            // 
            // ExitButton
            // 
            this.ExitButton.Font = new System.Drawing.Font("微软雅黑", 10.5F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.ExitButton.Location = new System.Drawing.Point(852, 473);
            this.ExitButton.Name = "ExitButton";
            this.ExitButton.Size = new System.Drawing.Size(102, 61);
            this.ExitButton.TabIndex = 3;
            this.ExitButton.Text = "返回";
            this.ExitButton.UseVisualStyleBackColor = true;
            this.ExitButton.Click += new System.EventHandler(this.ExitButton_Click);
            // 
            // Form2
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 18F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(1040, 617);
            this.Controls.Add(this.ToolForUserTablePanel);
            this.Controls.Add(this.MenuPanel);
            this.Controls.Add(this.UserMessageTable);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Name = "Form2";
            this.Text = "管理用户信息";
            this.FormClosed += new System.Windows.Forms.FormClosedEventHandler(this.Form2_FormClosed);
            this.Load += new System.EventHandler(this.Form2_Load);
            ((System.ComponentModel.ISupportInitialize)(this.UserMessageTable)).EndInit();
            this.ToolForUserTablePanel.ResumeLayout(false);
            this.ToolForUserTablePanel.PerformLayout();
            this.MenuPanel.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.DataGridView UserMessageTable;
        private System.Windows.Forms.TextBox SearchtextBox;
        private System.Windows.Forms.Button SearchButton;
        private System.Windows.Forms.Button AddButton;
        private System.Windows.Forms.Button DeleteButton;
        private System.Windows.Forms.Button MotifyButton;
        private System.Windows.Forms.Panel ToolForUserTablePanel;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.Panel MenuPanel;
        private System.Windows.Forms.Button ExitButton;
        private System.Windows.Forms.TextBox MotifyTextBox;
        private System.Windows.Forms.Button button4;
    }
}