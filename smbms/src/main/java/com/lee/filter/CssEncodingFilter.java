package com.lee.filter;

import javax.servlet.*;
import java.io.IOException;

public class CssEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/css;charset=utf-8");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
