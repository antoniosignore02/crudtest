package com.signore.crud.rest;

import com.signore.crud.beans.CustomerBean;
import com.signore.crud.beans.CustomerUpdateBean;
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

        Customer customer = new Customer(bean.getFirstName(), bean.getLastName(), bean.getMiddleName());
        Customer save = customerRepository.save(customer);
        return save;
    }

    public Customer updateCustomer (CustomerUpdateBean bean){

        Long id = bean.getId();

        // cerco il customer con Id = bean.getId();
        Optional<Customer> customerReturned = customerRepository.findById(id);

        if (customerReturned.isEmpty())
            throw new IllegalArgumentException("customer non trovato con id = " +id);

        customerReturned.get().setFirstName(bean.getFirstName());
        customerReturned.get().setLastName(bean.getLastName());
        customerReturned.get().setMiddleName(bean.getMiddleName());
        Customer returned = customerRepository.save(customerReturned.get());
        return  returned;

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
        customerByPk.ifPresent(customer -> customerRepository.delete(customer));
    }

    public Optional<Customer> searchByNomeMiddleNameECognome(String firstName,String middleName, String lastName) {
        return customerRepository.searchByFirstNameAndMiddleNameAndLastName(firstName,middleName, lastName);
    }

    public long count() {
        return customerRepository.count();
    }

}
