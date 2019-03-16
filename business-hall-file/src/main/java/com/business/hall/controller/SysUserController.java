package com.business.hall.controller;

import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
