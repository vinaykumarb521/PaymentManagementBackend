package com.credmarg.payment_management.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credmarg.payment_management.dto.EmailDTO;
import com.credmarg.payment_management.model.EmailLog;
import com.credmarg.payment_management.model.Vendor;
import com.credmarg.payment_management.service.EmailLogService;
import com.credmarg.payment_management.service.EmailService;
import com.credmarg.payment_management.service.VendorService;

@RestController
@RequestMapping("/email")
public class EmailController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private EmailLogService emailLogService;
	@Autowired
	private VendorService vendorService;
	@Autowired
	private JavaMailSender javaMailSender;

	@PostMapping("send")
	public ResponseEntity<String> sendEmail(@RequestBody EmailDTO emailDTO) {
		Vendor vendor = vendorService.getVendorById(emailDTO.getVendorId());
		String emailContent = "Sending payments to vendor " + vendor.getName() + " at upi " + vendor.getUpi();

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(vendor.getEmail());
		mailMessage.setSubject("Payment Notification");
		mailMessage.setText(emailContent);

		try {
			javaMailSender.send(mailMessage);

			EmailLog emailLog = new EmailLog();
			emailLog.setVendorId(emailDTO.getVendorId());
			emailLog.setEmailContent(emailContent);
			emailLog.setTimestamp(LocalDateTime.now());
			emailLog.setCreatedBy(emailDTO.getAgentName());
			emailLogService.saveEmailLog(emailLog);

			return ResponseEntity.ok("Email sent successfully to " + vendor.getEmail());
		} catch (MailException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to send email: " + e.getMessage());
		}
	}

	@PostMapping("sendBulk")
	public ResponseEntity<String> sendBulkEmail(@RequestBody List<EmailDTO> emailDTOList) {
		StringBuilder emails = new StringBuilder();
		for (EmailDTO emailDTO : emailDTOList) {
			Vendor vendor = vendorService.getVendorById(emailDTO.getVendorId());
			String emailContent = "Sending payments to vendor " + vendor.getName() + " at upi " + vendor.getUpi();

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(vendor.getEmail());
			mailMessage.setSubject("Payment Notification");
			mailMessage.setText(emailContent);

			try {
				javaMailSender.send(mailMessage);

				EmailLog emailLog = new EmailLog();
				emailLog.setVendorId(emailDTO.getVendorId());
				emailLog.setEmailContent(emailContent);
				emailLog.setTimestamp(LocalDateTime.now());
				emailLogService.saveEmailLog(emailLog);
				emails.append(vendor.getEmail()).append(" ");

			} catch (MailException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
						.body("Failed to send email: " + e.getMessage());
			}
		}

		return ResponseEntity.ok("Bulk emails sent successfully to " + emails.toString());
	}

	@GetMapping("emailLogs")
	public List<EmailLog> getAllEmailLogs() {
		return emailLogService.getAllEmailLogs();
	}
	
	@GetMapping("/emailLogs/byCreatedBy")
	public List<EmailLog> getEmailLogsByCreatedBy(@RequestParam String createdBy) {
	    return emailLogService.getEmailLogsByCreatedBy(createdBy);
	}
}