package com.signore.crud.rest;

import com.signore.crud.beans.CustomerBean;
import com.signore.crud.model.Customer;
import com.signore.crud.repositories.CustomerRepository;
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
        if (customerByPk != null) return customerByPk;
        return null;
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



}


