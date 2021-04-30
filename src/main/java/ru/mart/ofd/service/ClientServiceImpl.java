package ru.mart.ofd.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mart.ofd.model.entityModel.Client;
import ru.mart.ofd.model.ofdRuModel.AuthDtoForRequest;
import ru.mart.ofd.repository.ClientRepository;

import java.util.List;

public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepo;
    @Autowired
    private OfdRuClient client;

    /**
     * Получаем из таблицы Client все cтроки и преобразуем
     * в коллекцию AuthDtoForRequest объектов
     * @return ArrayList<AuthDtoForRequest>
     */
    @Override
    public List<AuthDtoForRequest> getAllClientAndConvertInAuthDtoForRequest() {
        List<Client> clients = (List<Client>)clientRepo.findAll();
        return null;
    }

    @Override
    public Client findById(Long id) {
        return null;
    }

    @Override
    public List<Client> saveUpdateClient(Client client) {
        return null;
    }
}
