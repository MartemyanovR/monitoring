package ru.mart.ofd.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.mart.ofd.model.entityModel.Client;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query(value = "SELECT login, password FROM Client")
    List<Object[]> getAllClientOnlyWithLoginAndPassword();

    Client findClientByLogin(String login);

    @Transactional
    @Modifying
    @Query(value = "update Client c set c.authToken = :token , c.dataUtc = :data " +
            "where c.login = :login")
    void updateClientsDataGetOnTheWebService(@Param("token") String token,
                                             @Param("data") LocalDateTime data, @Param("login")  String login);


}
