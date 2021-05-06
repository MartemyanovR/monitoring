package ru.mart.ofd.service;

import com.google.common.collect.ImmutableSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mart.ofd.model.entityModel.Client;
import ru.mart.ofd.model.ofdRuModel.AuthDtoForRequest;
import ru.mart.ofd.repository.ClientRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
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
    public Set<AuthDtoForRequest> getAllClientAndConvertInAuthDtoForRequest() {
        Set<Client> clientList = (Set<Client>)clientRepo.findAll();
        Set<AuthDtoForRequest> authDtoForRequestSet = clientList.stream().
                map(client -> (new AuthDtoForRequest(client.getLogin(),client.getPassword()))).
                collect(Collectors.collectingAndThen(Collectors.toSet(), ImmutableSet::copyOf));
        return authDtoForRequestSet;
    }

    /**
     *
     * @param client
     * @return
     */
    @Override
    public List<Client> saveUpdateClient(Client client) {
        return null;
    }
}
