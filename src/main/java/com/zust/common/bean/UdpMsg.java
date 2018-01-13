package com.zust.common.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class UdpMsg
{
	private static final long serialVersionUID = 1L;
	public static final byte REQUEST = 0;
	public static final byte CONFIRM = 1;

	//udp的id
	private int udpId;
	//消息的总长度
	private int totalLength;
	//消息的类型
	private byte type;
	//记录目标Id，不传输，只用来给重传
	private int toId;
	//传输的次数，不传输，只用来判断
	private int count = 1;
	//最后一次传输的时间的毫秒数，不传输，只用来判断
	private long time;
	//传输的数据（类型为CONFIRM时，为空）
	private byte[] data;

	//声明UdpMsg时调用
	public UdpMsg(int udpId, byte type, int toId, long time, byte[] data)
	{
		this.udpId = udpId;
		this.type = type;
		this.toId = toId;
		this.time = time;
		this.data = data;
		this.totalLength = 4 + 4 + 1 + 4 + data.length;
	}

	//声明确认包时调用
	public UdpMsg(int udpId, byte type)
	{
		this.udpId = udpId;
		this.type = type;
	}

	//获取UdpMsg时调用
	public UdpMsg(byte[] udpData)
	{
		try
		{
			ByteArrayInputStream bins = new ByteArrayInputStream(udpData);
			DataInputStream dins = new DataInputStream(bins);

			this.totalLength = dins.readInt();
			this.udpId = dins.readInt();
			this.type = dins.readByte();

			if (type == REQUEST)
			{
				this.data = new byte[totalLength - 4 - 4 - 1 - 4];
				dins.readFully(data);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public byte[] toByte()
	{
		try
		{
			ByteArrayOutputStream bous = new ByteArrayOutputStream();
			DataOutputStream dous = new DataOutputStream(bous);
			dous.writeInt(totalLength);
			dous.writeInt(udpId);
			dous.writeByte(type);
			if (type == REQUEST)
			{
				dous.write(data);
			}
			dous.flush();

			return bous.toByteArray();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public int getUdpId()
	{
		return udpId;
	}

	public void setUdpId(int udpId)
	{
		this.udpId = udpId;
	}

	public int getTotalLength()
	{
		return totalLength;
	}

	public void setTotalLength(int totalLength)
	{
		this.totalLength = totalLength;
	}

	public byte getType()
	{
		return type;
	}

	public void setType(byte type)
	{
		this.type = type;
	}

	public int getToId()
	{
		return toId;
	}

	public void setToId(int toId)
	{
		this.toId = toId;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public long getTime()
	{
		return time;
	}

	public void setTime(long time)
	{
		this.time = time;
	}

	public byte[] getData()
	{
		return data;
	}

	public void setData(byte[] data)
	{
		this.data = data;
	}
}
