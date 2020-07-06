pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'sh ant -f build.xml war'
      }
    }

    stage('Tests') {
      steps {
        sh 'sh agent.sh -s {$WORKSPACE} -n Chess'
      }
    }

  }
}