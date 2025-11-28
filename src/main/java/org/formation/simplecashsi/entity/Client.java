package org.formation.simplecashsi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String telephone;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompteBancaire> comptes = new ArrayList<>();

    public Client(String nom, String prenom, String adresse, String codePostal, String ville, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.telephone = telephone;
    }
}