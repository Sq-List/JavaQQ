package com.zust.server.controller;

import com.zust.common.bean.DataFormat;
import com.zust.common.bean.LoginBean;
import com.zust.common.tool.PicturePath;
import com.zust.server.UDP.ServerUDP;
import com.zust.server.service.FriendService;
import com.zust.server.service.MessageService;
import com.zust.server.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;

public class ServerController implements Runnable
{
	//发送方的InetAddress
	private InetAddress senderAddress;
	private DataFormat dataFormat;

//	private static ApplicationContext ctx = new FileSystemXmlApplicationContext(
//			PicturePath.getPicturePath("/spring/spring-service.xml").getPath());
//	private UserService userService;
//	private FriendService friendService;
//	private MessageService messageService;

	public ServerController(InetAddress inetAddress, byte[] data)
	{
		this.senderAddress = inetAddress;
//		userService = ctx.getBean(UserService.class);
//		friendService = ctx.getBean(FriendService.class);
//		messageService = ctx.getBean(MessageService.class);

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

		LoginBean login = (LoginBean) dataFormat.getData();

		LoginBean loginBean = new LoginBean();
		loginBean.setType(1);
		DataFormat respDataFormat = new DataFormat(0, senderUserId, DataFormat.LOGIN, loginBean, System.currentTimeMillis());

//		DataFormat respDataFormat = userService.login(dataFormat);
		try
		{
			ServerUDP.sendUdpMsg(respDataFormat);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
