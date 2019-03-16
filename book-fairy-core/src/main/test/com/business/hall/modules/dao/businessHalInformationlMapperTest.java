//package com.business.hall.modules.dao;
//
//import com.alibaba.fastjson.JSON;
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.baomidou.mybatisplus.plugins.Page;
//import com.business.hall.modules.entity.SysDict;
//import com.business.hall.modules.mapper.SysDictMapper;
//import com.business.hall.sys.model.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class businessHalInformationlMapperTest {
//    @Autowired
//    BusinessHallInformationMapper businessHallInformationMapper;
//
//    @Autowired
//    EmployeeInformationMapper employeeInformationMapper;
//
//    @Test
//    public void insert() {
//        BusinessHallInformation businessHalInformationl = new BusinessHallInformation();
//        businessHalInformationl.setBusinessHallAddress("dddw");
//        businessHallInformationMapper.insert(businessHalInformationl);
//
//        System.out.println("user : " + JSON.toJSONString(businessHalInformationl));
//    }
//
//    @Test
//    public void insert1() {
//        EmployeeInformation employeeInformation = new EmployeeInformation();
//        employeeInformation.setEmail("dddw");
//        employeeInformationMapper.insert(employeeInformation);
//
//        System.out.println("user : " + JSON.toJSONString(employeeInformation));
//    }
//
//    //根据条件查询列表信息
//    @Test
//    public void selectList() {
//        List<BusinessHallInformation> list = businessHallInformationMapper.selectList(new EntityWrapper<BusinessHallInformation>());
//
//        System.out.println("result : " + JSON.toJSONString(list));
//    }
//
//
//
//
//}
//
