/*
 * Copyright © 2020 Dalet - All Rights Reserved
 *
 * This file is part of Ooyala Flex.
 *
 * Unauthorized copying and/or distribution of this file or any other part of Ooyala Flex, via any medium,
 * is strictly prohibited.  Proprietary and confidential.
 */
package com.oliverlockwood.mongodb.persistence.converter;

/**
 * A constant interface is created for event fields of mongo database.
 * <p>
 * ====================================================================
 * = NOTE: This copy of this file also exists in the Migrator module! =
 * ====================================================================
 */
public interface CustomerConverter {

    String FIELD_ID = "_id";
    String FIELD_FIRST_NAME = "firstName";
    String FIELD_LAST_NAME = "lastName";
    String FIELD_ADDITIONAL_DATA = "additionalData";
    String FIELD_ADDITIONAL_DATA_NAME = "name";
    String FIELD_ADDITIONAL_DATA_VALUE = "value";
}
