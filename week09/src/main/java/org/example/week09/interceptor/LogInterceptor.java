package org.example.week09.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author
 * @date
 * @description 日志拦截器 - 记录请求和响应时间
 */
@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {

    private static final String START_TIME_KEY = "startTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute(START_TIME_KEY, startTime);
        
        log.info("【日志拦截器】请求进入 path={}, method={}, ip={}, time={}",
                request.getRequestURI(),
                request.getMethod(),
                request.getRemoteAddr(),
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 可以在这里修改 ModelAndView
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long startTime = (Long) request.getAttribute(START_TIME_KEY);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        log.info("【日志拦截器】响应结束 path={}, status={}, 耗时={}ms, time={}",
                request.getRequestURI(),
                response.getStatus(),
                duration,
                new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()));
    }
}
