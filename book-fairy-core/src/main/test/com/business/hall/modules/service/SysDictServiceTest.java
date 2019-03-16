package com.book.fairy.modules.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.book.fairy.modules.entity.SysDict;
import com.book.fairy.modules.mapper.SysDictMapper;
import com.book.fairy.sys.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SysDictServiceTest {
    @Autowired
    SysDictService sysDictService;

    //新增业务字典
    @Test
    public void insert() {
        SysDict sysDict = new SysDict();
        sysDict.setName("ddd");
        sysDictService.insert(sysDict);

        System.out.println("user : " + JSON.toJSONString(sysDict));
    }

    //删除业务字典
    @Test
    public void deleteById() {
        Boolean result = sysDictService.deleteById("a2bb0848a8694b139f32bd95e75390e5");

        System.out.println("user : " + JSON.toJSONString(result));
    }

    //根据条件删除业务字典
    @Test
    public void deleteByCondition() {
        SysDict sysDict = new SysDict();
        sysDict.setName("adadfd");
        EntityWrapper entityWrapper = new EntityWrapper<SysDict>(sysDict);
        Boolean result = sysDictService.delete(entityWrapper);

        System.out.println("result : " + JSON.toJSONString(result));
    }

    //根据ID修改业务字典
    @Test
    public void updateById() {
        SysDict sysDict = new SysDict();
        sysDict.setId("87b3deb26962474a9a746ab6aaa8e8b6");
        sysDict.setName("aaaaaaaaaaaaaaa");
        Boolean result = sysDictService.updateById(sysDict);

        System.out.println("result : " + JSON.toJSONString(result));
    }

    //根据条件修改业务字典
    @Test
    public void update() {
        SysDict sysDict = new SysDict();
        sysDict.setName("eee");
        sysDict.setConfigType("eee");
        Boolean result = sysDictService.update(sysDict, new EntityWrapper<SysDict>().eq("name", "ddd"));

        System.out.println("result : " + JSON.toJSONString(result));
    }

    //根据ID查询业务字典
    @Test
    public void selectById() {
        SysDict sysDict = sysDictService.selectById("87b3deb26962474a9a746ab6aaa8e8b6");

        System.out.println("result : " + JSON.toJSONString(sysDict));
    }

    //根据对象信息去查询对一条业务字典信息
    @Test
    public void selectOne() {
        SysDict querySysDict = new SysDict();
        querySysDict.setId("87b3deb26962474a9a746ab6aaa8e8b6");
        SysDict sysDict = sysDictService.selectOne(new EntityWrapper<SysDict>().eq("name", "aaaaaaaaaaaaaaa"));

        System.out.println("result : " + JSON.toJSONString(sysDict));
    }

    //根据条件查询列表信息
    @Test
    public void selectList() {
        List<SysDict> list = sysDictService.selectList(new EntityWrapper<SysDict>().eq("name", "eee"));

        System.out.println("result : " + JSON.toJSONString(list));
    }

    //根据条件查询列表信息
    @Test
    public void selectCount() {
        Integer count = sysDictService.selectCount(new EntityWrapper<SysDict>().eq("name", "eee"));

        System.out.println("result : " + JSON.toJSONString(count));
    }

    //分页查询 10 条姓名为‘eee’ 的业务字典列表信息
    @Test
    public void selectPage() {
        Page<SysDict>  userList = sysDictService.selectPage(
                new Page<SysDict>(1, 10),
                new EntityWrapper<SysDict>().eq("name", "eee"));

        System.out.println("result : " + JSON.toJSONString(userList));
    }

    @Test
    public void selectSysDictByConditions() {
        SysDict sysDict = new SysDict();
        List<SysDict>  sysDicts = sysDictService.selectSysDictByConditions(sysDict);

        System.out.println("result : " + JSON.toJSONString(sysDicts));
    }
}

