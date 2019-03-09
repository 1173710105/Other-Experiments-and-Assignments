package mybean;

import javax.sql.rowset.*;
import com.sun.rowset.CachedRowSetImpl;
//离线缓冲数据集，帮数据库减压

public class DataByPage
{
	CachedRowSet rowSet = null;//定义一个缓冲集对象
	int pageSize = 1; //没页显示记录条数
	int totalPages = 1;//总页数
	int currentPage = 1;//当前页数
	
	public void setRowSet(CachedRowSetImpl set)
	{
		rowSet = set;
	}
	public CachedRowSetImpl getRowSet()
	{
		return (CachedRowSetImpl) rowSet;
	}
	public void setPageSize(int size)
	{
		pageSize = size;
	}
	public int getPageSize()
	{
		return pageSize;
	}
	public int getTotalPages()
	{
		return totalPages;
	}
	public void setTotalPages(int n)
	{
		totalPages = n;
	}
	public void setCurrentPage(int n)
	{
		currentPage = n;
	}
	public int getCurrentPage()
	{
		return currentPage;
	}
}
