package org.formation.simplecashsi.dto;

import jakarta.validation.constraints.Pattern;

public record ClientUpdateDto(

        String nom,
        String prenom,
        String adresse,

        @Pattern(regexp = "\\d{5}", message = "Zip code must contain 5 digits if provided")
        String codePostal,

        String ville,

        @Pattern(regexp = "^\\d{10}$", message = "Phone number must contain 10 digits if provided")
        String telephone

) { }
