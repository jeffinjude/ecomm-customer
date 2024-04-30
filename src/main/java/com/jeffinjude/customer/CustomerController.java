package com.jeffinjude.customer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
	
	@Value("${ecomm.customer.teststatus}")
	private String testProp;

	
	@GetMapping("test")
	public ResponseEntity<String> test() {	
		return ResponseEntity.ok("Customer Service Test status: " + testProp);
	}
}
