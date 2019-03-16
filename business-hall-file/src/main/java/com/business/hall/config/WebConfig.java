package com.business.hall.sys.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.thetransactioncompany.cors.CORSConfiguration;
import com.thetransactioncompany.cors.CORSFilter;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by yin-zhao on 2016-07-26.
 */
@Lazy
@Configuration
public class WebConfig {

    //初始化过滤器
    //跨域访问
    @Bean
    Filter corsFilter() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("cors.allowOrigin", "*");
        properties.setProperty("cors.supportedMethods", "GET, POST, HEAD, PUT, DELETE");
        properties.setProperty("cors.supportedHeaders", "Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        properties.setProperty("cors.exposedHeaders", "Set-Cookie");
        properties.setProperty("cors.supportsCredentials ", "true");//支持跨域携带证书
        CORSConfiguration configuration = new CORSConfiguration(properties);
        CORSFilter filter = new CORSFilter();
        filter.setConfiguration(configuration);
        return filter;
    }


    //编码过滤器
    @Bean
    Filter characterFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

    //消息转换器
    @Bean
    HttpMessageConverters fastJsonHttpMessageConverter() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(supportedMediaTypes);
        SerializerFeature[] features = {SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullStringAsEmpty};
        converter.setFeatures(features);
        return new HttpMessageConverters(converter);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(10*1024L * 1024L);
        return factory.createMultipartConfig();
    }

}
