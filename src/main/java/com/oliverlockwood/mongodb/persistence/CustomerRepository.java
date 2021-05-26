package com.oliverlockwood.mongodb.persistence;

import java.util.List;

import com.oliverlockwood.mongodb.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String>, CustomerRepositoryCustom {

  public Customer findByFirstName(String firstName);
  public List<Customer> findByLastName(String lastName);

}