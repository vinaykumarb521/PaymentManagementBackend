package com.credmarg.payment_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credmarg.payment_management.Repository.VendorRepository;
import com.credmarg.payment_management.model.Vendor;

@Service
public class VendorService {
	@Autowired
	private VendorRepository vendorRepository;

	public Vendor saveVendor(Vendor vendor) {

		if (vendorRepository.findByEmail(vendor.getEmail()).isPresent()) {
			throw new IllegalArgumentException("Email is already in use.");
		}
		return vendorRepository.save(vendor);
	}

	public List<Vendor> getAllVendors() {
		return vendorRepository.findAll();
	}

	public Vendor getVendorById(Long vendorId) {

		return vendorRepository.getReferenceById(vendorId);
	}
}