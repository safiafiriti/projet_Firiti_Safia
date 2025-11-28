package org.formation.simplecashsi.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record VirementRequestDto(
        @NotNull(message = "The debit account ID is mandatory")
        Long compteDebiteurId,

        @NotNull(message = "The credit account ID is mandatory")
        Long compteCrediteurId,

        @NotNull(message = "he amount is mandatory")
        @Min(value = 1, message = "The amount must be greater than zero")
        BigDecimal montant
) { }
