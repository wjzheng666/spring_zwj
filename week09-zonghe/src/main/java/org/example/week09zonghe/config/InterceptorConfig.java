package org.example.week09zonghe.config;

import lombok.RequiredArgsConstructor;
import org.example.week09zonghe.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类：注册自定义拦截器
 */
@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/**")   // 添加需要拦截的接⼝
                .excludePathPatterns("/api/login")  // 添加放⾏的接⼝
                .excludePathPatterns("/api/hello")  // 添加放⾏的接⼝
                .order(2);
    }
}