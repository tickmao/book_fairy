package com.business.hall.sys.dao;

import com.alibaba.fastjson.JSON;
import com.business.hall.ApplicationTest;
import com.business.hall.sys.config.BaseConfiguration;
import org.junit.Test;

import javax.annotation.Resource;


public class CommonServiceTest extends ApplicationTest{

	@Resource
	private BaseConfiguration baseConfiguration;

	@Test
	public void getServerNamePortPath() {
		String serverName = baseConfiguration.getServerNamePortPath();

		System.out.println(" =============================================================================================================== ");
		System.out.println("serverName : " + JSON.toJSONString(serverName));
	}
}
