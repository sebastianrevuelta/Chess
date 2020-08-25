pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        bat(script: 'createWar.cmd', returnStatus: true, returnStdout: true)
      }
    }
    stage('sast-sca') {
       steps {
         bat(script: 'agent.cmd -s . -n Chess -l \"from jenkins pipeline\" -as completeDelivery', returnStatus: true, returnStdout: true)
       }
    }
    stage('containers') {
       steps {
          bat(script: 'docker run --rm -i hadolint/hadolint < Dockerfile')
       }
    }
  }
}