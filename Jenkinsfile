pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        bat(script: 'createWar.cmd', returnStatus: true, returnStdout: true)
      }
    }

    stage('Security Testing') {
      parallel {
        stage('sast') {
          steps {
            bat(script: 'agent.cmd -s \"${WORKSPACE}\" -n Chess -l \"from jenkins pipeline\" -as completeDelivery ignore=insights', returnStatus: true, returnStdout: true)
          }
        }
        stage('sca') {
          steps {
            bat(script: 'agent.cmd -s \"${WORKSPACE}\" -n Chess -l \"from jenkins pipeline\" -as completeDelivery ignore=rules,metrics,clones', returnStatus: true, returnStdout: true)
          }
        }
        stage ("containers") {
          agent {
            docker {
              image 'hadolint/hadolint:latest-debian'
            }
           }
           steps {
             sh 'hadolint dockerfiles/* | tee -a hadolint_lint.txt'
           }
           post {
             always {
               archiveArtifacts 'hadolint_lint.txt'
             }
           }
        }
      }
    }
  }
}