pipeline {
    agent any

    environment {
        SONARQUBE_URL = 'http://localhost:9000'
        SONAR_TOKEN = credentials('sonar-token')  // Make sure to use your credential for SonarQube token
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-repo-url.git'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv('SonarQube') {
                        sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=Maska_Hunter_League -Dsonar.projectName="Maska_Hunter_League" -Dsonar.host.url=$SONARQUBE_URL -Dsonar.login="        <sonar.login>sqp_71009f8e9236235065247a5b3a2fbee03d15a8fc</sonar.login>
"'
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    // Wait for the Quality Gate result and fail if not passed
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}
