pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        bat(script: 'createWar.cmd', returnStatus: true, returnStdout: true)
      }
    }

    stage('Tests') {
      steps {
        bat(script: 'kiuwan.cmd', returnStatus: true, returnStdout: true)
      }
    }

  }
}