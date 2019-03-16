package com.book.fairy.constant.upload;

/**
 * Created with IntelliJ IDEA.
 * User: yz-he
 * Date: 2018\2\13 0013 16:52
 * To change this template use File | Settings | File Templates.
 * Description: 上传文件的富文本框类型
 */
public enum UploadEditTypeEnum {
    CLOUD_CLINIC_HIGH_SPEED(9), //云门诊高拍仪上传文件
    UEditor(2),//百度富文本框
    Wangeditor(3);//wangEditor 富文本框

    private int value;

    UploadEditTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
