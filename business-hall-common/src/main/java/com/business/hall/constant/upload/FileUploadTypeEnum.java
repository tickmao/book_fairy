package com.business.hall.constant.upload;

/**
 * Created with IntelliJ IDEA.
 * User: yz-he
 * Date: 2018\2\13 0013 16:52
 * To change this template use File | Settings | File Templates.
 * Description: 文件上传类型  (文件类型/上传使用项目类型)
 */
public enum FileUploadTypeEnum {
    IMG_UPLOAD("/img"),//阿姨图片
    FILE_UPLOAD("/file");//阿姨文件
    private String value;

    FileUploadTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}