package com.fengsong97.spring.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger 配置文件
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Value("${customVariables.swagger.show}")
    private boolean show = false;

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(show)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fengsong97.spring.demo"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("swagger-restfulApi-接口")
                .description("XXX接口文档")
                .termsOfServiceUrl("https://help.github.com/")
                .contact(new Contact("冯松", "https://github.com/fengsong97", "973017388@qq.com"))
                .version("0.1")
                .build();
    }
}
