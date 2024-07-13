package com.credmarg.payment_management.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credmarg.payment_management.model.EmailLog;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {
	List<EmailLog> findByCreatedBy(String createdBy);
}
