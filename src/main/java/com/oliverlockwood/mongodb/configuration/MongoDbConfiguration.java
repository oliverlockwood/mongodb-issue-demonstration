/*
 * Copyright © 2020 Dalet - All Rights Reserved
 *
 * This file is part of Ooyala Flex.
 *
 * Unauthorized copying and/or distribution of this file or any other part of Ooyala Flex, via any medium,
 * is strictly prohibited.  Proprietary and confidential.
 */
package com.oliverlockwood.mongodb.configuration;

import com.oliverlockwood.mongodb.persistence.converter.CustomerReadConverter;
import com.oliverlockwood.mongodb.persistence.converter.CustomerWriteConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;


/**
 * A configuration class for MongoDB.
 *
 * This class works with com.ooyala.flex.resourcemanager.configuration.MongoDbAutoConfiguration.mongoClientOptions().
 */
@Configuration
@Slf4j
public class MongoDbConfiguration {

    @Bean
    public MongoCustomConversions customConversions() {

        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new CustomerReadConverter());
        converters.add(new CustomerWriteConverter());

        return new MongoCustomConversions(converters);
    }

}
