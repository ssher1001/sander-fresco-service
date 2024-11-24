 pipeline {
        agent none
        stages {
          stage("build & SonarQube analysis") {
            agent any
            steps {
              withSonarQubeEnv('SonarQube_Server') {
               sh "echo before start"
                sh 'mvn clean package sonar:sonar'
                              sh "echo after start"

              }
            }
          }
         
        }
      }
