package com.evaluation.client.service;

import com.evaluation.client.model.Client;
import com.evaluation.client.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    public ClientServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetClienteByRfc() {
        Client client = new Client();
        client.setRfc("TESTRFC");
        when(clientRepository.findByRfc("TESTRFC")).thenReturn(Optional.of(client));

        Optional<Client> found = clientService.getClientByRfc("TESTRFC");

        assertEquals("TESTRFC", found.get().getRfc());
    }

    @Test
    public void testCreateOrUpdateCliente() {
        Client client = new Client();
        client.setRfc("TESTRFC");
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        Client savedCliente = clientService.createOrUpdateClient(client);

        assertEquals("TESTRFC", savedCliente.getRfc());
    }
}