package com.oliverlockwood.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "customer")
@Data
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    public String id;

    public String firstName;
    public String lastName;

    public Map<String, DataValue> additionalData;

}
