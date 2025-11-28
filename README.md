# SimpleCashSI : Système d'Information Bancaire

## Architecture du Projet

## Endpoints et Tests Clés

L'application démarre et initialise la DB avec un Conseiller et des Comptes de test (`Dupont` possède Compte ID 1 et 2).

| Module | Méthode | Endpoint | Rôle | Tests |
| :--- | :--- | :--- | :--- | :--- |
| **Client** | `GET` | `/clients` | Liste les clients existants (vérifie `initdb`). | **200 OK** |
| **Client** | `POST` | `/clients` | Crée un nouveau client. | **201 Created / 400 Bad Request (Validation)** |
| **Virement** | `POST` | `/transfers` | Effectue un virement transactionnel. | **204 No Content** |
| **Virement** | `POST` | `/transfers` | Test d'échec (Solde Insuffisant). | **400 Bad Request (Rollback)** |

### Accès à la Console H2

Pour vérifier les soldes et les tables après exécution :

* **URL :** `http://localhost:8080/h2-console`
* **JDBC URL :** `jdbc:h2:mem:simplecashdb`

## Document de Conception

Le document d'analyse complet (UML, User Stories, Bilan Reste à faire) est disponible **ici** :

[Document de Conception Complet (Livrable 1)](https://docs.google.com/document/d/16Rfs3iJgkrR7H7oulP3qzAs6e5vD0FBfdOjnfj44K6c/edit?usp=sharing)