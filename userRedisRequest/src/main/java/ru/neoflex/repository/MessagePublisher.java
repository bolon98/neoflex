package ru.neoflex.repository;

import ru.neoflex.model.BankAccount;

public interface MessagePublisher {

    /**
     * Получить BankAccount из Redis
     *
     */
    public BankAccount get(String id);
}
