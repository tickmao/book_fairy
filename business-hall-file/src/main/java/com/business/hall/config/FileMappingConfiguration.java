package com.business.hall.sys.config;

import com.business.hall.constant.upload.FileUploadTypeEnum;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Created by chuang-xiong on 2017/1/11.
 */
@Component
public class FileMappingConfiguration extends WebMvcConfigurerAdapter {
    @Resource
    private BaseConfiguration baseConfiguration;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(FileUploadTypeEnum.FILE_UPLOAD.getValue() + "/**").addResourceLocations("file:///" + baseConfiguration.getUploadFilePath() + "/");
        registry.addResourceHandler(FileUploadTypeEnum.IMG_UPLOAD.getValue() + "/**").addResourceLocations("file:///" + baseConfiguration.getUploadImgPath() + "/");
    }
}
