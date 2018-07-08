package com.company.project.utlis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * 关于时间的一些工具类
 * @author 阳斌
 */
public class UUIDutlis {
	/**
	 * 获得一个UUID
	 * 
	 * @return String UUID
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	/**
	 * 获得一个时间字符串
	 * 
	 * @return String UUID
	 */
	public static String getTimeStr() {
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		// new Date()为获取当前系统时间
		return df.format(new Date());
	}

	/**
	 * 返回时间的一个int
	 * 
	 * @return 返回时间的一个数字
	 */
	public static int getTimeInt(String format) {
		int i = Integer.valueOf(format).intValue();
		System.out.println(i+"");
		return i;
	}
	
	/**
	 * 获得一个时间字符串
	 * 
	 * @return String UUID
	 */
	public static String getTime() {
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
		// new Date()为获取当前系统时间
		return df.format(new Date());
	}

}
