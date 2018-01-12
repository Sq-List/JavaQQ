package com.zust.common.bean;

public class User
{
	private Integer id;
	private String userName;
	private String password;
	private String avatarSrc;
	private boolean status;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
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
