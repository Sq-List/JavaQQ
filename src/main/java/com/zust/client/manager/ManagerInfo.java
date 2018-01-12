package com.zust.client.manager;

import com.zust.common.bean.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理个人信息和好友信息
 */
public class ManagerInfo
{
	private static User user;
	private static Map<Integer, User> friendMap;

	public static void setUser(User loginUser)
	{
		user = loginUser;
	}

	public static User getUser()
	{
		return user;
	}

	public static void setUserMap(Map<Integer, User> userMap)
	{
		friendMap = userMap;
	}

	public static Map<Integer, User> getUserMap()
	{
		return friendMap;
	}
}
