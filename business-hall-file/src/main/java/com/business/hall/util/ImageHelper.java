package com.business.hall.util;


import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by yz-he on 2017/9/7.
 */
public class ImageHelper {

    /**
     * 采用指定宽度、高度或压缩比例 的方式对图片进行压缩
     * @param imgsrc 源图片地址文件
     * @param imgdist 目标图片地址
     * @param widthdist 压缩后图片宽度（当rate==null时，必传）
     * @param heightdist 压缩后图片高度（当rate==null时，必传）
     * @param rate 压缩比例
     */
    public static void reduceImg(String imgsrc, String imgdist, int widthdist,
                                 int heightdist, Float rate) {
        try {
            File srcfile = new File(imgsrc);
            // 检查文件是否存在
            if (!srcfile.exists()) {
                return;
            }
            // 如果rate不为空说明是按比例压缩
            if (rate != null && rate > 0) {
                // 获取文件高度和宽度
                int[] results = getImgWidth(srcfile);
                if (results == null || results[0] == 0 || results[1] == 0) {
                    return;
                } else {
                    widthdist = (int) (results[0] * rate);
                    heightdist = (int) (results[1] * rate);
                }
            }
            // 开始读取文件并进行压缩
            Image src = javax.imageio.ImageIO.read(srcfile);
            BufferedImage tag = new BufferedImage((int) widthdist,
                    (int) heightdist, BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(
                    src.getScaledInstance(widthdist, heightdist,
                            Image.SCALE_SMOOTH), 0, 0, null);

            FileOutputStream out = new FileOutputStream(imgdist);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(tag);
            out.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取图片宽度
     *
     * @param file
     *            图片文件
     * @return 宽度
     */
    public static int[] getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int result[] = { 0, 0 };
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            result[0] = src.getWidth(null); // 得到源图宽
            result[1] = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void reduceBigImg(String imgsrc, String imgdist, int widthdist,
                                 int heightdist){
        File imgsrcFile = new File(imgsrc);
        if(imgsrcFile.length()/1024 > 400){//大于 400 kb
            int[] results = getImgWidth(imgsrcFile);
            float size = results[0] > results[1] ? results[0] : results[1];
            if(size>900){
                float fl = 700 / size;
                reduceImg(imgsrc, imgdist, widthdist, heightdist,fl);
            }else{
                reduceImg(imgsrc, imgdist, widthdist, heightdist, new Float(1));
            }
        }else{
            reduceImg(imgsrc, imgdist, 1000, 1000, new Float(1));
        }
    }




    /*public static void main(String[] args) {
        *//**
         * d://3.jpg 源图片
         * d://31.jpg 目标图片
         * 压缩宽度和高度都是1000
         *
         *//*
        System.out.println("压缩图片开始...");
        File srcfile = new File("D:\\upload\\aaa\\c.jpg");
        System.out.println("压缩前srcfile size:" + srcfile.length());

        System.out.println("压缩前srcfile size:" + srcfile.length()/1024);
        if(srcfile.length()/1024>400){//大于 400 kb
            //File _srcfile = new File("D:\\upload\\aaa\\a.jpg");
            int[] results = getImgWidth(srcfile);
            float size = results[0] > results[1] ? results[0] : results[1];
            if(size>900){
                float fl = 900 / size;
                reduceImg("D:\\upload\\aaa\\c.jpg", "D:\\upload\\aaa\\d.jpg", 1000, 1000,fl);
                System.out.println("\"我压缩了\" = " + "我压缩了");
            }
        }




        //reduceImg("D:\\upload\\aaa\\a.jpg", "D:\\upload\\aaa\\b.jpg", 1000, 1000,null);
        File distfile = new File("D:\\upload\\aaa\\b.jpg");
        System.out.println("压缩后distfile size:" + distfile.length());

    }*/


























}
