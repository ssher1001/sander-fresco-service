 pipeline {
        agent none
        stages {
          stage("build & SonarQube analysis") {
            agent any
            steps {
              withSonarQubeEnv('SonarQube_Server') {
                sh 'mvn clean package sonar:sonar'
              }
            }
          }
         
        }
      }
