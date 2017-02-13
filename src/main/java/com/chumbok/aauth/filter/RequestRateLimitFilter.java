package com.chumboknnovators.aauth.filter;

import com.chumboknnovators.aauth.service.RequestRateLimitService;
import com.chumboknnovators.aauth.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestRateLimitFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(RequestRateLimitFilter.class);

    private static final String REQ_URI = "/ratelimit";

    @Autowired
    private RequestRateLimitService requestRateLimitService;


    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String url = request.getRequestURL().toString();
        String urlLastPart = url.substring(url.lastIndexOf("/"));

        String remoteIP = HttpUtil.getRemoteIP();

        if (!urlLastPart.contains(REQ_URI) && requestRateLimitService.isRequestBlocked(remoteIP)) {
            response.sendRedirect(REQ_URI);
            return;
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }


}