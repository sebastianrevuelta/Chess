pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        bat(script: 'createWar.cmd', returnStatus: true, returnStdout: true)
      }
    }

    stage ("containers") {
          agent {
            docker {
              image 'hadolint/hadolint:latest-alpine'
            }
           }
           steps {
             sh 'hadolint Dockerfile | tee -a hadolint_lint.txt'
          }
    }
  }
}