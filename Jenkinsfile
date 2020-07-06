pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'sh ant -f build.xml war'
      }
    }

  }
}