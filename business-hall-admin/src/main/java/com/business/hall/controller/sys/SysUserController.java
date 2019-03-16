package com.business.hall.controller.sys;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Describe:
 * User:tangjing
 * Date:2017/7/31
 * Time:下午2:15
 */
@RestController
@RequestMapping("/rs/swagger/")
@Api("查询用户")
public class SysUserController{
    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @RequestMapping(value="name/{name}", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户名获", httpMethod = "GET",consumes="application/json", response = Map.class, notes = "根据用户名获取用户对象")
    public List<Object> getUserByName(@PathVariable  String name) {
        logger.info("查询成功");
        return null;
    }

}
