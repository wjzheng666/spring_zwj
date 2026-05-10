package org.example.week09.config;

import org.example.week09.filter.AuthFilter;
import org.example.week09.filter.CORSFilter;
import org.example.week09.filter.CustomFilter;
import org.example.week09.filter.LogFilter;
import org.example.week09.filter.TestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 * @date
 * @description
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<TestFilter> testFilterRegistration() {
        // 创建 FilterRegistrationBean 对象
        FilterRegistrationBean<TestFilter> registrationBean = new FilterRegistrationBean<>();
        // 设置过滤器
        registrationBean.setFilter(new TestFilter());
        // 设置拦截的 URL 路径
        registrationBean.addUrlPatterns("/api/test");
        // 设置过滤器的执行顺序
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CustomFilter> customFilterRegistration() {
        // 创建 FilterRegistrationBean 对象
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();
        // 设置过滤器
        registrationBean.setFilter(new CustomFilter());
        // 设置拦截的 URL 路径
        registrationBean.addUrlPatterns("/api/test");
        // 设置过滤器的执行顺序
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<LogFilter> logFilterRegistration() {
        FilterRegistrationBean<LogFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LogFilter());
        registrationBean.addUrlPatterns("/api/test");
        registrationBean.setOrder(3);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/api/test");
        registrationBean.setOrder(4);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<CORSFilter> corsFilter() {
        FilterRegistrationBean<CORSFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CORSFilter());
        registrationBean.addUrlPatterns("/api/test");
        registrationBean.setOrder(0);
        return registrationBean;
    }
}
