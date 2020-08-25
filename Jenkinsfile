pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        bat(script: 'createWar.cmd', returnStatus: true, returnStdout: true)
      }
    }
        stage('sast') {
          steps {
            bat(script: 'agent.cmd -s . -n Chess -l \"from jenkins pipeline\" -as completeDelivery ignore=insights', returnStatus: true, returnStdout: true)
          }
        }
        stage('sca') {
          steps {
            bat(script: 'agent.cmd -s . -n Chess -l \"from jenkins pipeline\" -as completeDelivery ignore=rules,metrics,clones', returnStatus: true, returnStdout: true)
          }
        }
  }
}