package ru.mart.ofd.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.mart.ofd.repository.ClientRepository;

public class OfdRuService {

    @Autowired
    private ClientRepository clientRepo;
    @Autowired
    private OfdRuClient client;

}
