package com.business.hall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;


/**
 * Describe:
 * User:tangjing
 * Date:2017/7/31
 * Time:下午2:04
 */

@Configuration
@SpringBootApplication
public class WindFIleApplication extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WindFIleApplication.class);
    }

    public static void main(String[] args){
        SpringApplication.run(WindFIleApplication.class, args);
    }

}
