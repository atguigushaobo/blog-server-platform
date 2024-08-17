package com.blog;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@MapperScan("com.blog.mapper")
@SpringBootApplication
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
