package edu.hit.java.exp4.hit1173710105;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class GUIFileBMI extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					GUIFileBMI frame = new GUIFileBMI();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUIFileBMI()
	{
		getContentPane().setLayout(null);//使用绝对布局
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//使用 System exit 方法退出应用程序
		setBounds(100, 100, 700, 600);//设置窗体大小
		
		JMenuBar menuBar = new JMenuBar();//增加菜单栏
		setJMenuBar(menuBar);
		
		JMenu HelpMenu = new JMenu("Help");//增加Help菜单
		HelpMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		menuBar.add(HelpMenu);
		JMenuItem About = new JMenuItem("About");//Help菜单中增加About选项
		About.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		HelpMenu.add(About);
		About.addActionListener(new ActionListener()//点击About后弹窗
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "这是一个学生信息管理系统", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JMenu mnNewMenu = new JMenu("Fcuntion");//增加Fcuntion菜单
		mnNewMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		menuBar.add(mnNewMenu);

		
		JMenuItem AllInfo = new JMenuItem("学生信息总览");//增加学生信息总览选项
		AllInfo.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		mnNewMenu.add(AllInfo);
		AllInfo.addActionListener(new ActionListener()//点击后进入总览学生信息界面
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				showStudetns();
				setSize(700, 950);
			}
		});
		
		JMenuItem ModifyStudent = new JMenuItem("修改学生信息");//增加学生信息总览选项
		ModifyStudent.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		mnNewMenu.add(ModifyStudent);
		ModifyStudent.addActionListener(new ActionListener()//点击后进入修改学生信息界面
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				showModify();
				setSize(700, 600);
			}
		});

		JMenuItem AddStudent = new JMenuItem("增加学生信息");//增加 增加学生信息选项
		mnNewMenu.add(AddStudent);
		AddStudent.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		AddStudent.addActionListener(new ActionListener()//点击之后进入增加学生界面
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				showAddStudent();
				setSize(700, 600);
			}
		});

		JMenuItem BMIStatic = new JMenuItem("BMI统计信息");//增加BMI统计信息选项
		mnNewMenu.add(BMIStatic);
		BMIStatic.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		BMIStatic.addActionListener(new ActionListener()//点击之后进入BMI统计信息界面
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				showBMIStatic();
				setSize(1650,1080);
			}
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

}

	private void showStudetns()
	{
		this.setContentPane(new resultPanel());
		setVisible(true);
	}

	private void showModify()
	{
		this.setContentPane(new ModifyPanel());
		setVisible(true);
	}

	private void showAddStudent()
	{
		this.setContentPane(new addStudentPanel());
		setVisible(true);
	}

	private void showBMIStatic()
	{
		this.setContentPane(new BMIStaticPanel());
		setVisible(true);
	}
}

class BMIStaticPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BMIStaticPanel()
	{
		ArrayList<Student> studentlist = new ArrayList<Student>();
		HandleFileTool.readFile(studentlist, "StudentInfo.txt");//从文件之中读取信息，然后把信息放到数组列表studentlist
		
		JLabel AverageLabel = new JLabel("平均值:"+calculateAverageOfBMI(studentlist));//显示平均值
		AverageLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));// 设置标题字体
		AverageLabel.setBounds(300, 30, 150, 30);//设置位置
		
		JLabel MedianLabel = new JLabel("中位数:"+calculateMedian(studentlist));//显示中位数
		MedianLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));// 设置标题字体
		MedianLabel.setBounds(600, 30, 150, 30);//设置位置
		
		JLabel ModeLabel = new JLabel("众数:"+findMode(studentlist));//显示众数
		ModeLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));// 设置标题字体
		ModeLabel.setBounds(800, 30, 150, 30);//设置位置
		
		JLabel VarianceLabel = new JLabel("方差:"+calculateVariance(studentlist));//显示方差
		VarianceLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));// 设置标题字体
		VarianceLabel.setBounds(1100, 30, 150, 30);//设置位置
		
		this.setLayout(null);//绝对布局
		CategoryDataset dataset = getDataSet(studentlist);
		JFreeChart chart = ChartFactory.createBarChart3D("学生BMI值统计数据", // 图表标题
				"BMI值", // 目录轴的显示标签
				"学生人数", // 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图表方向：水平、垂直
				true, // 是否显示图例(对于简单的柱状图必须是false)
				true, // 是否生成工具
				false // 是否生成URL链接
		);
		// 从这里开始
		CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
		CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("黑体", Font.PLAIN, 14)); // 水平底部标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.PLAIN, 12)); // 垂直标
		ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
        BarRenderer3D renderer = new BarRenderer3D();    
        renderer.setItemMargin(0.0000001);
        renderer.setMaximumBarWidth(0.05);//设置柱形宽度
		rangeAxis.setLabelFont(new Font("微软雅黑", Font.PLAIN, 15));
		chart.getLegend().setItemFont(new Font("黑体", Font.PLAIN, 15));
		chart.getTitle().setFont(new Font("宋体", Font.PLAIN, 20));// 设置标题字体
		ChartPanel frame1=new ChartPanel(chart,true);        //这里也可以用chartFrame,可以直接生成一个独立的Frame		
		frame1.setBounds(0,100,1600,830);
		plot.setRenderer(renderer);
		
		this.add(AverageLabel);
		this.add(MedianLabel);
		this.add(ModeLabel);
		this.add(VarianceLabel);
		this.add(frame1);
	}

	/**
	 * 设置数据集，返回dataset，用书柱形图的数据*/
	private CategoryDataset getDataSet(ArrayList<Student> studentlist)
	{
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int[] range = calculateRange(studentlist);//获取每个BMI值范围内有多少人
		dataset.addValue(range[0], "10.5~13.5", "10.5~13.5");
		dataset.addValue(range[1], "13.5~17.0", "13.5~17.0");
		dataset.addValue(range[2], "17.0~20.5", "17.0~20.5");
		dataset.addValue(range[3], "20.5~24.0", "20.5~24.0");
		dataset.addValue(range[4], "24.0~27.5", "24.0~27.5");
		dataset.addValue(range[5], "27.5~31.0", "27.5~31.0");
		dataset.addValue(range[6], "31.0~34.5", "31.0~34.5");
		dataset.addValue(range[7], "34.5~38.0", "34.5~38.0");
		dataset.addValue(range[8], "38.0~41.5", "38.0~41.5");
		dataset.addValue(range[9], "41.5~45.0", "41.5~45.0");
		return dataset;
	}
	
	/**获取每个BMI值范围内有多少人,返回一个一位数组*/
	private int[] calculateRange(ArrayList<Student> studentlist)
	{
		int[] range = new int[10];
		for(int i=0;i<10;i++)
			range[i] = 0;
		Float BMI;
		for(int i=0;i<studentlist.size();i++)
		{
			BMI = studentlist.get(i).getBMI();
			if(10.5<=BMI&&BMI<13.5)
				range[0]++;
			else if(13.5<=BMI&&BMI<17)
				range[1]++;
			else if(17<=BMI&BMI<20.5)
				range[2]++;
			else if(20.5<=BMI&BMI<24)
				range[3]++;
			else if(24<=BMI&BMI<27.5)
				range[4]++;
			else if(27.5<=BMI&BMI<31)
				range[5]++;
			else if(31<=BMI&BMI<34.5)
				range[6]++;
			else if(34.5<=BMI&BMI<38)
				range[7]++;
			else if(38<=BMI&BMI<41.5)
				range[8]++;
			else if(41.5<=BMI&BMI<=45)
				range[9]++;
			else {
			}
		}
		return range;
 	}
	/** 计算所有学生的BMI的平均值 */
	private float calculateAverageOfBMI(ArrayList<Student> studentlist)
	{
		float average = 0;
		for (Student result : studentlist)//用for—loop循环，从数组列表studentlist里面获取每一个实例化对象
			average += result.getBMI();//获取每一个实例化对象对BMI值
		return (Math.round( average / studentlist.size() * 100)) / 100;
	}

	/**
	 * 计算所有学生的BMI的中位数
	 */
	private float calculateMedian(ArrayList<Student> studentlist)
	{
		Student student = new Student();//创建一个新对象，用与创建比较器对象
		//调用Collection.sort()，传进一个重写的比较器，实现对BMI升序排序
		Collections.sort(studentlist, student.new SortByBMIAsc());
		int lenght = studentlist.size();//记录学生人数
		float Median;
		if (lenght % 2 != 0)// 如果bmi数组长度是奇数，即有奇数个学生，则中位数就是数组索引值为length/2的值
		{
			Median = studentlist.get(lenght / 2).getBMI();
		} else // 如果bmi数组长度是偶数，即有偶数个学生，则中位数就是数组索引值为length/2与length/2+1的值的平均值
		{
			Median = (studentlist.get(lenght / 2 + 1).getBMI() + studentlist.get(lenght / 2).getBMI()) / 2;
		}
		return (Math.round( Median * 100)) / 100;
	}

	/**
	 * 用于寻找student对象数组里面所有学生各自BMI值的众数
	 */
	private String findMode(ArrayList<Student> studentlist)
	{

		Student student = new Student();//创建一个新对象，用与创建比较器对象
		//调用Collection.sort()，传进一个重写的比较器，实现对BMI升序排序
		Collections.sort(studentlist, student.new SortByBMIAsc());
		int length = studentlist.size();// 记录学生的个数，即数组BMI的长度
		float[] bmis = new float[length];// 用于获得student对象数组列表里面每个学生的BMI值
		for (int i = 0; i < length; i++)
		{
			bmis[i] = studentlist.get(i).getBMI();//获取象数组列表里面每个学生的BMI值
		}
		float[] num = new float[length];// 用于存储记录不同得BMIS值
		int[] count = new int[length];// 用于记录不同的BMI在数组中出现的次数
		int time = 0;// 用于计数，没出现一个相同的BMI值，自增一次
		int k = 0;// 用于num与count数组下标的自增

		for (int i = 0; i < length; i++)
		{
			if (bmis[i] > 0)// 判断bims[i]里面存放的是否是有用数据
			{
				num[k] = bmis[i];// 记录这个数值
				for (int j = 0; j < length; j++)
				{
					if (bmis[j] > 0 && bmis[j] == num[k])// 判断bims[j]里面存放的是否是有用数据且判断是否与num[k]相等
					{
						time++;// 这个数出现次数加一
						bmis[j] = 0f;// 将这个数置为无用数据，以后不再参与统计
					}
				}
				count[k] = time;// 记录出现次数
				k++;
				time = 0;// 重置计数器
			}
		}

		float max = count[0];
		// 找到出现次数最多的BMI值,若果max = 1，
		// BMI中所有数均只出现一次，每个数都不一样，那么众数不存在
		for (int i = 0; i < count.length; i++)
		{
			if (count[i] > max)
			{
				max = count[i];
			}
		}

		// 找到出现次数最多的数据的下标，并记录下来
		int[] ModeSub = new int[length];
		k = 0;
		for (int i = 0; i < length; i++)
		{
			if (count[i] == max)
			{
				ModeSub[k] = i;
				k++;
			}
		}

		if (max == 1)// BMI中所有数均只出现一次，每个数都不一样，那么众数不存在
		{
			//System.out.println("所有学生的BMI值均只出现一次，众数不存在！");
			return "无众数";
		}

		//System.out.print("\n" + "众数为:");
		String mode = "";
		for (int i = 0; i < length; i++)
		{
			if (ModeSub[i] != 0)// 过滤非法数据
			{
				mode = (Math.round( num[ModeSub[i]] * 100)) / 100 + ",";
			}
		}
		return mode.substring(0, mode.length()-1);
	}

	/**
	 * 计算所有学生的BMI的方差
	 */
	private float calculateVariance(ArrayList<Student> studentlist)
	{
		float average = 0;// 计算所有学生的BMI平均值
		float sum = 0;// 计算总值
		int length = studentlist.size();// 记录学生的个数，即数组BMI的长度
		float[] bmis = new float[length];//用于获得student对象数组列表里面每个学生的BMI值
		for (int i = 0; i < length; i++)
		{
			bmis[i] = studentlist.get(i).getBMI();//获取象数组列表里面每个学生的BMI值
			average = bmis[i];//计算每个学生的BMI值总和
		}
		average = average / length;//计算出平均值
		for (int i = 0; i < length; i++)
		{
			sum += (bmis[i] - average) * (bmis[i] - average);// 迭代计算出方差
		}
		return (Math.round( sum / length * 100)) / 100;
	}
	
}

class addStudentPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	public addStudentPanel()
	{
		ArrayList<Student> studentlist = new ArrayList<Student>();
		HandleFileTool.readFile(studentlist, "StudentInfo.txt");//从文件之中读取信息，然后把信息放到数组列表studentlist

		this.setLayout(null);
		JTextField IDjtf = new JTextField();
		IDjtf.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		IDjtf.setBounds(120, 40, 250, 30);//设置位置

		JTextField Namejtf = new JTextField();
		Namejtf.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Namejtf.setBounds(120, 90, 250, 30);//设置位置
		
		JTextField Heightjtf = new JTextField();
		Heightjtf.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Heightjtf.setBounds(120, 140, 250, 30);//设置位置

		JTextField Weightjtf = new JTextField();
		Weightjtf.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Weightjtf.setBounds(120, 190, 250, 30);//设置位置

		JTextField BMIjtf = new JTextField();
		BMIjtf.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		BMIjtf.setBounds(120, 230, 250, 30);//设置位置
		BMIjtf.setEditable(false);
		JTextField PyhsicalConditionjtf = new JTextField();
		PyhsicalConditionjtf.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		PyhsicalConditionjtf.setBounds(120, 270, 250, 30);//设置位置
		PyhsicalConditionjtf.setEditable(false);//设置为不能被选中

		JLabel IDLabel = new JLabel("   ID:");
		IDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		IDLabel.setBounds(50, 40, 250, 30);
		
		JLabel NameLabel = new JLabel("姓名:");
		NameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		NameLabel.setBounds(50, 90, 250, 30);
		JLabel HeightLabel = new JLabel("身高:");
		HeightLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		HeightLabel.setBounds(50, 140, 250, 30);
		JLabel WeightLabel = new JLabel("体重:");
		WeightLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		WeightLabel.setBounds(50, 190, 250, 30);
		JLabel BMILabel = new JLabel("BMI值:");
		BMILabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		BMILabel.setBounds(35, 230, 250, 30);
		JLabel PyhsicalConditionLabel = new JLabel("健康状况:");
		PyhsicalConditionLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		PyhsicalConditionLabel.setBounds(20, 270, 250, 30);

		JButton comfirm = new JButton();
		comfirm.setText("增加");
		comfirm.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		comfirm.setBounds(120, 310, 100, 30);
		comfirm.setEnabled(false);//在检测合法之前不能增加学生
		comfirm.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String id = IDjtf.getText();
				String name = Namejtf.getText();
				String height = Heightjtf.getText();
				String weight = Weightjtf.getText();
				Student student = new Student(id, name, Float.parseFloat(height), Float.parseFloat(weight));
				studentlist.add(student);
				HandleFileTool.saveFile(studentlist, "StudentInfo.txt");//将增加的学生数组保存文件之中
				BMIjtf.setText(student.getBMI() + "");
				PyhsicalConditionjtf.setText(student.getPhysicalCondition());
				JOptionPane.showMessageDialog(null, "增加学生信息成功", "Message", JOptionPane.INFORMATION_MESSAGE);
				comfirm.setEnabled(false);
			}
		});

		JButton islegal = new JButton();
		islegal.setText("合法性检测");
		islegal.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		islegal.setBounds(250, 310, 140, 30);
		islegal.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String id = IDjtf.getText();
				boolean flag1 = true;
				boolean flag2 = true;
				boolean flag3 = true;
				for (int i = 0; i < studentlist.size(); i++)
				{
					//数据合法性检测，检测学号是否是重复，检测身高体重是否是在一定范围之内的浮点数
					if (id.equals(studentlist.get(i).getID()))
					{
						JOptionPane.showMessageDialog(null, "ID已经被占用，请重新输入ID", "ERROR", JOptionPane.ERROR_MESSAGE);
						flag1 = false;
						break;
					}
				}
				if (flag1)
				{
					try
					{
						float height1 = Float.parseFloat(Heightjtf.getText());
						float weight1 = Float.parseFloat(Weightjtf.getText());
						flag2 = true;
						if (!isLegalNum(height1, 1.5f, 2.0f) || !isLegalNum(weight1, 45f, 100f))//是否已在一定范围之内的浮点数
						{
							JOptionPane.showMessageDialog(null, "数据不合法，体重和身高数据不合理", "Warning",
									JOptionPane.WARNING_MESSAGE);
							flag3 = false;
						}

					} 
					catch (Exception e2)
					{
						flag2 = false;
						JOptionPane.showMessageDialog(null, "数据不合法，体重和身高要求输入浮点数", "Warning",
								JOptionPane.WARNING_MESSAGE);//不合法数据，弹窗显示错误
					}
				}
				if (flag1 && flag2 && flag3)
				{
					JOptionPane.showMessageDialog(null, "数据合法", "Message", JOptionPane.INFORMATION_MESSAGE);
					comfirm.setEnabled(true);
				}
			}
		});

		this.add(IDjtf);
		this.add(Namejtf);
		this.add(Heightjtf);
		this.add(Weightjtf);
		this.add(IDLabel);
		this.add(NameLabel);
		this.add(HeightLabel);
		this.add(WeightLabel);
		this.add(PyhsicalConditionLabel);
		this.add(PyhsicalConditionjtf);
		this.add(BMIjtf);
		this.add(BMILabel);
		this.add(comfirm);
		this.add(islegal);

	}

	private boolean isLegalNum(float num, float min, float max)
	{
		if (num <= max && num >= min)
			return true;
		else
			return false;
	}
}

