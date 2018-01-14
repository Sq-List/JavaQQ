package com.zust;

import com.zust.client.UDP.ClientUDP;
import com.zust.client.manager.ManagerPanel;
import com.zust.client.view.Login;
import com.zust.common.bean.DataFormat;
import com.zust.common.bean.LoginBean;

import java.io.IOException;
import java.net.SocketException;

public class Client
{
	public static void main(String[] agrs)
	{
		try
		{
			new ClientUDP();
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
		Login login = new Login();
		//添加登陆面板
		ManagerPanel.add("loginPanel", login);
	}
}
