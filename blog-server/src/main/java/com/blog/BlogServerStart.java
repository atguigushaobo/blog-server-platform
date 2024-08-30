package com.blog;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@MapperScan("com.blog.mapper")
@SpringBootApplication
@CrossOrigin(value = "*",allowCredentials = "true")
public class BlogServerStart {
    public static void main(String[] args){
        SpringApplication.run(BlogServerStart.class, args);
    }

    /**
     * 配置分页查询的拦截器，归属于mybatis相关配置
     * @return
     */
    @Bean
    public Interceptor[] plugins() {
        return new Interceptor[]{new PageInterceptor()};
    }
}
