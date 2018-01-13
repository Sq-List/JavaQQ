package com.zust.server.dao;

import com.zust.server.entity.Tuser;

import java.util.List;

public interface TuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tuser record);

    int insertSelective(Tuser record);

    Tuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tuser record);

    int updateByPrimaryKey(Tuser record);

    Tuser selectByUsernameAndPassword(Tuser tuser);

    List<Tuser> selectFriendByUser(Tuser tuser);

    Tuser selectUserById(int id);

}
