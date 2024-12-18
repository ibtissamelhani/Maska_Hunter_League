pipeline {
    agent any

    environment {
        SONARQUBE_URL = 'http://sonarqube:9000'
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
                                 -Dsonar.projectKey=Maska_Hunter_League \
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
                    // Wait for the Quality Gate result and fail if not passed d
                    waitForQualityGate abortPipeline: true, sonarQubeServerUrl: 'http://sonarqube:9000'
                }
            }
        }
    }
}
