@Library('jenkins-shared-library@main') _

pipeline {
    agent any
    
    tools {
        maven 'mvn-3.9.6'
    }

    environment {
        DOCKER_TAG = 'v1.0.0' 
        DOCKER_IMAGE_NAME = 'java-mvn-app' 
    }

    parameters {
        string(name: 'USERNAME', defaultValue: 'ilhambelhaj', description: 'Docker Hub Username!')
     }
    
    stages {    

        stage('Git Checkout') {
            steps {
            gitCheckout(
                branch: "main",
                url: "https://github.com/IlhamBelhaj/formation-jenkins.git" )
            }
}
        stage('Build') {
            steps {
                sh '''
                   pwd
                   ls 
                   mvn clean install
                '''
            }
        }

        stage('Generate Artifact') {
            steps {
                sh "mvn package"
              
            }
        }

        stage('Build Docker Image') {
            steps {
                    dockerBuild("${params.USERNAME}", "${DOCKER_IMAGE_NAME}", "${DOCKER_TAG}")
                  }
        }

        stage('Login to Docker Registry') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-cred', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh "echo ${DOCKER_PASS} | docker login -u ${DOCKER_USER} --password-stdin"
                    }
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    sh "docker push ${params.USERNAME}/${DOCKER_IMAGE_NAME}:${DOCKER_TAG}"
                }
            }
        }
        stage('Cleanup') {
                steps {
                    sh "docker rmi ${params.USERNAME}/${DOCKER_IMAGE_NAME}:${DOCKER_TAG}"
                }
        }

        stage('Push') {
            when {
               environment name: 'DOCKER_IMAGE_NAME', value: 'java-mvn-app'
            }
            steps {
                echo 'Pushing the image to Docker Hub'
            }
    }
}
}