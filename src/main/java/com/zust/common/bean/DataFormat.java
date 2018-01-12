package com.zust.common.bean;

public class DataFormat
{
	//添加好友
	public static final int ADD_FRIEND = 0;
	//删除好友
	public static final int DELETE_FRIEND = 1;
	//搜索好友
	public static final int SEARCH_FRIEND = 2;
	//聊天
	public static final int MESSAGE = 3;
	//登陆
	public static final int LOGIN = 4;
	//退出
	public static final int QUIT = 5;
	//用户上线下线状态
	public static final int USER_STATE = 6;

	//发送请求的id
	private int fromId;
	//需要接收请求的id
	private int toId;
	//请求的类型
	private int type;
	//请求的数据
	private Object data;
	//请求的时间
	private long time;

	public DataFormat()
	{

	}

	public DataFormat(int fromId, int toId, int type, Object data, long time)
	{
		this.fromId = fromId;
		this.toId = toId;
		this.type = type;
		this.data = data;
		this.time = time;
	}

	public int getFromId()
	{
		return fromId;
	}

	public void setFromId(int fromId)
	{
		this.fromId = fromId;
	}

	public int getToId()
	{
		return toId;
	}

	public void setToId(int toId)
	{
		this.toId = toId;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public long getTime()
	{
		return time;
	}

	public void setTime(long time)
	{
		this.time = time;
	}
}
