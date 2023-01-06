package com.myIT.SpringSecurity.Filters;

import jakarta.servlet.*;

import java.io.IOException;

public class PreAuthenticationCustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("~~~~~~~~~Authentication is in Progress~~~~~~~~~~");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
