package org.formation.simplecashsi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClientCreateDto(
        @NotBlank(message = "Client name cannot be empty")
        String nom,
        @NotBlank(message = "Client first name cannot be empty")
        String prenom,
        @NotBlank(message = "Client address cannot be empty")
        String adresse,
        @Pattern(regexp = "\\d{5}", message = "Zip code must contain 5 digits")
        String codePostal,
        @NotBlank(message = "Client city  cannot be empty")
        String ville,
        @Pattern(regexp = "^\\d{10}$", message = "Phone number must contain 10 digits")
        String telephone
        ) {}
