package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.entity.AdminDto;
import com.example.demo.repository.AdminRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    Admin admin = adminRepository.findByEmail(email);
	    if (admin == null) {
	        throw new UsernameNotFoundException("User not found for email: " + email);
	    }
	    return new UserPrincipal(admin);
	}
	public Admin findByUsername(String email) {
        return adminRepository.findByEmail(email);
    }
	@Transactional 
	public void save(AdminDto adminDto) {
	    Admin admin = new Admin();
	    admin.setEmail(adminDto.getEmail()); 
	    admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));

	    adminRepository.save(admin);
	}
}