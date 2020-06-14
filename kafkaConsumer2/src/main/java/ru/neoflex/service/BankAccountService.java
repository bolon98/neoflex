package ru.neoflex.service;

import ru.neoflex.model.BankAccountInfo;

public interface BankAccountService {

    /**
     * Получить BankAccountInfo из Kafka
     *
     */
    void getBankAccountInfo(BankAccountInfo bankAccountInfo);
}
