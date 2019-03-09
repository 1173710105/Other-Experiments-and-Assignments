package BANK;

import java.io.*;
import java.util.*;

public class ATMTool
{	
	public static int flag;//��־���������ڱ�־�˺������201000��ƫ����
	
	public static String NewCode()//���������𣬷���string���͵�����
	{
		Scanner in = new Scanner(System.in);
		String code1, code2;
		boolean InputMARK = true;
		while (true)
		{
			System.out.print("������������:");
			if (in.hasNext()||InputMARK)
			{
				InputMARK = false;
				code1 = in.next();
			}
			else 
			{
				System.out.println("��������");
				in.nextLine();
				continue;
			}
			if (!Isillegal(code1))
			{
				in.nextLine();
				System.err.println("������Ӧ�ò�С��6λ����������λ����ȫ��ͬ�����ܰ����ո�");
				continue;
			} 
			else
				break;
		}
		while (true)
		{
			System.out.println("���ٴ����룬ȷ��������:");
			if (in.hasNext())
			{
				code2 = in.next();
			}
			else
			{
				System.out.println("��������");
				in.nextLine();
				continue;
			}
			
			if (code1.equals(code2))
			{
				break;
			}
			else
			{
				System.out.println("������������Ӧ��һ��");
			}
		}
		return code2;
	}

	public static boolean Isillegal(String code)//����������Ƿ�Ϸ�
	{
		if (code.length() < 6)
			return false;
		for (int i = 0, j = code.length() - 1; i <= j; i++, j--)
		{
			if (code.charAt(i) != code.charAt(j))
				return true;
		}
		return false;
	}

	public static void readFileByLines(String fileName, String[] strings)//���ж�ȡ�ļ�
	{
		File file = new File(fileName);
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(file));
			int line = 0;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
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
				catch (	IOException e1)
				{
				}
			}
		}
	}

	public static int HowManyLine(String fileName)//��ȡ�ļ�����
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
				catch (	IOException e1)
				{
				}
			}
		}
		return line;
	}

	public static void DivideString(String[] strings1, String[][] strings2, int line)//�ԡ�#����ÿһ�е��ַ����ֿ������浽��ά������
	{
		for (int i = 0; i < line; i++)
		{
			strings2[i] = strings1[i].split("#");
		}
	}

	public static boolean CountIsExit(String[][] strings2, String temp, int line)//����˺��Ƿ���ڣ�����ס�˺�ƫ����
	{
		for (int i = 0; i < line; i++)
		{
			if (temp.equals(strings2[i][0]))
			{
				flag = i;
				return true;
			}
		}
		return false;
	}

	public static boolean CodeIsRight(String[][] strings2, String temp)//��������Ƿ���ȷ
	{
		if (temp.equals(strings2[flag][1]))
			return true;
		return false;
	}

	public static USER RestoreOneUserData(String[][]strings2,int flags)
	{
		int ID = Integer.parseInt(strings2[flags][0]);
		String code = strings2[flags][1];
		int balance = Integer.parseInt(strings2[flags][2]);
		String tag = strings2[flags][3];
		int years,months,days,hours,minutes,seconds;
		years =	Integer.parseInt(strings2[flags][4]);
		months = Integer.parseInt(strings2[flags][5]);
		days = Integer.parseInt(strings2[flags][6]);
		hours = Integer.parseInt(strings2[flags][7]);
		minutes = Integer.parseInt(strings2[flags][8]);
		seconds = Integer.parseInt(strings2[flags][9]);
		USER user = new USER(ID,code,balance,tag,years,months,days,hours,minutes,seconds);
		return user;
	}
	
	public static void RewriteToFile(String[] Data1 ,String[] Data2,boolean IsManager)throws IOException//������������д���ļ��У�����ԭ�ı�����
	{
		int line = HowManyLine("UserInfo.txt");
		String[] strings1 = new String[line + 1];
		readFileByLines("UserInfo.txt", strings1);
		String[][] strings2 = new String[line][10];
		DivideString(strings1, strings2, line);
		ModifyData(strings2[0], Data2);
		if(IsManager)
		{
			Write(strings2,line);
		}
		else
		{
			ModifyData(strings2[flag], Data1);
			Write(strings2,line);
		}
	}
	
	public static void ModifyData(String[] strings2,String[] target)//�޸��ַ������е����ݣ�������д�ı�
	{
		for(int i=0;i<10;i++)
		{
			strings2[i]  = target[i];
		}
	}
	
	public static void Write(String[][] strings2,int line) throws IOException//���ַ������е�����д���ĵ�
	{
		FileOutputStream fileOutputStream = new FileOutputStream("UserInfo.txt",false);//false Ϊ�����ĵ�
		PrintWriter pWriter = new PrintWriter(fileOutputStream);
		for(int i=0;i<line;i++)
		{
			pWriter.println(strings2[i][0]+"#"+strings2[i][1]+"#"+strings2[i][2]+
					"#"+strings2[i][3]+"#"+strings2[i][4]+"#"+strings2[i][5]+"#"+
					strings2[i][6]+"#"+strings2[i][7]+"#"+strings2[i][8]+"#"+
					strings2[i][9]);
		}
		pWriter.close();
		fileOutputStream.close();
	}
	
	public static boolean IsIntegerAndHundrel(int amount)//�жϽ���Ƿ�Ϊ100��������
	{
		if(amount/100*100==amount)
			return true;
		else
			return false;
	}
	
	public static void ChestWithdrawalDepositDetail(String[][] TradeRcorder2,int TradeLine,int ID,boolean IsManager)//�鿴������ϸ
	{
		String SID = Integer.toString(ID);
		boolean mark = false;
		int month;
		for(int i=0;i<TradeLine;i++)
		{
			if(IsManager||SID.equals(TradeRcorder2[i][0]))
			{
				month = Integer.parseInt(TradeRcorder2[i][4])+1;
				System.out.print("�û�����:"+TradeRcorder2[i][2]+";�˺�:"+TradeRcorder2[i][0]+";���׽��:"+TradeRcorder2[i][1]);
				System.out.print("; ʱ��:");
				System.out.printf("%04d-%02d-%02d",Integer.parseInt(TradeRcorder2[i][3]),month,Integer.parseInt(TradeRcorder2[i][5]));
				System.out.printf(" %02d:%02d:%02d\n",
								Integer.parseInt(TradeRcorder2[i][6]),
								Integer.parseInt(TradeRcorder2[i][7]),
								Integer.parseInt(TradeRcorder2[i][8]));
				mark = true;
			}
		}
		if(!mark)
		{
			System.out.println("���˺Ŵ�ȡ��ϸ");
		}
			
	}

}
