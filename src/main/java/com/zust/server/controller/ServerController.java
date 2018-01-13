package com.zust.server.controller;

import com.zust.common.bean.DataFormat;
import com.zust.common.bean.LoginBean;
import com.zust.common.bean.User;
import com.zust.server.UDP.ServerUDP;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.util.ArrayList;

public class ServerController implements Runnable
{
	//发送方的InetAddress
	private InetAddress senderAddress;
	private DataFormat dataFormat;

	public ServerController(InetAddress inetAddress, byte[] data)
	{
		this.senderAddress = inetAddress;

		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try
		{
			bais = new ByteArrayInputStream(data);
			ois = new ObjectInputStream(bais);
			dataFormat = (DataFormat) ois.readObject();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (ois != null)
				{
					ois.close();
				}

				if (bais != null)
				{
					bais.close();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void run()
	{
		switch (dataFormat.getType())
		{
			case DataFormat.ADD_FRIEND:
				//TODO: 添加好友请求，最好写成方法
				break;

			case DataFormat.SEARCH_FRIEND:
				//TODO: 搜索好友请求，最好写成方法
				break;

			case DataFormat.DELETE_FRIEND:
				//TODO: 删除好友请求，最好写成方法
				break;

			case DataFormat.MESSAGE:
				//TODO: 消息请求，最好写成方法
				break;

			case DataFormat.LOGIN:
				login();
				break;

			case DataFormat.QUIT:
				//TODO: 退出请求，最好写成方法
				break;

			case DataFormat.USER_STATE:
				//TODO: 其他用户上下线请求，最好写成方法
				break;
		}
	}

	public void login()
	{
		int senderUserId = dataFormat.getFromId();
		ServerUDP.addUserIp(senderUserId, senderAddress.getHostAddress());

		//TODO:数据库处理

		LoginBean login = (LoginBean) dataFormat.getData();

		LoginBean loginBean = new LoginBean();
		loginBean.setType(1);
		DataFormat data = new DataFormat(0, senderUserId, DataFormat.LOGIN, loginBean, System.currentTimeMillis());
		try
		{
			ServerUDP.sendUdpMsg(data);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
