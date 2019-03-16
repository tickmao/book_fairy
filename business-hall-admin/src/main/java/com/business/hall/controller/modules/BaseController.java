/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.business.hall.controller.modules;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.business.hall.modules.entity.SysDict;
import com.business.hall.sys.model.User;
import com.business.hall.utils.UserUtil;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器支持类
 *
 * @author ThinkGem
 * @version 2013-3-23
 */
public abstract class BaseController {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取当前用户信息
     * @return
     */
    public User getCurrentUser(){
        User user = UserUtil.getCurrentUser();

        return user;
    }

    /**
     * 转换为 bootstrap-table 需要的分页格式 JSON
     * @param page 分页对象
     * @return
     */
    protected JSONObject getPageJSONObject(Page<?> page) {
        JSONObject jo = new JSONObject();
        jo.put("total", page.getTotal());
        jo.put("rows", page.getRecords());
        return jo;
    }

    /**
     * 转换为 combobox 需要的下拉列表格式 JSON
     * @param list 下拉列表对象
     * @return
     */
    protected List<Map<String, Object>> getPageJSONObject(List<Object> list, String idName, String textName) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        for(Object o: list){
            Map<String, Object> map = new HashMap<>(10);
            map.put("id", getFieldByClasss(idName, o));
            map.put("text", getFieldByClasss(textName, o));
            mapList.add(map);
        }

        return mapList;
    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    private String getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问

            return (String) field.get(object);
        } catch (Exception e) {

            return null;
        }
    }

    /**
     * 根据属性名获取属性元素，包括各种安全范围和所有父类
     *
     * @param fieldName
     * @param object
     * @return
     */
    private Field getFieldByClasss(String fieldName, Object object) {
        Field field = null;
        Class<?> clazz = object.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (Exception e) {
                // 这里甚么都不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会进入

            }
        }
        return field;

    }



}
