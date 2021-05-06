package ru.mart.ofd.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

class OfdRuClientTest {
    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private List<String> jsonRequestList;
    private final String jsonRequest_1 = "{\"login\":\"cto@retailservice24.ru\",\"password\":\"~2-=@oa4\"}";
    private final String jsonRequest_2 = "{\"login\":\"tsarekolga@mail.ru\",\"password\":\"Tsarek22@\"}";
    private final String jsonRequest_3 = "{\"login\":\"Oku.labi54@mail.ru\",\"password\":\"-a2a%0~r\"}";
    private final String url = "https://ofd.ru/api/Authorization/CreateAuthToken";


    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        jsonRequestList = Stream.of(jsonRequest_1,jsonRequest_2,jsonRequest_3).
                collect(Collectors.toList());
    }

    @Test
    void useExchangeMethodForAuthDto() {
        String jsonResponse;

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url,
                        new HttpEntity(jsonRequest_1, headers), String.class);

        if(responseEntity.hasBody() && (responseEntity.getStatusCodeValue()==200)) {
            jsonResponse =  responseEntity.getBody();
        } else jsonResponse = null;

        assertNotEquals(null, jsonResponse);
        assertEquals(String.class, jsonResponse.getClass());
    }

    @Test
    public void useExchangeMethodForListAuthDto()  {
        List<String> jsonResponseList = new ArrayList<>(3);
        jsonRequestList.stream().forEach(jsonRequest -> {
            ResponseEntity<String> responseEntity =
                            restTemplate.postForEntity(url, new HttpEntity(jsonRequest,
                    headers),String.class);
                    if (responseEntity.hasBody() && (responseEntity.getStatusCodeValue()==200)) {
                        jsonResponseList.add(responseEntity.getBody());
                    } //передать код ошибки
                }
        );

        assertNotEquals(null, jsonResponseList);
        assertThat(jsonResponseList, hasSize(3));
    }
}