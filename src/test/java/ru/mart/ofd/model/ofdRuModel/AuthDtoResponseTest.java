package ru.mart.ofd.model.ofdRuModel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AuthDtoResponseTest {
    @Mock
    private AuthDtoResponse mockedAuthDto;
    private AuthDtoResponse authDto;
    private ObjectMapper objectMapper;
    private String response;

    @BeforeEach
    void setUp() {
        response = "{\"AuthToken\":\"d8f2c2b3358f49fe866221d80f028f1b\",\"ExpirationDateUtc\":\"2021-04-30T13:30:23\"}";
        authDto = new AuthDtoResponse();
        objectMapper = new ObjectMapper();
        mockedAuthDto = new AuthDtoResponse("login","d8f2c2b3358f49fe866221d80f028f1b","2021-04-30T13:30:23");
    }

    @Test
    void getAuthDtoForResponseFromJson() throws JsonProcessingException {
        objectMapper.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        authDto = objectMapper.readValue(response, AuthDtoResponse.class);
        authDto.setLogin("login");
        System.out.println(authDto);

        assertNotNull(authDto);
        assertThat(mockedAuthDto, is(authDto));
    }

    @AfterEach
    void tearDown() {
    }

}