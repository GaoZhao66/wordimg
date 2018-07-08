package com.company.project.utlis;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

public class PdfzImg2 {
	/**
	 * 
	 * @param filePath
	 *            传入PDF路径
	 * @param outPath
	 *            传出图片路径
	 * @param icepdf_a
	 *            名字
	 */
	public static List<String> pdfimg(String filePath, String outPath, String timeStr) {
		Document document = new Document();
		document.setFile(filePath);
		// 缩放比例
		float scale = 2.5f;
		// 旋转角度
		float rotation = 0f;

		// 设置一个存储图片的集合
		List<String> imgPathsList = new ArrayList<String>();

		for (int i = 0; i < document.getNumberOfPages(); i++) {
			BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
					org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
			RenderedImage rendImage = image;
			try {
				String imgName = i + ".png";
				System.out.println(imgName);
				File file = new File(outPath + imgName);

				ImageIO.write(rendImage, "png", file);
				imgPathsList.add(timeStr + "/" + i + ".png");

			} catch (Exception e) {
				e.printStackTrace();
			}
			image.flush();
		}
		document.dispose();

	
		return imgPathsList;
	}

}
