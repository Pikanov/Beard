package com.beard.filter;


import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class ErrorPageFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(ErrorPageFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        try {
            filterChain.doFilter(req, resp);
        } catch (Throwable e) {
            resp.sendRedirect("/html/404.html");
            LOGGER.info("404 page");
        }

    }

    @Override
    public void destroy() {

    }
}
