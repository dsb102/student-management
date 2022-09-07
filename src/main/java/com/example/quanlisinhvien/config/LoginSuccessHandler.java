package com.example.quanlisinhvien.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String redirectURL = request.getContextPath();
        if (userDetails.hasRole("ROLE_ADMIN"))
            redirectURL = "admin";
        else if (userDetails.hasRole("ROLE_MENTOR"))
            redirectURL = "mentor";
        else if (userDetails.hasRole("ROLE_INTERNSHIP"))
            redirectURL = "internship";
        response.sendRedirect(redirectURL);
    }
}
