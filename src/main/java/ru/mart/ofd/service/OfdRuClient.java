package ru.mart.ofd.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.mart.ofd.model.ofdRuModel.AuthDtoForRequest;
import ru.mart.ofd.model.ofdRuModel.AuthDtoForResponse;


import java.util.List;

@Component
public class OfdRuClient {
    
    private ObjectMapper objectMapper;
    private final String login;
    private final String password;
    private AuthDtoForRequest authDtoRequest;
    private AuthDtoForResponse authDtoResponse;

    {
        login = "cto@retailservice24.ru";
        password = "~2-=@oa4";
        authDtoRequest = new AuthDtoForRequest(login, password );
        objectMapper = new ObjectMapper();
    }

    /**
     *
     * @return
     */
    public List<AuthDtoForRequest> useExchangeMethodForAuthDto() {
        String jsonResponse = null;
        String url = "https://ofd.ru/api/Authorization/CreateAuthToken";
        RestTemplate restTemplate = null;
        
        HttpEntity<String> httpEntityResponse =
                restTemplate.postForEntity(url, createHttpEntity(), String.class);
        if(httpEntityResponse.hasBody()) {
            jsonResponse =  httpEntityResponse.getBody();
        }
        getAuthDtoForResponseFromJson(jsonResponse);

        return null;
    }

    /**
     * Десереализация объекта AuthDtoForResponse
     * @param json ответ от сервера
     * @return полученый объектAuthDtoForResponse  из json
     */
    @Nullable
    private AuthDtoForResponse getAuthDtoForResponseFromJson(@NonNull String json) {

        try {
            objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
            authDtoResponse = objectMapper.readValue(json, AuthDtoForResponse.class);
            return authDtoResponse;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Создаем и заполняяем объект HttpEntity
     * * @return HttpEntity for pass to postForEntity method
     */
    private HttpEntity<String> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = getJsonFromAuthDtoRequestObject();
        HttpEntity<String> httpEntityRequest =
                     new HttpEntity<>(json,headers);

        return httpEntityRequest;
    }

    /**
     * Преобразует объект AuthDtoForRequest в Json
     * @return Json as String
     */
    @Nullable
    private String getJsonFromAuthDtoRequestObject() {
        try {
            return objectMapper.writeValueAsString(authDtoRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
