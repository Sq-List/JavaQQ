package com.zust.server.service.impl;

import com.zust.common.bean.AddFriendRequestBean;
import com.zust.common.bean.DataFormat;
import com.zust.common.bean.DeleteFriendRequestBean;
import com.zust.common.bean.User;
import com.zust.server.dao.TfriendMapper;
import com.zust.server.dao.TuserMapper;
import com.zust.server.entity.Tfriend;
import com.zust.server.entity.Tuser;
import com.zust.server.service.FriendService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService
{

    @Autowired
    private TuserMapper tuserMapper;

    @Autowired
    private TfriendMapper tfriendMapper;

    @Override
    public DataFormat addFriendRequest(DataFormat dataFormat) {
        AddFriendRequestBean addFriendRequestBean = (AddFriendRequestBean) dataFormat.getData();
        User user = addFriendRequestBean.getUser();
        User userBack = new User();
        Tuser tuser = tuserMapper.selectUserById(dataFormat.getFromId());
        tuser.setPassword(null);
        BeanUtils.copyProperties(tuser, userBack);
        DataFormat dataFormatBack = new DataFormat();
        AddFriendRequestBean addFriendRequestBeanBack = new AddFriendRequestBean();
		addFriendRequestBeanBack.setType(0);
		addFriendRequestBeanBack.setUser(userBack);
        dataFormatBack.setTime(System.currentTimeMillis());
        dataFormatBack.setFromId(0);
        dataFormatBack.setToId(dataFormat.getToId());
        dataFormatBack.setData(addFriendRequestBeanBack);
        dataFormatBack.setType(DataFormat.ADD_FRIEND);
        return dataFormatBack;
    }

    @Override
    public List<DataFormat> sendRequestResult(DataFormat dataFormat) {
        AddFriendRequestBean addFriendRequestBean = (AddFriendRequestBean) dataFormat.getData();
        ArrayList<DataFormat> dataFormatArrayList = new ArrayList<DataFormat>();
        AddFriendRequestBean addFriendRequestBeanToTo = new AddFriendRequestBean();
        AddFriendRequestBean addFriendRequestBeanToFrom = new AddFriendRequestBean();

        if (addFriendRequestBean.getType() == 1){
            addFriendRequestBeanToTo.setType(1);
            addFriendRequestBeanToFrom.setType(1);
            Tfriend tfriend = new Tfriend();
            tfriend.setRequestUserId(dataFormat.getToId());
            tfriend.setBerequestUserId(dataFormat.getFromId());
            tfriendMapper.insert(tfriend);
        }else if (addFriendRequestBean.getType() == 2){
            addFriendRequestBeanToTo.setType(2);
            addFriendRequestBeanToFrom.setType(2);
        }

        User userFrom = new User();
        Tuser tuserFrom = tuserMapper.selectUserById(dataFormat.getFromId());
        tuserFrom.setPassword(null);
        BeanUtils.copyProperties(tuserFrom, userFrom);

        User userTo = new User();
        Tuser tuserTo = tuserMapper.selectUserById(dataFormat.getToId());
        tuserTo.setPassword(null);
        BeanUtils.copyProperties(tuserTo, userTo);

        DataFormat dataFormatToFrom = new DataFormat();
        addFriendRequestBeanToFrom.setUser(userTo);
        dataFormatToFrom.setFromId(0);
        dataFormatToFrom.setToId(dataFormat.getFromId());
        dataFormatToFrom.setType(DataFormat.ADD_FRIEND);
        dataFormatToFrom.setData(addFriendRequestBeanToFrom);
        dataFormatToFrom.setTime(System.currentTimeMillis());
        dataFormatArrayList.add(dataFormatToFrom);

        DataFormat dataFormatToTo = new DataFormat();
        addFriendRequestBeanToTo.setUser(userFrom);
        dataFormatToTo.setFromId(0);
        dataFormatToTo.setToId(dataFormat.getToId());
        dataFormatToTo.setType(DataFormat.ADD_FRIEND);
        dataFormatToTo.setData(addFriendRequestBeanToTo);
        dataFormatToTo.setTime(System.currentTimeMillis());
        dataFormatArrayList.add(dataFormatToTo);

        return dataFormatArrayList;
    }

    @Override
    public List<DataFormat> deleteFriend(DataFormat dataFormat) {
        tfriendMapper.deleteByBothId(dataFormat.getFromId(), dataFormat.getToId());

        ArrayList<DataFormat> dataFormatArrayList = new ArrayList<DataFormat>();
        DeleteFriendRequestBean deleteFriendRequestBeanToFrom = new DeleteFriendRequestBean();
        DeleteFriendRequestBean deleteFriendRequestBeanToTo = new DeleteFriendRequestBean();

        User userFrom = new User();
        Tuser tuserFrom = tuserMapper.selectUserById(dataFormat.getFromId());
        tuserFrom.setPassword(null);
        BeanUtils.copyProperties(tuserFrom, userFrom);

        User userTo = new User();
        Tuser tuserTo = tuserMapper.selectUserById(dataFormat.getToId());
        tuserTo.setPassword(null);
        BeanUtils.copyProperties(tuserTo, userTo);

        deleteFriendRequestBeanToTo.setBedeleteder(null);
        deleteFriendRequestBeanToTo.setAsker(userFrom);
        DataFormat dataFormatToTo = new DataFormat();
        dataFormatToTo.setFromId(0);
        dataFormatToTo.setToId(dataFormat.getToId());
        dataFormatToTo.setType(DataFormat.DELETE_FRIEND);
        dataFormatToTo.setData(deleteFriendRequestBeanToTo);
        dataFormatToTo.setTime(System.currentTimeMillis());
        dataFormatArrayList.add(dataFormatToTo);

        deleteFriendRequestBeanToFrom.setAsker(userFrom);
        deleteFriendRequestBeanToFrom.setBedeleteder(userTo);
        DataFormat dataFormatToFrom = new DataFormat();
        dataFormatToFrom.setFromId(0);
        dataFormatToFrom.setToId(dataFormat.getFromId());
        dataFormatToFrom.setType(DataFormat.DELETE_FRIEND);
        dataFormatToFrom.setData(deleteFriendRequestBeanToFrom);
        dataFormatToFrom.setTime(System.currentTimeMillis());
        dataFormatArrayList.add(dataFormatToFrom);

        return dataFormatArrayList;
    }

}
