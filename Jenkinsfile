pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        withAnt(installation: 'ant', jdk: 'java8') {
          bat(script: 'createWar.cmd', returnStatus: true, returnStdout: true)
          withAnt()
        }

      }
    }

    stage('Tests') {
      steps {
        sh 'sh agent.sh -s {$WORKSPACE} -n Chess'
      }
    }

  }
}