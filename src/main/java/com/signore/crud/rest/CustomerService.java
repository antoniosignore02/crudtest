package com.signore.crud.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.signore.crud.beans.BankAccountBean;
import com.signore.crud.beans.CustomerBean;
import com.signore.crud.beans.CustomerUpdateBean;
import com.signore.crud.model.BankAccount;
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

    public Customer addBankAccount(BankAccountBean bankAccountBean) {
        Optional<Customer> customerByPk = customerRepository.findById(bankAccountBean.getCustomerId());
        if (customerByPk.isPresent()){

            Customer returnedCustomer = customerByPk.get();

            BankAccount newBankAccount = new BankAccount();
            newBankAccount.setCustomer(returnedCustomer);
            newBankAccount.setBankName(bankAccountBean.getBankName());
            newBankAccount.setIban(bankAccountBean.getIban());


            returnedCustomer.getBankAccountList().add(newBankAccount);
            Customer saved = customerRepository.save(returnedCustomer);
            log.info("this is the saved customer: {}, numero di bank accounts: {}", saved.toString(),saved.getBankAccountList().size());

            List<BankAccount> list = saved.getBankAccountList();
            for (int i = 0; i < list.size(); i++) {
                BankAccount bankAccount =  list.get(i);
                log.info("bankAccounts {}",bankAccount);
            }

            return saved;
        }
        else{
            return null;
        }
    }
}
