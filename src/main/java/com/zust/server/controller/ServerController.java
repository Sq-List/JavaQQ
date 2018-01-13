package com.zust.server.controller;

import com.zust.common.bean.AddFriendRequestBean;
import com.zust.common.bean.DataFormat;
import com.zust.common.bean.LoginBean;
import com.zust.common.bean.User;
import com.zust.server.tool.LoadXml;
import com.zust.server.UDP.ServerUDP;
import com.zust.server.service.FriendService;
import com.zust.server.service.MessageService;
import com.zust.server.service.UserService;
import org.springframework.context.ApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.util.List;

public class ServerController implements Runnable
{
	//发送方的InetAddress
	private InetAddress senderAddress;
	private DataFormat dataFormat;

	private UserService userService;
	private FriendService friendService;
	private MessageService messageService;

	public ServerController(InetAddress inetAddress, byte[] data)
	{
		this.senderAddress = inetAddress;

		ApplicationContext ctx = LoadXml.getCtx();
		userService = ctx.getBean(UserService.class);
		friendService = ctx.getBean(FriendService.class);
		messageService = ctx.getBean(MessageService.class);

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
				quit();
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

		DataFormat respDataFormat = userService.login(dataFormat);
		try
		{
			ServerUDP.sendUdpMsg(respDataFormat);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void quit()
	{
		int senderUserId = dataFormat.getFromId();
		ServerUDP.deleteUserIp(senderUserId);
	}

	public void addFriend(){
		AddFriendRequestBean addFriendRequestBean = (AddFriendRequestBean) dataFormat.getData();
		User user = addFriendRequestBean.getUser();
		if (addFriendRequestBean.getType() == 0){
			DataFormat respDataFormat = friendService.addFriendRequest(dataFormat);
			try {
				ServerUDP.sendUdpMsg(respDataFormat);
			}catch (Exception e){
				e.printStackTrace();
			}
		}else {
			List<DataFormat> dataFormatList = friendService.sendRequestResult(dataFormat);
			try{
				ServerUDP.sendUdpMsg(dataFormatList.get(0));
				ServerUDP.sendUdpMsg(dataFormatList.get(1));
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	public void deleteFriend(){
		List<DataFormat> dataFormatList = friendService.deleteFriend(dataFormat);
		try{
			ServerUDP.sendUdpMsg(dataFormatList.get(0));
			ServerUDP.sendUdpMsg(dataFormatList.get(1));
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
