package com.business.hall.controller.sys;

import com.business.hall.annotation.LogAnnotation;
import com.business.hall.sys.dao.UserDao;
import com.business.hall.sys.dto.UserDto;
import com.business.hall.sys.model.User;
import com.business.hall.sys.page.table.PageTableHandler;
import com.business.hall.sys.page.table.PageTableRequest;
import com.business.hall.sys.page.table.PageTableResponse;
import com.business.hall.sys.service.UserService;
import com.business.hall.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户相关接口
 * 
 * @author 何杨洲
 *
 */
@Api(tags = "用户")

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private UserService userService;
	@Autowired
	private UserDao userDao;

	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "保存用户")
	@RequiresPermissions("sys:user:add")
	public User saveUser(@RequestBody UserDto userDto) {
		User u = userService.getUser(userDto.getUsername());
		if (u != null) {
			throw new IllegalArgumentException(userDto.getUsername() + "已存在");
		}

		return userService.saveUser(userDto);
	}

	@LogAnnotation
	@PutMapping
	@ApiOperation(value = "修改用户")
	@RequiresPermissions("sys:user:add")
	public User updateUser(@RequestBody UserDto userDto) {
		return userService.updateUser(userDto);
	}

	@LogAnnotation
	@PutMapping(params = "headImgUrl")
	@ApiOperation(value = "修改头像")
	public void updateHeadImgUrl(String headImgUrl) {
		User user = UserUtil.getCurrentUser();
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		userDto.setHeadImgUrl(headImgUrl);

		userService.updateUser(userDto);
		log.debug("{}修改了头像", user.getUsername());
	}

	@LogAnnotation
	@PutMapping("/{username}")
	@ApiOperation(value = "修改密码")
	@RequiresPermissions("sys:user:password")
	public void changePassword(@PathVariable String username, String oldPassword, String newPassword) {
		userService.changePassword(username, oldPassword, newPassword);
	}

	@GetMapping
	@ApiOperation(value = "用户列表")
	@RequiresPermissions("sys:user:query")
	public PageTableResponse listUsers(PageTableRequest request) {
		return new PageTableHandler(new PageTableHandler.CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return userDao.count(request.getParams());
			}
		}, new PageTableHandler.ListHandler() {

			@Override
			public List<User> list(PageTableRequest request) {
				List<User> list = userDao.list(request.getParams(), request.getOffset(), request.getLimit());
				return list;
			}
		}).handle(request);
	}

	@ApiOperation(value = "当前登录用户")
	@GetMapping("/current")
	public User currentUser() {
		return UserUtil.getCurrentUser();
	}

	@ApiOperation(value = "根据用户id获取用户")
	@GetMapping("/{id}")
	@RequiresPermissions("sys:user:query")
	public User user(@PathVariable Long id) {
		return userDao.getById(id);
	}

}
