package com.credmarg.payment_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credmarg.payment_management.Repository.EmailLogRepository;
import com.credmarg.payment_management.model.EmailLog;

@Service
public class EmailLogService {
    @Autowired
    private EmailLogRepository emailLogRepository;

    public EmailLog saveEmailLog(EmailLog emailLog) {
        return emailLogRepository.save(emailLog);
    }

    public List<EmailLog> getAllEmailLogs() {
        return emailLogRepository.findAll();
    }
    
    
    public List<EmailLog> getEmailLogsByCreatedBy(String createdBy) {
        return emailLogRepository.findByCreatedBy(createdBy);
    }
}