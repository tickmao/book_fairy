package com.business.hall.controller;


import com.business.hall.sys.config.BaseConfiguration;
import com.business.hall.helper.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 *
 */
@Controller
@RequestMapping(value = "/api/fileUpload")
public class FileUploadController {
    @Resource
    private BaseConfiguration baseConfiguration;


    @RequestMapping("/test")
    @ResponseBody
    public Object testController() {

        try{
            System.out.println("你好，测试接口！ " );

            return "success";
        } catch (Exception e) {
            return Result.error("上传失败，原因:" + e.getMessage());
        }
    }
}
