package com.book.fairy;

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
         .apiInfo(apiInfo())
         .select()
         .apis(RequestHandlerSelectors.basePackage("com.book.fairy"))
         .paths(PathSelectors.any())
         .build();
    }

//    @Bean
//    public Docket sys_api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.book.fairy.controller.sys"))
//                .paths(PathSelectors.regex("^/[^(api)].*$"))
//                .build()
//                .groupName("系统接口文档V4.4")
//                .pathMapping("/")
//                .apiInfo(apiInfo("系统接口文档V1.0","文档中可以查询及测试接口调用参数和结果","1.0"));
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                         .description("更多Spring Boot相关文章请关注：http://blog.didispace.com/")
                         .termsOfServiceUrl("http://blog.didispace.com/")
                         .contact("zk")
                         .version("1.0")
                         .build();
    }

}
