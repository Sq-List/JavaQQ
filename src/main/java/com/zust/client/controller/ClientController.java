package com.zust.client.controller;

import com.google.gson.Gson;
import com.zust.client.UDP.ClientUDP;
import com.zust.client.manager.ManagerPanel;
import com.zust.client.view.ChatPane;
import com.zust.client.view.Login;
import com.zust.common.bean.ChatBean;
import com.zust.common.bean.DataFormat;
import com.zust.common.bean.User;
import com.zust.common.bean.UserStateBean;


import java.awt.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientController implements Runnable
{
	private DataFormat dataFormat;

	public ClientController(byte[] data)
	{
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
				receiveMessage();
				break;

			case DataFormat.LOGIN:
				login();
				break;

			case DataFormat.QUIT:
				//TODO: 退出请求，最好写成方法
				break;

			case DataFormat.USER_STATE:
				//TODO: 其他用户上下线请求，最好写成方法
				changeStatus();
				break;
		}
	}

	public void login()
	{

	}
	public void receiveMessage(){
		ChatBean chatBean= (ChatBean) dataFormat.getData();
		ChatPane chatPanel=(ChatPane)ManagerPanel.get("chatPanel");
		chatPanel.receiveMsg(chatBean.getMessage(),dataFormat.getToId());
	}
	public void changeStatus(){
		UserStateBean userStateBean= (UserStateBean) dataFormat.getData();
		ChatPane chatPanel=(ChatPane)ManagerPanel.get("chatPanel");
		if(userStateBean.getUser().isStatus()){
			chatPanel.addOnlineFriend(userStateBean.getUser());
		}else{
			chatPanel.delteOfflineFriend(userStateBean.getUser().getId());
		}

	}
}
