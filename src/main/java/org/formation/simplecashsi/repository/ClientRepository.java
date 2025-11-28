package org.formation.simplecashsi.repository;

import org.formation.simplecashsi.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
