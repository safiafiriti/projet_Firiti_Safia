package org.formation.simplecashsi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.formation.simplecashsi.dto.VirementRequestDto;
import org.formation.simplecashsi.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transfers")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void executeTransfer(@RequestBody @Valid VirementRequestDto request) {

        transactionService.effectuerVirement(request);
    }
}
