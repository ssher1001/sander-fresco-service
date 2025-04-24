pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        jdk 'java17'
        maven "maven"
    }
    
     environment {
         IMAGE_NAME = 'sumit193/sander-fresco-service'
        IMAGE_TAG = "latest"
    }

stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', changelog: false, poll: false, url: 'https://github.com/ssher1001/sander-fresco-service.git'

            }
        }

        stage('Compile') {
            steps {
                echo 'Code Comile'
                bat 'mvn clean compile'

            }

        }
        
        stage('Code review') {
            steps {
                    echo 'Code Review'
                
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
               
            bat 'docker build -t sumit193/sander-fresco-service:latest .'

                withCredentials([usernamePassword(credentialsId: 'my-docker-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
            bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
            bat "docker push sumit193/sander-fresco-service:latest"
        }

            }
        }
        
        stage('Push Docker Image') {
            steps {
               
            echo "Docker build completed. Logging into Docker Hub..."

            withCredentials([usernamePassword(credentialsId: 'my-docker-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
            bat 'docker login -u %DOCKER_USER% -p %DOCKER_PASS%'
            
            echo "Login successful. Pushing image..."
            bat "docker push sumit193/sander-fresco-service:latest"
        }
            echo "Docker image push complete."

            }
        }
        
    }
}
