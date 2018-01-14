package com.zust.server.UDP;


import com.zust.common.bean.DataFormat;
import com.zust.common.bean.UdpMsg;
import com.zust.server.controller.ServerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.*;

public class ServerUDP
{
	private static final Logger logger = LoggerFactory.getLogger(ServerUDP.class);

	private static final Map<Integer, String> userIpMap = new HashMap<>();
	private static final Map<String, UdpMsg> udpMsgMap = new HashMap<>();
	private final Map<String, Boolean> reviceUdpMsg = new HashMap<>();
	//接收端的DatagramSocket
	private DatagramSocket ds;

	public ServerUDP() throws SocketException
	{
		ds = new DatagramSocket(2222);

		startReciveUdpMsg();
		startResendUpdMsg();
	}

	public static void sendUdpMsg(DataFormat dataFormat) throws IOException
	{
		DatagramSocket dSender = new DatagramSocket();

		//内存流（基类流）
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//对象流（包装流 ）因为传输对象
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(dataFormat);

		String udpId = UUID.randomUUID().toString();
		UdpMsg udpMsg = new UdpMsg(udpId, UdpMsg.REQUEST, dataFormat.getToId(), System.currentTimeMillis(), baos.toByteArray());
		byte[] buffer = udpMsg.toByte();

		//发送数据包给目标对象
		InetAddress toAddress = InetAddress.getByName(userIpMap.get(udpMsg.getToId()));
		if (toAddress != null)
		{
			DatagramPacket dp = new DatagramPacket(buffer, buffer.length, toAddress, 2223);
			dSender.send(dp);

			//将数据包放入消息队列
			udpMsgMap.put(udpMsg.getUdpId(), udpMsg);
			logger.info("发送端-已发送req:" + udpMsg.getUdpId() + "的请求");
		}

		if (oos != null)
		{
			oos.close();
		}

		if (baos != null)
		{
			baos.close();
		}
	}

	//启动重传线程
	public void startResendUpdMsg()
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				while(true)
				{
					resendUdpMsg();
					try
					{
						Thread.sleep(300);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	//重传方法
	public void resendUdpMsg()
	{
		Set<String> keyset = udpMsgMap.keySet();
		Iterator<String> it = keyset.iterator();
		while(it.hasNext())
		{
			String key=it.next();
			UdpMsg msg = udpMsgMap.get(key);

//			System.out.println("循环中， msg信息：" + msg.getUdpId() + "已发送：" + msg.getCount());
			if(msg.getCount() >= 3)
			{
				logger.info("***发送端---检测到发送给" + userIpMap.get(msg.getToId()) + "的" + msg.getToId() + "丢失的消息" + msg.getUdpId());
				it.remove();
			}

			long cTime = System.currentTimeMillis();
			if((cTime - msg.getTime()) > 3000 && msg.getCount() < 5)
			{
				byte[] buffer = msg.toByte();
				try
				{
					DatagramSocket dSender = new DatagramSocket();
					DatagramPacket dp = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(userIpMap.get(msg.getToId())), 2223);
					dSender.send(dp);
					msg.setCount(msg.getCount()+1);
					logger.info("客户端--重发消息:" + userIpMap.get(msg.getToId()) + "的" + msg.getUdpId() + ", 已发送：" + msg.getCount());
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	//启动接收线程
	public void startReciveUdpMsg()
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				reciveUdpMsg();
			}
		}).start();
	}

	//接收方法
	public void reciveUdpMsg()
	{
		logger.info("接收端-接收线程启动...");
		while(true)
		{
			try
			{
				byte[] recvData = new byte[1024 * 10];

				//创建接收数据包对象
				DatagramPacket recvPacket = new DatagramPacket(recvData,recvData.length);
				ds.receive(recvPacket);
				UdpMsg udpMsg = new UdpMsg(recvPacket.getData());
				InetAddress inetAddress = recvPacket.getAddress();

				//数据包为确认类型
				if (udpMsg.getType() == UdpMsg.CONFIRM)
				{
					if (udpMsgMap.get(udpMsg.getUdpId()) != null)
					{
						logger.info("接收端-已接收来自" + inetAddress.getHostAddress() + "的req:" + udpMsg.getUdpId() + "的确认");
						udpMsgMap.remove(udpMsg.getUdpId());
					}
				}
				//收到请求包，首先发送确认包
				else
				{
					if (reviceUdpMsg.get(udpMsg.getUdpId()) == null)
					{
						reviceUdpMsg.put(udpMsg.getUdpId(), true);
						UdpMsg resp = new UdpMsg(udpMsg.getUdpId(), UdpMsg.CONFIRM);

						byte[] data = resp.toByte();
						logger.info("接收端-已收到resp:" + resp.getUdpId() + "来自" + inetAddress.getHostAddress() + "的请求");
						//声明一个新的端口的Socket
						DatagramSocket dSender = new DatagramSocket();
						//获取发送方的InetAddress
						DatagramPacket dp = new DatagramPacket(data, data.length, inetAddress, 2223);
						dSender.send(dp);

						logger.info("接收端-已发送resp:" + resp.getUdpId() + "的应答");

						//交给其他线程处理数据库
						new Thread(new ServerController(inetAddress, udpMsg.getData())).start();
					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void addUserIp(int userId, String ip)
	{
		synchronized (userIpMap)
		{
			userIpMap.put(new Integer(userId), ip);
		}
	}

	public static void deleteUserIp(int userId)
	{
		synchronized (userIpMap)
		{
			if (userIpMap.get(userId) != null)
			{
				userIpMap.remove(userId);
			}
		}
	}

	public static String getUserIp(int userId)
	{
		synchronized (userIpMap)
		{
			return userIpMap.get(userId);
		}
	}
}
