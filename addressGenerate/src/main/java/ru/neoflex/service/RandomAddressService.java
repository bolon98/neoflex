package ru.neoflex.service;

import org.json.JSONException;
import ru.neoflex.model.Address;

public interface RandomAddressService {

    /**
     * Получить Случайный адрес с https://randomapi.com/
     *
     */
    Address getAddress() throws JSONException;
}
