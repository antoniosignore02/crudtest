package com.signore.crud.rest;

import com.signore.crud.model.Customer;
import com.signore.crud.repositories.CustomerRepository;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ContextConfiguration(initializers = {CustomerControllerTest.Initializer.class})
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();
    }

    @Container
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("crudrest")
            .withUsername("crudrest")
            .withPassword("crudrest");

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }


    @Test
    void getCustomers() throws Exception {


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
    void getCustomerByPk() throws Exception {

        customerRepository.deleteAll();
        Customer savedcustomer = customerRepository.save(new Customer("nicholas", "signore"));


        this.mockMvc
                .perform(get("/crud/customers/" + savedcustomer.getId()))
                .andDo(print())
                .andExpect(status().isOk())    // <--- mi aspetto 200
                .andExpect(content()
                        .string(containsString("{\"id\":"+ savedcustomer.getId() +",\"firstName\":\"nicholas\",\"lastName\":\"signore\"}"))); // <--- mi aspetto nicholas signore

    }



    @Test
    void postNewCustomer() {
    }

    @Test
    void delete() {
    }
}