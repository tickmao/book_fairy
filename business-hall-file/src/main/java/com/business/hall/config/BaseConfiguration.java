package com.business.hall.sys.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Created by yin-zhao on 2016-08-17.
 */
@Lazy
@Configuration
public class BaseConfiguration {
    @Value("${system.publicPath}")
    private String publicPath;

    @Value("${system.upload.img}")
    private String uploadImgPath;

    @Value("${system.upload.file}")
    private String uploadFilePath;

    @Value("${system.serverNamePortPath}")
    private String serverNamePortPath;

    public String getPublicPath() {
        return publicPath;
    }

    public void setPublicPath(String publicPath) {
        this.publicPath = publicPath;
    }

    public String getUploadImgPath() {
        return uploadImgPath;
    }

    public void setUploadImgPath(String uploadImgPath) {
        this.uploadImgPath = uploadImgPath;
    }

    public String getUploadFilePath() {
        return uploadFilePath;
    }

    public void setUploadFilePath(String uploadFilePath) {
        this.uploadFilePath = uploadFilePath;
    }

    public String getServerNamePortPath() {
        return serverNamePortPath;
    }

    public void setServerNamePortPath(String serverNamePortPath) {
        this.serverNamePortPath = serverNamePortPath;
    }
}