/**
 * 学生信息总览，里面包括增加学生信息，按指定顺序输出信息的功能*/
class resultPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	public resultPanel()
	{
		this.setSize(150, 80);
		JTextArea textarea = new JTextArea("", 50, 60);
		ArrayList<Student> studentlist = new ArrayList<Student>();
		HandleFileTool.readFile(studentlist, "StudentInfo.txt");
		StringBuffer sb = new StringBuffer();
		sb.append("学号\t姓名\t身高\t体重\tBMI值\t健康状况\n");
		//往TextArea里面写书数据
		for (Student st : studentlist)
		{
			sb.append(st.toString()).append("\n");
		}
		textarea.setText(sb.toString());
		textarea.setEditable(false);
		JScrollPane sp = new JScrollPane(textarea);
		sp.setBounds(5, 5, 500, 300);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JButton addBtn = new JButton("增加数据");
		addBtn.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		addBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String[][] data = HandleStudentInfo.genStudents(studentlist);//增加学生，将增加的学生信息返回到一个二维数组之中
				//将新增加的数据追加到TextArea末尾
				for (int i = 0; i < data.length; i++)
				{
					for (int j = 0; j < data[i].length - 1; j++)
					{
						textarea.append(data[i][j] + "\t");
					}
					textarea.append(data[i][5] + "\n");
				}
			}
		});

		//下来列表，让用户排序方式，根据选择执行相应的排序方式
		JComboBox<String> cBox = new JComboBox<String>(
				new String[] { "请选择排序方式", "按学号从小到大排序", "按姓名从小到大排序", "按身高从小到大排序", "按体重从小到大排序", "按BMI值从小到大排序" });
		cBox.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		cBox.addItemListener(new ItemListener()
		{

			@Override
			public void itemStateChanged(ItemEvent e)
			{
				Student student = new Student();
				switch ((String) e.getItem())
				{
				case "按学号从小到大排序":
					Collections.sort(studentlist, student.new SortByIDAsc());//按学号从小到大对数组列表studentlist排序
					ChangeTextArea(studentlist, textarea);//更新数组列表
					break;
				case "按姓名从小到大排序":
					Collections.sort(studentlist, student.new SortByNameAsc());//按姓名从小到大对数组列表studentlist排序
					ChangeTextArea(studentlist, textarea);//更新数组列表
					break;
				case "按身高从小到大排序":
					Collections.sort(studentlist, student.new SortByHeightAsc());//按身高从小到大对数组列表studentlis排序
					ChangeTextArea(studentlist, textarea);//更新数组列表
					break;
				case "按体重从小到大排序":
					Collections.sort(studentlist, student.new SortByWeightAsc());//按体重从小到对数组列表studentlist进行排序
					ChangeTextArea(studentlist, textarea);//更新数组列表
					break;
				case "按BMI值从小到大排序":
					Collections.sort(studentlist, student.new SortByBMIAsc());//按体重从小到对数组列表studentlist进行排序
					ChangeTextArea(studentlist, textarea);//更新数组列表
					break;
				default:
					break;
				}
			}
		});
		this.add(addBtn);
		this.add(cBox);
		this.add(sp);
	}
	
	/**
	 * 更新数组列表，将排序之后的studentlist重新写到textarea之中*/
	private void ChangeTextArea(ArrayList<Student> studentlist, JTextArea textarea)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("学号\t姓名\t身高\t体重\tBMI值\t健康状况\n");
		for (Student st : studentlist)
		{
			sb.append(st.toString()).append("\n");
		}
		textarea.setText(sb.toString());
	}
}

