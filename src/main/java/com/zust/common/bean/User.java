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
	private String gender;
	private Integer age;
	private String birthday;
	private String address;
	private String intro;
	private Boolean status;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status)
	{
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

	public String getLoginName()
	{
		return loginName;
	}

	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
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

	public static long getSerialVersionUID()
	{
		return serialVersionUID;
	}

	public String getGender()
	{
		return gender;
	}

	public void setGender(String gender)
	{
		this.gender = gender;
	}

	public Integer getAge()
	{
		return age;
	}

	public void setAge(Integer age)
	{
		this.age = age;
	}

	public String getBirthday()
	{
		return birthday;
	}

	public void setBirthday(String birthday)
	{
		this.birthday = birthday;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getIntro()
	{
		return intro;
	}

	public void setIntro(String intro)
	{
		this.intro = intro;
	}
}
