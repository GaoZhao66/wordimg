package com.company.project.utlis;

import org.apache.log4j.Logger;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * pdf转化为图片的工具类
 * @author 阳斌
 */
public class PdfzImg {

	private static Logger logger = Logger.getLogger(PdfzImg.class);
	/**
	 * 
	 * @param filePath
	 *            传入PDF路径
	 * @param outPath
	 *            传出图片路径
	 * @param timeStr 名字
	 *
	 */
	public static List<String> pdfimg(String filePath, String outPath, String timeStr) {

		Document document = new Document();
		
		try {
			document.setFile(filePath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//pdf的总页数
		int pages = document.getNumberOfPages();
		logger.info("PDF总的页数"+"pages  " + pages);


		//设置一个存储图片的集合
		List<String> imgPathsList = new ArrayList<String>();

		BufferedImage image = null;

		RenderedImage rendImage = null;
		// 缩放比例（大图）
		float scale = 1.5f;
		// 旋转角度
		float rotation = 0f;
		
		//生成图片
		for (int i = 0; i < pages; i++) {
			image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN, Page.BOUNDARY_TRIMBOX, rotation,
					scale);
			rendImage = image;
			
			File file = new File(outPath  + i + ".jpg");

			imgPathsList.add(timeStr + "/" + i + ".jpg");

			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					ImageIO.write(rendImage, "jpg", file);
				} catch (IOException e) {
					e.printStackTrace();
				}
				image.flush();
			} else {
				logger.info("/t capturing page is exist !");
				System.out.println("/t capturing page is exist !");
			}
		}

		document.dispose();
		return imgPathsList;
	}

}
