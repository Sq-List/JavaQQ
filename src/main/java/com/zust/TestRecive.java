package com.zust;

import com.zust.common.tool.PicturePath;
import com.zust.server.UDP.ServerUDP;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.net.SocketException;

public class TestRecive
{
	public static void main(String[] agrs)
	{
		try
		{
			ApplicationContext ctx = new FileSystemXmlApplicationContext(PicturePath.getPicturePath("/spring/spring-service.xml").getPath());
			new ServerUDP();
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
	}
}
