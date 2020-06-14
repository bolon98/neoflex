package ru.neoflex.repository;

import ru.neoflex.model.BankAccountInfo;

public interface MessagePublisher {

    /**
     * Добавить BankAccountInfo в Redis
     *
     */
    public void send(BankAccountInfo bankAccountInfo);

}
