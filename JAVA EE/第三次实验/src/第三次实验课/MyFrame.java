package ������ʵ���;

import javax.swing.JFrame;

public class MyFrame extends JFrame
{
	public MyFrame()
	{
		setTitle("XCopy");//���ô��ڱ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,160);//���ô��ڴ�С
		add(new MyPanel());//��MyPanel�ŵ�MyFrame��
		setResizable(false);
	}
}
