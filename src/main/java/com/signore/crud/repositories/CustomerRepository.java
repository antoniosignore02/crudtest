package com.signore.crud.repositories;

import com.signore.crud.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findById(Long id);

    Optional<Customer> searchByFirstNameAndMiddleNameAndLastName(String firstName,String middleName, String lastName);

}