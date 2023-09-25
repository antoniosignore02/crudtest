package com.signore.crud.rest;

import com.signore.crud.model.Customer;
import com.signore.crud.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void getCustomers() throws Exception {

        // puliamo la tabella dei customer.
        customerRepository.deleteAll();

        // aggiungo nicholas nel DB
        customerRepository.save(new Customer(1L, "nicholas", "signore"));

        // eseguo la REST sotto test
        this.mockMvc
                .perform(get("/crud/customers"))
                .andDo(print())
                .andExpect(status().isOk())    // <--- mi aspetto 200
                .andExpect(content()
                .string(containsString("\"firstName\":\"nicholas\",\"lastName\":\"signore\""))); // <--- mi aspetto nicholas signore

    }

    @Test
    void getCustomerByPk() {
    }

    @Test
    void postNewCustomer() {
    }

    @Test
    void delete() {
    }
}