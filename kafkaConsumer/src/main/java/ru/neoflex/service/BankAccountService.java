package ru.neoflex.service;

public interface BankAccountService {

    /**
     * Получить банковский аккаунт из Kafka
     *
     */
    void getBankAccount(Object bankAccount);
}
