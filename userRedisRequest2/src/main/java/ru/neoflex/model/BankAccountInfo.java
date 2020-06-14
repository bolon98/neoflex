package ru.neoflex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountInfo {
    private BankAccount bankAccount;
    private Address address;

}
