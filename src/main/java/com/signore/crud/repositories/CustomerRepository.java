package com.signore.crud.repositories;

import com.signore.crud.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findById(Long id);

    Optional<Customer> searchByFirstNameAndLastName(String firstName, String lastName);

    // todo crea il prototype per contare in numero di customer
    long countByFirstNameAndLastName();

}