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

	/**
	 * flag为false时，搜索全部好友
	 * flag为true时，搜索在线好友
	 * @param tuser
	 * @param flag
	 * @return
	 */
	List<Tuser> selectFriendByUser(@Param("tuser") Tuser tuser, @Param("flag") boolean flag);

	/**
	 * 根据昵称进行搜索
	 * @param tuser
	 * @return
	 */
	List<Tuser> selectUserByNickName(String info);
}
