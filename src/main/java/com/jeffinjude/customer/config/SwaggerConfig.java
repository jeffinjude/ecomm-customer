package com.jeffinjude.customer.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	    info = @Info(
	        title = "Customer Service API",
	        description = "This api manages the customer profiles.",
	        summary = "This api can add, edit, update and delete customer profile. It can also list all customers and also get details of a single customer.",
	        contact = @Contact(
	        			name = "Jeffin Pulickal",
	        			email = "test@test.com"
	        		),
	        version = "v1"
	    )
	)
public class SwaggerConfig {
	//Access swagger at http://localhost:8093/swagger-ui/index.html
}
