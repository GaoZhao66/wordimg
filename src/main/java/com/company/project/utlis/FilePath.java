package com.company.project.utlis;

/**
 * 路径的配置
 * 
 * @author Administrator
 *
 */
public class FilePath {

	// 扫描目录
	private static String mFile = "C:\\" + "word" + "\\";

	// 测试服务器
	//private static String mScanning = "http://bainian.tunnel.echomod.cn";
	// 本地服务器
	// private static String mScanning="http://localhost:8080";
	 
	 //private static String mScanning="http://106.14.83.181:8081";
	
	//海油服务器
	private static String mScanning = "http://10.78.56.81:8080";
	
	public static String getmScanning() {
		return mScanning;
	}

	public static String getmFile() {
		return mFile;
	}

	
}
