package com.zust;

import com.zust.common.tool.PicturePath;
import com.zust.server.UDP.ServerUDP;
import com.zust.server.tool.LoadXml;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.net.SocketException;

// 服务器开启
public class Server
{
	public static void main(String[] agrs)
	{
		try
		{
			LoadXml.getCtx();
			new ServerUDP();
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
	}
}
