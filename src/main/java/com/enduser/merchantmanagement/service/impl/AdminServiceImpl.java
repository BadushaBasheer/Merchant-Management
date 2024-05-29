package com.enduser.merchantmanagement.service.impl;

import com.enduser.merchantmanagement.entity.Admin;
import com.enduser.merchantmanagement.repository.AdminRepo;
import com.enduser.merchantmanagement.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepo adminRepo;

    private final PasswordEncoder passwordEncoder;

    public Admin loginUser(String username, String password) {
        Admin user = adminRepo.findByEmail(username);
        if (user == null) {
            throw new NoSuchElementException("User is not found");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Incorrect password");
        }
        return user;
    }

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    @Transactional
    public void saveAdmin() {
        Admin existingAdmin = adminRepo.findByEmail(adminEmail);
        if (existingAdmin != null) {
            log.info("Admin already exists in the database.");
            return;
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Admin admin = new Admin();
        admin.setEmail(adminEmail);
        admin.setPassword(passwordEncoder.encode(adminPassword));
        adminRepo.save(admin);
        log.info("Admin saved successfully.");
    }
}
