package com.example.limitloginattempts.config;

import com.example.limitloginattempts.model.CustomUserDetails;
import com.example.limitloginattempts.model.User;
import com.example.limitloginattempts.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private UserServices userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        CustomUserDetails userDetails =  (CustomUserDetails) authentication.getPrincipal();
        User user = userService.getUserByUsername(userDetails.getUsername());
        if (user.getFailedAttempt() > 0) {
            userService.resetFailedAttempts(user.getUsername());
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
