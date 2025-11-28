package org.formation.simplecashsi.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class CompteCourant extends CompteBancaire {
    private BigDecimal decouvertAutorise = new BigDecimal("1000.00");

    public CompteCourant(BigDecimal solde, Client client) {
        super(solde, client);
    }

    @Override
    public void retirer(BigDecimal montant) {
        BigDecimal limiteRetrait = getSolde().add(decouvertAutorise);

        if (limiteRetrait.compareTo(montant) < 0) {
            throw new IllegalArgumentException("Exceeds authorized overdraft limit.");
        }
        setSolde(getSolde().subtract(montant));
    }

    @Override
    public void deposer(BigDecimal montant) {
        setSolde(getSolde().add(montant));
    }
}
