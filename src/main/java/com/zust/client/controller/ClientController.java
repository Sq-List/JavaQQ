package com.zust.client.controller;

import com.google.gson.Gson;
import com.zust.client.UDP.ClientUDP;
import com.zust.client.view.Login;
import com.zust.common.bean.DataFormat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientController implements Runnable
{
	private DataFormat dataFormat;

	public ClientController(byte[] data)
	{
		Gson gson = new Gson();
		dataFormat = gson.fromJson(new String(data), DataFormat.class);
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
		try
		{
			new ClientUDP();

			DataFormat dataFormat = new DataFormat(1, 0, DataFormat.LOGIN, new String[]{"你好"}, System.currentTimeMillis());
			ClientUDP.sendUdpMsg(dataFormat);
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
