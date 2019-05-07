pipeline {
  agent any
  options {
    buildDiscarder(logRotator(numToKeepStr: '3'))
  }
  triggers {
    cron('0 0 * * *')
  }
  tools {
    maven '3.5.0'
    jdk 'JDK8'
  }
  environment {
    SERVER_NAME = 'octriapp01.acc.ohsu.edu'
    SERVER_PATH = '/opt/projects/clinchit/clinchit_dev'
    GIT_URL = 'git@github.com:OCTRI/clinch-it.git'
    GIT_CREDENTIALS = 'octri-github-ssh'
  }
  stages {
    stage('Prepare') {
      steps {
        deleteDir()
        checkoutBranch(env.GIT_URL, env.GIT_CREDENTIALS, 'master')
      }
    }
    stage('Build') {
      steps {
        sh '''
        mvn -DskipTests=true install
        '''
      }
    }
    stage('Deploy to octridev') {
      steps {
        dockerComposeDeploy('dev', env.SERVER_NAME, env.SERVER_PATH, 'maven')
      }
    }
  }
  post {
    changed {
      emailStatusChange()
    }
  }
}
