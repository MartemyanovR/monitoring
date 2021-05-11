package ru.mart.ofd.service;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mart.ofd.model.entityModel.Client;
import ru.mart.ofd.model.ofdRuModel.AuthDtoRequest;
import ru.mart.ofd.model.ofdRuModel.AuthDtoResponse;
import ru.mart.ofd.repository.ClientRepository;

import java.time.LocalDateTime;
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
    public List<AuthDtoRequest> getAllClientAndConvertInAuthDtoForRequest() {
        List<Client> clientList = (List<Client>)clientRepo.findAll();
        List<AuthDtoRequest> authDtoRequestSet = clientList.stream().
                map(client -> (new AuthDtoRequest(client.getLogin(),client.getPassword()))).
                collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
        return authDtoRequestSet;
    }

    /**
     *Получаеем из метода "useExchangeMethodForAuthDto"
     *список с обьектами типа AuthDtoResponse, далее проходим
     * по данному списку и обновляем таблицу Client
     */
    @Override
    public void saveUpdateClient() {
        List<AuthDtoResponse> authDtoResponseList = client.useExchangeMethodForAuthDto();
        authDtoResponseList.stream().forEach(auth -> clientRepo.
                updateClientsDataGetOnTheWebService(auth.getAuthToken(),
                        LocalDateTime.parse(auth.getExpirationDateUtc()), auth.getLogin()));
    }
}
