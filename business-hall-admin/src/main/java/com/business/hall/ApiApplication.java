package com.business.hall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 * Describe:
 * User:yz-he
 * Date:2017/7/31
 * Time:下午2:04
 */
//@MapperScan("com.business.hall")
@SpringBootApplication
public class ApiApplication extends SpringBootServletInitializer {
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
            return application.sources(ApiApplication.class);
        }

        public static void main(String[] args) throws Exception {
            SpringApplication.run(ApiApplication.class, args);
        }


}