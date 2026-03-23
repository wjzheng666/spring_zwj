package com.example.week03.controller;

import com.example.week03.common.Result;
import com.example.week03.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
public class BaseConfigController {

    // 系统配置注入
    @Value("${server.port}")
    private Integer port;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${spring.application.name}")
    private String applicationName;

    // 自定义配置注入（通过 AppConfig 配置类）
    private final AppConfig appConfig;

    /**
     * 获取基础配置信息接口
     * @return 统一返回结果
     */
    @GetMapping("/base")
    public Result<Map<String, Object>> getBaseConfigInfo() {
        Map<String, Object> configMap = new HashMap<>();

        // 填充系统配置
        configMap.put("port", port);
        configMap.put("contextPath", contextPath);
        configMap.put("applicationName", applicationName);

        // 填充自定义配置（从 AppConfig 获取）
        configMap.put("appName", appConfig.getAppName());
        configMap.put("version", appConfig.getVersion());
        configMap.put("description", appConfig.getDescription());
        configMap.put("published", appConfig.getPublished());
        configMap.put("author", appConfig.getAuthor());
        configMap.put("features", appConfig.getFeatures());

        return Result.success(configMap);
    }

    /**
     * 单独测试开发环境应用名
     * @return 应用名
     */
    @GetMapping("/app-name")
    public Result<String> getApplicationName() {
        return Result.success(applicationName);
    }
}