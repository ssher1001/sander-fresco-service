pipeline {
    agent any

    tools {
        jdk 'java17'
        maven 'maven'
    }

    environment {
        IMAGE_NAME = 'sumit193/sander-fresco-service'
        IMAGE_TAG = 'latest'
       SONAR_SCANNER_HOME = tool 'sonarqube' // Match the name from Global Tool Config

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
                    withSonarQubeEnv('SonarQube_server') { // Match the name from Configure System
                    bat "${SONAR_SCANNER_HOME}\\bin\\sonar-scanner"            }
        }
        }

            //stage('Quality Gate') {
            //steps {
              //  timeout(time: 5, unit: 'MINUTES') {
                //    waitForQualityGate abortPipeline: true
              //  }
          //  }
       // }

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


        stage('Run Docker Image on appcontainer') {
    steps {
        echo "Running Docker image on container 'appcontainer'..."

        // Stop and remove existing container (if any)
        //bat 'docker stop appcontainer || echo "Container appcontainer not running"'
        //bat 'docker rm appcontainer || echo "Container appcontainer not found"'

        // Run the container with port mapping (host:container)
        bat "docker run -d -p 8081:8080 --name appcontainer %IMAGE_NAME%:%IMAGE_TAG%"

        echo "Container 'appcontainer' is now running and port 8080 is exposed."
    }
}

        stage('Pipeline Finished') {
            steps {
                echo 'Pipeline successfully completed.'
            }
        }
    }
}
