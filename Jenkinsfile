pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        withAnt(installation: 'ant', jdk: 'java8') {
          sh 'ant war'
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