/**
 * 修改学生信息*/
class ModifyPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private String ID;//固定输入学生的ID
	private int sub;//记录这个ID在studentlist中的位置

	public ModifyPanel()
	{

		ArrayList<Student> studentlist = new ArrayList<Student>();
		HandleFileTool.readFile(studentlist, "StudentInfo.txt");//从文件之中读取信息，防止到塑料布studentlist之中

		this.setLayout(null);//绝对布局
		JTextField IDjtf = new JTextField("请输入请修改学生的学号");
		IDjtf.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		IDjtf.setBounds(120, 40, 250, 30);

		JTextField Namejtf = new JTextField();
		Namejtf.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Namejtf.setBounds(120, 90, 250, 30);
		JTextField Heightjtf = new JTextField();
		Heightjtf.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Heightjtf.setBounds(120, 140, 250, 30);

		JTextField Weightjtf = new JTextField();
		Weightjtf.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		Weightjtf.setBounds(120, 190, 250, 30);

		JTextField BMIjtf = new JTextField();
		BMIjtf.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		BMIjtf.setBounds(120, 230, 250, 30);
		BMIjtf.setEditable(false);
		JTextField PyhsicalConditionjtf = new JTextField();
		PyhsicalConditionjtf.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		PyhsicalConditionjtf.setBounds(120, 270, 250, 30);
		PyhsicalConditionjtf.setEditable(false);

		JLabel IDLabel = new JLabel("   ID:");
		IDLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		IDLabel.setBounds(50, 40, 250, 30);
		JLabel NameLabel = new JLabel("姓名:");
		NameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		NameLabel.setBounds(50, 90, 250, 30);
		JLabel HeightLabel = new JLabel("身高:");
		HeightLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		HeightLabel.setBounds(50, 140, 250, 30);
		JLabel WeightLabel = new JLabel("体重:");
		WeightLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		WeightLabel.setBounds(50, 190, 250, 30);
		JLabel BMILabel = new JLabel("BMI值:");
		BMILabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		BMILabel.setBounds(35, 230, 250, 30);
		JLabel PyhsicalConditionLabel = new JLabel("健康状况:");
		PyhsicalConditionLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		PyhsicalConditionLabel.setBounds(20, 270, 250, 30);

		JButton check = new JButton();
		check.setText("查询");
		check.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		check.setBounds(400, 40, 100, 30);
		check.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				String IDtext = IDjtf.getText().toString();
				boolean flag = false;
				if (IDtext != "")//检测IDtext里面是否为空
				{
					for (int i = 0; i < studentlist.size(); i++)
					{
						if (IDtext.equals(studentlist.get(i).getID()))
						{
							flag = true;
							ID = IDtext;//记录学生的ID
							sub = i;//记录该ID学生在数组列表在中的位置
							//将该学生的信息写入到窗口里面的文本框
							Namejtf.setText(studentlist.get(i).getName());
							Heightjtf.setText("" + studentlist.get(i).getHeight());
							Weightjtf.setText("" + studentlist.get(i).getWeight());
							BMIjtf.setText("" + studentlist.get(i).getBMI());
							PyhsicalConditionjtf.setText(studentlist.get(i).getPhysicalCondition());
							break;
						}
					}
					if (!flag)
						JOptionPane.showMessageDialog(null, "查无此人，请重新输入学号", "Warning", JOptionPane.WARNING_MESSAGE);
				} else
				{
					JOptionPane.showMessageDialog(null, "ID不能为空", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		JButton delete = new JButton();
		delete.setText("删除");
		delete.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		delete.setBounds(250, 310, 100, 30);
		delete.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				//弹窗，让用户选择是否确认删除学生
				Object[] options = { "是", "否" };
				int option = JOptionPane.showOptionDialog(null, "是否确认删除该学生信息", "Warning", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				if (option == 0)
				{
					studentlist.remove(sub);//从苏州了表之中移除该学生信息
					//将窗口文本框清空
					IDjtf.setText("");
					Namejtf.setText("");
					Heightjtf.setText("");
					Weightjtf.setText("");
					BMIjtf.setText("");
					PyhsicalConditionjtf.setText("");
					HandleFileTool.saveFile(studentlist, "StudentInfo.txt");//将新数组列表重新存储到TXT文件之中
					JOptionPane.showMessageDialog(null, "删除学生信息成功", "Message", JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		JButton modify = new JButton();
		modify.setText("修改");
		modify.setFont(new Font("微软雅黑", Font.PLAIN, 18));
		modify.setBounds(120, 310, 100, 30);
		modify.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String id = IDjtf.getText();
				String name = Namejtf.getText();
				String height = Heightjtf.getText();
				String weight = Weightjtf.getText();
				boolean flag1 = true;
				boolean flag2 = true;
				if (!id.equals("") && !name.equals("") && !height.equals("") && !weight.equals(""))
				{
					//修改合法性检测，不能修改学生ID
					if (!ID.equals(IDjtf.getText()))
					{
						JOptionPane.showMessageDialog(null, "不能修改学号", "Error", JOptionPane.ERROR_MESSAGE);
					} else
					{
						try
						{
							//检测用户是否输入了浮点型数据，如果不是则是非法数据
							float height1 = Float.parseFloat(Heightjtf.getText());
							float weight1 = Float.parseFloat(Weightjtf.getText());
							flag1 = true;
							if (!isLegalNum(height1, 1.5f, 2.0f) || !isLegalNum(weight1, 45f, 100f))
							{
								JOptionPane.showMessageDialog(null, "数据不合法，体重和身高数据不合理", "Warning",
										JOptionPane.WARNING_MESSAGE);
								flag2 = false;
							}

						} catch (Exception e2)
						{
							flag2 = false;
							JOptionPane.showMessageDialog(null, "数据不合法，体重和身高要求输入浮点数", "Warning",
									JOptionPane.WARNING_MESSAGE);
						}
						if (flag1 && flag2)
						{
							Student student = new Student(id, name, Float.parseFloat(height), Float.parseFloat(weight));
							studentlist.set(sub, student);
							HandleFileTool.saveFile(studentlist, "StudentInfo.txt");//将修改之后的数据重新学鹭岛文件之中
							IDjtf.setText(student.getID());
							Namejtf.setText(student.getName());
							Heightjtf.setText("" + student.getHeight());
							Weightjtf.setText("" + student.getWeight());
							BMIjtf.setText("" + student.getBMI());
							PyhsicalConditionjtf.setText(student.getPhysicalCondition());
							JOptionPane.showMessageDialog(null, "学生信息修改成功", "Message", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				} else
				{
					JOptionPane.showMessageDialog(null, "学生信息不为空", "ERROR", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		this.add(modify);
		this.add(delete);
		this.add(IDjtf);
		this.add(Namejtf);
		this.add(Heightjtf);
		this.add(Weightjtf);
		this.add(IDLabel);
		this.add(NameLabel);
		this.add(HeightLabel);
		this.add(WeightLabel);
		this.add(check);
		this.add(PyhsicalConditionLabel);
		this.add(PyhsicalConditionjtf);
		this.add(BMIjtf);
		this.add(BMILabel);
	}

	/**
	 * 检测输入的数据是否是一个在合法范围之内的浮点数*/
	private boolean isLegalNum(float num, float min, float max)
	{
		if (num <= max && num >= min)
			return true;
		else
			return false;
	}
}

class HandleStudentInfo
{
	/**随机生成两百个学生的信息，存放到数组列表studentlist中*/
	public static String[][] genStudents(ArrayList<Student> studentlist)
	{
		String id;// 学生ID
		String name;// 学生姓名
		float height;// 学生身高
		float weight;// 学生体重
		String[][] data = new String[200][6];
		for (int i = 0; i < 200; i++)
		{
			do
			{
				id = RandomGenerateID();// 生成学生ID
			} while (isExists(id, studentlist));// 判断学生的ID时候已经存在
			name = RandomGenerateName();// 随机生成学生名字
			height = nextFloat(1.5f, 2.0f);// 生成指定范围1.5~2.0的浮点数
			weight = nextFloat(45f, 100f);// 生成指定范围455~100的浮点数
			// 根据生成的的信息生成一个新的Student对象实例，并增加到数组列表的末尾
			Student student = new Student(id, name, height, weight);
			studentlist.add(student);
			data[i] = student.toArray();
		}
		HandleFileTool.saveFile(studentlist, "StudentInfo.txt");
		return data;
	}

	/**
	 * 生成一个指定范围min~max的浮点数
	 */
	public static float nextFloat(final float min, final float max)
	{
		float a = min + ((max - min) * new Random().nextFloat());
		return (float) (Math.round(a * 100)) / 100;// (这里的100就是2位小数点,如果要其它位,如4位,这里两个100改成10000)

	}

	/**
	 * 随机生成一个201001~211001的学生ID
	 */
	public static String RandomGenerateID()
	{
		Random random = new Random();
		int i = random.nextInt(10000);
		return (201001 + i) + "";
	}

	/**
	 * 随机生成学生的名字
	 */
	public static String RandomGenerateName()
	{
		Random random = new Random();
		String name = "";
		for (int i = 0; i < 5; i++)// 学生名字长度为5
		{
			name += (char) (random.nextInt(26) + 'a');// 随机生成一个0~26的数，计算其余字母a的相对偏移量，得出一个ASCII码字符，连接成一个字符串
		}
		return UpperFirstLetter(name);
	}

	/**
	 * 将字符串的首字母改成大写，其余的字母改成小写，并返回
	 */
	public static String UpperFirstLetter(String name)
	{
		name = name.toLowerCase();
		return name.substring(0, 1).toUpperCase() + name.substring(1);// 将字符串的第一个字母变成大写，并且返回
	}

	/**
	 * 判断ID是否与已经存在，避免重复
	 */
	public static boolean isExists(String id, ArrayList<Student> studentlist)
	{
		for (int i = 0; i < studentlist.size(); i++)
		{
			if (studentlist.get(i) != null)// 数据合法性判断
			{
				if (id.equals(studentlist.get(i).getID()))
				{
					return true;// ID重复返回true
				}
			}
		}
		return false;// ID不重复返回false
	}
}

class HandleFileTool
{
	/**从文件中读取信息，存放到数组列表studentlist中*/
	public static void readFile(ArrayList<Student> studentlist, String string)
	{
		File file = new File(string);
		BufferedReader reader = null;
		ArrayList<String> information = new ArrayList<String>();
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String temp;
			while ((temp = reader.readLine()) != null)//判断是否是文件末尾
			{
				information.add(temp);//把学生信息增加到数组列表information中
			}
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		String[][] strings = new String[1][5];
		//用information数组列表里面的信息创建student实例，然后存放到studenlist中
		for (int i = 0; i < information.size(); i++)
		{
			strings[0] = information.get(i).split(",");//以逗号为风格符，将数据分开
			studentlist.add(new Student(strings[0][0], strings[0][1], Float.parseFloat(strings[0][2]),
					Float.parseFloat(strings[0][3])));
		}
	}

	/**从文件中读取信息，存放到二维数组里面中*/
	public static void readFile(String[][] data, String filename)
	{
		File file = new File(filename);
		BufferedReader reader = null;
		ArrayList<String> information = new ArrayList<String>();
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String temp;
			while ((temp = reader.readLine()) != null)//判断是否读取到文件末尾
			{
				information.add(temp);//把学生信息增加到数组列表information中
			}
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		//用information数组列表里面的信息放置到二维数组data里面
		for (int i = 0; i < information.size(); i++)
		{
			data[i] = information.get(i).split(",");//以逗号未分割符
		}
	}

	/**判断文件里面一共有多少行学生信息*/
	public int HowManyLine(String fileName)
	{
		File file = new File(fileName);
		BufferedReader reader = null;
		int line = 0;
		try
		{
			reader = new BufferedReader(new FileReader(file));
			while (reader.readLine() != null)//判断是否读取到文件末尾
			{
				line++;
			}
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		} 
		finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				} catch (IOException e1)
				{
				}
			}
		}
		return line;
	}

	/**将数组列表里面为数据存放到studentlist中*/
	public static void saveFile(ArrayList<Student> studentlist, String filename)
	{
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream(filename, false);
			PrintWriter pWriter = new PrintWriter(fileOutputStream);
			String StudentInfo;
			for (int i = 0; i < studentlist.size(); i++)
			{
				StudentInfo = studentlist.get(i).getID() + "," + studentlist.get(i).getName() + ","
						+ studentlist.get(i).getHeight() + "," + studentlist.get(i).getWeight() + ","
						+ studentlist.get(i).getBMI() + "," + studentlist.get(i).getPhysicalCondition();
				pWriter.println(StudentInfo); //以逗号未分割符，存放到文件之中
			}
			pWriter.close();
			fileOutputStream.close();
		} catch (IOException e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

class Student
{
	private String id;// 学生学号
	private String name;// 学生姓名
	private float height;// 学生身高
	private float weight;// 学生体重
	private float bmi;// 学生bmi值
	private String physicalCondition;// 学生健康状况

	public Student()// 空构造方法，用于创建对象，调用比较器
	{
	}

	public Student(String id, String name, float height, float weight)// 构造方法，初始化数据
	{
		this.id = id;
		this.name = name;
		this.height = height;
		this.weight = weight;
		bmi = calculateBMI(height, weight);// 计算出根据输入的数据计算出学生BMI
		physicalCondition = checkHealth(bmi);// 根据BMI推算出学生的健康状况
	}

	//将学生类实例里面的私有域变成一个一维数组
	public String[] toArray()
	{
		String[] data = new String[6];
		data[0] = id;
		data[1] = name;
		data[2] = String.valueOf(getHeight());
		data[3] = String.valueOf(getWeight());
		data[4] = String.valueOf(getBMI());
		data[5] = physicalCondition;
		return data;
	}

	@Override
	/** 返回学生的个人信息 */
	public String toString()
	{
		return id + "\t" + name + "\t" + height + "\t" + weight + "\t" + getBMI() + "\t" + physicalCondition;
	}

	/** 计算出学生的bmi */
	private float calculateBMI(float height, float weight)
	{
		return weight / (height * height);
	}

	/** 返回私有域id，即学生的学号 */
	public String getID()
	{
		return id;
	}

	/** 返回私有域BMI */
	public float getBMI()
	{
		return (float) (Math.round(bmi * 100)) / 100;
	}

	/** 返回私有域height，即学生的身高 */
	public float getHeight()
	{
		return (float) (Math.round(height * 100)) / 100;
	}

	/** 返回私有域weight，即学生的体重 */
	public float getWeight()
	{
		return (float) (Math.round(weight * 100)) / 100;
	}

	/** 返回私有域name，即学生的姓名 */
	public String getName()
	{
		return name;
	}

	/** 返回私有域PhysicalCondition，即学生的健康状况 */
	public String getPhysicalCondition()
	{
		return physicalCondition;
	}

	/** 根据学生bmi退出其健康状况 */
	public String checkHealth(float bmis)
	{
		String HealthType = null;// 记录学生的健康类型
		if (bmis < 18.5)
		{
			HealthType = "Underweight";// 体重过轻
		} else if (18.5 <= bmis && bmis < 23)
		{
			HealthType = "Normal Range";// 正常范围
		} else if (23 <= bmis && bmis < 25)
		{
			HealthType = "Overweight-At Risk";// 有肥胖的趋势
		} else if (25 <= bmis && bmis < 30)
		{
			HealthType = "Overweight-Moderately Obese";// 超重
		} else if (30 <= bmis)
		{
			HealthType = "Overweight-Severely Obese";// 严重超重
		} else
		{
		}
		return HealthType;
	}

	/**
	 * Comparator接口，重写compare方法，实现按ID值对学生进行升序排序
	 */
	class SortByIDAsc implements Comparator<Student>
	{
		public int compare(Student o1, Student o2)
		{
			return o1.getID().compareTo(o2.getID());
		}
	}

	/**
	 * Comparator接口，重写compare方法，实现按ID值对学生进行降序排序
	 */
	class SortByIDDsc implements Comparator<Student>
	{
		public int compare(Student o1, Student o2)
		{
			return o2.getID().compareTo(o1.getID());
		}
	}

	/**
	 * Comparator接口，重写compare方法，实现按姓名值对学生进行升序排序
	 */
	class SortByNameAsc implements Comparator<Student>
	{
		public int compare(Student s1, Student s2)
		{
			return s1.getName().compareTo(s2.getName());
		}
	}

	/**
	 * Comparator接口，重写compare方法，实现按姓名值对学生进行降序排序
	 */
	class SortByNameDsc implements Comparator<Student>
	{
		public int compare(Student s1, Student s2)
		{
			return s2.getName().compareTo(s1.getName());
		}
	}

	/**
	 * Comparator接口，重写compare方法，实现按身高值对学生进行升序排序
	 */
	class SortByHeightAsc implements Comparator<Student>
	{
		public int compare(Student s1, Student s2)
		{
			if (s1.getHeight() < s2.getHeight())
				return -1;
			return 1;
		}
	}

	/**
	 * Comparator接口，重写compare方法，实现按身高值对学生进行降序排序
	 */
	class SortByHeightDsc implements Comparator<Student>
	{
		public int compare(Student s1, Student s2)
		{
			if (s2.getHeight() < s1.getHeight())
				return -1;
			return 1;
		}
	}

	/**
	 * Comparator接口，重写compare方法，实现按体重值对学生进行升序排序
	 */
	class SortByWeightAsc implements Comparator<Student>
	{
		public int compare(Student s1, Student s2)
		{
			if (s1.getWeight() < s2.getWeight())
				return -1;
			return 1;
		}
	}

	/**
	 * Comparator接口，重写compare方法，实现按体重值对学生进行降序排序
	 */
	class SortByWeightDsc implements Comparator<Student>
	{
		public int compare(Student s1, Student s2)
		{
			if (s2.getWeight() < s1.getWeight())
				return -1;
			return 1;
		}
	}

	/**
	 * Comparator接口，重写compare方法，实现按BMI值对学生进行升序排序
	 */
	class SortByBMIAsc implements Comparator<Student>
	{
		public int compare(Student s1, Student s2)
		{
			if (s1.getBMI() < s2.getBMI())
				return -1;
			return 1;
		}
	}

	/**
	 * Comparator接口，重写compare方法，实现按BMI值对学生进行降序排序
	 */
	class SortByBMIDsc implements Comparator<Student>
	{
		public int compare(Student s1, Student s2)
		{
			if (s2.getBMI() < s1.getBMI())
				return -1;
			return 1;
		}
	}
}
