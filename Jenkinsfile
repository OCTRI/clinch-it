pipeline {
  agent any
  options {
    buildDiscarder(logRotator(numToKeepStr: '3'))
    disableConcurrentBuilds()
  }
  environment {
    SERVER_NAME = 'octriapp01.acc.ohsu.edu'
    SERVER_PATH = '/opt/projects/clinch-it/clinch-it_dev'
  }
  triggers {
    // Needed to trigger builds from Bitbucket
    pollSCM('')
  }
  tools {
    maven '3.5.0'
    jdk 'JDK8'
  }
  stages {
    stage('Prepare') {
      steps {
        checkout scm
      }
    }
    stage('Build') {
      steps {
        artifactoryMavenBuild(env.BRANCH_NAME)
      }
    }
    stage('Deploy to dev') {
      when {
        branch 'master'
      }
      steps {
        dockerComposeDeploy('dev', env.SERVER_NAME, env.SERVER_PATH, 'maven')
      }
    }
  }
  post {
    // TODO: Uncomment when there are tests
    // always {
    //   junit 'target/surefire-reports/**/*.xml'
    // }
    changed {
      emailStatusChange()
    }
  }
}
