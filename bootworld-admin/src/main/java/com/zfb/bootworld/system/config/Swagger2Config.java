package com.zfb.bootworld.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangfeibiao
 * @create 2019/1/3 17:11
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {

        // header中的ticket参数非必填，传空也可以
        List<Parameter> pars = new ArrayList<Parameter>();
        ParameterBuilder ticketPar = new ParameterBuilder();
        ticketPar.name("token")
                .description("token")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .required(false).build();

        // 根据每个方法名也知道当前方法在设置什么参数
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                // 指定构建api文档的详细信息的方法：apiInfo()
                .pathMapping("/")
                .apiInfo(this.buildApiInfo())
                .select()
                // 指定要生成api接口的包路径，这里把controller作为包路径，生成controller中的所有接口
                .apis(RequestHandlerSelectors.basePackage("com.zfb.bootworld.**.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    /**
     * 构建api文档的详细信息
     * @return
     */
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                // 设置页面标题
                .title("Spring Boot集成Swagger2接口总览")
                // 设置接口描述
                .description("Springboot的世界")
                // 设置联系方式
                .contact("feibiao@zhangfeibiao.com")
                // 设置版本
                .version("1.0")
                .license("Apache License 2.0")
                .licenseUrl("#")
                // 构建
                .build();
    }

}