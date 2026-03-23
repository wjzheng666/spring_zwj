package com.example.week03.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * 应用配置类
 * 添加了@Validated开启配置校验，appName字段添加@NotBlank做非空校验
 */
@Data
@Component
@Validated // 核心：开启配置属性校验
@ConfigurationProperties(prefix = "app") // 绑定配置文件中app前缀的配置
public class AppConfig {

    @NotBlank(message = "app.name 不能为空") // 核心：校验appName非空
    private String appName; // 对应配置文件中的app.appName
    private String version;
    private String description;
    private Boolean published;
    private Author author;
    private List<String> features;

    /**
     * 内部静态类：作者信息
     */
    @Data
    public static class Author {
        private String name;
        private String website;
        private String email;
    }
}