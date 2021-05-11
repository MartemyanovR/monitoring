package ru.mart.ofd.model.ofdRuModel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;


class AuthDtoRequestTest {
    private AuthDtoRequest authDto;
    private ObjectMapper objectMapper;
    private String login;
    private String password;

    @BeforeEach
    void setUp() {
        login = "cto@retailservice24.ru";
        password = "~2-=@oa4";
        authDto = new AuthDtoRequest(login, password );
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getJsonFromObject() throws JsonProcessingException {
        String resultJson = objectMapper.writeValueAsString(authDto);
        String expectedJson = String.format("{\"login\":\"%s\",\"password\":\"%s\"}" , login,password);
        assertThat(expectedJson, is(resultJson));
        assertNotNull(resultJson);
    }

}