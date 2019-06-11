package com.beard.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/users/*", "/record_list/*"})
public class UserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        if (session != null
                && (session.getAttribute("role").equals("admin")
                || session.getAttribute("role").equals("master"))) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect("/schedule");
        }
    }

    @Override
    public void destroy() {

    }
}
