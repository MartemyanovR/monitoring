package ru.mart.ofd.service;

import ru.mart.ofd.model.entityModel.Client;
import ru.mart.ofd.model.ofdRuModel.AuthDtoForRequest;

import java.util.List;

public interface ClientService {
    List<AuthDtoForRequest> getAllClientAndConvertInAuthDtoForRequest();
    Client findById(Long id);
    List<Client> saveUpdateClient(Client client);

}
