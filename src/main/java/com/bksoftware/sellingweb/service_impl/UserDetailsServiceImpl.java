package com.bksoftware.sellingweb.service_impl;

import com.bksoftware.sellingweb.entities.AppAdmin;
import com.bksoftware.sellingweb.repository.AppAdminRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppAdminRepository appAdminRepository;

    public UserDetailsServiceImpl(AppAdminRepository appAdminRepository) {
        this.appAdminRepository = appAdminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AppAdmin appAdmin = appAdminRepository.findByUsername(s);
        if (appAdmin == null) throw  new UsernameNotFoundException(s);
        else return new User(appAdmin.getUsername(),appAdmin.getPassword(), getGrantedAuthority());
    }
    private Set<GrantedAuthority> getGrantedAuthority() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add((GrantedAuthority) () -> "ADMIN");
        return grantedAuthorities;
    }
}
