package com.book.fairy.controller.modules;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.book.fairy.annotation.LogAnnotation;
import com.book.fairy.core.Result;
import com.book.fairy.sys.dto.Token;
import com.book.fairy.sys.dto.UserDto;
import com.book.fairy.sys.model.User;
import com.book.fairy.sys.service.TokenManager;
import com.book.fairy.sys.service.UserService;
import com.book.fairy.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


/**
 * 登陆相关接口
 *
 * @author 何杨洲
 */

@Api(value="登陆", description = "登陆", tags = "登陆")
@RestController
@RequestMapping(value = "/api/sys", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class SysController {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private UserService userService;

    @LogAnnotation
    @ApiOperation(value = "门户页面登录接口", notes = "门户页面登录接口", response = Result.class)
    @PostMapping("/login")
    public Result restfulLogin(@RequestBody JSONObject jsonParam) {
        String username = jsonParam.getString("username");
        String password = jsonParam.getString("password");

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);

        // 开始进入shiro的认证流程
        SecurityUtils.getSubject().login(usernamePasswordToken);

        Token token = tokenManager.saveToken(usernamePasswordToken);
        User user = UserUtil.getCurrentUser();

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("token", token.getToken());
        userInfo.put("username", user.getUsername());
        userInfo.put("nickName", user.getNickname());
        userInfo.put("businessAreaId", user.getBusinessAreaId());
        userInfo.put("businessHallId", user.getBusinessHallId());
        userInfo.put("employeeId", user.getEmployeeId());

        return Result.success("登录成功！", userInfo);
    }


    @LogAnnotation
    @ApiOperation(value = "门户页面注册接口", notes = "门户页面注册接口", response = Result.class)
    @PostMapping("/registe")
    public User saveUser(@RequestBody UserDto userDto) {
        User u = userService.getUser(userDto.getUsername());
        if (u != null) {
            throw new IllegalArgumentException(userDto.getUsername() + "已存在");
        }
        return userService.saveUser(userDto);
    }

}
