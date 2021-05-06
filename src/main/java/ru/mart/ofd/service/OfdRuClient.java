package ru.mart.ofd.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.mart.ofd.model.ofdRuModel.AuthDtoForRequest;
import ru.mart.ofd.model.ofdRuModel.AuthDtoForResponse;
import ru.mart.ofd.repository.ClientRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 * Сервис для обмена авторизационными данными с API OFD.RU
 */
@Component
public class OfdRuClient {


    private ClientServiceImpl clientService;
    private ObjectMapper objectMapper;
    private AuthDtoForResponse authDtoResponse;
    private List<AuthDtoForResponse> authDtoForResponseList;
    private final String url = "https://ofd.ru/api/Authorization/CreateAuthToken";

    {
        clientService = new ClientServiceImpl();
        objectMapper = new ObjectMapper();
        authDtoForResponseList = new ArrayList<>();

    }

    /**
     * Получение коллекции Set<AuthDtoForRequest> с
     * авторизационными данными из БД, для запроса токена
     * на веб-сервисе ofd.ru .
     * @return List<AuthDtoForResponse> который содержит ответы
     * в виде токенов и  их сроков действия
     */
    public  List<AuthDtoForResponse>  useExchangeMethodForAuthDto() {
        Set<AuthDtoForRequest> authDtoForRequestSet =
                clientService.getAllClientAndConvertInAuthDtoForRequest();
        RestTemplate restTemplate = new RestTemplate();
        authDtoForRequestSet.stream().forEach(authDtoRequest -> {
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(url, createHttpEntity(authDtoRequest), String.class);
                    if (responseEntity.hasBody() && (responseEntity.getStatusCodeValue()==200)) {
                        String jsonResponse = responseEntity.getBody();
                       authDtoForResponseList.add(getAuthDtoForResponseFromJson(jsonResponse));
                    } //залогировать и обработать!
                }
        );
        return authDtoForResponseList;
    }

    /**
     * Десереализация объекта AuthDtoForResponse
     * @param json ответ от сервера
     * @return полученый объект AuthDtoForResponse  из json
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

        return authDtoResponse;
    }

    /**
     * Создаем и заполняяем объект HttpEntity
     * * @return HttpEntity for pass to postForEntity method
     */
    private HttpEntity<String> createHttpEntity(AuthDtoForRequest authDtoRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = getJsonFromAuthDtoRequestObject(authDtoRequest);
        HttpEntity<String> httpEntityRequest =
                     new HttpEntity<>(json,headers);

        return httpEntityRequest;
    }

    /**
     * Преобразует объект AuthDtoForRequest в Json
     * @return Json as String
     */
    @Nullable
    private String getJsonFromAuthDtoRequestObject(AuthDtoForRequest authDtoRequest) {
        try {
            return objectMapper.writeValueAsString(authDtoRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
