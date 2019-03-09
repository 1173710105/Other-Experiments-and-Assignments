package edu.hit.java.exp4.hit1173710105;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;


import java.awt.event.*;
import java.io.*;
import java.util.*;

public class GUIFileBMI extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model = null;
	private ArrayList<Student> studentlist = new ArrayList<Student>();
	private JTable table;
	private JTableHeader head;
	private JScrollPane jScrollPane;
	private JButton addBtn;
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
		
		HandleFileTool handle = new HandleFileTool();
		handle.readFile(studentlist, "StudentInfo.txt");//先将数组保留到Arraylist里面
		this.setLayout(null);
		
		setFont(new Font("微软雅黑", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 513);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu helpmenu = new JMenu("Help");
		helpmenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		menuBar.add(helpmenu);

		JMenuItem aboutitem = new JMenuItem("About");
		aboutitem.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 16));
		aboutitem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "这是一个学生信息管理系统", "功能提示", JOptionPane.PLAIN_MESSAGE);
			}
		});

		helpmenu.add(aboutitem);
		
	    JMenu FunctionMenu = new JMenu("Function");
		FunctionMenu.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		menuBar.add(FunctionMenu);
		
		JMenuItem AllInfo = new JMenuItem("学生信息总览");
		AllInfo.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		FunctionMenu.add(AllInfo);
		AllInfo.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String[][] datas = new String[handle.HowManyLine("StudentInfo.txt")][6];
				handle.readFile(datas, "StudentInfo.txt");
				String[] titles = new String[] { "学号", "姓名", "身高", "体重","BMI值","健康状况"};
				model = new DefaultTableModel(datas, titles);
				table = new JTable(model);
				head = table.getTableHeader(); // 创建表格标题对象
		        head.setFont(new Font("微软雅黑", Font.PLAIN, 20));// 设置表格字体
		        table.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		        table.setRowHeight(20);// 设置
		        table.setEnabled(false);
		        addBtn = new JButton("添加数据");
				addBtn.setFont(new Font("微软雅黑", Font.PLAIN, 20));
				addBtn.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						String[][] datas = HandleStudentInfo.genStudents(studentlist);
						for(int i =0;i<datas.length;i++)
							model.addRow(datas[i]);
					}
				});

				//getContentPane().add(addBtn, BorderLayout.NORTH);
				jScrollPane = new JScrollPane(table);
				//getContentPane().add(jScrollPane);
				JPanel jPanel1 = new JPanel();
				setSize(1500, 800);
				setLocationRelativeTo(null);
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				setVisible(true);
				getContentPane().setVisible(true);
				table.setVisible(true);
				head.setVisible(true);
				jScrollPane.setVisible(true);
				addBtn.setVisible(true);
				jPanel1.setVisible(true);
				jPanel1.add(table);
				jPanel1.add(jScrollPane);
				jPanel1.setSize(1500,800);
			}
		});
		
		JMenuItem addStudent = new JMenuItem("增加学生信息");
		addStudent.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		FunctionMenu.add(addStudent);
		addStudent.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				table.setVisible(false);
				head.setVisible(false);
				jScrollPane.setVisible(false);
				addBtn.setVisible(false);
			}
		});
		
		JMenuItem ModifyStudent = new JMenuItem("修改学生信息");
		ModifyStudent.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		FunctionMenu.add(ModifyStudent);
		
		JMenuItem BMIStatic = new JMenuItem("BMI统计信息");
		BMIStatic.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 18));
		FunctionMenu.add(BMIStatic);
		
	}

}

class HandleFileTool
{
	public void readFile(ArrayList<Student> studentlist, String filename)
	{
		File file = new File(filename);
		BufferedReader reader = null;
		ArrayList<String> information = new ArrayList<String>();
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String temp;
			while ((temp = reader.readLine()) != null)
			{
				information.add(temp);
			}
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		String[][] strings = new String[1][5];
		for (int i = 0; i < information.size(); i++)
		{
			strings[0] = information.get(i).split(",");
			studentlist.add(new Student(strings[0][0], strings[0][1], Float.parseFloat(strings[0][2]),
					Float.parseFloat(strings[0][3])));
		}
	}

	public void readFile(String[][] data, String filename)
	{
		File file = new File(filename);
		BufferedReader reader = null;
		ArrayList<String> information = new ArrayList<String>();
		try
		{
			reader = new BufferedReader(new FileReader(file));
			String temp;
			while ((temp = reader.readLine()) != null)
			{
				information.add(temp);
			}
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		for (int i = 0; i < information.size(); i++)
		{
			data[i] = information.get(i).split(",");
		}
	}

	public int HowManyLine(String fileName)
	{
		File file = new File(fileName);
		BufferedReader reader = null;
		int line = 0;
		try
		{
			reader = new BufferedReader(new FileReader(file));
			while (reader.readLine() != null)
			{
				line++;
			}
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
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
	
	public static void saveFile(ArrayList<Student> studentlist)
	{
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream("StudentInfo.txt",false);
			PrintWriter pWriter = new PrintWriter(fileOutputStream);
			String StudentInfo;
			for(int i = 0; i<studentlist.size(); i++)
			{
				StudentInfo = studentlist.get(i).getID()+","+studentlist.get(i).getName()+","+
							   studentlist.get(i).getHeight()+","+studentlist.get(i).getWeight()+","
							   +studentlist.get(i).getBMI()+","+studentlist.get(i).getPhysicalCondition();
				pWriter.println(StudentInfo);
			}
			pWriter.close();
			fileOutputStream.close();
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
class HandleStudentInfo
{
	public static String[][] genStudents(ArrayList<Student> studentlist)
	{
		String id;// 学生ID
		String name;// 学生姓名
		float height;// 学生身高
		float weight;// 学生体重	
		String[][] data = new String[200][6];
		for(int i =0;i<200;i++)
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
		HandleFileTool.saveFile(studentlist);
		return data;
	}
	
	/**
	 * 生成一个指定范围min~max的浮点数
	 */
	public static float nextFloat(final float min, final float max)
	{
		float a = min + ((max - min) * new Random().nextFloat());
		return (float)(Math.round(a*100))/100;//(这里的100就是2位小数点,如果要其它位,如4位,这里两个100改成10000)

	}

	/**
	 * 随机生成一个201001~211001的学生ID*/
	public static String RandomGenerateID()
	{
		Random random = new Random();
		int i = random.nextInt(10000);
		return (201001+i)+"";
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
	 * 将字符串的首字母改成大写，其余的字母改成小写，并返回*/
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
		return id + "\t" + name + "\t" + height + "\t" + weight + "\t" + bmi + "\t" + physicalCondition;
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
		return (float)(Math.round(bmi*100))/100;
	}

	/** 返回私有域height，即学生的身高 */
	public float getHeight()
	{
		return (float)(Math.round(height*100))/100;
	}

	/** 返回私有域weight，即学生的体重 */
	public float getWeight()
	{
		return (float)(Math.round(weight*100))/100;
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
