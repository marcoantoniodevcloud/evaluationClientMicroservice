package com.evaluation.client.service;

import com.evaluation.client.model.Client;
import com.evaluation.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Page<Client> getAllClients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clientRepository.findAll(pageable);
    }

    public Optional<Client> getClientByRfc(String rfc) {
        return clientRepository.findByRfc(rfc);
    }

    public Client createOrUpdateClient(Client client) {
        return clientRepository.save(client);
    }
}
