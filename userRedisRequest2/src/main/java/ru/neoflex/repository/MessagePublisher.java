package ru.neoflex.repository;

import ru.neoflex.model.BankAccountInfo;

public interface MessagePublisher {

    /**
     * Получить BankAccountInfo из Redis
     *
     */
    public BankAccountInfo get(String id);
}
