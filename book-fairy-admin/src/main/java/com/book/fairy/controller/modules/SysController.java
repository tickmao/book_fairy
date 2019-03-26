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
import com.book.fairy.utils.VerifyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
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
    public Result restfulLogin(@RequestBody JSONObject jsonParam,HttpServletResponse response,HttpServletRequest request) {
        String username = jsonParam.getString("username");
        String password = jsonParam.getString("password");
        String code = jsonParam.getString("code");

        HttpSession session=request.getSession();
        if(null == session.getAttribute("imageCode")){
            return Result.error("验证码已失效，请重新输入");
            //renderFail(response, "重新获取验证码");
        }else {
            if(session.getAttribute("imageCode").toString().equalsIgnoreCase(code)){

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

            }else {
                return Result.error("验证码错误！");
            }
        }

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


    @ApiOperation("生成验证码")
    @GetMapping("/getcode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) throws Exception{
        HttpSession session=request.getSession();
        //利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VerifyUtil.createImage();
        //将验证码存入Session
        session.setAttribute("imageCode",objs[0]);

        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

}
