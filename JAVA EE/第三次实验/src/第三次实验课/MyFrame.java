package 第三次实验课;

import javax.swing.JFrame;

public class MyFrame extends JFrame
{
	public MyFrame()
	{
		setTitle("XCopy");//设置窗口标题
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,160);//设置窗口大小
		add(new MyPanel());//将MyPanel放到MyFrame上
		setResizable(false);
	}
}
