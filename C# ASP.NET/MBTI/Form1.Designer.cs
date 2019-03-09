namespace MBTI测试版2
{
    partial class Form1
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要修改
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.richTextBox1 = new System.Windows.Forms.RichTextBox();
            this.first = new System.Windows.Forms.Button();
            this.second = new System.Windows.Forms.Button();
            this.third = new System.Windows.Forms.Button();
            this.fourth = new System.Windows.Forms.Button();
            this.EIXT = new System.Windows.Forms.Button();
            this.Type = new System.Windows.Forms.Button();
            this.latitude_type = new System.Windows.Forms.RichTextBox();
            this.rule = new System.Windows.Forms.Button();
            this.enter_the_text = new System.Windows.Forms.Button();
            this.rull_text = new System.Windows.Forms.RichTextBox();
            this.test = new System.Windows.Forms.Label();
            this.answer1 = new System.Windows.Forms.RadioButton();
            this.answer2 = new System.Windows.Forms.RadioButton();
            this.previous = new System.Windows.Forms.Button();
            this.next_test = new System.Windows.Forms.Button();
            this.a_weight = new System.Windows.Forms.TextBox();
            this.b_weight = new System.Windows.Forms.TextBox();
            this.answer3 = new System.Windows.Forms.RadioButton();
            this.text_result1 = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.comfirm1 = new System.Windows.Forms.Button();
            this.type_result = new System.Windows.Forms.Button();
            this.type_detail = new System.Windows.Forms.Button();
            this.endoftheprog = new System.Windows.Forms.Button();
            this.type_text = new System.Windows.Forms.RichTextBox();
            this.SuspendLayout();
            // 
            // button1
            // 
            this.button1.FlatAppearance.BorderSize = 2;
            this.button1.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.button1.Font = new System.Drawing.Font("微软雅黑", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.button1.Image = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.button1.Location = new System.Drawing.Point(30, 113);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(218, 52);
            this.button1.TabIndex = 0;
            this.button1.Text = "MBTI测试简介";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // button2
            // 
            this.button2.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.button2.FlatAppearance.BorderSize = 3;
            this.button2.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.button2.Font = new System.Drawing.Font("微软雅黑", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.button2.Location = new System.Drawing.Point(87, 212);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(246, 50);
            this.button2.TabIndex = 1;
            this.button2.Text = "点击进入MBTI测试 ";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // richTextBox1
            // 
            this.richTextBox1.BackColor = System.Drawing.Color.White;
            this.richTextBox1.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.richTextBox1.Font = new System.Drawing.Font("宋体", 11F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.richTextBox1.Location = new System.Drawing.Point(3, 3);
            this.richTextBox1.Name = "richTextBox1";
            this.richTextBox1.ReadOnly = true;
            this.richTextBox1.ScrollBars = System.Windows.Forms.RichTextBoxScrollBars.None;
            this.richTextBox1.Size = new System.Drawing.Size(877, 545);
            this.richTextBox1.TabIndex = 2;
            this.richTextBox1.Text = "";
            this.richTextBox1.Visible = false;
            this.richTextBox1.TextChanged += new System.EventHandler(this.richTextBox1_TextChanged);
            // 
            // first
            // 
            this.first.BackColor = System.Drawing.Color.White;
            this.first.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.first.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.first.Font = new System.Drawing.Font("微软雅黑", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.first.Location = new System.Drawing.Point(30, 280);
            this.first.Name = "first";
            this.first.Size = new System.Drawing.Size(122, 46);
            this.first.TabIndex = 3;
            this.first.Text = "第一维度";
            this.first.UseVisualStyleBackColor = false;
            this.first.Visible = false;
            this.first.Click += new System.EventHandler(this.first_Click);
            // 
            // second
            // 
            this.second.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.second.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.second.Font = new System.Drawing.Font("微软雅黑", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.second.Location = new System.Drawing.Point(126, 346);
            this.second.Name = "second";
            this.second.Size = new System.Drawing.Size(122, 46);
            this.second.TabIndex = 4;
            this.second.Text = "第二维度";
            this.second.UseVisualStyleBackColor = true;
            this.second.Visible = false;
            this.second.Click += new System.EventHandler(this.button4_Click);
            // 
            // third
            // 
            this.third.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.third.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.third.Font = new System.Drawing.Font("微软雅黑", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.third.Location = new System.Drawing.Point(211, 412);
            this.third.Name = "third";
            this.third.Size = new System.Drawing.Size(122, 46);
            this.third.TabIndex = 5;
            this.third.Text = "第三纬度";
            this.third.UseVisualStyleBackColor = true;
            this.third.Visible = false;
            this.third.Click += new System.EventHandler(this.third_Click);
            // 
            // fourth
            // 
            this.fourth.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.fourth.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.fourth.Font = new System.Drawing.Font("微软雅黑", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.fourth.Location = new System.Drawing.Point(305, 475);
            this.fourth.Name = "fourth";
            this.fourth.Size = new System.Drawing.Size(121, 46);
            this.fourth.TabIndex = 6;
            this.fourth.Text = "第四维度";
            this.fourth.UseVisualStyleBackColor = true;
            this.fourth.Visible = false;
            this.fourth.Click += new System.EventHandler(this.fourth_Click);
            // 
            // EIXT
            // 
            this.EIXT.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.EIXT.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.EIXT.Location = new System.Drawing.Point(688, 453);
            this.EIXT.Name = "EIXT";
            this.EIXT.Size = new System.Drawing.Size(147, 37);
            this.EIXT.TabIndex = 7;
            this.EIXT.Text = "返回上一层";
            this.EIXT.UseVisualStyleBackColor = true;
            this.EIXT.Visible = false;
            this.EIXT.Click += new System.EventHandler(this.button7_Click);
            // 
            // Type
            // 
            this.Type.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.Type.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.Type.Font = new System.Drawing.Font("微软雅黑", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.Type.Location = new System.Drawing.Point(620, 81);
            this.Type.Name = "Type";
            this.Type.Size = new System.Drawing.Size(215, 46);
            this.Type.TabIndex = 8;
            this.Type.Text = "十六种性格类型";
            this.Type.UseVisualStyleBackColor = true;
            this.Type.Visible = false;
            this.Type.Click += new System.EventHandler(this.Type_Click);
            // 
            // latitude_type
            // 
            this.latitude_type.BackColor = System.Drawing.Color.White;
            this.latitude_type.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.latitude_type.Font = new System.Drawing.Font("黑体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.latitude_type.Location = new System.Drawing.Point(3, 3);
            this.latitude_type.Name = "latitude_type";
            this.latitude_type.ReadOnly = true;
            this.latitude_type.ScrollBars = System.Windows.Forms.RichTextBoxScrollBars.None;
            this.latitude_type.Size = new System.Drawing.Size(877, 545);
            this.latitude_type.TabIndex = 9;
            this.latitude_type.Text = "";
            this.latitude_type.Visible = false;
            this.latitude_type.TextChanged += new System.EventHandler(this.类型以及维度_TextChanged);
            // 
            // rule
            // 
            this.rule.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.rule.FlatAppearance.BorderSize = 2;
            this.rule.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.rule.Font = new System.Drawing.Font("微软雅黑", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.rule.Location = new System.Drawing.Point(52, 114);
            this.rule.Name = "rule";
            this.rule.Size = new System.Drawing.Size(215, 52);
            this.rule.TabIndex = 10;
            this.rule.Text = "测试须知";
            this.rule.UseVisualStyleBackColor = true;
            this.rule.Visible = false;
            this.rule.Click += new System.EventHandler(this.rule_Click);
            // 
            // enter_the_text
            // 
            this.enter_the_text.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.enter_the_text.FlatAppearance.BorderSize = 2;
            this.enter_the_text.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.enter_the_text.Font = new System.Drawing.Font("微软雅黑", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.enter_the_text.Location = new System.Drawing.Point(126, 192);
            this.enter_the_text.Name = "enter_the_text";
            this.enter_the_text.Size = new System.Drawing.Size(215, 53);
            this.enter_the_text.TabIndex = 11;
            this.enter_the_text.Text = "进入MBTI测试";
            this.enter_the_text.UseVisualStyleBackColor = true;
            this.enter_the_text.Visible = false;
            this.enter_the_text.Click += new System.EventHandler(this.enter_the_text_Click);
            // 
            // rull_text
            // 
            this.rull_text.BackColor = System.Drawing.Color.White;
            this.rull_text.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.rull_text.Font = new System.Drawing.Font("黑体", 11F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.rull_text.Location = new System.Drawing.Point(3, 3);
            this.rull_text.Name = "rull_text";
            this.rull_text.ReadOnly = true;
            this.rull_text.ScrollBars = System.Windows.Forms.RichTextBoxScrollBars.None;
            this.rull_text.Size = new System.Drawing.Size(877, 545);
            this.rull_text.TabIndex = 12;
            this.rull_text.Text = "";
            this.rull_text.Visible = false;
            this.rull_text.TextChanged += new System.EventHandler(this.rull_text_TextChanged);
            // 
            // test
            // 
            this.test.AutoSize = true;
            this.test.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(200)))), ((int)(((byte)(237)))), ((int)(((byte)(204)))));
            this.test.Font = new System.Drawing.Font("微软雅黑", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.test.Location = new System.Drawing.Point(12, 48);
            this.test.Name = "test";
            this.test.Size = new System.Drawing.Size(94, 36);
            this.test.TabIndex = 13;
            this.test.Text = "label1";
            this.test.Visible = false;
            // 
            // answer1
            // 
            this.answer1.AutoSize = true;
            this.answer1.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.answer1.Location = new System.Drawing.Point(41, 92);
            this.answer1.Name = "answer1";
            this.answer1.Size = new System.Drawing.Size(189, 35);
            this.answer1.TabIndex = 14;
            this.answer1.TabStop = true;
            this.answer1.Text = "radioButton1";
            this.answer1.UseVisualStyleBackColor = true;
            this.answer1.Visible = false;
            this.answer1.CheckedChanged += new System.EventHandler(this.answer1_CheckedChanged);
            // 
            // answer2
            // 
            this.answer2.AutoSize = true;
            this.answer2.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.answer2.Location = new System.Drawing.Point(41, 151);
            this.answer2.Name = "answer2";
            this.answer2.Size = new System.Drawing.Size(189, 35);
            this.answer2.TabIndex = 15;
            this.answer2.TabStop = true;
            this.answer2.Text = "radioButton2";
            this.answer2.UseVisualStyleBackColor = true;
            this.answer2.Visible = false;
            this.answer2.CheckedChanged += new System.EventHandler(this.answer2_CheckedChanged);
            // 
            // previous
            // 
            this.previous.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.previous.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.previous.Location = new System.Drawing.Point(65, 361);
            this.previous.Name = "previous";
            this.previous.Size = new System.Drawing.Size(102, 46);
            this.previous.TabIndex = 16;
            this.previous.Text = "上一题";
            this.previous.UseVisualStyleBackColor = true;
            this.previous.Visible = false;
            this.previous.Click += new System.EventHandler(this.previous_Click);
            // 
            // next_test
            // 
            this.next_test.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.next_test.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.next_test.Location = new System.Drawing.Point(201, 361);
            this.next_test.Name = "next_test";
            this.next_test.Size = new System.Drawing.Size(98, 46);
            this.next_test.TabIndex = 18;
            this.next_test.Text = "下一题";
            this.next_test.UseVisualStyleBackColor = true;
            this.next_test.Visible = false;
            this.next_test.Click += new System.EventHandler(this.next_test_Click);
            // 
            // a_weight
            // 
            this.a_weight.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.a_weight.Location = new System.Drawing.Point(186, 210);
            this.a_weight.Name = "a_weight";
            this.a_weight.Size = new System.Drawing.Size(72, 39);
            this.a_weight.TabIndex = 19;
            this.a_weight.Visible = false;
            this.a_weight.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.a_weight_KeyPress);
            // 
            // b_weight
            // 
            this.b_weight.BackColor = System.Drawing.Color.White;
            this.b_weight.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.b_weight.Location = new System.Drawing.Point(186, 268);
            this.b_weight.Name = "b_weight";
            this.b_weight.Size = new System.Drawing.Size(72, 39);
            this.b_weight.TabIndex = 20;
            this.b_weight.Visible = false;
            this.b_weight.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.b_weight_KeyPress);
            // 
            // answer3
            // 
            this.answer3.AutoSize = true;
            this.answer3.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.answer3.Location = new System.Drawing.Point(41, 210);
            this.answer3.Name = "answer3";
            this.answer3.Size = new System.Drawing.Size(159, 35);
            this.answer3.TabIndex = 21;
            this.answer3.TabStop = true;
            this.answer3.Text = "自定义权重";
            this.answer3.UseVisualStyleBackColor = true;
            this.answer3.Visible = false;
            this.answer3.CheckedChanged += new System.EventHandler(this.answer3_CheckedChanged);
            // 
            // text_result1
            // 
            this.text_result1.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.text_result1.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.text_result1.Location = new System.Drawing.Point(331, 361);
            this.text_result1.Name = "text_result1";
            this.text_result1.Size = new System.Drawing.Size(121, 46);
            this.text_result1.TabIndex = 22;
            this.text_result1.Text = "查看结果";
            this.text_result1.UseVisualStyleBackColor = true;
            this.text_result1.Visible = false;
            this.text_result1.Click += new System.EventHandler(this.text_result_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(200)))), ((int)(((byte)(237)))), ((int)(((byte)(204)))));
            this.label1.Location = new System.Drawing.Point(158, 214);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(31, 31);
            this.label1.TabIndex = 23;
            this.label1.Text = "A";
            this.label1.Visible = false;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(200)))), ((int)(((byte)(237)))), ((int)(((byte)(204)))));
            this.label2.Location = new System.Drawing.Point(160, 270);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(29, 31);
            this.label2.TabIndex = 24;
            this.label2.Text = "B";
            this.label2.Visible = false;
            // 
            // comfirm1
            // 
            this.comfirm1.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.comfirm1.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.comfirm1.Location = new System.Drawing.Point(289, 228);
            this.comfirm1.Name = "comfirm1";
            this.comfirm1.Size = new System.Drawing.Size(75, 44);
            this.comfirm1.TabIndex = 25;
            this.comfirm1.Text = "确认";
            this.comfirm1.UseVisualStyleBackColor = true;
            this.comfirm1.Visible = false;
            this.comfirm1.Click += new System.EventHandler(this.comfirm1_Click);
            // 
            // type_result
            // 
            this.type_result.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.type_result.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.type_result.Location = new System.Drawing.Point(30, 74);
            this.type_result.Name = "type_result";
            this.type_result.Size = new System.Drawing.Size(170, 53);
            this.type_result.TabIndex = 26;
            this.type_result.Text = "查看性格类型";
            this.type_result.UseVisualStyleBackColor = true;
            this.type_result.Visible = false;
            this.type_result.Click += new System.EventHandler(this.type_result_Click);
            // 
            // type_detail
            // 
            this.type_detail.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.type_detail.Enabled = false;
            this.type_detail.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.type_detail.Location = new System.Drawing.Point(126, 158);
            this.type_detail.Name = "type_detail";
            this.type_detail.Size = new System.Drawing.Size(170, 53);
            this.type_detail.TabIndex = 27;
            this.type_detail.Text = "性格类型详解";
            this.type_detail.UseVisualStyleBackColor = true;
            this.type_detail.Visible = false;
            this.type_detail.Click += new System.EventHandler(this.type_detail_Click);
            // 
            // endoftheprog
            // 
            this.endoftheprog.BackgroundImage = global::MBTI测试版2.Properties.Resources.选项背景图;
            this.endoftheprog.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.endoftheprog.Location = new System.Drawing.Point(229, 239);
            this.endoftheprog.Name = "endoftheprog";
            this.endoftheprog.Size = new System.Drawing.Size(170, 53);
            this.endoftheprog.TabIndex = 28;
            this.endoftheprog.Text = "退出程序";
            this.endoftheprog.UseVisualStyleBackColor = true;
            this.endoftheprog.Visible = false;
            this.endoftheprog.Click += new System.EventHandler(this.endoftheprog_Click);
            // 
            // type_text
            // 
            this.type_text.BackColor = System.Drawing.Color.White;
            this.type_text.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.type_text.Font = new System.Drawing.Font("黑体", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.type_text.Location = new System.Drawing.Point(3, 3);
            this.type_text.Name = "type_text";
            this.type_text.ReadOnly = true;
            this.type_text.ScrollBars = System.Windows.Forms.RichTextBoxScrollBars.None;
            this.type_text.Size = new System.Drawing.Size(877, 545);
            this.type_text.TabIndex = 29;
            this.type_text.Text = "";
            this.type_text.Visible = false;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(14F, 31F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(878, 544);
            this.Controls.Add(this.endoftheprog);
            this.Controls.Add(this.type_detail);
            this.Controls.Add(this.type_result);
            this.Controls.Add(this.comfirm1);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.text_result1);
            this.Controls.Add(this.answer3);
            this.Controls.Add(this.b_weight);
            this.Controls.Add(this.a_weight);
            this.Controls.Add(this.next_test);
            this.Controls.Add(this.previous);
            this.Controls.Add(this.answer2);
            this.Controls.Add(this.answer1);
            this.Controls.Add(this.test);
            this.Controls.Add(this.Type);
            this.Controls.Add(this.third);
            this.Controls.Add(this.fourth);
            this.Controls.Add(this.second);
            this.Controls.Add(this.first);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.enter_the_text);
            this.Controls.Add(this.rule);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.EIXT);
            this.Controls.Add(this.rull_text);
            this.Controls.Add(this.type_text);
            this.Controls.Add(this.richTextBox1);
            this.Controls.Add(this.latitude_type);
            this.Cursor = System.Windows.Forms.Cursors.Default;
            this.Font = new System.Drawing.Font("微软雅黑", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedToolWindow;
            this.Margin = new System.Windows.Forms.Padding(5);
            this.Name = "Form1";
            this.Text = "MBTI测试";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.RichTextBox richTextBox1;
        private System.Windows.Forms.Button first;
        private System.Windows.Forms.Button second;
        private System.Windows.Forms.Button third;
        private System.Windows.Forms.Button fourth;
        private System.Windows.Forms.Button EIXT;
        private System.Windows.Forms.Button Type;
        private System.Windows.Forms.RichTextBox latitude_type;
        private System.Windows.Forms.Button rule;
        private System.Windows.Forms.Button enter_the_text;
        private System.Windows.Forms.RichTextBox rull_text;
        private System.Windows.Forms.Label test;
        private System.Windows.Forms.RadioButton answer1;
        private System.Windows.Forms.RadioButton answer2;
        private System.Windows.Forms.Button previous;
        private System.Windows.Forms.Button next_test;
        private System.Windows.Forms.TextBox a_weight;
        private System.Windows.Forms.TextBox b_weight;
        private System.Windows.Forms.RadioButton answer3;
        private System.Windows.Forms.Button text_result1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button comfirm1;
        private System.Windows.Forms.Button type_result;
        private System.Windows.Forms.Button type_detail;
        private System.Windows.Forms.Button endoftheprog;
        private System.Windows.Forms.RichTextBox type_text;
    }
}

