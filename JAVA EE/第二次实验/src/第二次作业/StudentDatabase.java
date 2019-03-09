package 第二次作业;

import java.sql.*;

public class StudentDatabase
{

	public static void main(String[] args)
	{
		{
			String UserName = null;
			String Code = null;
			AdminTool.Rigister(UserName,Code);
		}
		
		int choice;	
		String command = null;
		String condition = null;
		try
		{		
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hit","root","123");
			Statement statement = con.createStatement();
			ResultSet resultset;
			
			while(true)
			{
				AdminTool.Menu();//菜单
				choice = AdminTool.getChoice(5);
				switch (choice)
				{
					case 1:
						command = "select * from student";
						resultset = statement.executeQuery(command);
						AdminTool.CheckAllStudent(resultset);
						break;
					case 2:
						condition = AdminTool.GetCondition("������ѧ��ѧ��:", "ѧ��ѧ����������!");
						command = "select * from student where id = "+"'"+condition+"'";
						resultset = statement.executeQuery(command);
						if(AdminTool.CheckAllStudent(resultset))
							System.out.println("��ѯ�ɹ���ѧ����Ϣ���ϣ�");
						else
							System.out.println("��ѯʧ�ܣ�û�и�ѧ����Ϣ��");
						break;
					case 3:
						command = "select * from student order by id desc LIMIT 1";
						resultset = statement.executeQuery(command);
						String strID = AdminTool.GetOnePart(resultset);
						int ID = Integer.parseInt(strID)+1;
						command = AdminTool.AddStudentOfSetDATA(Integer.toString(ID));
						try
						{
							statement.executeUpdate(command);
							command = "select * from student where id = "+"'"+Integer.toString(ID)+"'";
							resultset = statement.executeQuery(command);
							AdminTool.CheckAllStudent(resultset);
							System.out.println("����ѧ����Ϣ�ɹ�");
						}
						catch (SQLException e)
						{
							e.printStackTrace();
						}
						break;
					case 4:
						condition = AdminTool.GetCondition("������ѧ��ѧ��:", "ѧ��ѧ����������!");
						command = "select * from student where id = "+"'"+condition+"'";
						resultset = statement.executeQuery(command);
						if(AdminTool.CheckAllStudent(resultset))					
						{
							command = "delete from student where id = "+condition;
							//System.out.println(command);
							try
							{
								statement.executeUpdate(command);
								System.out.println("ɾ��ѧ����Ϣ�ɹ�");
							}
							catch (SQLException e)
							{
								e.printStackTrace();
							}
						}
						else 
						{
							System.out.println("�޴�ѧ����ɾ��ʧ��");
						}
						break;
					case 5:
						condition = AdminTool.GetCondition("������ѧ��ѧ��:", "ѧ��ѧ����������!");
						command = "select * from student where id = "+"'"+condition+"'";
						resultset = statement.executeQuery(command);
						if(AdminTool.CheckAllStudent(resultset))
						{
							String[] strings1 = new String[] {"","name","sex","age","major"};
							String[] strings2 = new String[] {"","����","�Ա�","����","רҵ"};
							String ChanegeValue;
							while(true)
							{
								AdminTool.FuntionMenu();
								int flag = AdminTool.getChoice(4);
								if(flag==0)
								{
									break;
								}
								//System.out.println(flag);
								ChanegeValue = AdminTool.GetCondition("����������ѧ��"+strings2[flag]+":","ѧ���Ա���������");
								//System.out.println(ChanegeValue);
								//UPDATE runoob_tbl SET runoob_title='ѧϰ C++' WHERE runoob_id=3
								if(flag == 3)
								{
									command = "update student set "+strings1[flag]+"="+Integer.parseInt(ChanegeValue)+" Where id = '"+condition+"'";
								}
								else
									command = "update student set "+strings1[flag]+"="+"'"+ChanegeValue+"'"+"Where id = '"+condition+"'";
								//System.out.println(command);
								try
								{
									statement.executeUpdate(command);
									System.out.println("�޸�ѧ����Ϣ�ɹ�");
								}
								catch (SQLException e)
								{
									e.printStackTrace();
								}
							}
							
						}
						else
						{
							System.out.println("�޸�ʧ�ܣ��޴�ѧ����Ϣ");
						}
						break;
					case 0:
						con.close();
						System.out.println("�Ͽ��������ӣ�");
						System.out.println("����������˳�ϵͳ");
						System.exit(0);
						break;
					default:
						break;
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

}
