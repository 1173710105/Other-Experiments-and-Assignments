package BANK;

import java.io.*;
import java.util.*;

public class ATM 
{
	/*
	public static int flag;//��־���������ڱ�־�˺������201000��ƫ����
	
	public static String NewCode()//���������𣬷���string���͵�����
	{
		Scanner in = new Scanner(System.in);
		String code1, code2;

		while (true)
		{
			try
			{
				System.out.print("�����������룺");
				code1 = in.next();
			} 
			catch (Exception e)
			{
				System.out.println("��������");
				in = new Scanner(System.in);
				continue;
			}
			if (!Isillegal(code1))
			{
				System.err.println("������Ӧ�ô���6λ����������λ����ȫ��ͬ�����ܰ����ո�");
				continue;
			} 
			else
				break;
		}
		while (true)
		{
			try
			{
				System.out.println("���ٴ��������룺");
				code2 = in.next();
			} 
			catch (Exception e)
			{
				System.out.println("��������");
				in = new Scanner(System.in);
				continue;
			}
			if (code1.equals(code2))
				break;
			else
			{
				System.out.println("������������Ӧ��һ��");
			}
		}
		return code2;
	}

	public static boolean Isillegal(String code)//����������Ƿ�Ϸ�
	{
		if (code.length() <= 6)
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
		for(int i=0;i<TradeLine;i++)
		{
			if(SID.equals(TradeRcorder2[i][0])||IsManager)
			{
				System.out.println("�û�����:"+TradeRcorder2[i][2]+";�˺�:"+TradeRcorder2[i][0]+";���׽��:"+TradeRcorder2[i][1]
						+"; ʱ��:"+TradeRcorder2[i][3]+"-"+TradeRcorder2[i][4]+"-"+TradeRcorder2[i][5]+" "+
						TradeRcorder2[i][6]+":"+TradeRcorder2[i][7]+":"+TradeRcorder2[i][8]);
			}
		}
	}
	*/
	
