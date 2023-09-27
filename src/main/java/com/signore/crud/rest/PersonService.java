package com.signore.crud.rest;

import com.signore.crud.model.Customer;

import java.util.List;

public interface PersonService {
    List<Customer> searchByFirstAndOrLastName(String firstName, String lastName);
}
