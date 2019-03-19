package com.broctagon.exchangeadmin.config.security;

import com.broctagon.exchangeadmin.model.Admin;
import com.broctagon.exchangeadmin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AdminService adminService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        Admin admin = adminService.findByUserName(name);
        if (admin == null)
            throw new UsernameNotFoundException("Username : " + name + " not found");
        else
            return new UsernamePasswordAuthenticationToken(name, admin.getPassword(), Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
