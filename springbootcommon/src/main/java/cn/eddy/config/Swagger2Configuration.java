package cn.eddy.config;

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
 * Created by Administrator on 2018/5/8.
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket accessToken() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.groupName("api")// 定义组
                .select() // 选择那些路径和 api 会生成 document
                .apis(RequestHandlerSelectors.basePackage("cn.eddy.controller")) // 拦截的包路径
                .paths(PathSelectors.regex("/*/.*"))// 拦截的接口路径
                .build(); // 创建
        //.apiInfo(apiInfo()); // 配置说明
    }

    /*private ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("Spring Boot")// 标题
                .description("spring boot Web 基础构建")// 描述
                .termsOfServiceUrl("http://www.le-me.cn")//
                .contact(new Contact("leURL", "http://www.le-me.cn", "11683634@qq.com"))// 联系
                .version("1.0")// 版本
                .build();
    }*/
}
