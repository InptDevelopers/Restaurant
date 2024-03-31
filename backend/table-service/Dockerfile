# utilisation de l'image maven pour la construction
FROM maven AS build

# Définir le répertoire de travaille
WORKDIR /app/myapp

# Copie du code source dans l'image de construction
COPY . .

# Construction du projet avec Maven
RUN mvn clean package -DskipTests

# Image pour ranner le code java
FROM openjdk:17-alpine

# Définitir la répertoire de travaille
WORKDIR /app

EXPOSE 8081

# Copie du fichier JAR généré depuis l'étape de construction
COPY --from=build /app/myapp/target/spring-boot-data-jpa*.jar app.jar

# Commande à exécuter lors du démarrage du conteneur
ENTRYPOINT ["java","-jar","app.jar"]