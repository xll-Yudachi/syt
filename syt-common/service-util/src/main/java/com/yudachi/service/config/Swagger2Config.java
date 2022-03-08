package com.yudachi.service.config;

import com.google.common.base.Predicates;
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
 * Swagger2配置信息
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket hospApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("hospApi")
                .apiInfo(hospApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yudachi.syt.hosp.controller"))
                //只显示admin路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/hosp/.*")))
                .build();

    }

    private ApiInfo hospApiInfo(){

        return new ApiInfoBuilder()
                .title("后台管理系统-API文档")
                .description("本文档描述了医院设置微服务接口定义")
                .version("1.0")
                .contact(new Contact("yudachi", "", "452419829@qq.com"))
                .build();
    }


}
