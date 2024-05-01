package com.jeffinjude.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeffinjude.customer.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
}
