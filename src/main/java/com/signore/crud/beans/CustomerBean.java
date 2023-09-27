package com.signore.crud.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.signore.crud.model.BankAccount;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerBean {
    private String firstName;
    private String lastName;
    private List<BankAccount> bankAccountList;
}
