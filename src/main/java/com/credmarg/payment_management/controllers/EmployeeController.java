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

import com.credmarg.payment_management.model.Employee;
import com.credmarg.payment_management.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping("create")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
	    try {
	        Employee createdEmployee = employeeService.saveEmployee(employee);
	        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body("Error creating employee: " + e.getMessage());
	    }
	}

	@GetMapping("getAllEmployes")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	

    @GetMapping("/getAllEmployeesByAdmin")
    public List<Employee> getAllEmployeesCreatedBy(@RequestParam String adminName) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        List<Employee> filteredEmployees = new ArrayList<>();
        
        for (Employee employee : allEmployees) {
            if (employee.getCreatedBy().equals(adminName)) {
                filteredEmployees.add(employee);
            }
        }
        
        return filteredEmployees;
    }
}