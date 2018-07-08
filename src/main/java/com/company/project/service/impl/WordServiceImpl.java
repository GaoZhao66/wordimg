package com.company.project.service.impl;

import com.company.project.model.TFile;
import com.company.project.model.TFileImages;
import com.company.project.model.WordFile;
import com.company.project.service.TFileImagesService;
import com.company.project.service.TFileService;
import com.company.project.service.WordService;
import com.company.project.utlis.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * 文档转化的实现类
 *
 * @author Administrator
 */
@Service
public class WordServiceImpl implements WordService {

    private static Logger logger = Logger.getLogger(WordServiceImpl.class);

    @Resource
    private TFileService tFileService;

    @Resource
    private TFileImagesService tFileImagesService;

    @Override
    public List<String> getWordImgList(WordFile wordfile) {
        // 图片地址的集合
        List<String> wordImgList;
        try {
            // 判断重复 得到哈希值
            String haxi = HaXiUtil.haxi(wordfile.getFilename());

            // 如果哈希可以查到
            wordImgList = queryHaxi(haxi);

            if (wordImgList != null) {
                return wordImgList;
            }

            // 文档转化
            wordImgList = wordimg(wordfile.getFilename(), wordfile, haxi);

            if (wordImgList != null & wordImgList.size() != 0) {
                return wordImgList;
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("查询哈希的时候发生的异常：" + e);
            return null;
        }

    }


    /**
     * @param filenameaddress 文件地址
     * @param wordfile        文件的类型
     * @param haxi            文件的哈希码
     * @return 返回图片的集合
     * @throws Exception 发生的异常
     */
    private List<String> wordimg(String filenameaddress, WordFile wordfile, String haxi) throws Exception {
        logger.info("文件转换进来的参数：" +"文件名字"+filenameaddress+"-----文件的请求参数"+wordfile+"----哈希码"+haxi);
        // 图片地址的集合
        List<String> wordImgList = new ArrayList<>();
        // 获取一个唯一字符串
        String timeStr = UUIDutlis.getUUID();
        // 图片地址
        String imgAddress = getImgAddress(timeStr);
        // 获取文件名字
        String[] wordnames = filenameaddress.split("\\\\");
        // 文件名字
        String wordname = wordnames[wordnames.length - 1];
        // pdf地址
        String pdfpath = FilePath.getmFile() + "tmp" + "\\" + timeStr + ".pdf";

        long start = System.currentTimeMillis();

        logger.info("转换的类型：" + wordfile.getFiletype());
        logger.info("转换的地址：" +"文档地址"+ filenameaddress+"----------"+"pdf地址"+pdfpath);
        // 根据类型判断
        switch (wordfile.getFiletype()) {
            case "DOC":
            case "DOCX":
                // 第一个参数的文档地址，第二个参数是pdf地址
                boolean docfolat = Word2Pdf.word2pdf(filenameaddress, pdfpath);
                logger.info("doc转换结果：" + docfolat);
                if (docfolat) {
                    //第一个参数是pdf的地址，第二个参数是图片地址，第三个参数是id
                    wordImgList = PdfzImg.pdfimg(pdfpath, imgAddress, timeStr);
                }
                break;
            case "XLS":
            case "XLSX":
                //第一个参数的文档地址，第二个参数是pdf地址
                boolean xlsfloast = Word2Pdf.excel2pdf(filenameaddress, pdfpath);
                logger.info("表格转换结果：" + xlsfloast);
                if (xlsfloast) {
                    //第一个参数是pdf的地址，第二个参数是图片地址，第三个参数是id
                    wordImgList = PdfzImg.pdfimg(pdfpath, imgAddress, timeStr);
                }
                break;
            case "PPT":
            case "PPTX":
                //第一个参数的文档地址，第二个参数是pdf地址
                boolean pptfolat = Word2Pdf.ppt2pdf(filenameaddress, pdfpath);
                logger.info("PPT转换结果：" + pptfolat);
                if (pptfolat) {
                    //第一个参数是pdf的地址，第二个参数是图片地址，第三个参数是id
                    wordImgList = PdfzImg.pdfimg(pdfpath, imgAddress, timeStr);
                }
                break;
            case "PDF":
                // PDF转码
                wordImgList = PdfzImg.pdfimg(filenameaddress, imgAddress, timeStr);
                break;
            default:
                break;
        }
        long end = System.currentTimeMillis();

        logger.info("转换完成..用时：" + (end - start) + "ms.");

        // 创建主表对象
        TFile worddata = new TFile();
        // 创建副表对象
        List<TFileImages> wordimglist = new ArrayList<>();
        // 文件的地址
        worddata.setAddress(filenameaddress);
        // 设置时间
        worddata.setCreateTime(UUIDutlis.getTime());
        // 设置类型
        worddata.setType(wordfile.getFiletype());
        // 设置主表的哈希码
        worddata.setHaxi(haxi);
        // 文件名字
        worddata.setName(wordname);

        for (String aWordImgList : wordImgList) {
            wordimglist.add(new TFileImages(aWordImgList));
        }
        // 创建
        boolean bododo = tFileService.setDocument(worddata, wordimglist);
        if (!bododo) {
            return null;
        }
        return wordImgList;
    }

    /**
     * 根据哈希去查找文件
     *
     * @param haxi 哈希编码
     * @return  数据库的文件列表
     */
    private List<String> queryHaxi(String haxi) {
        List<String> wordImgList;
        // 设置文件的一个对象
        List<TFile> tFileList = tFileService.findByhaxi(haxi);

        // 如果有记录就返回以前的记录
        if (tFileList.size() != 0) {
            // 根据ID查询文档图片
            wordImgList = tFileImagesService.findByWordId(tFileList.get(0).getId());

            //如果附表没有就把主表的删了
            if (wordImgList.size() == 0 || null == wordImgList) {

                Integer integer = tFileService.deleteTFileById(tFileList.get(0).getId());

                if (integer > 0) {
                    return null;
                }
            }
            logger.info("哈希查询成功 以前经过转化：" + wordImgList.toString());
            return wordImgList;
        }
        return null;
    }

    /**
     * 返回一个唯一的地址
     *
     * @param timeStr 传入一个随机时间
     * @return  返回一个路径
     */
    private String getImgAddress(String timeStr) throws Exception {

        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if (!path.exists()) {
            path = new File("");
        }

        // 获取项目路径
        String property = path.getParentFile().getParentFile().getAbsolutePath();

        logger.info("项目路径" + property);
       // property = property + "\\uploadFile" + "\\" + timeStr + "\\";
        property = "E:" + "\\uploadFile" + "\\" + timeStr + "\\";
        logger.info("准备创建的路径" + property);

        // 创建文件夹
        CreateFileUtil.createDir(property);
        logger.info("文件夹创建成功地址为：" + property);
        return property;
    }

}
