package com.book.fairy.constant.upload;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yz-he on 2017/9/29.
 */
public enum ImgUploadTableEnum {
    temp("", 0),
    employee_information("business_hall@employee_information", 1),              //员工头像存放的表
    equipment_basic_information("business_hall@equipment_basic_information", 2);//设备图片存放的表

    // 成员变量
    private String name;
    private int index;

    private static Map<Integer, ImgUploadTableEnum> mgUploadTableEnumEnumMap = null;
    private static Map<Integer, String> ImgUploadTableMap = null;

    // 构造方法
    private ImgUploadTableEnum(String name, int index) {
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
    public static Map<Integer, ImgUploadTableEnum> getWeekEnumMap(){
        if(null==mgUploadTableEnumEnumMap){
            mgUploadTableEnumEnumMap = new HashMap<>();
            for (ImgUploadTableEnum w : ImgUploadTableEnum.values()) {
                System.out.println("添加元素：" + w);
                mgUploadTableEnumEnumMap.put(w.getIndex(), w);
            }
        }
        return mgUploadTableEnumEnumMap;
    }

    /**
     * 获取所有 枚举名称 Map信息
     * @return
     */
    public static Map<Integer, String> getEnumMap(){
        if(null==ImgUploadTableMap){
            ImgUploadTableMap = new HashMap<>();
            for (ImgUploadTableEnum w : ImgUploadTableEnum.values()) {
                System.out.println("添加元素：" + w);
                ImgUploadTableMap.put(w.getIndex(), w.getName());
            }
        }
        return ImgUploadTableMap;
    }

    /**
     * 根据索引 获取枚举名称
     * @param index
     * @return
     */
    public static String getValueByIndex(Integer index){
        if(null==ImgUploadTableMap){
            ImgUploadTableMap = new HashMap<>();
            for (ImgUploadTableEnum w : ImgUploadTableEnum.values()) {
                System.out.println("添加元素 ：" + w);
                ImgUploadTableMap.put(w.getIndex(), w.getName());
            }
        }
        return ImgUploadTableMap.get(index);
    }

    /**
     * 查询是否有该索引对应的值
     * @param index
     * @return
     */
    public static Boolean isContainsIndexKey(Integer index){
        if(null==ImgUploadTableMap){
            ImgUploadTableMap = new HashMap<>();
            for (ImgUploadTableEnum w : ImgUploadTableEnum.values()) {
                System.out.println("添加元素：" + w);
                ImgUploadTableMap.put(w.getIndex(), w.getName());
            }
        }
        return ImgUploadTableMap.containsKey(index);
    }

}
