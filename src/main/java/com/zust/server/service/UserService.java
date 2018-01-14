package com.zust.server.service;

import com.zust.common.bean.DataFormat;
import com.zust.common.bean.User;

import java.util.List;

public interface UserService
{
	DataFormat login(DataFormat data);

	void quit(DataFormat data);

	List<DataFormat> findUserOnlineFriend(DataFormat data);

	DataFormat searchUser(DataFormat data);
}
