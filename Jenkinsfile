pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        bat(script: 'createWar.cmd', returnStatus: true, returnStdout: true)
      }
    }

    stage('Tests') {
      parallel {
        stage('KLA_Local') {
          steps {
            bat(script: 'kiuwan.cmd', returnStatus: true, returnStdout: true)
          }
        }

        stage('Test_KLA_Docker_No_Engine') {
          steps {
            bat(script: 'kla_docker_no_engine.cmd', returnStatus: true, returnStdout: true)
          }
        }

      }
    }

  }
}