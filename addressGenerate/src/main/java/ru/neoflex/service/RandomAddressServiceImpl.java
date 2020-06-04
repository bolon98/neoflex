package ru.neoflex.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.neoflex.model.Address;

@Service
public class RandomAddressServiceImpl implements RandomAddressService {

    @Override
    public Address getAddress() throws JSONException {
        RestTemplate restTemplate = new RestTemplate();

        String randomAddress = restTemplate.getForObject("https://randomapi.com/api/518ecc3294bfea8c6def671aefc3711f", String.class);

        JSONObject jsonObject = new JSONObject(randomAddress);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        JSONObject addressObj = jsonArray.getJSONObject(0);

        String street = addressObj.getJSONObject("Address").getString("street");
        String city = addressObj.getJSONObject("Address").getString("city");
        String state = addressObj.getJSONObject("Address").getString("state");

        Address address = new Address(street, city, state);

        return address;
    }
}
