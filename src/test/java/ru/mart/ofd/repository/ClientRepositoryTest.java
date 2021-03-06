package ru.mart.ofd.repository;

import org.h2.tools.Script;
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

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ClientRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ClientRepository clientRepository;

    private List<Client> clientList;

    @BeforeEach
    void setUp() throws SQLException {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Client");
        clientList = Stream.of(
                new Client(1L,"111@mail.ru","123","f4s6d5f46sd4free6e", LocalDateTime.now(), null),
                new Client(2L,"222@mail.ru","123","f5ds46f54sd6eee4ss", LocalDateTime.now(), null),
                new Client(3L,"333@mail.ru","123","f5d46fe46sd4f6d65e", LocalDateTime.now(), null)).
                collect(Collectors.toList());

        clientRepository.saveAll(clientList);
    }

    @AfterEach
    void tearDown() throws SQLException {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Client");
    }

    @Test
    void compareCountRowsInClientTable() {
        String login = "111@mail.ru";
        String passwrd = "123";

        assertEquals(1, JdbcTestUtils.countRowsInTableWhere(
                jdbcTemplate,"Client","login = '" + login + "'"));
        assertEquals(3, JdbcTestUtils.countRowsInTableWhere(
                jdbcTemplate,"Client","passwrd = '" + passwrd + "'"));
    }

    @Test
    public void givenClientRepository_whenSaveAndRetreiveClient_thenOK() {
        Client genericClient_4 = new Client(4L,"444@mail.ru","123","f5d46fe46sd8f6d65e", LocalDateTime.now(), null);
        Client expectedClient = clientRepository
                .save(genericClient_4);
        Client foundClient = clientRepository.findById(expectedClient.getId()).get();

        assertNotNull(foundClient);
        assertEquals(expectedClient.getId(), foundClient.getId());
    }

    @Test
    public void giveListClients_whenListContainOnlyLoginAndPassword_thenOk() {

        List<Object[]> clients =  clientRepository.getAllClientOnlyWithLoginAndPassword();
        for(int i = 0; i < clients.size(); i++) {
            Object[] genericClient = clients.get(i);
            String login = (String) genericClient[0];
            String password = (String) genericClient[1];
            Client expectedClient =  clientList.get(i);
            assertEquals(expectedClient.getLogin(), login);
            assertEquals(expectedClient.getPassword(), password);
        }
    }

    @Test
    public void findClientByLoginAndUpdateTheTokenAndDate() {
        String updateToken = "dddfff444";
        LocalDateTime updateDate = LocalDateTime.parse("2017-01-24T14:44:21");
        String login = "111@mail.ru";
        String wrongLogin = "100@mail.ru";
        clientRepository.updateClientsDataGetOnTheWebService(updateToken, updateDate, wrongLogin);

        List<Client> clientList = (List<Client>) clientRepository.findAll();
        System.out.println(clientList);

        assertThat((List<Client>) clientRepository.findAll(), is(not(empty())));
        assertThat((List<Client>) clientRepository.findAll(), is(hasSize(3)));
        assertThat(clientRepository.findClientByLogin("111@mail.ru").getAuthToken(), is(equalTo(updateToken)));
        assertThat(clientRepository.findClientByLogin("111@mail.ru").getDataUtc(), is(equalTo(updateDate)));
        assertThat(clientRepository.findClientByLogin("111@mail.ru").getLogin(), is(not(equalTo(wrongLogin))));
    }

}