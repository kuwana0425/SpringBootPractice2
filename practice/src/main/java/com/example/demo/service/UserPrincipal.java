package com.example.demo.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Admin;

public class UserPrincipal implements UserDetails {
	
	private Admin admin;
	
	public UserPrincipal(Admin admin) {
		this.admin = admin;
	}
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }
	@Override
    public String getPassword() {
        return admin.getPassword();
    }
	@Override
    public String getUsername() {
        return admin.getEmail();
    }
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }
	@Override
    public boolean isAccountNonLocked() {
        return true;
    }
	@Override
    public boolean isCredentialsNonExpired() {
        return true;
    }@Override
    public boolean isEnabled() {
        return true;
    }
}