package com.zust.server.controller;

import com.google.gson.Gson;
import com.zust.common.bean.DataFormat;
import com.zust.server.UDP.ServerUDP;

import java.io.IOException;
import java.net.InetAddress;

public class ServerController implements Runnable
{
	//发送方的InetAddress
	private InetAddress senderAddress;
	private DataFormat dataFormat;

	public ServerController(InetAddress inetAddress, byte[] data)
	{
		this.senderAddress = inetAddress;
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
		int senderUserId = dataFormat.getFromId();
		ServerUDP.addUserIp(senderUserId, senderAddress.getHostAddress());

		//TODO:数据库处理

		DataFormat data = new DataFormat(0, senderUserId, DataFormat.LOGIN, "登陆成功", System.currentTimeMillis());
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
