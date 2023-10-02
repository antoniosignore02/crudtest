package com.signore.crud.rest;

import com.signore.crud.beans.CustomerBean;
import com.signore.crud.beans.CustomerUpdateBean;
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
        return customerByPk;
    }

    @GetMapping("/crud/customers/{firstName}/{lastName}")
    public Customer search(@PathVariable("firstName") String firstName,
                           @PathVariable("lastName") String lastName) {
        Optional<Customer> customer = customerService.searchByNomeECognome(firstName, lastName);
        return customer.orElse(null);
    }



    @DeleteMapping("/crud/customers/{pk}")
    public void delete(@PathVariable("pk") Long pk) {
        customerService.deleteCustomer(pk);
    }

    @GetMapping("/crud/customers/count")
    public long count(String firstName,
                      String lastName) {
        long customer = customerService.count();
        return customer;
    }

    // POST http://host:8080/crud/customer"
    @PostMapping(value = "/crud/customers",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer postNewCustomer(@RequestBody CustomerBean customerBean) {
        Customer savedCustomer = customerService.saveCustomer(customerBean);
        return savedCustomer;
    }

    // PUT http://host:8080/crud/customer"
    /*
        public class CustomerUpdateBean {
            Long id;
            String firstName;
            String lastName;
        }

        da terminal
        curl -X PUT -H 'Content-Type: application/json' -d '{"id":35,"firstName":"Francesco","lastName":"Provolone"}' localhost:8080/crud/customer
     */
    @PutMapping(value = "/crud/customer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer update(@RequestBody CustomerUpdateBean customerUpdateBean) {
        Customer  customerUpdated = customerService.updateCustomer(customerUpdateBean);

        log.info("returning customerUpdated : {} ", customerUpdated.toString());

        return customerUpdated;
    }


}






