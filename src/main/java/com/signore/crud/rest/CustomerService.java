package com.signore.crud.rest;

import com.signore.crud.beans.CustomerBean;
import com.signore.crud.model.Customer;
import com.signore.crud.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer saveCustomer (CustomerBean bean){

        Customer customer = new Customer(bean.getFirstName(), bean.getLastName());
        Customer save = customerRepository.save(customer);
        return save;
    }


    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    public Customer getCustomer(Long pk) {
        Optional<Customer> customerByPk = customerRepository.findById(pk);
        if(customerByPk.isPresent()){
            log.info("numero di bankaccount:{} ",customerByPk.get().getBankAccountList());
            return customerByPk.get();
        }
        return null;
    }

    public void deleteCustomer(Long pk) {
        Optional<Customer> customerByPk = customerRepository.findById(pk);
        if(customerByPk.isPresent()){
            customerRepository.delete(customerByPk.get());
        }
    }

    public Optional<Customer> searchByNomeECognome(String firstName, String lastName) {
        return customerRepository.searchByFirstNameAndLastName(firstName, lastName);
    }

    // to do count

    public long count() {
        return customerRepository.count();
    }



}
