package com.zust;

import com.zust.client.UDP.ClientUDP;
import com.zust.common.bean.DataFormat;
import com.zust.common.bean.LoginBean;

import java.io.IOException;
import java.net.SocketException;

public class TestSend
{
	public static void main(String[] agrs)
	{
		try
		{
			new ClientUDP();

			LoginBean loginBean = new LoginBean();
			loginBean.setType(1);
			DataFormat dataFormat = new DataFormat(1, 2, DataFormat.LOGIN, loginBean, System.currentTimeMillis());
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
