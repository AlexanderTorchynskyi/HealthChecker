def project = 'cryptex-stage'
def appName = 'health'
def branchName = 'master'
def imageTag = "gcr.io/${project}/${appName}:${branchName}.${env.BUILD_NUMBER}"

pipeline {
  agent {
    kubernetes {
      label 'health'
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
labels:
  component: ci
spec:
  # Use service account that can deploy to all namespaces
  serviceAccountName: cd-jenkins
  containers:
  - name: maven
    image: maven:3.5.4-jdk-8
    command:
    - cat
    tty: true
  - name: gcloud
    image: gcr.io/cloud-builders/gcloud
    command:
    - cat
    tty: true
  - name: kubectl
    image: gcr.io/cloud-builders/kubectl
    command:
    - cat
    tty: true
"""
}
  }
  stages {
    stage('Test') {
      steps {
            sh 'echo test'
      }
    }
    stage('Build and push image with Container Builder') {
      steps {
      container('gcloud'){
           sh "gcloud container builds submit -t ${imageTag} ."
        }
      }
    }

    stage('Deploy Production') {
      // Production branch

      steps{
        container('kubectl') {
        // Change deployed image in canary to the one we just built
          sh("sed -i.bak 's#gcr.io/cryptex-stage/health:1#${imageTag}#' ./k8s/production/*.yml")
          sh("kubectl --namespace=production apply -f k8s/services/")
          sh("kubectl --namespace=production apply -f k8s/production/")
        }
      }
    }
  }
}