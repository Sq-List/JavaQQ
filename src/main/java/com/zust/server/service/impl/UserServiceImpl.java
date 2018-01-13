package com.zust.server.service.impl;

import com.zust.common.bean.DataFormat;
import com.zust.common.bean.LoginBean;
import com.zust.common.bean.User;
import com.zust.server.dao.TuserMapper;
import com.zust.server.entity.Tuser;
import com.zust.server.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		respDataFormat.setFromId(0);
		respDataFormat.setToId(data.getFromId());

		LoginBean respLoginBean = new LoginBean();

		LoginBean loginBean = (LoginBean) data.getData();
		User loginUser = loginBean.getLoginUser();

		Tuser tuser = new Tuser();
		BeanUtils.copyProperties(loginUser, tuser);

		tuser = tuserMapper.selectByUsernameAndPassword(tuser);
		if (tuser != null)
		{
			tuser.setStatus(true);
			tuserMapper.updateByPrimaryKey(tuser);

			respLoginBean.setType(1);

			User respLoginUser = new User();
			BeanUtils.copyProperties(tuser, respLoginUser);
			respLoginBean.setLoginUser(respLoginUser);

			Map<Integer, User> userMap = new HashMap<>();
			List<Tuser> tuserList = tuserMapper.selectFriendByUser(tuser);
			for (Tuser t : tuserList)
			{
				User user = new User();
				BeanUtils.copyProperties(t, user);
				userMap.put(user.getId(), user);
			}
			loginBean.setFriendMap(userMap);
		}
		else
		{
			respLoginBean.setType(2);
			respLoginBean.setFriendMap(null);
			respLoginBean.setLoginUser(null);
		}

		respDataFormat.setData(loginBean);

		return respDataFormat;
	}
}
