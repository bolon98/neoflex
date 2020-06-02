package ru.neoflex.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.neoflex.model.Address;

@Service
public class RandomAddressServiceImpl implements RandomAddressService {

    @Scheduled(fixedDelay = 5000)
    @Override
    public void getAddress() {
        RestTemplate restTemplate = new RestTemplate();

        String address = restTemplate.getForObject("https://randomapi.com/api/518ecc3294bfea8c6def671aefc3711f", String.class);
        System.out.println(address);
    }
}
