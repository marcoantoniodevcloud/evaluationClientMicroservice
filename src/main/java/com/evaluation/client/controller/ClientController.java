package com.evaluation.client.controller;

import com.evaluation.client.model.Client;
import com.evaluation.client.service.ClientService;
import com.evaluation.client.utils.Constants;
import com.evaluation.client.utils.Encryption;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clients")
@Tag(name = "API Client", description = "All the endpoints from client Service")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    Encryption encryption;

    @Operation(summary = "List All clients")
    @GetMapping
    public ResponseEntity<Page<Client>> getAllClients(
            HttpServletRequest request,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (!isValidDataHeader(request)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(clientService.getAllClients(page, size), HttpStatus.OK);
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
