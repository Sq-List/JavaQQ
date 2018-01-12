package com.zust.common.tool;

import java.net.URL;

/**
 * 工具类：用于获取图片的路径
 */
public class PicturePath
{
	//路径的最前面记得加斜杠 比如："/image/1.png"
	public static URL getPicturePath(String relativePath)
	{
		return PicturePath.class.getResource(relativePath);
	}
}
