package ru.mart.ofd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mart.ofd.model.entityModel.Organization;

@Repository
public interface OrganizationRepository extends CrudRepository<Organization, Long> {

}
