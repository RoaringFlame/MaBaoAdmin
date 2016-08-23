package com.mabao.admin.util.security;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
public class AdminInfoServiceImpl implements AdminInfoService {

    private UserDetails userDetails = (UserDetails)
            SecurityContextHolder.getContext().getAuthentication() .getPrincipal();

    private WebAuthenticationDetails details = (WebAuthenticationDetails)
            SecurityContextHolder.getContext().getAuthentication().getDetails();

    public String getAdminUsername(){
        return this.userDetails.getUsername();
    }

    public String getRemoteAddress(){
        return this.details.getRemoteAddress();
    }
}
