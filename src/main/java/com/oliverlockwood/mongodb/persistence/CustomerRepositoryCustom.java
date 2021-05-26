package com.oliverlockwood.mongodb.persistence;

import com.oliverlockwood.mongodb.controller.CustomerQuery;
import com.oliverlockwood.mongodb.model.Customer;

import java.util.List;

public interface CustomerRepositoryCustom {

    List<Customer> getCustomers(final CustomerQuery customerQuery);

}
