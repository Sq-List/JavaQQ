package com.zust.server.service.impl;

import com.zust.common.bean.DataFormat;
import com.zust.common.bean.LoginBean;
import com.zust.common.bean.SearchUserRequestBean;
import com.zust.common.bean.User;
import com.zust.server.dao.TuserMapper;
import com.zust.server.entity.Tuser;
import com.zust.server.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private TuserMapper tuserMapper;

	@Override
	public DataFormat login(DataFormat data)
	{
		//要返回的DataFormat
		DataFormat respDataFormat = new DataFormat();
		respDataFormat.setType(DataFormat.LOGIN);
		respDataFormat.setFromId(0);
		respDataFormat.setToId(data.getFromId());

		//要返回的LoginBean
		LoginBean respLoginBean = new LoginBean();

		//获得的数据 LoginBean
		LoginBean loginBean = (LoginBean) data.getData();
		User loginUser = loginBean.getLoginUser();

		//将获得数据转换成entity
		Tuser tuser = new Tuser();
		BeanUtils.copyProperties(loginUser, tuser);

		tuser = tuserMapper.selectByUsernameAndPassword(tuser);
		//如果密码用户名正确
		if (tuser != null)
		{
			tuser.setStatus(true);
			tuserMapper.updateByPrimaryKey(tuser);

			User respUser = new User();
			BeanUtils.copyProperties(tuser, respUser);
			respUser.setPassword(null);
			respLoginBean.setType(1);
			respLoginBean.setLoginUser(respUser);

			Map<Integer, User> userMap = new HashMap<>();
			List<Tuser> tuserList = tuserMapper.selectFriendByUser(tuser, false);
			for (Tuser t : tuserList)
			{
				User user = new User();
				BeanUtils.copyProperties(t, user);
				userMap.put(user.getId(), user);
			}
			loginBean.setFriendMap(userMap);
		}
		//错误
		else
		{
			respLoginBean.setType(2);
			respLoginBean.setLoginUser(null);
			respLoginBean.setFriendMap(null);
			respLoginBean.setLoginUser(null);
		}

		//将LoginBean
		respDataFormat.setData(respLoginBean);

		return respDataFormat;
	}

	@Override
	public void quit(DataFormat data)
	{
		int quitId = data.getFromId();
		Tuser tuser = tuserMapper.selectByPrimaryKey(quitId);
		tuser.setStatus(false);
		tuserMapper.updateByPrimaryKey(tuser);
	}

	@Override
	public List<DataFormat> findUserOnlineFriend(DataFormat data)
	{
		//获得上线或下线的用户
		Tuser tuser = tuserMapper.selectByPrimaryKey(data.getFromId());
		User user = new User();
		BeanUtils.copyProperties(tuser, user);

		List<DataFormat> dataFormatList = new ArrayList<>();
		//获取上线下线用户的在线好友
		List<Tuser> tuserList = tuserMapper.selectFriendByUser(tuser, false);
		//遍历封装数据
		for (Tuser t : tuserList)
		{
			DataFormat respDataFormat = new DataFormat();
			respDataFormat.setFromId(data.getFromId());
			respDataFormat.setToId(t.getId());
			respDataFormat.setType(DataFormat.USER_STATE);
			respDataFormat.setData(user);
			respDataFormat.setTime(System.currentTimeMillis());

			dataFormatList.add(respDataFormat);
		}
		return dataFormatList;
	}

	@Override
	public DataFormat searchUser(DataFormat data)
	{
		int senderId = data.getFromId();
		SearchUserRequestBean searchUserRequestBean = (SearchUserRequestBean) data.getData();

		List<User> userList = new ArrayList<>();
		List<Tuser> tuserList = tuserMapper.selectUserByNickName(searchUserRequestBean.getInfo());
		for (Tuser t : tuserList)
		{
			User u = new User();
			BeanUtils.copyProperties(t, u);
			userList.add(u);
		}

		DataFormat respDatFormat = new DataFormat();
		respDatFormat.setFromId(0);
		respDatFormat.setToId(senderId);
		respDatFormat.setType(DataFormat.SEARCH_FRIEND);
		respDatFormat.setData(userList);
		respDatFormat.setTime(System.currentTimeMillis());
		return respDatFormat;
	}
}
