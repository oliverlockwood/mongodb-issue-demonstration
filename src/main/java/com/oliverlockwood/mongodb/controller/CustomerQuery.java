package com.oliverlockwood.mongodb.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerQuery {

    private String firstName;
    private String lastName;
    private String additionalDataName;
    private String additionalDataValue;

}
