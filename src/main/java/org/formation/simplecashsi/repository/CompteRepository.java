package org.formation.simplecashsi.repository;

import org.formation.simplecashsi.entity.CompteBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompteRepository extends JpaRepository<CompteBancaire, Long> {

    Optional<CompteBancaire> findByNumeroCompte(Long numeroCompte);
}