package com.credmarg.payment_management.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credmarg.payment_management.model.Vendor;
import com.credmarg.payment_management.service.VendorService;

@RestController
@RequestMapping("/vendors")
public class VendorController {
	@Autowired
	private VendorService vendorService;

	@PostMapping("create")
	public ResponseEntity<?> createVendor(@RequestBody Vendor vendor) {
		try {
			return new ResponseEntity<>(vendorService.saveVendor(vendor), HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("getAllVendors")
	public List<Vendor> getAllVendors() {
		return vendorService.getAllVendors();
	}

	@GetMapping("/getAllVendorsByAdmin")
	public List<Vendor> getAllVendorsByAdmin(@RequestParam String adminName) {
		List<Vendor> allVendors = vendorService.getAllVendors();

		List<Vendor> filteredVendors = new ArrayList<>();

		for (Vendor vendor : allVendors) {
			if (vendor.getCreatedBy().equals(adminName)) {
				filteredVendors.add(vendor);
			}
		}
		return filteredVendors;
	}

}