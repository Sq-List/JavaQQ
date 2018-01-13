package com.zust.server.service;

import com.zust.common.bean.DataFormat;

import java.util.List;

public interface FriendService
{
    DataFormat addFriendRequest(DataFormat dataFormat);

    List<DataFormat> sendRequestResult(DataFormat dataFormat);

    List<DataFormat> deleteFriend(DataFormat dataFormat);

}
