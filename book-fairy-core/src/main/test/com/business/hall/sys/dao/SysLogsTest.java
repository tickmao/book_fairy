package com.business.hall.sys.dao;

import com.alibaba.fastjson.JSON;
import com.business.hall.sys.model.User;
import com.business.hall.sys.page.table.PageTableRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SysLogsTest {
    @Autowired
    SysLogsDao sysLogsDao;

    @Test
    public void insert() {
        PageTableRequest request = new PageTableRequest();
        request.setParams(new HashMap<String, Object>());
        Integer num = sysLogsDao.count(request.getParams());

        System.out.println("user : " + JSON.toJSONString(num));
    }



}

