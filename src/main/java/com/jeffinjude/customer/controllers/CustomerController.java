package com.jeffinjude.customer.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeffinjude.customer.entities.Customer;
import com.jeffinjude.customer.services.CustomerService;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
	
	@Value("${ecomm.customer.teststatus}")
	private String testProp;
	
	@Autowired
	CustomerService customerService;
	
	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@GetMapping("test")
	public ResponseEntity<String> test() {	
		return ResponseEntity.ok("Customer Service Test status: " + testProp);
	}
	
	@PostMapping("add")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		ResponseEntity<Customer> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Customer createdCustomer = customerService.addCustomerService(customer);
			log.info("Inside addCustomer. Created addCustomer: " + createdCustomer.toString());
			responseEntity = new ResponseEntity<>(createdCustomer, HttpStatus.OK);
		}
		catch(Exception e) {
			log.debug("Inside addCustomer. Exception: " + e.getMessage());
		}
		return responseEntity;
	}
	
	@GetMapping("list")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		ResponseEntity<List<Customer>> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			List<Customer> fetchedCustomers = customerService.getAllCustomersService();
			log.info("Inside getAllCustomers. Fetched customers: " + fetchedCustomers.toString());
			responseEntity = new ResponseEntity<>(fetchedCustomers, HttpStatus.OK);
		}
		catch(Exception e) {
			log.debug("Inside getAllCustomers. Exception: " + e.getMessage());
		}
		return responseEntity;
	}
	
	@GetMapping("details/{id}")
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable("id") int id) {
		ResponseEntity<Customer> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Optional<Customer> fetchedCustomer = customerService.getCustomerDetailsService(id);
			log.info("Inside getCustomerDetails. Fetched customer: " + fetchedCustomer.toString());
			if(fetchedCustomer.isPresent()) {
				responseEntity = new ResponseEntity<>(fetchedCustomer.get(), HttpStatus.OK);
			}
			else {
				responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
		}
		catch(Exception e) {
			log.debug("Inside getCustomerDetails. Exception: " + e.getMessage());
		}
		return responseEntity;
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable("id") int id) {
		ResponseEntity<Customer> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Optional<Customer> fetchedCustomer = customerService.getCustomerDetailsService(id);
			log.info("Inside updateCustomer. Fetched customer: " + fetchedCustomer.toString());
			if(fetchedCustomer.isPresent()) {
				
				customer.setCustomerId(fetchedCustomer.get().getCustomerId());
				
				if(customer.getCustomerName() == null) {
					customer.setCustomerName(fetchedCustomer.get().getCustomerName());
				}
				if(customer.getCustomerMobile() == null) {
					customer.setCustomerMobile(fetchedCustomer.get().getCustomerMobile());
				}
				if(customer.getCustomerEmail() == null) {
					customer.setCustomerEmail(fetchedCustomer.get().getCustomerEmail());
				}
				if(customer.getCustomerAddress() == null) {
					customer.setCustomerAddress(fetchedCustomer.get().getCustomerAddress());
				}
				
				Customer updatedCustomer = customerService.addCustomerService(customer);
				responseEntity = new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
			}
			else {
				responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
		}
		catch(Exception e) {
			log.debug("Inside updateCustomer. Exception: " + e.getMessage());
		}
		return responseEntity;
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<String> deleteCustomerDetails(@PathVariable("id") int id) {
		ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		try {
			Optional<Customer> fetchedCustomer = customerService.getCustomerDetailsService(id);
			log.info("Inside deleteCustomerDetails. Fetched customer: " + fetchedCustomer.toString());
			if(fetchedCustomer.isPresent()) {
				customerService.deleteCustomerService(id);
				responseEntity = new ResponseEntity<>("Customer deleted.", HttpStatus.OK);
			}
			else {
				responseEntity = new ResponseEntity<>("Customer does not exist.", HttpStatus.NO_CONTENT);
			}
		}
		catch(Exception e) {
			log.debug("Inside deleteProductDetails. Exception: " + e.getMessage());
		}
		return responseEntity;
	}
}