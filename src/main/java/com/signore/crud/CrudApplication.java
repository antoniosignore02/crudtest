package com.signore.crud;

import com.signore.crud.model.BankAccount;
import com.signore.crud.model.Customer;
import com.signore.crud.repositories.CustomerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Autowired
	private CustomerRepository repository;

	@PostConstruct
	public void initializeSomething(){
		repository.save(new Customer("Jack", "Bauer"));
		repository.save(new Customer("Chloe", "O'Brian"));
		repository.save(new Customer("Kim", "Bauer"));
		repository.save(new Customer("David", "Palmer"));
		repository.save(new Customer("Michelle", "Dessler"));
		Customer mickyMouse = new Customer("micky","mouse");

		List<BankAccount> bankAccountList = new ArrayList<>();

		BankAccount bankAccount1 = createBankAccount(mickyMouse, "sparkasse", "1234");
		bankAccountList.add(bankAccount1);

		BankAccount bankAccount2 = createBankAccount(mickyMouse, "commerzbank", "2345");
		bankAccountList.add(bankAccount2);

		mickyMouse.setBankAccountList(bankAccountList);

		repository.save(mickyMouse);

	}

	private static BankAccount createBankAccount(Customer mickyMouse, String bankName, String iban) {
		BankAccount bankAccount1 = new BankAccount();
		bankAccount1.setBankName(bankName);
		bankAccount1.setIban(iban);
		bankAccount1.setCustomer(mickyMouse);
		return bankAccount1;
	}



}
