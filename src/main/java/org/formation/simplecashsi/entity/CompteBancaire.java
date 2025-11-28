package org.formation.simplecashsi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE_COMPTE")
@Data
@NoArgsConstructor
public abstract class CompteBancaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroCompte;

    private BigDecimal solde = BigDecimal.ZERO;
    private LocalDate dateOuverture = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    public CompteBancaire(BigDecimal solde, Client client) {
        this.solde = solde;
        this.dateOuverture = LocalDate.now();
        this.client = client;
    }

    public abstract void retirer(BigDecimal montant);
    public abstract void deposer(BigDecimal montant);
}