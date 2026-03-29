# Chatop

Application web de gestion de locations de maisons. Ce projet comprend une API RESTful (Back-end en Spring Boot) et une application cliente (Front-end en Angular).

## Sommaire

- [Prérequis](#Prérequis)
- [Installation](#installation)
- [Structure du projet](#structure-du-projet)

## Prérequis

Avant de commencer, assurez-vous d'avoir installé les outils suivants sur votre machine :
- Java
- Maven
- Node.js et npm
- Angular CLI
- MySQL

## Installation

### Base de données

Executez le  script SQL se trouvant ici : `src/main/resources/script.sql`, afin d'initialiser la base de données avec la bonne structure.

Ensuite, créez un utilisateur avec des permissions limitées :
```sql
CREATE DATABASE chatop_db;
CREATE USER 'user'@'localhost' IDENTIFIED BY 'mot_de_passe';
GRANT ALL PRIVILEGES ON chatop.* TO 'user'@'localhost';
FLUSH PRIVILEGES;
```

### Back-end

Modifiez le fichier `src/main/resources/application.properties` afin d'y inclure vos identifiants de connexion à la base de données et votre clé secrète JWT.

Voici un exemple :
```properties
# Configuration du serveur
spring.application.name=chatop
server.port=3001

# Configuration des logs
logging.level.root=ERROR
logging.level.com.chatop=INFO
logging.level.org.springframework.boot.web.embedded.tomcat=INFO

# Configuration de la base de données MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/chatop_db
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=user
spring.datasource.password=mot_de_passe

# Configuration JPA
spring.jpa.show-sql=true

# Configuration JWT
security.jwt.secret-key=clé_secrète_JWT
```

Lancez ensuite le back-end : 
```
mvn clean install
mvn spring-boot:run
```

### Front-end

Clonez le dépôt du front-end qui se trouve ici : https://github.com/OpenClassrooms-Student-Center/Mod-lisez-et-impl-mentez-le-back-end-en-utilisant-du-code-Java-maintenable

Accédez au dossier du projet que vous venez de cloner et installez les dépendances Node :
``` 
npm install 
```

Et démarrez l'application Angular :
```
npm start
```

## Structure du Back-end

```text
src/main/java/com/chatop
    ├── configuration/
    ├── controller/
    ├── DTO/
    ├── model/
    ├── repository/
    ├── service/
```

`configuration` : Fichiers de paramétrage pour Spring Security, OpenAPI (Swagger) et la gestion globale des exceptions de l'API.

`controller` : Fichiers qui gèrent les requêtes HTTP faites à l'API.  

`DTO` : Data Transfer Objects (DTO) utilisés pour formater les données afin qu'elles correspondent aux attentes des différentes requêtes HTTP.

`model` : Entités représentant la structure des tables de la base de données.

`repository` : Interfaces héritant de Spring Data JPA qui permet d'intéragir avec la base de données.

`service` : La logique métier et le lien entre controller et repository/