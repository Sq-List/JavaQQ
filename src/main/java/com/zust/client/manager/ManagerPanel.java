package com.zust.client.manager;

import com.zust.client.view.Login;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 面板管理类
 * 加入、取出、删除面板的统一名字:
 登录面板：	loginPanel – Login
 注册面板：	registerPanel – Register
 聊天面板：	chatPanel – ChatPane
 好友列表面板：	mainPanel – Main
 搜索好友面板：searchPanel – SearchPanel
 个人信息面板：userInfoFrame – UserInfoFrame
 */
public class ManagerPanel
{
	private static final Map<String, Object> panels = new HashMap<>();

	public static void add(String name, Object object)
	{
		synchronized (panels)
		{
			panels.put(name, object);
		}
	}

	public static void delete(String name)
	{
		synchronized (panels)
		{
			panels.remove(name);
		}
	}

	public static Object get(String name)
	{
		synchronized (panels)
		{
			return panels.get(name);
		}
	}
}
