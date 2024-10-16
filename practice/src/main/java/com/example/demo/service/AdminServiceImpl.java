package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveAdmin(AdminForm adminForm) {
        Admin admin = new Admin();

        admin.setLastName(adminForm.getLastName());
        admin.setFirstName(adminForm.getFirstName());
        admin.setEmail(adminForm.getEmail());
        admin.setPassword(passwordEncoder.encode(adminForm.getPassword())); // パスワードをハッシュ化

        adminRepository.save(admin);
    }
}