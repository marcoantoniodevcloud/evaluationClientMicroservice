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
    private ClientRepository clienteRepository;

    public List<Client> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Client> getClienteByRfc(String rfc) {
        return clienteRepository.findByRfc(rfc);
    }

    public Client createOrUpdateCliente(Client cliente) {
        return clienteRepository.save(cliente);
    }
}
