package com.jeffinjude.customer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeffinjude.customer.entities.Customer;
import com.jeffinjude.customer.repositories.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
    CustomerRepository customerRepository;
	
	public Customer addCustomerService(Customer product) {
		return customerRepository.save(product);
	}
	
	public List<Customer> getAllCustomersService() {
		return customerRepository.findAll();
	}
	
	public Optional<Customer> getCustomerDetailsService(int id) {
		return customerRepository.findById(id);
	}
	
	public void deleteCustomerService(int id) {
		customerRepository.deleteById(id);
	}
}
