package org.formation.simplecashsi.service;

import lombok.RequiredArgsConstructor;
import org.formation.simplecashsi.entity.CompteBancaire;
import org.formation.simplecashsi.repository.CompteRepository;
import org.springframework.stereotype.Service;
import org.formation.simplecashsi.dto.VirementRequestDto;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final CompteRepository compteRepository;

    @Override
    @Transactional
    public void effectuerVirement(VirementRequestDto request) {

        CompteBancaire compteDebiteur = compteRepository.findById(request.compteDebiteurId())
                .orElseThrow(() -> new IllegalArgumentException("Source account not found."));

        CompteBancaire compteCrediteur = compteRepository.findById(request.compteCrediteurId())
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found."));

        if (compteDebiteur.getNumeroCompte().equals(compteCrediteur.getNumeroCompte())) {
            throw new IllegalArgumentException("Cannot transfer money to the same account.");
        }

        try {
            compteDebiteur.retirer(request.montant());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Transfer failed: " + e.getMessage());
        }
        compteCrediteur.deposer(request.montant());

        compteRepository.save(compteDebiteur);
        compteRepository.save(compteCrediteur);

    }
}
