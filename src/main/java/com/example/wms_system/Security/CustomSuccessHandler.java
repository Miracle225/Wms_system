package com.example.wms_system.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws
            IOException,
            ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String redirectURL = request.getContextPath();

        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            redirectURL = "/admin/users";
        } else if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_WAREHOUSE_MANAGER"))) {
            redirectURL = "/goods";
        }

        response.sendRedirect(redirectURL);
    }

    }
