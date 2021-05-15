package com.example.demo.security.filter;

import com.example.demo.constant.JwtConstant;
import com.example.demo.security.util.CookieUtil;
import com.example.demo.security.util.JwtUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String token = CookieUtil.getValue(req, JwtConstant.JWT_COOKIE_NAME);
        if (token != null) {
            String userName = JwtUtil.getSubjectFromJwt(token);
            if (userName != null) {
                req.setAttribute("userName", userName);
                filterChain.doFilter(req, res);
            } else {
                String authLoginUrl = getFilterConfig().getInitParameter("authLoginUrl");
                res.sendRedirect(authLoginUrl + "?redirect=" + req.getRequestURL());
            }
        } else {
            String authLoginUrl = getFilterConfig().getInitParameter("authLoginUrl");
            res.sendRedirect(authLoginUrl + "?redirect=" + req.getRequestURL());
        }
    }

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest req) throws ServletException{
//        String path = req.getRequestURI();
//        return path.contains("/onboard/");
//    }
}
