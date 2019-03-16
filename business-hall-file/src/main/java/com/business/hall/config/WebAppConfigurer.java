package com.business.hall.sys.config;

import com.business.hall.interceptor.TokenInterceptor;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Created by yin-zhao on 2016/12/28.
 */

@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {
    Logger logger = org.slf4j.LoggerFactory.getLogger(WebAppConfigurer.class);

    @Resource
    private BaseConfiguration baseConfiguration;

    @Bean
    TokenInterceptor loginInterceptor() {
        return new TokenInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截器
        String[] path = baseConfiguration.getPublicPath().split("\\|");
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(path);
        super.addInterceptors(registry);
    }
}
