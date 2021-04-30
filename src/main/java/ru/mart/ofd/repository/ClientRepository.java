package ru.mart.ofd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mart.ofd.model.entityModel.Client;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findAllById();

}
