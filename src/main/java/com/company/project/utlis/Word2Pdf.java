package com.company.project.utlis;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.apache.log4j.Logger;

/**
 * word转pdf
 *
 * @author Administrator
 */
public class Word2Pdf {
    private static Logger logger = Logger.getLogger(Word2Pdf.class);

    static final int wdDoNotSaveChanges = 0;// 不保存待定的更改。
    static final int wdFormatPDF = 17;// word转PDF 格式
    static final int ppSaveAsPDF = 32;// ppt 转PDF 格式
    private static final int xlTypePDF = 0;

    /**
     * word转pdf
     *
     * @param source 传入路径
     * @param target 传出路径
     */
    public static boolean word2pdf(String source, String target) {

        //初始化com线程
        ComThread.InitSTA();
        logger.info("启动Word");
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", false);

            Dispatch docs = app.getProperty("Documents").toDispatch();
            System.out.println("打开文档" + source);
            // FileName // ConfirmConversions // ReadOnly
            Dispatch doc = Dispatch.call(docs,
                    "Open",
                    source,
                    false,
                    true
            ).toDispatch();
            logger.info("转换文档到PDF " + target);

            File tofile = new File(target);

            if (tofile.exists()) {
                tofile.delete();
            }
            Dispatch.call(doc,
                    "SaveAs",
                    target,
                    wdFormatPDF);

            Dispatch.call(doc, "Close", false);
            long end = System.currentTimeMillis();
            logger.info("转换doc文档..用时 " + +(end - start) + "ms.");

        } catch (Exception e) {
            logger.error("========Error:文档转换失败：" + e.getMessage());
            return false;
        } finally {
            if (app != null) {
                app.invoke("Quit", wdDoNotSaveChanges);
            }
        }
        //关闭文档线程
        ComThread.Release();
        return true;
    }

    /**
     * exce转pdf 这个只能转单表
     *
     * @param source 传入路径
     * @param target 传出路径
     * @return 返回的结果
     */
    public static boolean excel2pdf2(String source, String target) {

        //初始化com线程
        ComThread.InitSTA();

        System.out.println("启动Excel");
        logger.info("启动Excel");
        long start = System.currentTimeMillis();
        // 启动excel(Excel.Application)
        ActiveXComponent app = new ActiveXComponent("Excel.Application");
        try {
            app.setProperty("Visible", false);
            Dispatch workbooks = app.getProperty("Workbooks").toDispatch();
            System.out.println("打开文档" + source);
            logger.info("打开文档" + source);
            // 打开文档的方法
            Dispatch workbook = Dispatch.invoke(workbooks, "Open", Dispatch.Method,
                    new Object[]{source, new Variant(false), new Variant(false)}, new int[3]).toDispatch();

            /**
             * workbook 目标文件位置
             *
             */
            Dispatch.invoke(workbook, "SaveAs", Dispatch.Method,
                    new Object[]{target, new Variant(57), new Variant(false), new Variant(57), new Variant(57),
                            new Variant(false), new Variant(true), new Variant(57), new Variant(true),
                            new Variant(true), new Variant(true)},
                    new int[1]);

            Variant f = new Variant(false);

            logger.info("转换文档到PDF " + target);
            Dispatch.call(workbook, "Close", f);
            long end = System.currentTimeMillis();
            logger.info("转换完成..用时：" + (end - start) + "ms.");
            System.out.println("转换完成..用时：" + (end - start) + "ms.");
        } catch (Exception e) {
            logger.error("========Error:文档转换失败：" + e.getMessage());
            System.out.println("========Error:文档转换失败：" + e.getMessage());
            return false;
        } finally {
            if (app != null) {
                app.invoke("Quit", new Variant[]{});
            }
        }
        //文档转换完毕,关闭线程
        ComThread.Release();
        return true;
    }

    /**
     * Excel转换为PDF
     *
     * @param inputFile 传入的ppt路径
     * @param pdfFile   传出的pdf路径
     * @return   成功或者失败
     */
    public static boolean excel2pdf(String inputFile, String pdfFile) {

        //初始化com线程
        ComThread.InitSTA();
        logger.info("文档路径：" + inputFile + "----" + "转出路径：" + pdfFile);
        System.out.println("文档路径：" + inputFile + "----" + "转出路径：" + pdfFile);
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        Dispatch excels = null;
        Dispatch excel = null;
        try {
            System.out.println("启动Excel");
            logger.info("启动Excel");
            app = new ActiveXComponent("Excel.Application");

            app.setProperty("Visible", false);

            excels = app.getProperty("Workbooks").toDispatch();
            logger.info("打开文档" + inputFile);
            System.out.println("打开文档" + inputFile);

            excel = Dispatch.call(excels, "Open", inputFile, false, true).toDispatch();

            Dispatch.call(excel, "ExportAsFixedFormat", xlTypePDF, pdfFile);


            long end = System.currentTimeMillis();
            logger.info("转换完成..用时：" + (end - start) + "ms.");
            System.out.println("转换完成..用时：" + (end - start) + "ms.");

        } catch (Exception e) {
            logger.error(e);
            return false;
        } finally {
            if (app != null) {
                Dispatch.call(excel, "Close", false);
                app.invoke("Quit");

            }
            //文档转换完毕,释放com线程
            ComThread.Release();
        }
        //文档转换完毕,释放com线程
        ComThread.Release();
        return true;

    }

    /**
     * ppt转pdf
     *
     * @param source 传入路径
     * @param target  传出路径
     * @return  返回结果
     */
    public static boolean ppt2pdf(String source, String target) {
        //启动com线程
        ComThread.InitSTA();
        logger.info("启动PPT");
        System.out.println("启动PPT");
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        try {
            app = new ActiveXComponent("Powerpoint.Application");
            Dispatch presentations = app.getProperty("Presentations").toDispatch();
            logger.info("打开文档" + source);
            System.out.println("打开文档" + source);
            Dispatch presentation = Dispatch.call(presentations,
                    "Open", source,
                    true,
                    true,
                    false
            ).toDispatch();
            logger.info("转换文档到PDF " + target);
            System.out.println("转换文档到PDF " + target);
            File tofile = new File(target);
            if (tofile.exists()) {
                tofile.delete();
            }
            Dispatch.call(presentation,
                    "SaveAs",
                    target,
                    ppSaveAsPDF);

            Dispatch.call(presentation, "Close");
            long end = System.currentTimeMillis();
           logger.info("转换完成..用时：" + (end - start) + "ms.");
            System.out.println("转换完成..用时：" + (end - start) + "ms.");
        } catch (Exception e) {
            logger.error("========Error:文档转换失败：" + e.getMessage());
            System.out.println("========Error:文档转换失败：" + e.getMessage());
            return false;
        } finally {
            if (app != null){
                app.invoke("Quit");
            }
            //释放com线程
            ComThread.Release();
        }
        //释放com线程
        ComThread.Release();
        return true;
    }
}