	public static void main(String[] args) throws IOException
	{
		ATMTool.flag = 0;
		
		int ID, count = 3;
		boolean InputMark = true;
		String code = "0", temp;
		Scanner in = new Scanner(System.in);
		
		int line = ATMTool.HowManyLine("UserInfo.txt");
		String[] strings1 = new String[line + 1];
		ATMTool.readFileByLines("UserInfo.txt", strings1);
		String[][] strings2 = new String[line][10];
		ATMTool.DivideString(strings1, strings2, line);
		USER.index = line;
		//System.out.println(USER.index);
		
		//System.out.println(TradeLine);
		
		while (true)
		{
			System.out.print("�����뿨�ţ�");
			if(in.hasNextInt()||InputMark)
			{
				InputMark = false;
				ID = in.nextInt();		
			}
			else
			{
				System.err.println("��������,�����Ǵ����֣����ܴ����ַ�������ĸ");
				System.out.println("���ٴ�����");
				if (in.hasNextLine())
					in.nextLine();
				continue;
			}	
			temp = Integer.toString(ID);
			if (!ATMTool.CountIsExit(strings2, temp, line))
			{
				System.out.println("�˺��޲����ڣ����ٴ�����");
			} 
			else
				break;
		}
		while (count > 0)
		{
			System.out.println("���������룺");
			if (in.hasNext())
			{
				code = in.next();
			}
			else
			{
				System.err.println("��������");
				System.out.println("���ٴ�����");
				if (in.hasNextLine())
					in.nextLine();
				continue;
			}
			temp = code;
			if (!ATMTool.CodeIsRight(strings2, temp))
			{
				count--;
				if(count!=0)
				{
					if (in.hasNextLine())
						in.nextLine();
					System.err.println("�����в��ܰ����ո���޷����͵��ַ�");
					System.err.println("��������㻹��" + count + "�λ���");
				}
			} 
			else
				break;
		}
		
		if (count == 0)
		{
			System.err.println("���Ѿ�������");
			System.err.println("��������");
			System.exit(1);
		}
		
		USER manager = ATMTool.RestoreOneUserData(strings2,0);
		//System.err.println(flag);
		if (strings2[ATMTool.flag][3].equals("ADMIN"))
		{
			int choice=-1;
			boolean mark = false;
			while(choice!=0)
			{
				manager.MenuADMIN();
				System.out.print("����������ѡ��");
				if (in.hasNextInt())
				{
					choice = in.nextInt();
				}
				else
				{
					System.err.println("��������������0~4");
					System.out.println("���ٴ����룺");
					if (in.hasNextLine())
						in.nextLine();
					continue;
				}								
				switch (choice)
				{
					case 0:
						in.close();
						USER.Exit();
						break;
					case 1:
						if(mark)
						{
							line = ATMTool.HowManyLine("UserInfo.txt");
							strings1 = new String[line + 1];
							ATMTool.readFileByLines("UserInfo.txt", strings1);
							strings2 = new String[line][10];
							ATMTool.DivideString(strings1, strings2, line);
							mark = false;
						}
						System.out.println("���л����û���Ϣ��");
						manager.ChestAllCustomer(strings2);
						break;
					case 2:
						USER NewCustomer = manager.Establish();
						NewCustomer.ShowMessage();
						NewCustomer.OutPutToFile();
						mark = true;
						break;
					case 3:
						System.out.println("��ǰATM�ʽ����Ϊ��"+manager.Getbalance()+"Ԫ");
						break;
					case 4:
						int TradeLine = ATMTool.HowManyLine("Record.txt");
						String[] TradeRcorder1 = new String[TradeLine+1];
						ATMTool.readFileByLines("Record.txt", TradeRcorder1);
						String[][] TradeRcorder2 = new String[TradeLine][9];
						ATMTool.DivideString(TradeRcorder1, TradeRcorder2, TradeLine);	
						ATMTool.ChestWithdrawalDepositDetail(TradeRcorder2, TradeLine, manager.GetID(), true);
						break;
					case 5:
						code = ATMTool.NewCode();
						manager.ChangePassword(code);
						System.out.println("�޸�����ɹ�");
						strings2[0][1] = code;
						ATMTool.RewriteToFile(strings2[0], strings2[0],true);
						break;
					default:
						System.err.println("��������������0~4");
						System.out.println("���ٴ����룺");
						break;
				}				
			}
		}
		
		if(strings2[ATMTool.flag][3].equals("HQ"))
		{
			GregorianCalendar calendar = new GregorianCalendar();
			USER customer = ATMTool.RestoreOneUserData(strings2,ATMTool.flag);
			int choice=-1;
			int TempAmount;
			while(choice!=0)
			{
				customer.MenuHQ();
				
				System.out.print("����������ѡ��");
				if (in.hasNextInt())
				{
					choice = in.nextInt();
				}
				else
				{
					System.err.println("��������������0~5");
					System.out.println("���ٴ����룺");
					if (in.hasNextLine())
						in.nextLine();
					continue;
				}
				switch (choice)
				{
					case 0:
						in.close();
						ATMTool.RewriteToFile(strings2[ATMTool.flag], strings2[0],false);
						USER.Exit();
						break;
					case 1:
						System.out.println("�˻���"+customer.Getbalance());
						break;
					case 2:
						int deposit = 100;
						System.out.println("��С��ֵΪ100Ԫ����Ϊ100�ı���");
						do
						{
							System.out.print("���������");
							if(in.hasNextInt())	
								deposit= in.nextInt();
							else
							{	
								System.err.println("��������������100�ı���");
								System.out.println("���ٴ����룺");
								if (in.hasNextLine())
									in.nextLine();
								continue;
							}		
						}while(!ATMTool.IsIntegerAndHundrel(deposit));
						
						calendar = new GregorianCalendar();
						customer.SetCalendar(calendar);
						
						customer.Deposit(deposit);
						TempAmount = Integer.parseInt(strings2[ATMTool.flag][2]) + deposit;
						strings2[ATMTool.flag][2] = Integer.toString(TempAmount);
						
						manager.Deposit(deposit);
						TempAmount = Integer.parseInt(strings2[0][2]) + deposit;
						strings2[0][2] = Integer.toString(TempAmount);
						
						manager.WriteWithdrawalDepositDetail(deposit, calendar,true);
						customer.WriteWithdrawalDepositDetail(deposit, calendar,true);
						ATMTool.RewriteToFile(strings2[ATMTool.flag], strings2[0],false);
						
						System.out.println("���ɹ�");
						customer.ShowOneTradeRecoder(calendar, deposit, true);
						break;
					case 3:
						if(manager.Getbalance()<100)
							break;
						
						int withdrawal = 100;
						System.out.println("����ȡ���ܶ����2000Ԫ����С��ֵΪ100Ԫ");
						do
						{
							if(!ATMTool.IsIntegerAndHundrel(withdrawal)||withdrawal>2000)
								System.err.println("��Ч���룬������100�ı����Ҳ��ܳ���2000Ԫ");
							else if(manager.Getbalance()<withdrawal)
							{
								System.err.println("ATM������"+withdrawal+"Ԫ");
								System.out.println("������������");
							}
							else if(customer.Getbalance()<withdrawal)
								System.err.println("�˺����㣬�޷����ȡ�����");	
							
							System.out.print("������ȡ���:");
							
							if (in.hasNextInt())
							{
								withdrawal= in.nextInt();
							}		
							else
							{
								System.err.println("��������������100�ı����Ҳ��ܳ���2000Ԫ");
								System.out.println("���ٴ�����");
								if (in.hasNextLine())
									in.nextLine();
								continue;
							}
						}while(!ATMTool.IsIntegerAndHundrel(withdrawal)||customer.Getbalance()<withdrawal||manager.Getbalance()<withdrawal||withdrawal>2000);
						
						calendar = new GregorianCalendar();
						customer.SetCalendar(calendar);
						
						customer.Withdrawal(withdrawal);
						TempAmount = Integer.parseInt(strings2[ATMTool.flag][2]) - withdrawal;
						strings2[ATMTool.flag][2] = Integer.toString(TempAmount);
						
						manager.Withdrawal(withdrawal);
						TempAmount = Integer.parseInt(strings2[0][2]) - withdrawal;
						strings2[0][2] = Integer.toString(TempAmount);
						
						manager.WriteWithdrawalDepositDetail(withdrawal, calendar,false);
						customer.WriteWithdrawalDepositDetail(withdrawal, calendar,false);
						ATMTool.RewriteToFile(strings2[ATMTool.flag], strings2[0],false);
						
						System.out.println("ȡ��ɹ�");
						customer.ShowOneTradeRecoder(calendar, withdrawal, false);
						break;
					case 4:
						int TradeLine = ATMTool.HowManyLine("Record.txt");
						String[] TradeRcorder1 = new String[TradeLine+1];
						ATMTool.readFileByLines("Record.txt", TradeRcorder1);
						String[][] TradeRcorder2 = new String[TradeLine][9];
						ATMTool.DivideString(TradeRcorder1, TradeRcorder2, TradeLine);	
						ATMTool.ChestWithdrawalDepositDetail(TradeRcorder2, TradeLine, customer.GetID(), false);
						break;
					case 5:
						code = ATMTool.NewCode();
						customer.ChangePassword(code);
						System.out.println("�޸�����ɹ�");
						strings2[ATMTool.flag][1] = code;
						ATMTool.RewriteToFile(strings2[ATMTool.flag], strings2[0], false);
						break;
					default:
						System.err.println("��������������0~5");
						System.out.println("���ٴ����룺");
						break;
				}
			}
			
		}
	}
}
