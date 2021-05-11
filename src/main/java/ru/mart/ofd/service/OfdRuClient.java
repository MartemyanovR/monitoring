package ru.mart.ofd.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.mart.ofd.model.ofdRuModel.AuthDtoRequest;
import ru.mart.ofd.model.ofdRuModel.AuthDtoResponse;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Сервис для обмена авторизационными данными с API OFD.RU
 */
@Component
public class OfdRuClient {


    private ClientServiceImpl clientService;
    private ObjectMapper objectMapper;
    private AuthDtoResponse authDtoResponse;
    private List<AuthDtoResponse> authDtoResponseList;
    private final String url = "https://ofd.ru/api/Authorization/CreateAuthToken";

    {
        clientService = new ClientServiceImpl();
        objectMapper = new ObjectMapper();
        authDtoResponseList = new ArrayList<>();

    }

    /**
     * Получение коллекции List<AuthDtoForRequest> с
     * авторизационными данными из БД, для запроса токена
     * на веб-сервисе ofd.ru .
     * @return List<AuthDtoForResponse> который содержит ответы
     * в виде токенов и их сроков действия
     */
    public  List<AuthDtoResponse>  useExchangeMethodForAuthDto() {
        List<AuthDtoRequest> authDtoRequestSet =
                clientService.getAllClientAndConvertInAuthDtoForRequest();
        RestTemplate restTemplate = new RestTemplate();
        authDtoRequestSet.stream().forEach(authDtoRequest -> {
            ResponseEntity<String> responseEntity =
                    restTemplate.postForEntity(url, createHttpEntity(authDtoRequest), String.class);
                    if (responseEntity.hasBody() && (responseEntity.getStatusCodeValue()==200)) {
                        String jsonResponse = responseEntity.getBody();

                       authDtoResponseList.add(getAuthDtoForResponseFromJson(jsonResponse, authDtoRequest));
                    } //залогировать и обработать!
                }
        );
        return authDtoResponseList;
    }

    /**
     * Десереализация объекта AuthDtoForResponse
     * @param json ответ от сервера
     * @return полученый объект AuthDtoForResponse  из json
     */
    @Nullable
    private AuthDtoResponse getAuthDtoForResponseFromJson(@NonNull String json,
                                                          AuthDtoRequest authDtoRequest) {
        try {
            objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
            authDtoResponse = objectMapper.readValue(json, AuthDtoResponse.class);
            authDtoResponse.setLogin(authDtoRequest.getLogin());
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
    private ResponseEntity<String> createHttpEntity(AuthDtoRequest authDtoRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String json = getJsonFromAuthDtoRequestObject(authDtoRequest);
        ResponseEntity<String> httpResponseEntity =
                     new ResponseEntity(json,headers,200);

        return httpResponseEntity;
    }

    /**
     * Преобразует объект AuthDtoForRequest в Json
     * @return Json as String
     */
    @Nullable
    private String getJsonFromAuthDtoRequestObject(AuthDtoRequest authDtoRequest) {
        try {
            return objectMapper.writeValueAsString(authDtoRequest);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
