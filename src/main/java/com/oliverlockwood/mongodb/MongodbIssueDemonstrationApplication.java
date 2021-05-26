package com.oliverlockwood.mongodb;

import com.oliverlockwood.mongodb.controller.CustomerQuery;
import com.oliverlockwood.mongodb.model.Customer;
import com.oliverlockwood.mongodb.model.DataValue;
import com.oliverlockwood.mongodb.persistence.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class MongodbIssueDemonstrationApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(MongodbIssueDemonstrationApplication.class, args);
	}

	@Override
	public void run(String... args) {

		repository.deleteAll();

		// save a couple of customers
		repository.save(Customer.builder().firstName("Alice").lastName("Smith").build());
		repository.save(Customer.builder().firstName("Bob").lastName("Smith").build());
		repository.save(Customer.builder().firstName("Mary").lastName("Smith")
				.additionalData(Collections.singletonMap("a", DataValue.builder().name("a").value("b").build()))
				.build());

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		printResults(repository.findAll());

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		printResults(repository.findByLastName("Smith"));

		System.out.println("Customers found with additionalData('a=b'):");
		System.out.println("--------------------------------");
		CustomerQuery customerQuery = CustomerQuery.builder().additionalDataName("a").additionalDataValue("b").build();
		printResults(repository.getCustomers(customerQuery));
	}

	private void printResults(List<Customer> results) {
		if (results.isEmpty()) {
			System.out.println("### No customers found! ###");
		}
		for (Customer customer : results) {
			System.out.println(customer);
		}
		System.out.println();
	}

}
