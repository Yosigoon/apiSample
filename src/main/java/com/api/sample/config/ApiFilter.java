package com.api.sample.config;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class ApiFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("=====================Filter TEST  : init ApiFilter=====================");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        logger.info("=====================Filter TEST  : doFilter=====================");
        logger.info("doFilter ApiFilter, uri : {}", ((HttpServletRequest)servletRequest).getRequestURI());
        logger.info("=====================Filter TEST  : doFilter=====================");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("=====================Filter TEST  : destroy ApiFilter=====================");
    }
}