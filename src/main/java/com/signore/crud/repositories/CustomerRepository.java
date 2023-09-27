package com.signore.crud.repositories;

import com.signore.crud.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);

    List<Customer> findByLastNameEndsWith(String lastName);

    List<Customer> searchByFirstAndOrLastName(String firstName, String lastName);
}