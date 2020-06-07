package ru.neoflex.service;

import reactor.core.publisher.Mono;
import ru.neoflex.model.Address;

public interface RandomAddressService {

    /**
     * Получить Случайный адрес с https://randomapi.com/
     *
     */
    Mono<Address> getAddress();
}
