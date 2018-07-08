package com.company.project.web;

import com.company.project.model.WordFile;
import com.company.project.service.WordService;
import com.company.project.utlis.CreateFileUtil;
import com.company.project.utlis.HaXiUtil;
import com.company.project.utlis.UUIDutlis;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 文档转换的一个Controller
 *
 * @author Administrator
 */
@Controller
public class WordImgController {
    private static Logger logger = Logger.getLogger(WordImgController.class);

    @Resource
    private WordService wordService;

    /**
     * post请求然后将文档转化存入数据库
     */
    @RequestMapping(value = "/wordFile", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> wordFile2(@RequestBody WordFile wordfile) {

        logger.info("服务器传入参数:" + wordfile.toString());

        Map<String, Object> modelMap = new HashMap<>(16);
        List<String> wordImgList;

        // 1.判断文件地址是否有
        boolean fileExists = CreateFileUtil.judeFileExists(new File(wordfile.getFilename()));

        if (!fileExists) {
            modelMap.put("coding", 104);
            modelMap.put("message", "请求失败,请检查文件路径是否正确！");
            modelMap.put("success", false);
            logger.error("coding:104" + "请求失败,请检查文件路径是否正确！");
            return modelMap;
        }
        // 2.判断类型是否正确
        boolean fileTypeExists = CreateFileUtil.judeFileTypeExists(wordfile.getFiletype());

        if (!fileTypeExists) {
            modelMap.put("coding", 103);
            modelMap.put("message", "请求失败,请检查文件类型是否正确!");
            modelMap.put("success", false);
            logger.error("coding:103" + "请求失败,请检查文件类型是否正确！");
            return modelMap;
        }
       String to= "C:\\\\word\\\\"+ UUIDutlis.getUUID()+"."+wordfile.getFiletype();

        //将文件拷贝到指定的路径
        try {
            CreateFileUtil.copyFileUsingApacheCommonsIO(wordfile.getFilename(),to);
        } catch (IOException e) {
            logger.error("文件拷贝异常");
            e.printStackTrace();
        }
        wordfile.setFilename(to);
        logger.error("文件拷贝成功地址为"+to);
        // 将其转化为大写方便后面判断
        wordfile.setFiletype((wordfile.getFiletype()).toUpperCase());


        // 判断重复 得到哈希值
        String haxi=null;
        try {
            haxi = HaXiUtil.haxi(wordfile.getFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 根据传进来的参数进行封装
        // 根据传进来的参数进行封装
        StringBuilder sb = new StringBuilder();
        sb.append("PNG");
        sb.append(haxi);

        logger.info("------------锁外面--------------");
        // 定义一个锁如果传进来的相同的参数就进去这个锁
        synchronized (sb.toString().intern()) {
            logger.info("------------锁里面--------------");
            System.out.println("------------锁--------------");
            //WordService word = new WordImpi();
            wordImgList = wordService.getWordImgList(wordfile);

            //删除拷贝的文件
            CreateFileUtil.deleteFile(wordfile.getFilename());

            if (wordImgList != null && wordImgList.size() != 0) {

                modelMap.put("coding", 200);
                modelMap.put("message", "请求成功");
                //加上服务器标识
                for (int i=0;i<wordImgList.size();i++){
                    wordImgList.set(i,"B/"+wordImgList.get(i));
                }
                modelMap.put("quiryWordImgList", wordImgList);
                modelMap.put("success", true);
                logger.info("quiryWordImgList"+ wordImgList.toString());
                return modelMap;
            }
            modelMap.put("coding", 100);
            modelMap.put("message", "请求失败");
            modelMap.put("success", false);
            logger.error("coding:100" + "请求失败！");
            return modelMap;
        }
    }

}
