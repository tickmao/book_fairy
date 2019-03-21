package com.book.fairy.controller.sys;

import com.alibaba.fastjson.JSON;
import com.book.fairy.annotation.LogAnnotation;
import com.book.fairy.sys.dao.RoleDao;
import com.book.fairy.sys.dto.Token;
import com.book.fairy.sys.model.Role;
import com.book.fairy.sys.model.User;
import com.book.fairy.sys.service.TokenManager;
import com.book.fairy.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 登陆相关接口
 * 
 * @author 何杨洲
 *
 */
@Api(tags = "登陆")
@RestController
@RequestMapping
public class LoginController {

	@Autowired
	private TokenManager tokenManager;

	@Autowired
	private RoleDao roleDao;

	@LogAnnotation
	@ApiOperation(value = "web端登陆")
	@PostMapping("/sys/login")
	public void login(String username, String password) {
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
		SecurityUtils.getSubject().login(usernamePasswordToken);
	}

	@LogAnnotation
	@ApiOperation(value = "Restful方式登陆,前后端分离时登录接口")
	@PostMapping("/sys/login/restful")
	public Token restfulLogin(String username, String password) {
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
		SecurityUtils.getSubject().login(usernamePasswordToken);

		return tokenManager.saveToken(usernamePasswordToken);
	}

	@ApiOperation(value = "当前登录用户")
	@GetMapping("/sys/login")
	public User getLoginInfo() {
		User user = UserUtil.getCurrentUser();

		List<Role> roleList = roleDao.listByUserId(user.getId()); // 根据人员ID获取角色列表

		String adminRole = "user";
		// Java8新特性： anyMatch表示，判断的条件里，任意一个元素成功，返回true
		if(roleList.stream().anyMatch(u->u.getId().longValue()==1)){
			adminRole = "system";
			user.setAdminRole(adminRole);
		}

		System.out.println("obj : " + JSON.toJSONString(user));
		return user;
	}

}
