package com.signore.crud.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
public class BankAccount {
    @Id
    @GeneratedValue
    private Long id;

    private String bankName;

    private String iban;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name = "id_customer", nullable = false)
    private Customer customer;

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", bankName='" + bankName + '\'' +
                ", iban='" + iban + '\'' +
                '}';
    }
}
