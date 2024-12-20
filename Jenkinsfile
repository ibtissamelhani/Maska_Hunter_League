pipeline {
    agent {
            docker {
                image 'maven:3.8.8-eclipse-temurin-17'
                args '-v /var/run/docker.sock:/var/run/docker.sock'
            }
    }

    environment {
        SONARQUBE_URL = 'http://sonarqube:9000'
        SONAR_PROJECT_KEY='Maska_Hunter_League'
        SONAR_TOKEN = credentials('sonar-token')  // Make sure to use your credential for SonarQube token
        IMAGE_NAME = 'maska_hunters_league'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url:'https://github.com/ibtissamelhani/Maska_Hunter_League.git'
            }
        }

        stage('Build') {
            steps {
                sh '''
                    mvn clean package -DskipTests --batch-mode --errors
                '''
            }
        }

        stage('Unit Tests') {
                    steps {
                        sh 'mvn test --batch-mode'
                    }
                    post {
                        always {
                            junit '**/target/surefire-reports/*.xml'
                            jacoco(
                                execPattern: '**/target/*.exec',
                                classPattern: '**/target/classes',
                                sourcePattern: '**/src/main/java'
                            )
                        }
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

        stage('Build Docker Image') {
                   steps {
                        script {
                            sh 'docker build -t maska_hunters_league:latest .'
                            }
                   }
        }


    }
}
