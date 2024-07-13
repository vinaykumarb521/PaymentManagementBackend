package com.credmarg.payment_management.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendEmail(String emailContent) {
        System.out.println("Email sent: " + emailContent);
    }
}
