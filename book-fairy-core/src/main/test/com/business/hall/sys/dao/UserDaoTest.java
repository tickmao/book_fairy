package com.business.hall.sys.dao;

import com.alibaba.fastjson.JSON;
import com.business.hall.sys.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    UserDao userDao;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("hel2lo1");
        user.setPassword("223");
        user.setSalt("dfdfd");
        user.setNickname("dfdffdf");
        user.setHeadImgUrl("dfdfdf");
        user.setPhone("dfdfd");
        user.setTelephone("dfdfdfdf");
        user.setEmail("dfdfdfdf");
        user.setBirthday(new Date());
        user.setSex(1);
        user.setStatus(1);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userDao.save(user);

        System.out.println("user : " + JSON.toJSONString(user));
    }


    @Test
    public void selectById() throws Exception {
        User user = userDao.getById(Long.valueOf(1));

        System.out.println(" ======================================================================= ");
        System.out.println("sysUser : " + JSON.toJSONString(user));
    }

    @Test
    public void getUserByName() throws Exception {
        User user = userDao.getUser("dddd");

        System.out.println(" ======================================================================= ");
        System.out.println("sysUser : " + JSON.toJSONString(user));
    }

    /**
     * 测试事物
     * http://localhost:8080/user/test_transactional<br>
     * 访问如下并未发现插入数据说明事物可靠！！<br>
     * http://localhost:8080/user/test<br>
     * <br>
     * 启动  Application 加上 @EnableTransactionManagement 注解其实可无默认貌似就开启了<br>
     * 需要事物的方法加上 @Transactional 必须的哦！！
     */
    @Transactional
    @Test
    public void testTransactional() {
        //sysUserMapper.insert(new User(1000L, "测试事物", 16, 3));
        System.out.println(" 这里手动抛出异常，自动回滚数据");
        throw new RuntimeException();
    }
}

