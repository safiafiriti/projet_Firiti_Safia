package org.formation.simplecashsi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conseiller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @OneToMany(mappedBy = "conseiller", fetch = FetchType.LAZY)
    private List<Client> clients = new ArrayList<>();

    public Conseiller(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public boolean isFull() {
        return this.clients.size() >= 10;
    }
}