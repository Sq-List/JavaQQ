package com.zust;

import com.zust.server.UDP.ServerUDP;

import java.net.SocketException;

public class TestRecive
{
	public static void main(String[] agrs)
	{
		try
		{
			new ServerUDP();
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
	}
}
