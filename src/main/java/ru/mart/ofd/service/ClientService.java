package ru.mart.ofd.service;

import ru.mart.ofd.model.entityModel.Client;
import ru.mart.ofd.model.ofdRuModel.AuthDtoRequest;
import ru.mart.ofd.model.ofdRuModel.AuthDtoResponse;

import java.util.List;
import java.util.Set;

public interface ClientService {
    List<AuthDtoRequest> getAllClientAndConvertInAuthDtoForRequest();
    void saveUpdateClient();

}
