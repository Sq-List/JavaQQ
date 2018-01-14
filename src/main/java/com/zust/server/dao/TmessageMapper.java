package com.zust.server.dao;

import com.zust.server.entity.Tmessage;

public interface TmessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tmessage record);

    int insertSelective(Tmessage record);

    Tmessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tmessage record);

    int updateByPrimaryKeyWithBLOBs(Tmessage record);

    int updateByPrimaryKey(Tmessage record);
}