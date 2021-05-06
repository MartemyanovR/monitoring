package ru.mart.ofd.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mart.ofd.model.entityModel.Client;

import java.util.List;
import java.util.Set;

public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query(value = "SELECT login, password FROM Client")
    List<Object[]> getAllClientOnlyWithLoginAndPassword();

}
