package edu.hit.java.exp1.hit1173710105;

import java.util.*;


public class BMI
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		String[] ids = null;// 存储学生学号
		String[] names = null;// 存储学生姓名
		float[] heights = null;// 存储学生身高
		float[] weights = null;// 存储学生体重
		float[] bmis = null;// 存储学生bmi值
		String[] PhysicalCondition = null;//用于记录学生的健康状况
		int[] sortedIndex = null;// 该数组中保存了进行排序的数组排序后的下标
		int i;
		
		int num = 0;// 确认学生数目
		Scanner scanner = new Scanner(System.in);
		System.out.print("请输入学生数目:");
		num = scanner.nextInt();
		
		//创建数组，确认数组的长度
		ids = new String[num];
		names = new String[num];
		heights = new float[num];
		weights = new float[num];
		bmis = new float[num];
		sortedIndex = new int[num];
		PhysicalCondition = new String[num];
		
		do
		{
			i = menu();// 获取用户想执行功能
			switch (i)
			{
			case 1:
				//输入学生信息
				BMI.inputStudents(ids, names, heights, weights, bmis, sortedIndex, PhysicalCondition,num);
				break;
			case 2:
				//打印学生信息
				BMI.printStudents(ids, names, heights, weights, bmis, sortedIndex, PhysicalCondition);
				break;
			case 3:
				//按学号从小到大排序
				BMI.sortByID(ids, sortedIndex);
				break;
			case 4:
				//按姓名从小到大排序
				BMI.sortByName(names, sortedIndex);
				break;
			case 5:
				//按身高从小到大排序
				BMI.sortByHeights(heights, sortedIndex);
				break;
			case 6:
				//按体重从小到大排序
				BMI.sortByWeights(weights, sortedIndex);
				break;
			case 7:
				//按BMI从小到大排序
				BMI.sortByBMI(bmis, sortedIndex);
				break;
			case 8:
				//程序结束，退出
				System.out.println("It is the end!");
				scanner.close();
				System.exit(0);
				break;
			default:
				break;
			}
		} while (true);
	}

	/**
	 * 用户通过键盘输入学生信息 所接收到的信息将会存储到数组之中
	 */
	public static void inputStudents(String[] ids, String[] names, float[] heights, float[] weights, float[] bmis,
			int[] sortedIndex, String[] PhysicalCondition,int num)
	{

		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < num; i++)
		{
			System.out.println("现请输入第"+(i+1)+"名学生的信息:");
			System.out.print("请输入学生学号:");
			ids[i] = scanner.next();
			System.out.print("请输入学生姓名:");
			names[i] = scanner.next();
			System.out.print("请输入学生身高(单位m):");
			heights[i] = scanner.nextFloat();
			System.out.print("请输入学生体重(单位kg):");
			weights[i] = scanner.nextFloat();
			bmis[i] = weights[i] / (heights[i] * heights[i]);
			PhysicalCondition[i] = checkHealth(bmis[i]);
		}
		// 默认顺序
		for (int i = 0; i < sortedIndex.length; i++)
		{
			sortedIndex[i] = i;
		}		
	}

	/**
	 * 按格式，按顺序打印学生信息*/
	public static void printStudents(String[] ids, String[] names, float[] heights, float[] weights, float[] bmis,
			int[] sortedIndex, String[] PhysicalCondition)
	{
		if (ids == null)
		{
			System.out.println("非法操作，请输入学生信息之后再进行此项操作");
		}
		System.out.println("学生信息总览:");
		System.out.println("学号" + "\t" + "姓名" + "\t" + " 身高" + "\t" + " 体重" + "\t" + "BMI指数" + "\t" + "    健康状况");
		//sortedIndex数组里面存储了经从小大排序以后的数组下标
		//按这个小标打印学生信息
		for (int i = 0; i < sortedIndex.length; i++)
		{
			System.out.print(ids[sortedIndex[i]] + "\t");//打印学号
			System.out.print(names[sortedIndex[i]] + "\t");//打印姓名
			System.out.print(String.format("%.2f",heights[sortedIndex[i]])+"\t");//打印身高
			System.out.print(String.format("%.2f",weights[sortedIndex[i]]) + "\t");//打印体重
			System.out.print(String.format("%.2f",bmis[sortedIndex[i]]) + "\t");//打印BMI的值
			System.out.println(PhysicalCondition[sortedIndex[i]]);//打印健康状况
		}
	}

	/**
	 * 打印菜单，并且获取用户输入
	 * 返回这个输入所对应的功能的索引*/
	public static int menu()
	{
		String num = null;// 获取用户想执行功能
		int i = 8;// 获取用户想执行功能
		boolean flag = true;// 错误输入标志;
		Scanner scanner = new Scanner(System.in);
		do
		{
			if (flag)// 只打印一次菜单，避免重复
			{
				BMI.printMenuMessage();
			}
			num = scanner.nextLine();
			// 检查非法输入，如果用户输入非数字，这再次输入
			try
			{
				i = Integer.parseInt(num);
				flag = true;
			} 
			catch (Exception e)
			{
				flag = false;
			}
			if (!flag)
			{
				System.out.println("输入有误，请重新输入:");
			}
		} while (!flag || i > 8 || i < 0);

		return i;
	}

	/**
	 * 打印菜单信息*/
	public static void printMenuMessage()
	{
		System.out.println("本程序功能：");
		System.out.println("*注:在实现其他功能之前，请先输入学生信息");
		System.out.println("1.输入学生信息");
		System.out.println("2.打印学生信息");
		System.out.println("3.按学号进行小到大排序");
		System.out.println("4.按姓名进行小到大排序");
		System.out.println("5.按身高进行小到大排序");
		System.out.println("6.按体重进行小到大排序");
		System.out.println("7.按BMI进行小到大排序");
		System.out.println("8.退出程序");
	}

	/**
	 * 根据每个学生的BMI的值判断其健康状况
	 * 返回一个代表健康状况的字符标识符*/
	public static String checkHealth(float bmis)
	{
		String HealthType = null;// 记录学生的健康类型
		if (bmis < 18.5)
		{
			HealthType = "Underweight";//体重过轻
		} else if (18.5 <= bmis && bmis < 23)
		{
			HealthType = "Normal Range";//正常范围
		} else if (23 <= bmis && bmis < 25)
		{
			HealthType = "Overweight-At Risk";//有肥胖的趋势
		} else if (25 <= bmis && bmis < 30)
		{
			HealthType = "Overweight-Moderately Obese";//超重
		} else if (30 <= bmis)
		{
			HealthType = "Overweight-Severely Obese";//严重超重
		} else
		{
		}
		return HealthType;
	}

	/**
	 * 按ID从小到大排序，将排序结果存放到数组sortedIndex里面*/
	public static void sortByID(String[] ids, int[] sortedIndex)
	{
		if (ids == null)//判断是否是非法操作
		{
			System.out.println("非法操作，请输入学生信息之后再进行此项操作");
		}
		String[] temp = ids.clone();//将数组复制一份，以免误改原本数组
		String minValue = "9999999999";// 记录最小值
		int minSub = 0;// 记录最小值的下标
		int k = 0;// 用于sortedIndex下标自增
		for (int j = 0; j < ids.length; j++)
		{
			for (int i = 0; i < ids.length; i++)
			{
				if (temp[i] != null)//判断参与比较的数据是否是合法的，如果不合法，就剔除掉，不参与比较
				{
					if (temp[i].compareTo(minValue) <= 0)//在数组里面找最小值，并且记录下标
					{
						minValue = temp[i];//迭代最小值
						minSub = i;//记录当前最小值下标
					}
				}
			}
			temp[minSub] = null;//将数组里面最小的值剔除，不再参与比较
			minValue = "9999999999";//重设最小值
			sortedIndex[k] = minSub;//将最小值下标按顺序记录在数组sortedIndex中
			k++;
		}
	}

	/**
	 * 按姓名从小到大排序，将排序结果存放到数组sortedIndex里面*/
	public static void sortByName(String[] names, int[] sortedIndex)
	{
		if (names == null)//判断是否是非法操作
		{
			System.out.println("非法操作，请输入学生信息之后再进行此项操作");
		}
		String[] temp = names.clone();//将数组复制一份，以免误改原本数组
		String minValue = "zzzzzzzz";// 记录最小值
		int minSub = 0;// 记录最小值的下标
		int k = 0;// 用于sortedIndex下标自增
		for (int j = 0; j < names.length; j++)
		{
			for (int i = 0; i < names.length; i++)
			{
				if (temp[i] != null)//判断参与比较的数据是否是合法的，如果不合法，就剔除掉，不参与比较
				{
					if (temp[i].compareTo(minValue) <= 0)//在数组里面找最小值，并且记录下标
					{
						minValue = temp[i];//迭代最小值
						minSub = i;//记录当前最小值下标
					}
				}
			}
			temp[minSub] = null;//将数组里面最小的值剔除，不再参与比较
			minValue = "zzzzzzzz";//重设最小值
			sortedIndex[k] = minSub;//将最小值下标按顺序记录在数组sortedIndex中
			k++;
		}
	}
	
	/**
	 * 按身高从小到大排序，将排序结果存放到数组sortedIndex里面*/
	public static void sortByHeights(float[] heights, int[] sortedIndex)
	{
		if (heights == null)//判断是否是非法操作
		{
			System.out.println("非法操作，请输入学生信息之后再进行此项操作");
		}
		float[] temp = heights.clone();//将数组复制一份，以免误改原本数组
		float minValue = 10f;// 记录最小值
		int minSub = 0;// 记录最小值的下标
		int k = 0;// 用于sortedIndex下标自增
		for (int j = 0; j < heights.length; j++)
		{
			for (int i = 0; i < heights.length; i++)
			{
				if (temp[i] > 0 )//判断参与比较的数据是否是合法的，如果不合法，就剔除掉，不参与比较
				{
					if (temp[i] <= minValue)//在数组里面找最小值，并且记录下标
					{
						minValue = temp[i];//迭代最小值
						minSub = i;//记录当前最小值下标
					}
				}
			}
			temp[minSub] = -1f;//将数组里面最小的值剔除，不再参与比较
			minValue = 10;//重设最小值
			sortedIndex[k] = minSub;//将最小值下标按顺序记录在数组sortedIndex中
			k++;
		}
	}

	public static void sortByBMI(float[] bmis, int[] sortedIndex)
	{
		if (bmis == null)//判断是否是非法操作
		{
			System.out.println("非法操作，请输入学生信息之后再进行此项操作");
		}
		float[] temp = bmis.clone();//将数组复制一份，以免误改原本数组
		float minValue = 100f;// 记录最小值
		int minSub = 0;// 记录最小值的下标
		int k = 0;// 用于sortedIndex下标自增
		for (int j = 0; j < bmis.length; j++)
		{
			for (int i = 0; i < bmis.length; i++)
			{
				if (temp[i] > 0)//判断参与比较的数据是否是合法的，如果不合法，就剔除掉，不参与比较
				{
					if (temp[i] <= minValue)//在数组里面找最小值，并且记录下标
					{
						minValue = temp[i];//迭代最小值
						minSub = i;//记录当前最小值下标
					}
				}
			}
			temp[minSub] = -1f;//将数组里面最小的值剔除，不再参与比较
			minValue = 100f;//重设最小值
			sortedIndex[k] = minSub;//将最小值下标按顺序记录在数组sortedIndex中
			k++;
		}
	}

	public static void sortByWeights(float[] weights, int[] sortedIndex)
	{
		if (weights == null)//判断是否是非法操作
		{
			System.out.println("非法操作，请输入学生信息之后再进行此项操作");
		}
		float[] temp = weights.clone();//将数组复制一份，以免误改原本数组
		float minValue = 1000f;// 记录最小值
		int minSub = 0;// 记录最小值的下标
		int k = 0;// 用于sortedIndex下标自增
		for (int j = 0; j < weights.length; j++)
		{
			for (int i = 0; i < weights.length; i++)
			{
				if (temp[i]>0)//判断参与比较的数据是否是合法的，如果不合法，就剔除掉，不参与比较
				{
					if (temp[i] <= minValue)//在数组里面找最小值，并且记录下标
					{
						minValue = temp[i];//迭代最小值
						minSub = i;//记录当前最小值下标
					}
				}
			}
			temp[minSub] = -1f;//将数组里面最小的值剔除，不再参与比较
			minValue = 1000f;//重设最小值
			sortedIndex[k] = minSub;//将最小值下标按顺序记录在数组sortedIndex中
			k++;
		}
	}
}
