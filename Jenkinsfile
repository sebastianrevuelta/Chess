pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        withAnt(installation: 'ant', jdk: 'java8')
      }
    }

    stage('Tests') {
      steps {
        sh 'sh agent.sh -s {$WORKSPACE} -n Chess'
      }
    }

  }
}