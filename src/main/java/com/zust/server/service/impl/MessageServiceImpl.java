package com.zust.server.service.impl;

import com.zust.common.bean.ChatBean;
import com.zust.common.bean.DataFormat;
import com.zust.server.dao.TmessageMapper;
import com.zust.server.entity.Tmessage;
import com.zust.server.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageServiceImpl implements MessageService
{
	@Autowired
	private TmessageMapper tmessageMapper;

	@Override
	public void addMessage(DataFormat data)
	{
		ChatBean chatBean = (ChatBean) data.getData();
		Tmessage tmessage = new Tmessage();
		tmessage.setSendId(data.getFromId());
		tmessage.setReciveId(data.getToId());
		tmessage.setTime(new Date(data.getTime()));
		tmessage.setMessage(chatBean.getMessage());

		tmessageMapper.insert(tmessage);
	}
}
