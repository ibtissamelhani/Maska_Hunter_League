# Dockerfile for Custom Jenkins
FROM jenkins/jenkins:lts

# Switch to root for installation
USER root

# mvn
RUN apt-get update && apt-get install -y \
    maven && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

# Install Docker Compose
RUN curl -SL https://github.com/docker/compose/releases/latest/download/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose \
    && chmod +x /usr/local/bin/docker-compose

# Add Jenkins user to Docker group for permissions
RUN groupadd docker && usermod -aG docker jenkins

# Environment setup for Jenkins to use Docker
ENV DOCKER_HOST=unix:///var/run/docker.sock

# Switch back to Jenkins user
USER jenkins