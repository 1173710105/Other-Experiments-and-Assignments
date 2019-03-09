package BANK;

import java.io.*;
import java.util.*;


public class Test
{

	public static void main(String[] args) throws IOException
	{

		
		/*
		 USER user1 = new USER(100000,201000,"123","ADMIN"); 
		 USER user2 = new USER(10000,201001,"123","HQ"); 
		 USER user3 = new USER(10000,201002,"123","HQ"); 
		 USER user4 = new USER(10000,201003,"123","HQ");
		 USER user5 = new USER(10000,201004,"123","HQ");
		 USER user6 = new USER(10000,201005,"123","HQ");
		 USER user7 = new USER(10000,201006,"123","HQ");
		 USER user8 = new USER(10000,201007,"123","HQ");
		 USER user9 = new USER(10000,201008,"123","HQ"); 
		 USER user10 = new USER(10000,201009,"123","HQ");
		 user1.OutPutToFile(); 
		 user2.OutPutToFile();
		 user3.OutPutToFile(); 
		 user4.OutPutToFile(); 
		 user5.OutPutToFile();
		 user6.OutPutToFile();
		 user7.OutPutToFile();
		 user8.OutPutToFile();
		 user9.OutPutToFile(); 
		 user10.OutPutToFile(); 
		 USER user11 = new USER();
		 user11.OutPutToFile();*/
		
		/*System.out.println(HowManyLine("UserInfo.txt"));
		String[] strings = new String[HowManyLine("UserInfo.txt")+1];
		readFileByLines("UserInfo.txt", strings);
		for(int i=0;i<strings.length;i++)
		{
			System.out.println(strings[i]);
		}
		GregorianCalendar calendar = */
		
		/*GregorianCalendar calendar = new GregorianCalendar(2018,2,1,7,30,0);
		System.out.println(calendar.get(calendar.YEAR));
		System.out.println(calendar.get(calendar.MONTH));
		System.out.println(calendar.get(calendar.DAY_OF_MONTH));
		System.out.println(calendar.get(calendar.HOUR_OF_DAY));
		System.out.println(calendar.get(calendar.MINUTE));
		System.out.println(calendar.get(calendar.SECOND));*/
		
		/*GregorianCalendar calendar2 = new GregorianCalendar();
		SetCalendar(calendar2);
		System.out.println(calendar2.get(calendar2.YEAR));
		System.out.println(calendar2.get(calendar2.MONTH));
		System.out.println(calendar2.get(calendar2.DAY_OF_MONTH));
		System.out.println(calendar2.get(calendar2.HOUR_OF_DAY));
		System.out.println(calendar2.get(calendar2.MINUTE));
		System.out.println(calendar2.get(calendar2.SECOND));*/
		
		/*GregorianCalendar calendar = new GregorianCalendar();
		SetCalendar(calendar);
		FileOutputStream fileOutputStream = new FileOutputStream("DateData.txt",true);
		PrintWriter pWriter = new PrintWriter(fileOutputStream);
		pWriter.println(calendar.get(calendar.YEAR) 
						+" "+calendar.get(calendar.MONTH)+" "+(calendar.get(calendar.DAY_OF_MONTH))+" "+
						calendar.get(calendar.HOUR_OF_DAY)+" "+calendar.get(calendar.MINUTE)+" "+calendar.get(calendar.SECOND));
		pWriter.close();
		fileOutputStream.close();*/
		
		/*int line = HowManyLine("UserInfo.txt");
		System.out.println(line);
		String[] strings1 = new String[line+1];
		readFileByLines("UserInfo.txt",strings1);
		String[][] strings2 = new String [line][10];
		for(int i=0;i<strings2.length;i++)
		{
			System.out.println(strings1[i]);
		}*/
		
		
		/*for(int i=0,j;i<line;i++)
		{
			j = 0;
			StringTokenizer stringTokenizer = new StringTokenizer(strings[i],"#",false);
			while(stringTokenizer.hasMoreTokens())
			{
				System.out.println(stringTokenizer.nextToken());
				//strings2[j] = stringTokenizer.nextToken().toString();
				//j++;
			}
		}*/
		/*DivideString(strings1, strings2, line);
		for(int i=0;i<line;i++)
		{
			for(int j=0;j<10;j++)
			{
				System.out.println(strings2[i][j]);
			}
		}*/
		
		/*
		Scanner in = new Scanner(System.in);
		int ID;
		while (true)
		{
			
				System.out.print("请输入卡号：");
			    if (in.hasNextInt())
				{
			    	ID = in.nextInt();
			    	System.out.println(ID);
				}
			    else
			    {	
			    	in.nextLine();
			    	System.err.println("输入有误,必须是纯数字，不能带有字符或者字母");
			    	System.out.println("请再次输入：");
			    	continue;
			    }
		}*/
		
		Scanner in = new Scanner(System.in);
		int choice = in.nextInt();
		switch (choice)
		{
			case 1:
				int i;
				break;
			case 2:
				i = 2;
				System.out.println(i);
			default:
				break;
		}
	}

	
	public static void readFileByLines(String fileName,String[] strings)
	{
		File file = new File(fileName);
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(file));
			int line = 0;
			// 一次读入一行，直到读入null为文件结束
			while ((strings[line] = reader.readLine()) != null)
			{
				line++;
			}
			reader.close();
		}
		catch (IOException e)
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
				}
				catch (IOException e1)
				{
				}
			}
		}
	}
	public static int HowManyLine(String fileName)
	{
		File file = new File(fileName);
		BufferedReader reader = null;
		int line = 0;
		try
		{
			reader = new BufferedReader(new FileReader(file));
			while (reader.readLine()!= null)
			{
				line++;
			}
			reader.close();
		}
		catch (IOException e)
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
				}
				catch (IOException e1)
				{
				}
			}
		}
		return line;
}
	private static void SetCalendar (GregorianCalendar calendar2)
	{
		GregorianCalendar calendar = new GregorianCalendar();
		calendar2 = new GregorianCalendar(calendar.get(calendar.YEAR),
										calendar.get(calendar.MONTH),
										calendar.get(calendar.DAY_OF_MONTH),
										calendar.get(calendar.HOUR_OF_DAY),
										calendar.get(calendar.MINUTE),
										calendar.get(calendar.SECOND));
	}
	public static void DivideString(String[] strings1,String[][] strings2,int line)
	{
		for(int i =0;i<line;i++)
		{
			strings2[i] = strings1[i].split("#");
		}
	}
}