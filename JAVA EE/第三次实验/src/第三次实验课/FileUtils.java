package 第三次实验课;

import java.io.*;
import javax.swing.*;


public class FileUtils
{
	private CallBack callBack;
	
	public FileUtils (CallBack callBack)
	{
		this.callBack = callBack;
	}
	
	public void copy(InputStream in,OutputStream out,int bufSize) throws IOException
	{
		Runnable runnable = () ->
		{
			int total;
			int past = 0;
			int percent = 0;
			byte [] bt = new byte[bufSize];
			int count;
			try 
			{
				total = in.available();
				while((count=in.read(bt))>0)
				{
					out.write(bt,0,count);
					past += count;
					percent = (int)(past * 100.0 / total);
					callBack.tellPercent(percent);
				}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
