package com.example.week04.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class StudentConfig implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 1. 定义日期格式化规则
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 2. 构建自定义 ObjectMapper，配置序列化规则（将 var 改为明确类型 ObjectMapper）
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
                .modules(
                        // 处理 LocalDateTime 格式
                        new JavaTimeModule()
                                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(formatter)),
                        // 处理 Long 转 String（解决前端精度丢失）
                        new SimpleModule()
                                .addSerializer(Long.class, ToStringSerializer.instance)
                )
                .build();

        // 3. 将自定义转换器添加到列表最前面，确保优先使用
        converters.add(0, new MappingJackson2HttpMessageConverter(objectMapper));
    }
}