package com.myIT.SpringSecurity.Filters;

import jakarta.servlet.*;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class AuthoritiesLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            System.out.println("User"+authentication.getName()+"is successfully authenticated and has authorities: "+
                    authentication.getAuthorities().toString());
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
