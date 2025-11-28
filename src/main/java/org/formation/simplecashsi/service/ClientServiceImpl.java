package org.formation.simplecashsi.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.formation.simplecashsi.dto.ClientUpdateDto;
import org.formation.simplecashsi.entity.CompteCourant;
import org.formation.simplecashsi.entity.CompteEpargne;
import org.formation.simplecashsi.entity.Conseiller;
import org.formation.simplecashsi.repository.CompteRepository;
import org.formation.simplecashsi.repository.ConseillerRepository;
import org.springframework.transaction.annotation.Transactional;
import org.formation.simplecashsi.dto.ClientCreateDto;
import org.formation.simplecashsi.dto.ClientDto;
import org.formation.simplecashsi.entity.Client;
import org.formation.simplecashsi.mapper.ClientMapper;
import org.formation.simplecashsi.repository.ClientRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CompteRepository compteRepository;
    private final ClientMapper clientMapper;
    private final ConseillerRepository conseillerRepository;

    @PostConstruct
    private void initdb(){
        Client clientA = new Client("Dupont", "Alice", "1 rue de la Paix", "75001", "Paris", "0611223344");

        clientRepository.save(clientA);

        CompteCourant cc1 = new CompteCourant(new BigDecimal("5000.00"), clientA);
        CompteEpargne ce2 = new CompteEpargne(new BigDecimal("1000.00"), clientA);

        compteRepository.saveAll(List.of(cc1, ce2));

        Conseiller conseillerPrincipal = new Conseiller("Boutgayout", "Jean");

        conseillerRepository.save(conseillerPrincipal);

        clientA.setConseiller(conseillerPrincipal);
        clientA.setComptes(List.of(cc1, ce2));
        clientRepository.save(clientA);
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

        Conseiller conseillerDisponible = conseillerRepository.findAll().stream()
                .filter(c -> !c.isFull())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Cannot create client: No available advisor (limit reached)."));

        Client client = clientMapper.toEntity(clientDto);
        client.setConseiller(conseillerDisponible);

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
