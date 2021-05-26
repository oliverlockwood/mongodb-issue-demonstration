/*
 * Copyright © 2020 Dalet - All Rights Reserved
 *
 * This file is part of Ooyala Flex.
 *
 * Unauthorized copying and/or distribution of this file or any other part of Ooyala Flex, via any medium,
 * is strictly prohibited.  Proprietary and confidential.
 */
package com.oliverlockwood.mongodb.persistence.converter;

import com.oliverlockwood.mongodb.model.Customer;
import com.oliverlockwood.mongodb.model.DataValue;
import org.bson.BasicBSONObject;
import org.bson.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Converts {@link BasicBSONObject} to <code>Event</code>. Needed because Spring can't automatically convert the event data
 * field.
 */
@ReadingConverter
public class CustomerReadConverter implements CustomerConverter, Converter<Document, Customer> {

    @Override
    @SuppressWarnings("unchecked")
    public Customer convert(Document document) {

        Customer.CustomerBuilder builder = Customer.builder()
                .firstName(document.getString(FIELD_FIRST_NAME))
                .lastName(document.getString(FIELD_LAST_NAME));

        final ArrayList<Document> additionalDataEntries = (ArrayList<Document>) document.get(FIELD_ADDITIONAL_DATA);

        if (additionalDataEntries != null) {
            List<DataValue> additionalData = new ArrayList<>();

            for (Document additionalDataEntry : additionalDataEntries) {
                additionalData.add(DataValue.builder()
                        .name(additionalDataEntry.getString(FIELD_ADDITIONAL_DATA_NAME))
                        .value(additionalDataEntry.getString(FIELD_ADDITIONAL_DATA_VALUE))
                        .build());
            }

            Map<String, DataValue> additionalDataMap = additionalData.stream()
                    .collect(Collectors.toMap(DataValue::getName, Function.identity()));
            builder.additionalData(additionalDataMap);
        }

        return builder.build();
    }

}
