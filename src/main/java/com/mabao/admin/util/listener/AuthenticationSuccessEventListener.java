package com.mabao.admin.util.listener;

import com.mabao.admin.pojo.Admin;
import com.mabao.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessEventListener
        implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private AdminRepository adminRepository;

    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        UserDetails userDetails = (UserDetails)
                e.getAuthentication().getPrincipal();
        Admin admin = this.adminRepository.findByUsername(userDetails.getUsername());
        admin.setCount(admin.getCount()+1);
        this.adminRepository.saveAndFlush(admin);
    }
}