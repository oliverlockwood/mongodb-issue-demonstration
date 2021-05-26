package com.oliverlockwood.mongodb.persistence;

import com.oliverlockwood.mongodb.controller.CustomerQuery;
import com.oliverlockwood.mongodb.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Customer> getCustomers(CustomerQuery customerQuery) {
        Query query = mongoQueryFor(customerQuery);

        List<Customer> customers = mongoTemplate.find(query, Customer.class);

        return customers;
    }

    private Query mongoQueryFor(final CustomerQuery customerQuery) {

        Criteria criteria = new Criteria();

        if (customerQuery.getFirstName() != null) {
            criteria.and("firstName").is(customerQuery.getFirstName());
        }
        if (customerQuery.getLastName() != null) {
            criteria.and("firstName").is(customerQuery.getLastName());
        }

        // filter for data free text search
        if (customerQuery.getAdditionalDataValue() != null && customerQuery.getAdditionalDataValue().length() > 0) {
            criteria.andOperator(
                    Criteria.where("additionalData.name").is(customerQuery.getAdditionalDataName()),
                    Criteria.where("additionalData.value").is(customerQuery.getAdditionalDataValue()));
        }

        Query query = new Query();
        query.addCriteria(criteria);
        log.info("Mongo query: {}", query);

        return query;
    }
}
