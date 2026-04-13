package com.example.zhihu.api; // 包名必须和目录结构完全一致！

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.zhihu.api.mapper") // 扫描Mapper包
public class ZhihuApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZhihuApiApplication.class, args);
    }
}