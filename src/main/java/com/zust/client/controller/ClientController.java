package com.zust.client.controller;

import com.google.gson.Gson;
import com.zust.client.UDP.ClientUDP;
import com.zust.client.manager.ManagerPanel;
import com.zust.client.view.Login;
import com.zust.client.view.Main;
import com.zust.common.bean.DataFormat;
import com.zust.common.bean.LoginBean;

<<<<<<< HEAD
import javax.swing.*;
=======
import java.io.ByteArrayInputStream;
>>>>>>> 39459c3594234ff9b77713ef8e35d74966e702e2
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.SocketException;

public class ClientController implements Runnable
{
	private DataFormat dataFormat;

	public ClientController(byte[] data)
	{
<<<<<<< HEAD
		Gson gson = new Gson();
		dataFormat = gson.fromJson(new String(data), DataFormat.class);
		System.out.println("构造器" + dataFormat.getType());
=======
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
>>>>>>> 39459c3594234ff9b77713ef8e35d74966e702e2
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
		System.out.println("123");
		System.out.println(dataFormat.getData());
		LoginBean loginBean = (LoginBean) dataFormat.getData();
		if (loginBean.getType() == 1)
		{
			Main main = new Main();
			ManagerPanel.add("mainPanel", main);
			Login login = (Login) ManagerPanel.get("loginPanel");

		}
		else
		{
			JOptionPane.showConfirmDialog(null, "登录失败");
		}
	}
}
