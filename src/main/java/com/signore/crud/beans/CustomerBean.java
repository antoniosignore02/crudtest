package com.signore.crud.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.signore.crud.model.BankAccount;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerBean {
    public CustomerBean(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    private String firstName;
    private String middleName;
    private String lastName;

    private List<BankAccount> bankAccountList;

}
