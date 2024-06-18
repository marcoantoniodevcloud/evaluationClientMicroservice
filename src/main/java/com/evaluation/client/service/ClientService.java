package com.evaluation.client.service;

import com.evaluation.client.model.Client;
import com.evaluation.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RedisTemplate<String, Client> redisTemplate;

    public Page<Client> getAllClients(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return clientRepository.findAll(pageable);
    }

    public Optional<Client> getClientByRfc(String rfc) {
         Client client = redisTemplate.opsForValue().get(rfc);
         if (client != null) {
             System.out.println("Client found in cache: " + client);
             return Optional.of(client);
         }
 
         Optional<Client> optionalClient = clientRepository.findByRfc(rfc);
         optionalClient.ifPresent(c -> {
             redisTemplate.opsForValue().set(rfc, c);
             redisTemplate.expire(rfc, 5, java.util.concurrent.TimeUnit.MINUTES);
         });
 
         return optionalClient;
    }

    public Client createOrUpdateClient(Client client) {
        return clientRepository.save(client);
    }
}
