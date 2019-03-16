package com.business.hall;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger文档
 *
 * @author 何杨洲
 *
 *         2017年7月21日
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket business_api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.business.hall.controller.modules"))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//加了ApiOperation注解的方法，生成接口文档
                .paths(PathSelectors.ant("/api/**"))
                //.paths(PathSelectors.any())
                .build()
                .groupName("业务接口文档V4.4")
                .pathMapping("/")
                .apiInfo(apiInfo("业务接口文档V1.0","文档中可以查询及测试接口调用参数和结果","1.0"));
    }

    @Bean
    public Docket sys_api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.business.hall.controller.sys"))
                .paths(PathSelectors.regex("^/[^(api)].*$"))
                .build()
                .groupName("系统接口文档V4.4")
                .pathMapping("/")
                .apiInfo(apiInfo("系统接口文档V1.0","文档中可以查询及测试接口调用参数和结果","1.0"));
    }

    private ApiInfo apiInfo(String name,String description,String version) {
        return new ApiInfoBuilder()
                .title(name)
                .description(description)
                .termsOfServiceUrl("http://localhost:8083/login.html")
                .contact("yz-he")
                .version(version)
                .build();
    }

}
