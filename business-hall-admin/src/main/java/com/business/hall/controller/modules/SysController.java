package com.business.hall.controller.modules;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.business.hall.annotation.LogAnnotation;
import com.business.hall.core.Result;
import com.business.hall.sys.dto.Token;
import com.business.hall.sys.model.User;
import com.business.hall.sys.service.TokenManager;
import com.business.hall.utils.UserUtil;
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

    @LogAnnotation
    @ApiOperation(value = "Restful方式登陆,前后端分离时登录接口", notes = "Restful方式登陆,前后端分离时登录接口", response = Result.class)
    @PostMapping("/login")
    public Result restfulLogin(@RequestBody JSONObject jsonParam) {
        String username = jsonParam.getString("username");
        String password = jsonParam.getString("password");

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        SecurityUtils.getSubject().login(usernamePasswordToken);

        Token token = tokenManager.saveToken(usernamePasswordToken);
        User user = UserUtil.getCurrentUser();

        System.out.println("user:" + JSON.toJSONString(user));

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("token", token.getToken());
        userInfo.put("username", user.getUsername());
        userInfo.put("nickName", user.getNickname());
        userInfo.put("businessAreaId", user.getBusinessAreaId());
        userInfo.put("businessHallId", user.getBusinessHallId());
        userInfo.put("employeeId", user.getEmployeeId());

        return Result.success("登录成功！", userInfo);
    }

}
