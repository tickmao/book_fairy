package com.business.hall.sys.config;

import com.thetransactioncompany.cors.CORSConfiguration;
import com.thetransactioncompany.cors.CORSFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.util.Properties;

/**
 * Created by yangzhou-he on 2017-08-18.
 */
@Lazy
@Configuration
public class WebConfig {
    //初始化过滤器
    //跨域访问
    @Bean
    Filter corsFilter() throws  Exception{
        Properties properties = new Properties();
        properties.setProperty("cors.allowOrigin","*");
        properties.setProperty("cors.supportedMethods","GET, POST, HEAD, PUT, DELETE");
        //properties.setProperty("cors.supportedHeaders","Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        properties.setProperty("cors.supportedHeaders","Accept, Origin, X-Requested-With, Content-Type, Content-Length, uthorization, Last-Modified");
        properties.setProperty("cors.exposedHeaders","Set-Cookie");
        properties.setProperty("cors.supportsCredentials ","true");//支持跨域携带证书
        CORSConfiguration configuration = new CORSConfiguration(properties);
        CORSFilter  filter = new CORSFilter();

        filter.setConfiguration(configuration);
        return filter;
    }

    //编码过滤器
    @Bean
    Filter characterFilter(){
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

}
