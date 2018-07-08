package com.company.project.utlis;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * 有关文件的工具类
 * @author 阳斌
 */
public class CreateFileUtil {

	private static Logger logger = Logger.getLogger(CreateFileUtil.class);
	
	/**
	 * 创建文件
	 * 
	 * @param destFileName  创建文件地址
	 * @return 是否成功
	 */
	public static boolean createFile(String destFileName) {
		File file = new File(destFileName);
		if (file.exists()) {
			logger.info("创建单个文件" + destFileName + "失败，目标文件已存在！");
			return false;
		}
		if (destFileName.endsWith(File.separator)) {
			logger.info("创建单个文件" + destFileName + "失败，目标文件不能为目录！");
			return false;
		}
		// 判断目标文件所在的目录是否存在
		if (!file.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			logger.info("目标文件所在目录不存在，准备创建它！");
			if (!file.getParentFile().mkdirs()) {
				
				logger.info("创建目标文件所在目录失败！");
				return false;
			}
		}
		// 创建目标文件
		try {
			if (file.createNewFile()) {
				logger.info("创建单个文件" + destFileName + "成功！");
				return true;
			} else {
				logger.info("创建单个文件" + destFileName + "失败！");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("创建单个文件" + destFileName + "失败！" + e.getMessage());
			return false;
		}
	}

	/**
	 * 创建文件夹
	 * 
	 * @param destDirName  需要创建的文件夹的名字
	 * @return  成功或者失败
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {
			logger.info("创建目录" + destDirName + "失败，目标目录已经存在");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {
			destDirName = destDirName + File.separator;
		}
		// 创建目录
		if (dir.mkdirs()) {
			logger.info("创建目录" + destDirName + "成功！");
			return true;
		} else {
			logger.info("创建目录" + destDirName + "失败！");
			return false;
		}
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param file 文件
	 * @return  存在返回真
	 */
	public static boolean judeFileExists(File file) {
		if (file.exists()) {
			logger.info("file exists");
			return true;
		}
		return false;

	}

	/**
	 * 判断文件类型是否正确
	 * 
	 * @param str 判断是否是可以转换的类型
	 * @return 如果有类型就返回真
	 */
	public static boolean judeFileTypeExists(String str) {
		String s = str.toUpperCase();
		switch (s) {
		case "DOC":
		case "DOCX":
		case "XLS":
		case "XLSX":
		case "PPT":
		case "PPTX":
		case "PDF":
			return true;
			default:
		}
		return false;
	}

	/**
	 * 文件的拷贝
	 * @param up 传入文件的路径
	 * @param to  需要拷贝的文件的路径
	 * @throws IOException 返回异常
	 */
	public static void  copyFileUsingApacheCommonsIO(String up, String to) throws IOException {
		File source=new File(up);
		File dest=new File(to);
		FileUtils.copyFile(source, dest);
	}

	/**
	 * 删除单个文件
	 *
	 * @param fileName
	 *            要删除的文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				logger.info("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				logger.info("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			logger.info("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

}
