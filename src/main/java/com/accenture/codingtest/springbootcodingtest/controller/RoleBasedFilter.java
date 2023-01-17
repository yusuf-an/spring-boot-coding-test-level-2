package com.accenture.codingtest.springbootcodingtest.controller;

import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.enums.ROLES_ENUMS;
import com.accenture.codingtest.springbootcodingtest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

@Component
public class RoleBasedFilter implements Filter {

    Logger LOG = LoggerFactory.getLogger(RoleBasedFilter.class);


    Hashtable<ROLES_ENUMS, List<String>> rolesAndAccess = new Hashtable<ROLES_ENUMS, List<String>>() {{
        put(ROLES_ENUMS.DEFAULT_USER, List.of("/tasks"));
        put(ROLES_ENUMS.PRODUCT_OWNER, List.of("/project", "/tasks"));
        put(ROLES_ENUMS.ADMIN, List.of("/users"));
    }};


    @Autowired
    UserService userService;

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        LOG.info(
                "Starting a transaction for req : {}",
                req);

        String userId = ((HttpServletRequest) request).getHeader("userId");

        if (userId == null) {
            response.getWriter().write("userId is not present in header");
            return;
        }

        Optional<User> userMayBe = userService.findByUserId(userId);
        if (userMayBe.isEmpty()) {
            response.getWriter().write("user not found");
            return;
        }

        User user = userMayBe.get();

        ROLES_ENUMS role = user.getRole();

        List<String> accessUrls = rolesAndAccess.get(role);

        LOG.info(
                "username:{} has the follwoing page access : {}",
                user.getUsername(), accessUrls);

        String url = ((HttpServletRequest) request).getRequestURI();

        for (String accessUrl : accessUrls) {
            if (url.contains(accessUrl)) {
                chain.doFilter(request, response);
                return;
            }
        }

        response.getWriter().write("user: " + userId + " with role:" + role + " is not allowed to access url: " + url);

    }


    // other methods 
}