package ru.neoflex.service;

import ru.neoflex.model.BankAccount;

public interface BankAccountService {

    /**
     * Получить банковский аккаунт из Kafka
     *
     */
    void getBankAccount(BankAccount bankAccount);
}
