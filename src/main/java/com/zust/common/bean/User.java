package com.zust.common.bean;

import java.io.Serializable;

public class User implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String loginName;
	private String userName;
	private String password;
	private String avatarSrc;
	private Boolean status;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}

	public Boolean getStatus()
	{
		return status;
	}

	public void setStatus(Boolean status)
	{
		this.status = status;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getAvatarSrc()
	{
		return avatarSrc;
	}

	public void setAvatarSrc(String avatarSrc)
	{
		this.avatarSrc = avatarSrc;
	}
}
