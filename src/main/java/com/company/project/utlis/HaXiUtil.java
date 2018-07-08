package com.company.project.utlis;

import com.company.project.model.TFile;
import com.company.project.model.TFileImages;
import com.company.project.service.TFileService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;




/**
 * 判断哈希的一个工具类
 * @author 阳斌
 */
@Component
public class HaXiUtil {

	@Resource
	private TFileService tFileService;

	/**
	 * 查询哈希值
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String haxi(String str) throws Exception {
		// 读入文件
		File file = new File(str);
		FileInputStream fis = new FileInputStream(file);
		String md5 = DigestUtils.md5Hex(IOUtils.toByteArray(fis));
		IOUtils.closeQuietly(fis);
		System.out.println("MD5:" + md5);
		return md5;
	}

	/**
	 * 查询数据库哈希值对的记录
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public  List<TFile> quiryhaxi(String haxi) throws Exception {

		return tFileService.findByhaxi(haxi);
	}
	
	/**
	 * 返回图片地址集合
	 * @param strimg
	 * @return
	 * @throws Exception
	 */
	/*public static List<String> quirywordimg(int id) throws Exception {
		
		List<String> inglist=new ArrayList<>();
		
		TFileImagesDao worddao = new TFileImagesDaopo();
		
		List<TFileImages> findImgIdlist = worddao.findImgId(id);
		
		for (int i = 0; i < findImgIdlist.size(); i++) {
			inglist.add(findImgIdlist.get(i).getAddress());
		}
		
		return inglist;
	}*/
}
