package com.signore.crud.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerUpdateBean {
    Long id;
    String firstName;
    String lastName;
    String middleName;
}
