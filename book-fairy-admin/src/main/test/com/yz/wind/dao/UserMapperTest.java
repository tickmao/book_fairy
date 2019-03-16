package com.book.fairy.sys.dao;/*
package com.book.fairy.dao;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.huchuang.laboratory.entity.UserRight;
import com.huchuang.laboratory.entity.WarningRecord;
import com.huchuang.laboratory.mapper.UserRightrMapper;
import com.huchuang.laboratory.mapper.WarningRecordMapper;
import com.huchuang.laboratory.service.UserService;
import com.huchuang.laboratory.service.WarningRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
	@Autowired
	UserRightrMapper userRightrMapper;

	@Autowired
	UserMapper userMapper;

	@Autowired
    UserService userService;

	@Autowired
	WarningRecordService warningRecordService;

	@Autowired
	WarningRecordMapper warningRecordMapper;

	@Test
	public void findUserByToken() {
		Map<String, Object> user = userRightrMapper.findUserByToken("222e23e2e23323");

		System.out.println("user : " + JSON.toJSONString(user));

	}

	@Test
	public void selectById() {
		UserRight user = userMapper.selectById("11");

		System.out.println("user : " + JSON.toJSONString(user));
	}

	@Test
	public void serviceTest() throws Exception {
		UserRight userRight = userService.selectById("11");

		System.out.println("userRight : " + JSON.toJSONString(userRight));
	}


	@Test
	public void selectUserPagination() throws Exception {
		Page page=new Page(1,3);
		page = userService.selectUserPage(page, "NORMAL");

		System.out.println("page : " + JSON.toJSONString(page));

	}

	@Test
	public void selectwarningRecord() throws Exception {
		//WarningRecord warningRecord = warningRecordService.selectById(1);

		//System.out.println("page : " + JSON.toJSONString(warningRecord));




		WarningRecord newWarningRecord = new WarningRecord();
		newWarningRecord.setHospitalCode("0001");
		newWarningRecord.setInputDateTime(new Date());
		newWarningRecord.setInstrumentNo("S1020");
		newWarningRecord.setWarningRemark("1号培养箱报警信号出现异常，请尽快查看！");
		warningRecordService.insert(newWarningRecord);

		System.out.println("newWarningRecord : " + JSON.toJSONString(newWarningRecord));
	}

	@Test
	public void selectUserListByHospAndInstrumentNo() throws Exception {
		List<Map<String, Object>> mapList = warningRecordMapper.findUserListByHospAndInstrumentNo("H0004", "S2001");

		System.out.println("mapList : " + JSON.toJSONString(mapList));
	}
}
*/
