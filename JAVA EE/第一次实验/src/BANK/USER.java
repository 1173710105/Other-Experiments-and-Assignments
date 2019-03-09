package BANK;

import java.io.*;
import java.util.*;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;

public class USER
{
	
	private int ID;
	private String code;
	private int balance;
	private String tag;
	private GregorianCalendar calendar;	
	public static int index = 0;
	
	public USER()
	{
		this.balance = 0;
		this.ID = 201000+index;
		this.tag = "HQ";
		this.code = "123";
		index++;
		calendar = new GregorianCalendar();
		SetCalendar(calendar);
	}
	
	public USER(int ID,String code,int balance,String tag)
	{
		this.balance = balance;
		this.ID = ID;
		this.code = code;
		this.tag = tag;
		index++;
		calendar = new GregorianCalendar();
		SetCalendar(calendar);
	}
	public USER(int ID,String code,int balance,String tag,int years,int months,int days,int hours,int minutes,int seconds)
	{
		this.balance = balance;
		this.ID = ID;
		this.code = code;
		this.tag = tag;
		calendar = new GregorianCalendar(years,months,days,hours,minutes,seconds);
	}
	public void SetCalendar (GregorianCalendar calendar)
	{
		GregorianCalendar calendar1 = new GregorianCalendar();
		calendar = new GregorianCalendar(calendar1.get(calendar1.YEAR),
										calendar1.get(calendar1.MONTH),
										calendar1.get(calendar1.DAY_OF_MONTH),
										calendar1.get(calendar1.HOUR_OF_DAY),
										calendar1.get(calendar1.MINUTE),
										calendar1.get(calendar1.SECOND));
	}
	public void MenuADMIN()
	{
		System.out.println("欢迎您，管理员");
		System.out.println("**********************");
		System.out.println("1.查询所有活期用户信息");
		System.out.println("2.创建活期账号（开新卡）");
		System.out.println("3.查询当前ATM资金金额");
		System.out.println("4.查看交易ATM详细");
		System.out.println("5.修改密码");
		System.out.println("0.退出");
		System.out.println("**********************");
	}
	public void MenuHQ()
	{
		System.out.println("**********************");
		System.out.println("1.查询余额");
		System.out.println("2.存款");
		System.out.println("3.取款");
		System.out.println("4.查询存取明细");
		System.out.println("5.修改密码");
		System.out.println("0.退出");
		System.out.println("**********************");
	}
	public void ChestAllCustomer(String[][] strings2)
	{
		if(this.tag.equals("ADMIN"))
		{
			for(int i=0;i<index;i++)
			{
				if(strings2[i][3].equals("HQ"))
				{
					System.out.print("账号:"+strings2[i][0]);
					System.out.print(";密码:"+strings2[i][1]);
					System.out.print(";余额:"+strings2[i][2]);
					System.out.print(";用户类型:"+strings2[i][3]+";开户日期:");
					System.out.printf("%04d-",Integer.parseInt(strings2[i][4]));
					System.out.printf("%02d-",Integer.parseInt(strings2[i][5])+1);
					System.out.printf("%02d ",Integer.parseInt(strings2[i][6]));
					System.out.printf("%02d:",Integer.parseInt(strings2[i][7]));
					System.out.printf("%02d:",Integer.parseInt(strings2[i][8]));
					System.out.printf("%02d\n",Integer.parseInt(strings2[i][9]));
				}
			}
		}
		else
		{
			System.out.println("权限不足，无法访问");
		}
		
	}
	public USER Establish()
	{
		if(this.tag.equals("ADMIN"))
		{
			USER customer = new USER();
			System.out.println("创建用户成功！");
			return customer;
		}
		else
		{
			System.out.println("权限不足！");
			return null;
		}
	}
	public void ShowMessage()
	{
		System.out.print("账号："+this.ID);
		System.out.print("；密码："+this.code);
		System.out.print("；余额："+this.balance);
		System.out.print("；用户类型："+this.tag);
		int month = calendar.get(calendar.MONTH)+1;
		System.out.println(" 开户时间："+calendar.get(calendar.YEAR)
		+"-"+month+"-"+calendar.get(calendar.DAY_OF_MONTH)+" "+
		calendar.get(calendar.HOUR_OF_DAY)+":"+calendar.get(calendar.MINUTE)+":"+calendar.get(calendar.SECOND));		
	}
	public void ShowOneTradeRecoder(GregorianCalendar OperatetionTime,int Money,boolean IsDeposit)
	{
		System.out.print("本次交易记录: ");
		System.out.print("用户类型:"+this.tag);
		System.out.print(";账户:"+this.ID);
		if(IsDeposit)
			System.out.print(";交易金额:+"+Money);
		else
			System.out.print(";交易金额:-"+Money);
		System.out.print(";交易操作时间:");
		System.out.printf("%04d-",OperatetionTime.get(OperatetionTime.YEAR));
		System.out.printf("%02d-",OperatetionTime.get(OperatetionTime.MONTH));
		System.out.printf("%02d ",OperatetionTime.get(OperatetionTime.DAY_OF_MONTH)+1);
		System.out.printf("%02d:",OperatetionTime.get(OperatetionTime.HOUR_OF_DAY));
		System.out.printf("%02d:",OperatetionTime.get(OperatetionTime.MINUTE));
		System.out.printf("%02d\n",OperatetionTime.get(OperatetionTime.SECOND));
	}
	public void ChangePassword(String NewCode)
	{
		this.code = NewCode;
	}
	public int Getbalance()
	{
		return this.balance;
	}
	public int GetID()
	{
		return ID;
	}
	public String GetCode()
	{
		return code;
	}
	public static int GetIndex()
	{
		return index;
	}
	public void Withdrawal(int num)
	{
		balance -= num;
	}
	public void Deposit(int num)
	{
		balance += num;
	}
	public String GetTag()
	{
		return this.tag; 
	}
	public static void Exit()
	{
		System.out.println("程序结束，退出系统！");
		System.exit(0);
	}
	public void OutPutToFile() throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream("UserInfo.txt",true);
		PrintWriter pWriter = new PrintWriter(fileOutputStream);
		pWriter.println(this.ID+"#"+this.code+"#"+this.balance+"#"+this.tag+"#"+calendar.get(calendar.YEAR)
						+"#"+calendar.get(calendar.MONTH)+"#"+calendar.get(calendar.DAY_OF_MONTH)+"#"+
						calendar.get(calendar.HOUR_OF_DAY)+"#"+calendar.get(calendar.MINUTE)+"#"+calendar.get(calendar.SECOND));
		pWriter.close();
		fileOutputStream.close();
	}
	public void WriteWithdrawalDepositDetail(int amount,GregorianCalendar OperatetionTime,boolean IsDeposit) throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream("Record.txt",true);
		PrintWriter pWriter = new PrintWriter(fileOutputStream);
		if(IsDeposit)
		{
			pWriter.println(this.ID+"#+"+Integer.toString(amount)+"#"+this.tag+"#"+
					OperatetionTime.get(OperatetionTime.YEAR)+"#"+
					OperatetionTime.get(OperatetionTime.MONTH)+"#"+
					OperatetionTime.get(OperatetionTime.DAY_OF_MONTH)+"#"+
					OperatetionTime.get(OperatetionTime.HOUR_OF_DAY)+"#"+
					OperatetionTime.get(OperatetionTime.MINUTE)+"#"+
					OperatetionTime.get(OperatetionTime.SECOND)
					);
		}
		else
		{
			pWriter.println(this.ID+"#-"+Integer.toString(amount)+"#"+this.tag+"#"+
					OperatetionTime.get(OperatetionTime.YEAR)+"#"+
					OperatetionTime.get(OperatetionTime.MONTH)+"#"+
					OperatetionTime.get(OperatetionTime.DAY_OF_MONTH)+"#"+
					OperatetionTime.get(OperatetionTime.HOUR_OF_DAY)+"#"+
					OperatetionTime.get(OperatetionTime.MINUTE)+"#"+
					OperatetionTime.get(OperatetionTime.SECOND)
					);
		}
		pWriter.close();
		fileOutputStream.close();
	}
}
