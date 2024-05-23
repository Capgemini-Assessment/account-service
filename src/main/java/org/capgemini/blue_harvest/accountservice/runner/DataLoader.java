package org.capgemini.blue_harvest.accountservice.runner;

import org.capgemini.blue_harvest.accountservice.entity.Customer;
import org.capgemini.blue_harvest.accountservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		customerRepository.save(new Customer(1, "John", "Doe", "john.doe@example.com", LocalDateTime.now()));
		customerRepository.save(new Customer(2, "Jane", "Smith", "jane.smith@example.com", LocalDateTime.now()));
		customerRepository.save(new Customer(3, "Alice", "Johnson", "alice.johnson@example.com", LocalDateTime.now()));
		customerRepository.save(new Customer(4, "Bob", "Williams", "bob.williams@example.com", LocalDateTime.now()));
		customerRepository.save(new Customer(5, "Emily", "Brown", "emily.brown@example.com", LocalDateTime.now()));
	}
}