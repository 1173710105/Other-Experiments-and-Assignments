package 第三次实验课;

import javax.swing.*;
import java.io.*;
import java.awt.Frame;
import java.awt.event.*;


public class MyPanel extends JPanel implements ActionListener,CallBack 
{
	private JLabel label1 = new JLabel("    源文件路径:");
	private JLabel label2 = new JLabel("目标文件路径:");
	private JTextField textfield1 = new JTextField(28);
	private JTextField textfield2 = new JTextField(28);
	private JButton button1 = new JButton("选择");
	private JButton button2 = new JButton("选择");
	private JButton buttonCopy = new JButton("Copy");
	private JFileChooser fileChooser = new JFileChooser();
	
	private JProgressBar progressBar = new JProgressBar();

	
	public MyPanel()
	{
		
		add(label1);    add(textfield1);    add(button1);
		add(label2);	add(textfield2);	add(button2);
		add(buttonCopy);    add(progressBar);
		
		progressBar.setStringPainted(true);
		button1.addActionListener(this);
		button2.addActionListener(this);
		buttonCopy.addActionListener(this);

	}


	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == button1)
		{
			if(fileChooser.showOpenDialog(this) == 0)//打开对话框
			{
				textfield1.setText(fileChooser.getSelectedFile().getPath());
				//System.out.println(textfield1.getText());
			}
		}
		else if(e.getSource() == button2)
		{
			if(fileChooser.showSaveDialog(this) == 0)//保存对话框
			{
				textfield2.setText(fileChooser.getSelectedFile().getPath());
				//System.out.println(textfield2.getText());
			}
		}
		else if(e.getSource() == buttonCopy) 
		{
			//构造输入输出流，调用FileUtils的copy函数
			//对源文件和目标文件路径进行合法性验证
			try
			{
				FileInputStream in = new FileInputStream(textfield1.getText());
				FileOutputStream out = new FileOutputStream(textfield2.getText());
				FileUtils fileUtils = new FileUtils(MyPanel.this);
				fileUtils.copy(in, out, 1024*1024);
			}
			catch (IOException e1)
			{
				e1.printStackTrace();
			}
			
		}
	}
	@Override
	public void tellPercent(int percent)
	{
		progressBar.setValue(percent);
	}
}


