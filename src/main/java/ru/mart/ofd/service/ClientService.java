package ru.mart.ofd.service;

import ru.mart.ofd.model.entityModel.Client;
import ru.mart.ofd.model.ofdRuModel.AuthDtoForRequest;

import java.util.List;
import java.util.Set;

public interface ClientService {
    Set<AuthDtoForRequest> getAllClientAndConvertInAuthDtoForRequest();
    List<Client> saveUpdateClient(Client client);

}
