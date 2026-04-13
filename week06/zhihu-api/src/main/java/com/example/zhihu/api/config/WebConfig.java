package com.example.zhihu.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

/**
 * Web MVC 配置
 * 处理根路径访问和静态资源
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置视图控制器，将根路径重定向到 API 文档
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 访问根路径时重定向到 Swagger UI (Spring Boot 3.x 使用新路径)
        registry.addRedirectViewController("/", "/swagger-ui/index.html");
    }

    /**
     * 配置静态资源处理器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/public/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestedResource = location.createRelative(resourcePath);
                        return requestedResource.exists() && requestedResource.isReadable() 
                                ? requestedResource 
                                : null;
                    }
                });
    }
}
