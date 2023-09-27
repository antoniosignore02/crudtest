package com.signore.crud.repositories;

import com.signore.crud.model.Customer;
import com.signore.crud.rest.PersonService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByLastNameStartsWithIgnoreCase(String lastName);

    List<Customer> findByLastNameEndsWith(String lastName);

    Optional<Customer> searchByFirstNameAndLastName(String firstName, String lastName);


}