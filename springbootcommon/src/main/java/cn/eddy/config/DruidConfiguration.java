package cn.eddy.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Arrays;

/**
 * Created by Administrator on 2018/5/8.
 */
@Configuration
/*@PropertySource("classpath:application.properties")*/
public class DruidConfiguration {
   /* @Value("${spring.datasource.druid.driver-class-name}")
    private String driver;
    @Value("${spring.datasource.druid.url}")
    private String url;
    @Value("${spring.datasource.druid.username}")
    private String username;
    @Value("${spring.datasource.druid.password}")
    private String password;
    @Value("${spring.datasource.druid.max-active}")
    private String maxActive;*/

    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Bean(initMethod = "init",destroyMethod = "close")
    public DruidDataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
       /* ds.setProxyFilters(Arrays.asList(statFilter()));
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setMaxActive(Integer.parseInt(maxActive));*/
        return ds;
    }

    @Bean
    public Filter statFilter() {
        StatFilter filter = new StatFilter();
        filter.setSlowSqlMillis(5000);
        filter.setLogSlowSql(true);
        filter.setMergeSql(true);
        return filter;
    }
}
