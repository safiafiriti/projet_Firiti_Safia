package org.formation.simplecashsi.service;
import org.formation.simplecashsi.dto.VirementRequestDto;

public interface TransactionService {
    void effectuerVirement(VirementRequestDto request);
}
