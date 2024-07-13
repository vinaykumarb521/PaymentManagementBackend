package com.credmarg.payment_management.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credmarg.payment_management.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

	Optional<Vendor> findByEmail(String email);
}
