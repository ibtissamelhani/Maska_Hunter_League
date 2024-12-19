pipeline {
    agent any

    environment {
        SONARQUBE_URL = 'http://sonarqube:9000'
        $SONAR_PROJECT_KEY='Maska_Hunter_League'
        SONAR_TOKEN = credentials('sonar-token')  // Make sure to use your credential for SonarQube token
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url:'https://github.com/ibtissamelhani/Maska_Hunter_League.git'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {

                    withSonarQubeEnv('sonarQube') {
                        sh '''
                              mvn clean verify sonar:sonar \
                                 -Dsonar.projectKey=$SONAR_PROJECT_KEY \
                                 -Dsonar.projectName="Maska_Hunter_League" \
                                 -Dsonar.host.url=$SONARQUBE_URL \
                                 -Dsonar.login=$SONAR_TOKEN
                           '''
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                 script {
                         def qualityGate = sh(
                             script: """
                             curl -s -u "$SONAR_TOKEN:" \
                             "$SONARQUBE_URL/api/qualitygates/project_status?projectKey=$SONAR_PROJECT_KEY" \
                             | jq -r '.projectStatus.status'
                             """,
                             returnStdout: true
                         ).trim()
                         if (qualityGate != "OK") {
                             error "Quality Gate failed! Stopping the build."
                         }
                 }
            }
        }
}
