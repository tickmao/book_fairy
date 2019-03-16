package com.book.fairy.constant.upload;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yz-he on 2017/9/29.
 */
public enum ImgBusinessTypeEnum {
    IMG_COMMON("core", 1),                       //公共路径
    IMG_COMMON_HEADPHOTO("core@headPhoto", 11) ,  //用户头像
    IMG_COMMON_IDENTITY("core@identity", 12) ,    //用户身份证
    IMG_COMMON_temp("core@temp", 19) ,            //临时照
    IMG_BUSINESS_HALL("businessHall", 2),
    IMG_BUSINESS_HALL_HEADPHOTO("businessHall@headPhoto", 21),              //员工头像
    IMG_BUSINESS_HALL_EQUIPMENTPHOTO("businessHall@equipmentPhoto", 22),    //设备图片
    IMG_BUSINESS_HALL_TEMP("businessHall@temp", 29),                 //临时照
    IMG_TEMP("temp", 9);                            //临时照片
    // 成员变量
    private String name;
    private int index;

    private static Map<Integer, ImgBusinessTypeEnum> imgUploadTypeEnumMap = null;
    private static Map<Integer, String> imgUploadTypeMap = null;

    // 构造方法
    private ImgBusinessTypeEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * 获取所有 枚举对象 Map信息
     * @return
     */
    public static Map<Integer, ImgBusinessTypeEnum> getImgUploadTypeEnumMap(){
        if(null==imgUploadTypeEnumMap){
            imgUploadTypeEnumMap = new HashMap<>();
            for (ImgBusinessTypeEnum w : ImgBusinessTypeEnum.values()) {
                System.out.println("添加元素：" + w);
                imgUploadTypeEnumMap.put(w.getIndex(), w);
            }
        }
        return imgUploadTypeEnumMap;
    }

    /**
     * 获取所有 枚举名称 Map信息
     * @return
     */
    public static Map<Integer, String> getEnumMap(){
        if(null==imgUploadTypeMap){
            imgUploadTypeMap = new HashMap<>();
            for (ImgBusinessTypeEnum w : ImgBusinessTypeEnum.values()) {
                System.out.println("添加元素：" + w);
                imgUploadTypeMap.put(w.getIndex(), w.getName());
            }
        }
        return imgUploadTypeMap;
    }

    /**
     * 根据索引 获取枚举名称
     * @param index
     * @return
     */
    public static String getValueByIndex(Integer index){
        if(null==imgUploadTypeMap){
            imgUploadTypeMap = new HashMap<>();
            for (ImgBusinessTypeEnum w : ImgBusinessTypeEnum.values()) {
                System.out.println("添加元素 ：" + w);
                imgUploadTypeMap.put(w.getIndex(), w.getName());
            }
        }
        return imgUploadTypeMap.get(index);
    }

    /**
     * 查询是否有该索引对应的值
     * @param index
     * @return
     */
    public static Boolean isContainsIndexKey(Integer index){
        if(null==imgUploadTypeMap){
            imgUploadTypeMap = new HashMap<>();
            for (ImgBusinessTypeEnum w : ImgBusinessTypeEnum.values()) {
                System.out.println("添加元素 ：" + w);
                imgUploadTypeMap.put(w.getIndex(), w.getName());
            }
        }
        return imgUploadTypeMap.containsKey(index);
    }

}
