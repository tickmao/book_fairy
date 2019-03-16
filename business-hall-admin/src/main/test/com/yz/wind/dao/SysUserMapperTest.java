package com.business.hall.sys.dao;

import com.alibaba.fastjson.JSON;
import com.business.hall.entity.SysUser;
import com.business.hall.sys.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserMapperTest {
	@Autowired
    SysUserMapper sysUserMapper;

	@Test
	public void selectById() {
        SysUser sysUser = sysUserMapper.selectById(1);

		System.out.println(" ======================================================================= " );
		System.out.println("sysUser : " + JSON.toJSONString(sysUser));

	}
}

