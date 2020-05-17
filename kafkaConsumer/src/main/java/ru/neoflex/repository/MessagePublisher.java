package ru.neoflex.repository;

import ru.neoflex.model.BankAccount;

public interface MessagePublisher {

    /**
     * Добавить BankAccount в Redis
     *
     */
    public void send(BankAccount bankAccount);

}
