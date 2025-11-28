package org.formation.simplecashsi.service;

import org.formation.simplecashsi.dto.ClientCreateDto;
import org.formation.simplecashsi.dto.ClientDto;
import org.formation.simplecashsi.dto.ClientUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    List<ClientDto> findAll();

    Optional<ClientDto> findById(Long id);

    ClientDto save(ClientCreateDto clientDto);

    void deleteById(Long id);

    Optional<ClientDto> update(Long id, ClientUpdateDto clientDto);
}
