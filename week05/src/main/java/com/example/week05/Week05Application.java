package com.example.week05;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.week05.mapper") // 你的 mapper 包路径
@EnableTransactionManagement
public class Week05Application {
    public static void main(String[] args) {
        SpringApplication.run(Week05Application.class, args);
    }
}