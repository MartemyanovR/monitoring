package ru.mart.ofd.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.jdbc.JdbcTestUtils;
import ru.mart.ofd.model.entityModel.Client;
import ru.mart.ofd.model.ofdRuModel.AuthDtoRequest;
import ru.mart.ofd.repository.ClientRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class ClientServiceImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ClientRepository clientRepository;

    private  List<Client> clientList;
    private final Client genericClient_1 = new Client(1L,"111@mail.ru","123","f4s6d5f46sd4free6e", LocalDateTime.now(), null);


    @BeforeEach
    void setUp() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Client");

        clientList = Stream.of(
                genericClient_1 ,
                new Client(2L,"222@mail.ru","123","f5ds46f54sd6eee4ss", LocalDateTime.now(), null),
                new Client(3L,"333@mail.ru","123","f5d46fe46sd4f6d65e", LocalDateTime.now(), null)).
                collect(Collectors.toList());

        clientRepository.saveAll(clientList);
    }

    @AfterEach
    void tearDown() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Client");
    }

    @Test
    public void givenClient_whenSave_thenGetOk() {

    }

    @Test
    void getAllClientAndConvertInAuthDtoForRequest() {
        List<Client> clientList = (List<Client>)clientRepository.findAll();
        List<AuthDtoRequest> authDtoRequestList = clientList.stream().
                map(client -> (new AuthDtoRequest(client.getLogin(),client.getPassword()))).
                collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
        List<AuthDtoRequest> expectedAuthDtoRequest = Stream.of(
                                            new AuthDtoRequest("111@mail.ru","123"),
                                            new AuthDtoRequest("222@mail.ru","123"),
                                            new AuthDtoRequest("333@mail.ru","123")).
                                            collect(Collectors.toList());
        assertThat(authDtoRequestList,is(expectedAuthDtoRequest));
        assertThat(authDtoRequestList,hasSize(3));
        assertThat(authDtoRequestList, hasItem(new AuthDtoRequest("111@mail.ru","123")));
        assertThat(authDtoRequestList, contains(expectedAuthDtoRequest.get(0),
                                                   expectedAuthDtoRequest.get(1),
                                                   expectedAuthDtoRequest.get(2)));
    }
}