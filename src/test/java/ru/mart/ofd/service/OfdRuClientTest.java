package ru.mart.ofd.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import ru.mart.ofd.model.ofdRuModel.AuthDtoForRequest;

import static org.junit.jupiter.api.Assertions.*;

class OfdRuClientTest {
    private RestTemplate restTemplate;
    private AuthDtoForRequest authDtoRequest;
    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        restTemplate = new RestTemplate();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void useExchangeMethodForAuthDto() {
        String jsonRequest = "{\"login\":\"cto@retailservice24.ru\",\"password\":\"~2-=@oa4\"}";
        String jsonResponse;
        String url = "https://ofd.ru/api/Authorization/CreateAuthToken";

        HttpEntity<String> httpEntityResponse =
                restTemplate.postForEntity(url,
                        new HttpEntity(jsonRequest, headers), String.class);

        if(httpEntityResponse.hasBody()) {
            jsonResponse =  httpEntityResponse.getBody();
        } else jsonResponse = null;

        System.out.println(jsonResponse);

        assertNotEquals(null, jsonResponse);
        assertEquals(String.class, jsonResponse.getClass());
    }
}