package org.example.week09.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author
 * @date
 * @description
 */
@Slf4j
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("自定义过滤器初始化完成");
    }

    @Override
    public void destroy() {
        log.info("自定义过滤器销毁完成");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("执行 CustomFilter 逻辑!");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
