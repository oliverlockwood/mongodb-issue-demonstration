/*
 * Copyright © 2020 Dalet - All Rights Reserved
 *
 * This file is part of Ooyala Flex.
 *
 * Unauthorized copying and/or distribution of this file or any other part of Ooyala Flex, via any medium,
 * is strictly prohibited.  Proprietary and confidential.
 */
package com.oliverlockwood.mongodb.persistence.converter;

import com.mongodb.BasicDBList;
import com.oliverlockwood.mongodb.model.Customer;
import com.oliverlockwood.mongodb.model.DataValue;
import org.bson.BasicBSONObject;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.util.Map;


@WritingConverter
public class CustomerWriteConverter implements CustomerConverter, Converter<Customer, Document> {

    @Override
    public Document convert(Customer customer) {

        Document document = new Document();

        document.put(FIELD_ID, customer.getId());

        document.put(FIELD_FIRST_NAME, customer.getFirstName());
        document.put(FIELD_LAST_NAME, customer.getLastName());

        BasicDBList additionalDataEntries = new BasicDBList();
        final Map<String, DataValue> additionalData = customer.getAdditionalData();

        if (additionalData != null && !additionalData.isEmpty()) {
            for (Map.Entry<String, DataValue> entry : additionalData.entrySet()) {
                BasicBSONObject values = new BasicBSONObject();
                values.put(FIELD_ADDITIONAL_DATA_NAME, entry.getKey());
                values.put(FIELD_ADDITIONAL_DATA_VALUE, entry.getValue().getValue());
                additionalDataEntries.add(values);
            }

            document.put(FIELD_ADDITIONAL_DATA, additionalDataEntries);
        }

        return document;
    }
}
