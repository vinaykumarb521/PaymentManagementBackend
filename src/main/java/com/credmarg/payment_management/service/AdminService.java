package com.credmarg.payment_management.service;


import com.credmarg.payment_management.Repository.AdminRepository;
import com.credmarg.payment_management.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin registerAdmin(Admin admin) {
        if (adminRepository.findByUsername(admin.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        return adminRepository.save(admin);
    }

    public Admin loginAdmin(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null;
    }
}
