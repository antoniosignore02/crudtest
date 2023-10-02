package com.signore.crud.rest;

import com.signore.crud.beans.CustomerBean;
import com.signore.crud.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @CrossOrigin(origins = "http://127.0.0.1:5500")

    @GetMapping("/crud/customers")
    public Collection<Customer> getCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customers;
    }

    @GetMapping("/crud/customers/{pk}")
    public Customer getCustomerByPk(@PathVariable("pk") Long pk) {
        Customer customerByPk = customerService.getCustomer(pk);
        return customerByPk;
    }

    @GetMapping("/crud/customers/{firstName}/{lastName}")
    public Customer search(@PathVariable("firstName") String firstName,
                           @PathVariable("lastName") String lastName ) {
        Optional<Customer> customer = customerService.searchByNomeECognome(firstName, lastName);
        return customer.orElse(null);
    }

    @PostMapping(value = "/crud/customer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer postNewCustomer(@RequestBody CustomerBean customerBean){
        Customer savedCustomer = customerService.saveCustomer(customerBean);
        return savedCustomer;
    }

    @DeleteMapping("/crud/customers/{pk}")
    public void delete (@PathVariable("pk") Long pk){
        customerService.deleteCustomer(pk);
    }

    @GetMapping("/crud/customers/count")
    public long count(String firstName,
                      String lastName ) {
        long customer = customerService.countByfirstNameAndLastName(firstName, lastName);
        return customer;
    }

    @PutMapping("/crud/customers/{id}/edit")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {

        if (!customerRepository.existsById(id)) {

            throw new ResourceNotFoundException("Customer not found with id: " + id);
        }

        updatedCustomer.setId(id);

        return customerRepository.save(updatedCustomer);
    }
    }






