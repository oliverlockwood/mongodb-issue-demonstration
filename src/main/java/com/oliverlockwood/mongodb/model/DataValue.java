/*
 * Copyright © 2020 Dalet - All Rights Reserved
 *
 * This file is part of Ooyala Flex.
 *
 * Unauthorized copying and/or distribution of this file or any other part of Ooyala Flex, via any medium,
 * is strictly prohibited.  Proprietary and confidential.
 */
package com.oliverlockwood.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


/**
 * Event data value.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DataValue implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String value;
}
