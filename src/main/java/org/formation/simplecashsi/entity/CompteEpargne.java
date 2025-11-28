package org.formation.simplecashsi.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class CompteEpargne extends CompteBancaire {

    private Double tauxRemuneration = 0.03;

    public CompteEpargne(BigDecimal solde, Client client) {
        super(solde, client);
    }

    @Override
    public void retirer(BigDecimal montant) {
        if (getSolde().compareTo(montant) < 0) {
            throw new IllegalArgumentException("Insufficient balance on savings account");
        }
        setSolde(getSolde().subtract(montant));
    }

    @Override
    public void deposer(BigDecimal montant) {
        setSolde(getSolde().add(montant));
    }

    public void capitaliserInterets() {
        // after
    }
}