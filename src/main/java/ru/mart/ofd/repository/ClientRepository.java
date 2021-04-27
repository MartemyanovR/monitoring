package ru.mart.ofd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mart.ofd.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
}
