pipeline {
    agent any

    tools {
        jdk 'java17'
        maven 'maven'
    }

    environment {
        IMAGE_NAME = 'sumit193/sander-fresco-service'
        IMAGE_TAG = 'latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', changelog: false, poll: false, url: 'https://github.com/ssher1001/sander-fresco-service.git'
            }
        }

        stage('Compile') {
            steps {
                echo 'Code Compile'
                bat 'mvn clean compile'
            }
        }

        stage('Code Review') {
            steps {
                echo 'Code Review'
                // Add any code scanning tools here later
            }
        }

        stage('Packaging') {
            steps {
                echo 'Packaging the application using Maven on Windows'
                bat 'mvn clean package'
                bat 'echo Listing target directory:'
                bat 'dir target\\'
                archiveArtifacts artifacts: 'target\\*.jar', allowEmptyArchive: true
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker Image"
                bat "docker build -t %IMAGE_NAME%:%IMAGE_TAG% ."
            }
        }

        stage('Push Docker Image') {
            steps {
                echo "Logging into Docker Hub and pushing image..."

                withCredentials([usernamePassword(credentialsId: 'my-docker-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
                    bat(script: "docker push %IMAGE_NAME%:%IMAGE_TAG%", returnStatus: true)
                }

                echo "Docker image push complete."
            }
        }

        stage('Pipeline Finished') {
            steps {
                echo 'Pipeline successfully completed.'
            }
        }
    }
}
