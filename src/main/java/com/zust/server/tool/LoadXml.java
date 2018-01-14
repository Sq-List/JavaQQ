package com.zust.server.tool;

import com.zust.common.tool.PicturePath;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class LoadXml
{
	private static ApplicationContext ctx = new FileSystemXmlApplicationContext(PicturePath.getPicturePath("/spring/spring-service.xml").getPath());;

	public static ApplicationContext getCtx()
	{
		return ctx;
	}
}
