# Étape 1 : Utiliser une image de base
FROM openjdk:17-jdk-slim

# Étape 2 : Installer les dépendances nécessaires
RUN apt-get update && \
    apt-get install -y git curl && \
    rm -rf /var/lib/apt/lists/*

# Étape 3 : Copier les fichiers nécessaires
# Copiez votre projet (ex. application Jenkins) dans le conteneur
COPY . /usr/src/app

# Étape 4 : Exposer les ports nécessaires
# Par défaut, Jenkins utilise le port 8080
EXPOSE 8080

# Étape 5 : Ajouter un point d'entrée
# Si c'est une application Jenkins ou Java :
CMD ["java", "-jar", "target/maska_hunters_league-0.0.1-SNAPSHOT.jar"]