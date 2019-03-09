package ������ʵ���;

import javax.swing.*;
import java.io.*;
import java.awt.Frame;
import java.awt.event.*;


public class MyPanel extends JPanel implements ActionListener,CallBack 
{
	private JLabel label1 = new JLabel("    Դ�ļ�·��:");
	private JLabel label2 = new JLabel("Ŀ���ļ�·��:");
	private JTextField textfield1 = new JTextField(28);
	private JTextField textfield2 = new JTextField(28);
	private JButton button1 = new JButton("ѡ��");
	private JButton button2 = new JButton("ѡ��");
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
			if(fileChooser.showOpenDialog(this) == 0)//�򿪶Ի���
			{
				textfield1.setText(fileChooser.getSelectedFile().getPath());
				//System.out.println(textfield1.getText());
			}
		}
		else if(e.getSource() == button2)
		{
			if(fileChooser.showSaveDialog(this) == 0)//����Ի���
			{
				textfield2.setText(fileChooser.getSelectedFile().getPath());
				//System.out.println(textfield2.getText());
			}
		}
		else if(e.getSource() == buttonCopy) 
		{
			//�������������������FileUtils��copy����
			//��Դ�ļ���Ŀ���ļ�·�����кϷ�����֤
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


