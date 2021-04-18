package com.familyapp.models.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        LOGGER.info("\n-------- LogInterception.preHandle --- ");
        LOGGER.info("Request URL: " + request.getRequestURL());
        LOGGER.info("Start Time: " + System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        LOGGER.info("\n-------- LogInterception.postHandle --- ");
        LOGGER.info("Request URL: " + request.getRequestURL());
        LOGGER.info("Last accessed: " + request.getSession().getLastAccessedTime());

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("\n-------- LogInterception.afterCompletion --- ");

        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        LOGGER.info("Request URL: " + request.getRequestURL());
        LOGGER.info("Username: " + request.getRemoteUser());
        LOGGER.info("End Time: " + endTime);

        LOGGER.info("Time Taken: " + (endTime - startTime));

    }
}
