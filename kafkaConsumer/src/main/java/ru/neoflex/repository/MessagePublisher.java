package ru.neoflex.repository;

import ru.neoflex.model.BankAccount;

public interface MessagePublisher {

    public void send(BankAccount bankAccount);

    public BankAccount get(String id);
}
