package 第二次作业;

import java.sql.*;
import java.util.*;

public class AdminTool
{
	public static void Menu()
	{
		System.out.println("1.��ѯ����ѧ����Ϣ");
		System.out.println("2.ѧ����Ϣ��ѯ����ѧ�ţ�");
		System.out.println("3.����ѧ����Ϣ");
		System.out.println("4.ɾ��ѧ����Ϣ����ѧ��ɾ����");
		System.out.println("5.�޸�ѧ����Ϣ��ѧ�Ų��ܸģ�");
		System.out.println("0.�˳�");
	}

	public static void Rigister(String UserName, String Code)
	{
		Scanner in = new Scanner(System.in);
		boolean help = true;
		do
		{
			System.out.print("�������û���:");
			if (in.hasNext() || help)
			{
				UserName = in.next();
				help = false;
				if (UserName.equals("root"))
				{
					System.out.println("�û���������");
					System.out.println("���ٴ�����:");
				} else
					break;
			} else
			{
				System.out.println("����������ٴ�����");
			}
		} while (true);
		help = true;
		do
		{
			System.out.print("����������:");
			if (in.hasNext() || help)
			{
				Code = in.next();
				help = false;
				if (!Code.equals("123"))
				{
					System.out.println("�������");
				} else
					break;
			} else
				System.out.println("����������ٴ�����");
		} while (true);
		// in.close();
	}

	public static int getChoice(int count)
	{
		Scanner in = new Scanner(System.in);
		boolean help = true;
		int choice = 0;
		while (true)
		{
			System.out.print("���������ѡ��:");
			if (in.hasNextInt() || help)
			{
				choice = in.nextInt();
				if (choice <= count && choice >= 0)
				{
					// in.close();
					return choice;
				} 
				else
				{
					System.out.println("���Ϸ����룬������0~"+count);
				}
				help = false;
			} 
			else
			{
				System.out.println("���Ϸ�����,������0~"+count);
				in.nextLine();
			}
		}
	}

	public static boolean CheckAllStudent(ResultSet resultset)
	{
		String ID = null;
		String name = null;
		String sex = null;
		int age = 0;
		String major = null;
		int flag = 1;
		boolean IsNull = true;
		System.out.println("-------------------------------------------------");
		System.out.println("  ID\t\tName\t  Sex\tAge\tMajor");
		try
		{
			while (resultset.next())
			{
				ID = resultset.getString("id");
				name = resultset.getString("name");
				sex = resultset.getString("sex");
				age = resultset.getInt("age");
				major = resultset.getString("major");

				System.out.print(flag + ".");
				System.out.print(ID + "\t");
				System.out.print(name + "\t  ");
				System.out.print(sex + "\t");
				System.out.print(age + "\t");
				System.out.println(major);				
				flag++;
				IsNull = false;
			}
		} 
		catch (Exception e	)
		{
			// TODO: handle exception
			System.err.println("�������");
			e.printStackTrace();
		}
		if(IsNull)
		{
			System.out.println("                     û�и�ѧ����Ϣ");
			System.out.println("-------------------------------------------------");
			return false;
		}
		System.out.println("-------------------------------------------------");
		return true;
	}

	public static String  GetCondition(String TipStatement,String ErrorStatement)
	{
		Scanner in = new Scanner(System.in);
		String Condition;
		while(true)
		{
			System.out.print(TipStatement);
			try
			{
				Condition = in.nextLine();
				break;
			} 
			catch (	Exception e)
			{
				in.nextLine();
				System.out.println(ErrorStatement);
				System.out.println("���ٴ�����");
			}
		}
		return Condition;
	}
	
	public static String GetOnePart(ResultSet resultset)
	{
		String temp = null;
		
		try
		{
			while (resultset.next())
			{
				 temp = resultset.getString("id");
			}
			return temp;
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public static String AddStudentOfSetDATA(String ID)
	{
		String Name = GetCondition("������ѧ������:", "������������!");
		String Sex = GetCondition("������ѧ���Ա�(m ���� f):","��������");
		String Age;
		int age;
		while (true)
		{
			Age = GetCondition("������ѧ������:", "������������");
			try
			{
				age = Integer.parseInt(Age);
				if(age<0||age>100)
				{
					System.out.println("��������������0~100֮�������");
					continue;
				}
				else
					break;
			}
			catch (Exception e)
			{
				// TODO: handle exception
				System.out.println("��������");
				continue;
			}
		}	
		String Major = GetCondition("������ѧ���Ͷ�רҵ:", "��������");
		
		//String command = "insert into student values (id,name,sex,age,major)"
			//	+ "("+"'"+ID+"',"+"'"+Name+"',"+"'"+Sex+"'"+","+age+","+"'"+Major+"')";
		String command = "insert into student values "+"('"+ID+"',"+"'"+Name+"',"+" '"+Sex+"',"+ age+", '"+Major+"')";
		
		//System.out.println(command);
		return command;	
	}

	public static void FuntionMenu()
	{
		System.out.println("1.�޸�����");
		System.out.println("2.�޸��Ա�");
		System.out.println("3.�޸�����");
		System.out.println("4.�޸�רҵ");
		System.out.println("0.�޸Ľ���");
		System.out.println("���������ѡ��:");	
	}
}
