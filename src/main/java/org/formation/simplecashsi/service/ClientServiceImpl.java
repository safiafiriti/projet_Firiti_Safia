package org.formation.simplecashsi.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.formation.simplecashsi.dto.ClientUpdateDto;
import org.springframework.transaction.annotation.Transactional;
import org.formation.simplecashsi.dto.ClientCreateDto;
import org.formation.simplecashsi.dto.ClientDto;
import org.formation.simplecashsi.entity.Client;
import org.formation.simplecashsi.mapper.ClientMapper;
import org.formation.simplecashsi.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @PostConstruct
    private void initdb(){
        Client clientA = new Client("Dupont", "Alice", "1 rue de la Paix", "75001", "Paris", "0611223344");
        Client clientB = new Client("Martin", "Bernard", "25 avenue des Fleurs", "69007", "Lyon", "0710203040");

        clientRepository.saveAll(List.of(clientA, clientB));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDto> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClientDto> findById(Long id) {
        return clientRepository.findById(id).map(clientMapper::toDto);
    }

    @Override
    public ClientDto save(ClientCreateDto clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDto(savedClient);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Optional<ClientDto> update(Long id, ClientUpdateDto clientDto) {
        return clientRepository.findById(id).map(existingClient -> {

            clientMapper.updateEntity(existingClient, clientDto);

            Client updatedClient = clientRepository.save(existingClient);

            return clientMapper.toDto(updatedClient);
        });
    }
}
