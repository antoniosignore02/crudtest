package com.signore.crud.repositories;

import com.signore.crud.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class CustomerRepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Autowired
    private CustomerRepository repository;

    @Test
    public void should_test_customer_repository() {

        // fetch all customers
        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        List<Customer> customers = repository.findAll();

        Assertions.assertEquals(5, customers.size());
        Assertions.assertEquals(5, repository.count());
        Assertions.assertEquals("Jack", customers.get(0).getFirstName());
        Assertions.assertEquals("Bauer", customers.get(0).getLastName());

        Assertions.assertEquals(1L, customers.get(0).getId());

        List<Customer> bauerList = repository
                .findByLastNameStartsWithIgnoreCase("Bauer");

        Assertions.assertEquals(2, bauerList.size());
        Assertions.assertEquals(1L, bauerList.get(0).getId());

        // fetch an individual customer by ID
        Customer customer = repository.findById(1L).get();
        log.info("Customer found with findOne(1L):");
        log.info("--------------------------------");
        log.info(customer.toString());
        log.info("");

        // fetch customers by last name
        log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
        log.info("--------------------------------------------");
        List<Customer> nameStartsWithIgnoreCase = repository
                .findByLastNameStartsWithIgnoreCase("bau");
        for (Customer bauer : nameStartsWithIgnoreCase) {
            log.info(bauer.toString());
        }
        log.info("");

        List<Customer> lastNameEndsWithR = repository.findByLastNameEndsWith("db");
        Assertions.assertEquals(4, lastNameEndsWithR.size());

        repository.delete(customer);
        Assertions.assertEquals(4, repository.count());

        Customer david = customers.get(3);
        david.setLastName("Provolino");
        Customer customer1 = repository.save(david);
        Assertions.assertEquals("Provolino", customer1.getLastName());


    }

}