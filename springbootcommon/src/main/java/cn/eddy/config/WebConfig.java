package cn.eddy.config;

import cn.eddy.Interceptor.TimeInterceptor;
import cn.eddy.listener.Listener;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/7.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter  {

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/fastjson/**")
                        .allowedOrigins("http://localhost:8088");// 允许 8088 端口访问
            }
        };
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters(){
        //1、先定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
        //2、添加fastjson的配置信息，比如是否要格式化返回的json数据；
        FastJsonConfig fastJsonConfig=new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //附加：处理中文乱码
        List<MediaType> fastMedisTypes = new ArrayList<MediaType>();
        fastMedisTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMedisTypes);
        //3、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter=fastConverter;
        return new HttpMessageConverters(converter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }


    @Bean
    public ServletListenerRegistrationBean<Listener> servletListenerRegistrationBean() {
        return new ServletListenerRegistrationBean<Listener>(new Listener());
    }

}
