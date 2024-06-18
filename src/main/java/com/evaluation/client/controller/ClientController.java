package com.evaluation.client.controller;


import com.evaluation.client.model.Client;
import com.evaluation.client.service.ClientService;
import com.evaluation.client.utils.Constants;
import com.evaluation.client.utils.Encryption;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    Encryption encryption;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@Valid @RequestBody Client client, HttpServletRequest request) {
        if (!isValidDataHeader(request)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(clientService.createOrUpdateClient(client), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Client> updateclient(@Valid @RequestBody Client client, HttpServletRequest request) {
        if (!isValidDataHeader(request)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(clientService.createOrUpdateClient(client), HttpStatus.OK);
    }

    @GetMapping("/{rfc}")
    public ResponseEntity<Client> getclientByRfc(@PathVariable String rfc, HttpServletRequest request) {
        if (!isValidDataHeader(request)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<Client> client = clientService.getClientByRfc(rfc);
        return client.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private boolean isValidDataHeader(HttpServletRequest request) {
        String dataHeader = request.getHeader(Constants.HEADER_REQUEST);
        return dataHeader != null && dataHeader.equals(encryption.resolveSha256(Constants.VALUE_HEADER));
    }

    
}

