package org.formation.simplecashsi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.formation.simplecashsi.dto.ClientUpdateDto;
import org.springframework.http.HttpStatus;
import org.formation.simplecashsi.dto.ClientCreateDto;
import org.formation.simplecashsi.dto.ClientDto;
import org.springframework.web.server.ResponseStatusException;
import org.formation.simplecashsi.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    List<ClientDto> getClients() { return clientService.findAll(); }

    @GetMapping("{id}")
    ResponseEntity<ClientDto> getClient(@PathVariable Long id) {
        return clientService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    ClientDto save(@RequestBody @Valid ClientCreateDto clientDto) {return clientService.save(clientDto); }

    @PatchMapping("{id}")
    ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody @Valid ClientUpdateDto dto){
        return clientService.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable Long id) {
        if (clientService.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client to delete not found with ID: " + id);
        }
        clientService.deleteById(id);
    }
}
