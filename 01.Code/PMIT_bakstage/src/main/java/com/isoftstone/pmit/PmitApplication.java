package com.isoftstone.pmit;

import com.isoftstone.pmit.common.paramresolver.JsonParamResolver;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.List;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 指定类为应用启动类
 *
 * @author pmoit
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"com.isoftstone.pmit.project.*.mapper", "com.isoftstone.pmit.system.*.mapper",
        "com.isoftstone.pmit.common.operationlog.mapper"})
@EnableScheduling
@Configuration
@EnableTransactionManagement
public class PmitApplication extends WebMvcConfigurerAdapter implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PmitApplication.class, args);
    }

    @Bean(name = "restTemplate")
//@LoadBalanced//开启负载均衡功能
    RestTemplate restTemplate() {
        //return new RestTemplate();
        return restTemplate(httpClientFactory());
    }

    @Value("${rest.ReadTimeout}")
    private int readTimeout;
    @Value("${rest.ConnectTimeout}")
    private int connectionTimeout;

    @Bean
    public SimpleClientHttpRequestFactory httpClientFactory() {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(readTimeout);
        httpRequestFactory.setConnectTimeout(connectionTimeout);

        return httpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate(SimpleClientHttpRequestFactory httpClientFactory) {
        RestTemplate restTemplate = new RestTemplate(httpClientFactory);
        return restTemplate;
    }


    @Override
    public void run(String... strings) throws Exception {
//        statistics.refresh();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new JsonParamResolver());
        super.addArgumentResolvers(argumentResolvers);
    }
}
