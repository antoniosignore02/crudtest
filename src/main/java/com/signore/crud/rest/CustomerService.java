package com.signore.crud.rest;

import com.signore.crud.beans.CustomerBean;
import com.signore.crud.model.Customer;
import com.signore.crud.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository repository;

    public Customer saveCustomer(CustomerBean bean){
        Customer customer = new Customer(bean.getFirstName(), bean.getLastName());
        Customer savedCustomer = repository.save(customer);
        return savedCustomer;
    }
}
