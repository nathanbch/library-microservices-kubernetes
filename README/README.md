# Library Microservices -- Docker & Kubernetes

## 1. Description du projet

Ce projet met en œuvre une architecture microservices pour la gestion
d'une bibliothèque, composée de :

-   **BookService** : gestion des livres (CRUD, disponibilité)
-   **UserService** : gestion des emprunts
-   **APIGateway** : point d'entrée unique basé sur Spring Cloud
    Gateway

L'ensemble est conteneurisé avec **Docker** et orchestré avec
**Kubernetes (Docker Desktop)**.

Architecture :

Client -\> NodePort (30082) -\> API Gateway -\> BookService /
UserService

------------------------------------------------------------------------

## 2. Technologies utilisées

-   Java 21
-   Spring Boot 3.2.1
-   Spring Data JPA
-   Spring Cloud Gateway
-   H2 Database
-   Docker
-   Docker Compose
-   Kubernetes (Docker Desktop)
-   kubectl

------------------------------------------------------------------------

## 3. Lancement avec Docker Compose

``` bash
docker compose build
docker compose up
```

Accès via : http://localhost:8082

------------------------------------------------------------------------

## 4. Déploiement Kubernetes

### 4.1 Démarrage du cluster

``` bash
kubectl get nodes
```

### 4.2 Déploiement des services

``` bash
kubectl apply -f k8s/
kubectl get pods
kubectl get services
```

L'API Gateway est exposée via un **NodePort 30082**.

------------------------------------------------------------------------

## 5. Tests fonctionnels via curl

### Liste des livres

``` bash
curl http://localhost:30082/books
```

### Emprunt d'un livre

``` bash
curl -X POST "http://localhost:30082/borrow?userId=1&bookId=1&days=10"
```

### Vérification de la réservation

``` bash
curl http://localhost:30082/books/1
```

### Retour du livre

``` bash
curl -X DELETE http://localhost:30082/borrow/1
curl http://localhost:30082/books/1
```

### Création d'un nouveau livre

``` bash
curl -X POST http://localhost:30082/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Clean Code","author":"Robert Martin","isbn":"ISBN4","available":true,"maxBorrowDays":21}'
```

------------------------------------------------------------------------

## 6. Conclusion

Ce projet démontre la mise en œuvre complète :

-   Architecture microservices
-   Conteneurisation Docker
-   Orchestration Kubernetes
-   API Gateway
-   Communication inter-services
-   Validation par appels REST (curl)

Des captures d'écran des commandes `kubectl` et `curl` sont fournies
dans le dossier `screens/` pour prouver le bon fonctionnement.

---

## Auteur

Réalisé par : Nathan Bouché  
GitHub : https://github.com/nathanbch
