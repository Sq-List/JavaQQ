package com.zust.server.dao;

import com.zust.server.entity.Tuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tuser record);

    int insertSelective(Tuser record);

    Tuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tuser record);

    int updateByPrimaryKey(Tuser record);

	Tuser selectByUsernameAndPassword(Tuser tuser);

	List<Tuser> selectFriendByUser(@Param("tuser") Tuser tuser, @Param("flag") Boolean flag);

	List<Tuser> selectUserByNickName(Tuser tuser);

	Tuser selectUserById(int id);
}
