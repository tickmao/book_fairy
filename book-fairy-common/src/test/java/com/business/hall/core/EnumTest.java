package com.book.fairy.core;

import com.book.fairy.constant.upload.ImgBusinessTypeEnum;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: yz-he
 * Date: 2018\2\13 0013 21:53
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class EnumTest {

    @Test
    public void getTypeUrl() throws Exception {
        System.out.println("ImgUploadTypeEnum.IMG_AUNT.getIndex() = " + ImgBusinessTypeEnum.IMG_BUSINESS_HALL.getIndex());
        System.out.println("ImgUploadTypeEnum.IMG_AUNT.getName() = " + ImgBusinessTypeEnum.IMG_BUSINESS_HALL.getName());
    }

    @Test
    public void getTypeUrlByIndex() throws Exception {

        System.out.println("ImgUploadTypeEnum.getValueByIndex(1) = " + ImgBusinessTypeEnum.getValueByIndex(3));

    }

    @Test
    public void stringTest() throws Exception {
        String str = "http://localhost:8083/wind-file/img/aunt/lifePhoto/2018-02-17/1/735651713fc44f92a61ab9cffff06119.jpg";

        System.out.println("str = " + str);

        System.out.println("str.length() : " + str.length());
    }
}