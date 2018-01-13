package com.zust.server.dao;

import com.zust.server.entity.Tfriend;
import com.zust.server.entity.Tuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TfriendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tfriend record);

    int insertSelective(Tfriend record);

    Tfriend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tfriend record);

    int updateByPrimaryKey(Tfriend record);

    int deleteByBothId(@Param("idone") int idone, @Param("idtwo") int idtwo);

}
