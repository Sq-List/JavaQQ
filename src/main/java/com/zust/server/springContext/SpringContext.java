package com.zust.server.springContext;

import com.zust.common.tool.PicturePath;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SpringContext
{
	private static ApplicationContext ctx;

	static
	{
		ctx = new FileSystemXmlApplicationContext(PicturePath.getPicturePath("/spring/spring-service.xml").getPath());
	}

	public static ApplicationContext getCtx()
	{
		return ctx;
	}
}
