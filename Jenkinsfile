pipeline {
    agent any

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
                    mvn clean package -DskipTests --batch-mode --errors -Dmaven.repo.local=.m2/repository
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
                    try {
                         withSonarQubeEnv('sonarQube') {
                             sh '''
                                 mvn clean verify sonar:sonar \
                                     -Dsonar.projectKey=$SONAR_PROJECT_KEY \
                                     -Dsonar.projectName="Maska_Hunter_League" \
                                     -Dsonar.host.url=$SONARQUBE_URL \
                                     -Dsonar.login=$SONAR_TOKEN
                             '''
                         }
                    } catch (Exception e) {
                        error "SonarQube analysis failed: ${e.message}"
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
                            def version = sh(script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true).trim()
                            sh """
                                docker build -t ${IMAGE_NAME}:latest -t ${IMAGE_NAME}:${version} .
                            """
                        }
                   }
        }


    }
}
