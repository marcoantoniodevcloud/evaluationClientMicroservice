package com.evaluation.client.service;

import com.evaluation.client.model.Client;
import com.evaluation.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientByRfc(String rfc) {
        return clientRepository.findByRfc(rfc);
    }

    public Client createOrUpdateClient(Client client) {
        return clientRepository.save(client);
    }
}
