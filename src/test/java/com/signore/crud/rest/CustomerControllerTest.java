package com.signore.crud.rest;

import com.signore.crud.model.Customer;
import com.signore.crud.repositories.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ContextConfiguration(initializers = {CustomerControllerTest.Initializer.class})
class CustomerControllerTest {

    @Autowired
    private CustomerRepository repository;

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
        customerRepository.save(new Customer(1L, "nicholas", "santos", "signore"));

        // eseguo la REST sotto test
        this.mockMvc
                .perform(get("/crud/customers"))
                .andDo(print())
                .andExpect(status().isOk())    // <--- mi aspetto 200
                .andExpect(content()
                .string(containsString("\"firstName\":\"nicholas\",\"lastName\":\"signore\",\"middleName\":\"santos\""))); // <--- mi aspetto nicholas signore

    }

    @Test
    void getCustomerByPk() throws Exception {

        Customer savedcustomer = customerRepository.save(new Customer("nicholas","santos", "signore"));

        this.mockMvc
                .perform(get("/crud/customers/" + savedcustomer.getId()))
                .andDo(print())
                .andExpect(status().isOk())    // <--- mi aspetto 200
                .andExpect(content()
                        .string(containsString("{\"id\":"+ savedcustomer.getId() +",\"firstName\":\"nicholas\",\"lastName\":\"signore\",\"middleName\":\"santos\"}"))); // <--- mi aspetto nicholas signore

    }

    @Test
    void postNewCustomer() throws Exception {
        String url = "/crud/customers";
        String contentType = "application/json;charset=UTF-8";
        String requestJson = "{\"firstName\":\"Francesco\",\"middleName\":\"Santos\",\"lastName\":\"Signore\"}";

        mockMvc.perform(post(url).contentType(contentType)
                .content(requestJson))
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void delete() {
    }

    @Test
    void findCustomer() throws Exception {

        repository.save(new Customer("Francesco","Santos", "Signore"));
        Optional<Customer> customers =  repository.searchByFirstNameAndMiddleNameAndLastName("Francesco", "Santos","Signore");

        Assertions.assertThat(customers.isPresent()).isTrue();

        String url = "/crud/customer/francesco/santos/signore";

        mockMvc.perform(post(url))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void customerCount() throws Exception {

        repository.save(new Customer("Francesco","Santos", "Signore"));

        String url = "/crud/customers/count";
        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string("6"));
        ;
    }





}