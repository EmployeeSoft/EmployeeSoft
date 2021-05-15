package com.example.demo.config;

import com.example.demo.security.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class FilterConfig {
    @Value("${services.auth.login}")
    private String authLoginUrl;

    @Bean
    public FilterRegistrationBean<JwtRequestFilter> jwtFilter() {
        final FilterRegistrationBean<JwtRequestFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new JwtRequestFilter());
        filterFilterRegistrationBean.setInitParameters(Collections.singletonMap("authLoginUrl", authLoginUrl));
        filterFilterRegistrationBean.addUrlPatterns("/mmmmm");

        return filterFilterRegistrationBean;
    }
}
