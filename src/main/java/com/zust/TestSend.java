package com.zust;

import com.zust.client.UDP.ClientUDP;
import com.zust.common.bean.DataFormat;

import java.io.IOException;
import java.net.SocketException;

public class TestSend
{
	public static void main(String[] agrs)
	{
		try
		{
			new ClientUDP();

			DataFormat dataFormat = new DataFormat(1, 2, DataFormat.LOGIN, new String[]{"你好"}, System.currentTimeMillis());
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
