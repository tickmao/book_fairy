package com.business.hall.sys.config;

/**
 * Created with IntelliJ IDEA.
 * User: yz-he
 * Date: 2018\4\7 0007 21:35
 * To change this template use File | Settings | File Templates.
 * Description:
 */

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/** mybatisplus自定义填充公共字段 ,即没有传的字段自动填充*/
@Component
public class MyMetaObjectHandler extends MetaObjectHandler {
    //新增填充
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = metaObject.getValue("createTime");//创建时间
        Object updateTime = metaObject.getValue("updateTime");//创建时间

        /*Object lastUpdateNameId = metaObject.getValue("lastUpdateNameId");
        //获取当前登录用户
        SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
        if (null == lastUpdateNameId) {
            metaObject.setValue("lastUpdateNameId", user.getId());
        }*/
        if (null == createTime) {
            metaObject.setValue("createTime", new Date());
        }
        if (null == updateTime) {
            metaObject.setValue("updateTime", new Date());
        }
    }

    //更新填充
    @Override
    public void updateFill(MetaObject metaObject) {
        /*Object updateTime = metaObject.getValue("updateTime");//修改时间
        if (null == updateTime) {
            metaObject.setValue("updateTime", new Date());
        }*/
    }
}
